
import 'package:my_fist_flutter/http/HttpRequest.dart';

class CourseApi {

  /**
   * 查询课表
   */
  static queryCourseList<T>(data, {Map<String, dynamic>? headers}) {
    return HttpRequest.post(
      "/website/course/query",
      data: data,
      headers: headers
    );
  }
  
  
  static _getHeaders() {
    Map<String, dynamic> headers = Map();
    
    headers['access_token'] = 'eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2tleSI6IjE3OTk0ZGEyLWE0ODMtNDc1NC05ZjI5LTdmYzkxNDg4MzhlYiJ9.gH1portKlXAjW5Fz8aRe6mqR3GvG9jqNm9tp4sRfW6-t3mvPo1DaCpVP-2EtcS4t7doNU-ID9M_CNvRR3QvM3g';
    headers['auth_password'] = '(jiang.4234)';
    return headers;
  }
}