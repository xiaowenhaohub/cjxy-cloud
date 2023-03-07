import 'package:my_fist_flutter/api/UserApi.dart';
import 'package:my_fist_flutter/utils/HttpRequest.dart';
import 'dart:math' as math;
import '../component/ClassSchedule.dart';
import '../model/UserInfo.dart';

class CourseApi {
  /// 查询课表
  static Future<List<List<Lesson>>> getCourseList() async {
    UserInfo userInfo = await UserApi.getUserInfoByLocal();
    Map<String, String> map = {};
    map['weekly'] = "1";
    map['semester'] = "2021-2022-2";
    map['classes'] = userInfo.classes!;
    map['institute'] = userInfo.institute!;
    var response = await HttpRequest.post("/website/course/get", data: map);
    if (response['code'] != 200) {}
    List<List<Lesson>> courseList = jsonToCourseList(response['data']);
    return courseList;
  }

  static List<List<Lesson>> jsonToCourseList(var data) {
    List<List<Lesson>> courseList = [];
    for (int i = 0; i < 5; i++) {
      List<Lesson> lessonRow = [];
      for (int j = 0; j < 7; j++) {
        var lesson = data[i][j];
        if (lesson['colorIndex'] == null) {
          lesson['colorIndex'] = getRandomNumber();
        }
        lessonRow.add(Lesson.fromJson(lesson));
      }
      courseList.add(lessonRow);
    }

    return courseList;
  }

  static int getRandomNumber() {
    return math.Random().nextInt(15);
  }
}
