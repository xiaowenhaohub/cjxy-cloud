import 'dart:convert';

MessageModel messageModelFromJson(String str) =>
    MessageModel.fromJson(json.decode(str));

String messageModelToJson(MessageModel data) => json.encode(data.toJson());

class MessageModel {
  MessageModel({
    this.createTime,
    this.updateTime,
    this.id,
    this.type,
    required this.fromUserAccount,
    required this.toUserAccount,
    required this.content,
    this.state,
  });

  DateTime? createTime;
  DateTime? updateTime;
  int? id;
  dynamic type;
  String fromUserAccount;
  String toUserAccount;
  String content;
  int? state;

  factory MessageModel.fromJson(Map<String, dynamic> json) => MessageModel(
        createTime: DateTime.parse(json["createTime"]),
        updateTime: DateTime.parse(json["updateTime"]),
        id: json["id"],
        type: json["type"],
        fromUserAccount: json["fromUserAccount"],
        toUserAccount: json["toUserAccount"],
        content: json["content"],
        state: json["state"],
      );

  Map<String, dynamic> toJson() => {
        "createTime": createTime!.toIso8601String(),
        "updateTime": updateTime!.toIso8601String(),
        "id": id,
        "type": type,
        "fromUserAccount": fromUserAccount,
        "toUserAccount": toUserAccount,
        "content": content,
        "state": state,
      };
}
