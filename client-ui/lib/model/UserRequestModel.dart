// To parse this JSON data, do
//
//     final userRequestModel = userRequestModelFromJson(jsonString);

import 'dart:convert';

UserRequestModel userRequestModelFromJson(String str) =>
    UserRequestModel.fromJson(json.decode(str));

String userRequestModelToJson(UserRequestModel data) =>
    json.encode(data.toJson());

class UserRequestModel {
  UserRequestModel({
    required this.createTime,
    required this.updateTime,
    required this.id,
    required this.userAccount,
    required this.friendAccount,
    required this.relState,
    required this.aliasUser,
    this.aliasFriend,
  });

  DateTime createTime;
  DateTime updateTime;
  int id;
  String userAccount;
  String friendAccount;
  int relState;
  dynamic aliasUser;
  dynamic aliasFriend;

  factory UserRequestModel.fromJson(Map<String, dynamic> json) =>
      UserRequestModel(
        createTime: DateTime.parse(json["createTime"]),
        updateTime: DateTime.parse(json["updateTime"]),
        id: json["id"],
        userAccount: json["userAccount"],
        friendAccount: json["friendAccount"],
        relState: json["relState"],
        aliasUser: json["aliasUser"],
        aliasFriend: json["aliasFriend"],
      );

  Map<String, dynamic> toJson() => {
        "createTime": createTime.toIso8601String(),
        "updateTime": updateTime.toIso8601String(),
        "id": id,
        "userAccount": userAccount,
        "friendAccount": friendAccount,
        "relState": relState,
        "aliasUser": aliasUser,
        "aliasFriend": aliasFriend,
      };
}
