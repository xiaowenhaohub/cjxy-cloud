import 'dart:async';
import 'dart:convert';

import 'package:common_utils/common_utils.dart';
import 'package:my_fist_flutter/api/UserApi.dart';
import 'package:my_fist_flutter/model/UserInfo.dart';
import 'package:my_fist_flutter/utils/TokenUtils.dart';
import 'package:web_socket_channel/io.dart';

import '../model/WebSocketResult.dart';
import '../utils/EventStreamController.dart';
import '../utils/WebSocketState.dart';

class WebSocketApi {
  static WebSocketState state =
      WebSocketState(socketUrl: "ws://localhost:4236/chat/");
  static late Timer hearTimer;

  static void connectSocket() async {
    await closeSocket();
    String socketBaseUrl = state.socketUrl;
    String token = await TokenUtils.getToken();
    String socketUrl = socketBaseUrl + token;
    LogUtil.v("发起WebSocket请求,地址为：${socketUrl}");
    state.webSocket = IOWebSocketChannel.connect(socketUrl);
    state.socketStatus = true;
    initConnectSocket();
  }

  static void initConnectSocket() {
    WebSocketApi.onMessageListener();
    heartPacket();
  }

  /// @desc WebSocket消息监听器
  static void onMessageListener() {
    state.webSocket?.stream.listen((response) {
      var jsonData = json.decode(response);
      //检测到心跳包
      if (jsonData['code'] == 9999) {
        //  不处理
      } else {
        state.webSocketResult = WebSocketResult.fromJson(json.decode(response));
        EventStreamController.sendMessage(state.webSocketResult);
        LogUtil.v("监听到服务端Socket返回数据： ${state.webSocketResult.toString()}");
      }
      onError:
      (e) {
        state.socketStatus = false;
        state.isError = true;
      };
      onDone:
      () {
        state.socketStatus = false;
      };
    });
  }

  ///销毁心跳包
  static void destoryHeart() {
    //为心跳包则直接
    if (state.heartStatus) {
      hearTimer.cancel();
      state.heartStatus = false;
    }
  }

  /// 发送消息
  static void sendMessage(String account, String message) async {
    Map<String, dynamic> data = {"toUserAccount": account, "content": message};
    var jsonData = json.encode(data);
    state.webSocket?.sink.add(jsonData);
  }

  /// 发送心跳包
  static void sendHeartPacket() async {
    UserInfo userInfo = await UserApi.getUserInfoByLocal();
    Map<String, dynamic> data = {"code": 9999, "content": "心跳包"};
    var jsonData = json.encode(data);
    state.webSocket?.sink.add(jsonData);
    state.heartStatus = true;
  }

  ///@desc WebSocket心跳包
  static void heartPacket() {
    if (!state.socketStatus) {
      hearTimer = Timer(Duration(seconds: state.socketClienTime), () async {
        //  重新连接
        reconnectSocket();
      });
      sendHeartPacket();
    }
  }

  /// 重新连接socket
  static void reconnectSocket() {
    destoryHeart();
    connectSocket();
  }

  /// @desc 关闭WebSocket
  static Future closeSocket() async {
    if (state.webSocket != null) {
      state.webSocket?.sink.close();
      state.webSocket = null;
      state.socketStatus = false;
    }
  }
}
