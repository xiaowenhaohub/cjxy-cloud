// To parse this JSON data, do
//
//     final webSocketResult = webSocketResultFromJson(jsonString);

import 'dart:convert';

WebSocketResult webSocketResultFromJson(String str) =>
    WebSocketResult.fromJson(json.decode(str));

String webSocketResultToJson(WebSocketResult data) =>
    json.encode(data.toJson());

class WebSocketResult {
  WebSocketResult({
    required this.code,
    required this.data,
    required this.fromAccount,
    required this.message,
    required this.toAccount,
  });

  int code;
  dynamic data;
  String fromAccount;
  String message;
  String toAccount;

  factory WebSocketResult.fromJson(Map<String, dynamic> json) =>
      WebSocketResult(
        code: json["code"],
        data: json["data"],
        fromAccount: json["fromAccount"],
        message: json["message"],
        toAccount: json["toAccount"],
      );

  Map<String, dynamic> toJson() => {
        "code": code,
        "data": data,
        "fromAccount": fromAccount,
        "message": message,
        "toAccount": toAccount,
      };
  @override
  String toString() {
    return "{$code$data$message$fromAccount$toAccount}";
  }
}
