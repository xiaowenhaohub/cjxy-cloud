import 'dart:convert';

FriendModel friendModelFromJson(String str) =>
    FriendModel.fromJson(json.decode(str));

String friendModelToJson(FriendModel data) => json.encode(data.toJson());

class FriendModel {
  FriendModel({
    required this.userAccount,
    required this.avatar,
    required this.alias,
  });

  String userAccount;
  dynamic avatar;
  dynamic alias;

  factory FriendModel.fromJson(Map<String, dynamic> json) => FriendModel(
        userAccount: json["userAccount"],
        avatar: json["avatar"],
        alias: json["alias"],
      );

  Map<String, dynamic> toJson() => {
        "userAccount": userAccount,
        "avatar": avatar,
        "alias": alias,
      };
}
