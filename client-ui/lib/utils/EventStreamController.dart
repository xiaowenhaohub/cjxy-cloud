//全局维护一个静态的stream
import 'dart:async';

import 'package:my_fist_flutter/model/WebSocketResult.dart';

class EventStreamController {
  //Message为流内的消息体，是一个自定义类
  static final StreamController<WebSocketResult>? _streamNotice =
      StreamController<WebSocketResult>.broadcast();

  static Stream<WebSocketResult> get noticeStream {
    return _streamNotice!.stream;
  }

  //提供外部调用发送消息
  static sendMessage(WebSocketResult? msg) {
    _streamNotice!.sink.add(msg!);
  }
}
