import 'package:my_fist_flutter/utils/HttpRequest.dart';

import '../model/UserInfo.dart';
import '../utils/SharedPreferencesUtils.dart';

class UserApi {
  /*
  * 查询个人用户信息 从服务器
  */
  static Future<bool> queryUserInfo() async {
    var userResponse = await HttpRequest.get("/user/userInfo");
    if (userResponse['code'] != 200) {
      return false;
    }
    UserInfo userInfo = UserInfo.fromJson(userResponse['data']);
    userInfo.isLogin = true;
    SharedPreferencesUtils.setStorage(
        SharedPreferencesUtils.USER_KEY, userInfo.toJson());
    return true;
  }

  //从本地获取用户信息
  static Future<UserInfo> getUserInfoByLocal() async {
    var userInfo = await SharedPreferencesUtils.getStorage(
        SharedPreferencesUtils.USER_KEY);
    if (userInfo == null) {
      return UserInfo(isLogin: false);
    }
    return UserInfo.fromJson(userInfo);
  }
}
