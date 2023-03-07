import 'package:my_fist_flutter/utils/http/HttpRequest.dart';

class UserApi {
  /**
   * 用户登录
   */
  static login(data) {
    return HttpRequest.post("/auth/login", data: data);
  }

  /*
  * 查询个人用户信息
  */
  static queryUserInfo(Map<String, dynamic> headers) {
    return HttpRequest.get("/user/userInfo", headers: headers);
  }

  static _getHeaders() {
    Map<String, dynamic> headers = Map();

    headers['access_token'] =
        'eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2tleSI6IjE3OTk0ZGEyLWE0ODMtNDc1NC05ZjI5LTdmYzkxNDg4MzhlYiJ9.gH1portKlXAjW5Fz8aRe6mqR3GvG9jqNm9tp4sRfW6-t3mvPo1DaCpVP-2EtcS4t7doNU-ID9M_CNvRR3QvM3g';
    headers['auth_password'] = '(jiang.4234)';
    return headers;
  }
}