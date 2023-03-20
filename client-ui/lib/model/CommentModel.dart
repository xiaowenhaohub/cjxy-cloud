// To parse this JSON data, do
//
//     final commentModel = commentModelFromJson(jsonString);

import 'dart:convert';

CommentModel commentModelFromJson(String str) =>
    CommentModel.fromJson(json.decode(str));

String commentModelToJson(CommentModel data) => json.encode(data.toJson());

class CommentModel {
  CommentModel({
    required this.commentId,
    required this.friendCircleId,
    required this.account,
    required this.content,
    this.likedCount,
    this.rootCommentId,
    this.toCommentId,
    required this.nickName,
    required this.avatar,
    required this.createTime,
  });

  int commentId;
  int friendCircleId;
  String account;
  String content;
  dynamic likedCount;
  dynamic rootCommentId;
  dynamic toCommentId;
  String nickName;
  String avatar;
  DateTime createTime;

  factory CommentModel.fromJson(Map<String, dynamic> json) => CommentModel(
        commentId: json["commentId"],
        friendCircleId: json["friendCircleId"],
        account: json["account"],
        content: json["content"],
        likedCount: json["likedCount"],
        rootCommentId: json["rootCommentId"],
        toCommentId: json["toCommentId"],
        nickName: json["nickName"],
        avatar: json["avatar"],
        createTime: DateTime.parse(json["createTime"]),
      );

  Map<String, dynamic> toJson() => {
        "commentId": commentId,
        "friendCircleId": friendCircleId,
        "account": account,
        "content": content,
        "likedCount": likedCount,
        "rootCommentId": rootCommentId,
        "toCommentId": toCommentId,
        "nickName": nickName,
        "avatar": avatar,
        "createTime": createTime.toIso8601String(),
      };
}
