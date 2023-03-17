import 'package:common_utils/common_utils.dart';
import 'package:my_fist_flutter/model/FriendModel.dart';
import 'package:my_fist_flutter/model/MomentModel.dart';
import 'package:my_fist_flutter/model/UserInfo.dart';
import 'package:my_fist_flutter/model/UserRequestModel.dart';

import '../utils/HttpRequest.dart';

class SocialApi {
  /// 获取好友列表
  static Future<List<FriendModel>> getFriendList() async {
    var response = await HttpRequest.get("/social/relationship/queryAllFriend");
    List<FriendModel> friendsList = [];
    response["data"].forEach((item) {
      friendsList.add(FriendModel.fromJson(item));
    });
    return friendsList;
  }

  /// 申请添加好友
  static Future<bool> requestRelationship(String account) async {
    Map<String, String> data = {"account": account};
    var response = await HttpRequest.post(
        "/social/relationship/insertRelationship",
        data: data);
    if (response["success"]) {
      return true;
    }
    return false;
  }

  /// 同意添加好友
  static Future<bool> agreeUserRequest(String account) async {
    Map<String, String> data = {"account": account};
    var response = await HttpRequest.post(
        "/social/relationship/agreeFriendRequest",
        data: data);
    if (response["success"]) {
      return true;
    }
    return false;
  }

  /// 好友申请列表
  static Future<List<UserInfo>> getRequestList() async {
    var response =
        await HttpRequest.get("/social/relationship/queryAllFriendRequest");
    LogUtil.v(response);
    List<UserInfo> requestList = [];
    response["data"].forEach((item) {
      requestList.add(UserInfo.fromJson(item));
    });
    return requestList;
  }

  /// 获取校园圈
  static Future<List<MomentModel>> getMoments(int pageSize, int pageNum) async {
    Map<String, int> data = {"pageSize": pageSize, "pageNum": pageNum};
    var response = await HttpRequest.post("/social/moments/get", data: data);
    // LogUtil.v(response);
    List<MomentModel> moments = [];
    response["data"].forEach((item) {
      MomentModel model = MomentModel.fromJson(item);
      if (item["picture"] != "") {
        List<String> list = item["picture"].split(";");
        list.forEach((element) {
          if (element != "") {
            model.picture.add(element);
          }
        });
        // model.picture = item["picture"].split(";");
      }
      moments.add(model);
    });
    return moments;
  }

  /// 发表校园圈
  static Future<bool> savaMoment(String content, String picture) async {
    Map<String, String> data = {"content": content, "picture": picture};
    var response =
        await HttpRequest.post("/social/social/saveFriendCircle", data: data);
    if (response['success']) {
      return true;
    }
    return false;
  }

  /// 点赞校园圈
  static Future<bool> liked(String id) async {
    var response =
        await HttpRequest.get("/social/social/likedFriendCircle/$id");
    if (response['success']) {
      return true;
    }
    return false;
  }
}
