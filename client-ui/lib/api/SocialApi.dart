import 'package:common_utils/common_utils.dart';
import 'package:my_fist_flutter/model/FriendModel.dart';

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
}
