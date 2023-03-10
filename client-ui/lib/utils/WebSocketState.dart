import 'package:web_socket_channel/io.dart';

import '../model/WebSocketResult.dart';

class WebSocketState {
  String socketUrl;
  IOWebSocketChannel? webSocket;
  bool socketStatus = false;
  bool isError = false;
  WebSocketResult? webSocketResult;
  int socketClienTime = 5;
  bool heartStatus = false;

  WebSocketState({required this.socketUrl, this.webSocket});
}
