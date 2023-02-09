import 'package:my_fist_flutter/utils/SharedPreferencesUtils.dart';

class HttpUtils {


  static Future<Map<String, dynamic>> createHeaders() async {
    Map<String, dynamic> headers = Map();
    headers['access_token'] = await SharedPreferencesUtils.getStorage(SharedPreferencesUtils.TOKEN_KEY);
    headers['auth_password'] = await SharedPreferencesUtils.getStorage(SharedPreferencesUtils.PASSWORD_KEY);
    return headers;
  }
}