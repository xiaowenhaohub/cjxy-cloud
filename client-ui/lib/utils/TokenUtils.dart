import 'dart:ffi';

import 'package:my_fist_flutter/utils/SharedPreferencesUtils.dart';

class TokenUtils {
  static Future<String> getToken() async {
    return await SharedPreferencesUtils.getStorage(
        SharedPreferencesUtils.TOKEN_KEY);
  }

  static void setToken(String token) async {
    SharedPreferencesUtils.setStorage(SharedPreferencesUtils.TOKEN_KEY, token);
  }
}
