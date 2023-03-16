// To parse this JSON data, do
//
//     final momentModel = momentModelFromJson(jsonString);

import 'dart:convert';

MomentModel momentModelFromJson(String str) =>
    MomentModel.fromJson(json.decode(str));

String momentModelToJson(MomentModel data) => json.encode(data.toJson());

class MomentModel {
  MomentModel({
    required this.id,
    required this.nickName,
    required this.avatar,
    required this.content,
    required this.picture,
    required this.location,
    required this.likedCount,
    required this.createTime,
    required this.liked,
  });

  int id;
  String nickName;
  String avatar;
  String content;
  String picture;
  String location;
  int likedCount;
  DateTime createTime;
  bool liked;

  factory MomentModel.fromJson(Map<String, dynamic> json) => MomentModel(
        id: json["id"],
        nickName: json["nickName"],
        avatar: json["avatar"],
        content: json["content"],
        picture: json["picture"],
        location: json["location"],
        likedCount: json["likedCount"],
        createTime: DateTime.parse(json["createTime"]),
        liked: json["liked"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "nickName": nickName,
        "avatar": avatar,
        "content": content,
        "picture": picture,
        "location": location,
        "likedCount": likedCount,
        "createTime": createTime.toIso8601String(),
        "liked": liked,
      };
}
