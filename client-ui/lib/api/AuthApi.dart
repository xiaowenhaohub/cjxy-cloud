import 'dart:ffi';
import 'package:common_utils/common_utils.dart';

import '../model/UserInfo.dart';
import '../utils/SharedPreferencesUtils.dart';
import '../utils/HttpRequest.dart';

class AuthApi {
  //用户登录
  static Future<bool> login(String account, String password) async {
    Map<String, String> map = {};
    map['account'] = account;
    map['password'] = password;

    var response = await HttpRequest.post("/auth/login", data: map);
    if (response['code'] != 200) {
      return false;
    }
    var data = response['data'];
    String token = data['access_token'];
    String authPassword = data['auth_password'];
    SharedPreferencesUtils.setStorage(SharedPreferencesUtils.TOKEN_KEY, token);
    SharedPreferencesUtils.setStorage(
        SharedPreferencesUtils.PASSWORD_KEY, authPassword);
    return true;
  }
}
