import 'package:my_fist_flutter/api/UserApi.dart';
import 'package:my_fist_flutter/model/QueryCourseDTO.dart';
import 'package:my_fist_flutter/model/UserInfo.dart';
import 'package:my_fist_flutter/service/CourseService.dart';
import 'package:my_fist_flutter/utils/HttpUtils.dart';
import 'package:my_fist_flutter/utils/SharedPreferencesUtils.dart';

class UserService {
  //从本地获取用户信息
  static Future<UserInfo> getUserInfoByLocal() async {
    var userInfo = await SharedPreferencesUtils.getStorage(
        SharedPreferencesUtils.USER_KEY);
    if (userInfo == null) {
      return UserInfo(isLogin: false);
    }
    return UserInfo.fromJson(userInfo);
  }

  // //用户登录
  // static Future<UserInfo> login(String account, String password) async {
  //   Map<String, String> map = {};
  //   map['account'] = account;
  //   map['password'] = password;
  //   var response = await UserApi.login(map);

  //   if (response['code'] != 200) {
  //     return UserInfo(isLogin: '0');
  //   }

  //   var data = response['data'];
  //   String token = data['access_token'];
  //   String authPassword = data['auth_password'];
  //   SharedPreferencesUtils.setStorage(SharedPreferencesUtils.TOKEN_KEY, token);
  //   SharedPreferencesUtils.setStorage(
  //       SharedPreferencesUtils.PASSWORD_KEY, authPassword);

  //   Map<String, dynamic> headers = await HttpUtils.createHeaders();

  //   var userResponse = await UserApi.queryUserInfo();
  //   UserInfo userInfo = UserInfo.fromJson(userResponse['data']);
  //   userInfo.isLogin = '1';
  //   SharedPreferencesUtils.setStorage(
  //       SharedPreferencesUtils.USER_KEY, userInfo.toJson());

  //   return userInfo;
  // }

  static void saveToken(String token) {
    SharedPreferencesUtils.setStorage(SharedPreferencesUtils.TOKEN_KEY, token);
  }

  static void logout(UserInfo userInfo) {
    userInfo.changLoginStatus(isLogin: false);
    SharedPreferencesUtils.clear();
  }
}
