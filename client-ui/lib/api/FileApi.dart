import 'dart:io';

import 'package:common_utils/common_utils.dart';
import 'package:dio/dio.dart';

import '../utils/uuidUtils.dart';

class FileApi {
  static const String baseUrl = 'http://43.132.148.227/api/mi/file/';

  static Future<String?> uploadOneImage(File file) async {
    FormData formData = FormData.fromMap({
      "file": await MultipartFile.fromFile(file.path,
          filename: '${UUID.uuid()}.${file.path.split(".")[1]}')
    });
    Response response = await Dio()
        .post('${baseUrl}upload/1636532488639746048', data: formData);
    LogUtil.v(response.data['data']['id']);
    if (response.statusCode == 200 && response.data["code"] == 200) {
      return response.data['data']['id'];
    }
    return null;
  }

  static Future<String> uploadManyImage(List<File> files) async {
    String picture = '';

    for (int i = 0; i < files.length; i++) {
      String? id = await uploadOneImage(files[i]);
      if (id != null) {
        picture = "$picture$id;";
      }
    }
    return picture;
  }
}
