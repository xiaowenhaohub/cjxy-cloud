import 'dart:math' as math;
import 'package:my_fist_flutter/model/UserInfo.dart';
import 'package:my_fist_flutter/service/UserService.dart';
import 'package:my_fist_flutter/utils/HttpUtils.dart';
import '../component/ClassSchedule.dart';
import '../http/api/CourseApi.dart';
import '../model/QueryCourseDTO.dart';
import '../utils/SharedPreferencesUtils.dart';

class CourseService{
  static Future<List<List<Lesson>>> getMyCourseList() async  {

    UserInfo userInfo = await UserService.getUserInfoByLocal();

    List<List<Lesson>> courseList = await getCourseListLocal();
    QueryCourseDTO queryCourseDTO = QueryCourseDTO("1", "", userInfo.institute!,  userInfo.classes!);
    if (courseList.isEmpty ) {
      courseList = await getCourseListNetwork(queryCourseDTO);
    }
    return courseList;
  }

  /**
   * 从本地获取课表
   */
  static Future<List<List<Lesson>>> getCourseListLocal() async {
    var data = await SharedPreferencesUtils.getStorage(SharedPreferencesUtils.COURSE_LIST_KEY);
    if (data == null) {
      return [];
    }
    return jsonToCourseList(data) ;
  }

  /**
   * 从服务器获取课表
   */
  static Future<List<List<Lesson>>> getCourseListNetwork(QueryCourseDTO queryCourseDTO) async {
    var headers = await HttpUtils.createHeaders();
    var response = await CourseApi.queryCourseList(queryCourseDTO.toJson(), headers: headers);
    List<List<Lesson>> courseList = jsonToCourseList(response['data']);
    saveCourseListToLocal(courseList);
    return courseList;
  }

  static List<List<Lesson>> jsonToCourseList(var data) {
    List<List<Lesson>> courseList = [];
    for (int i = 0; i < 5 ; i ++) {
      List<Lesson> lessonRow = [];
      for (int j = 0; j < 7; j ++) {
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

  /**
   * 保存到本地
   */
  static void saveCourseListToLocal(dynamic courseList) {
    SharedPreferencesUtils.setStorage(SharedPreferencesUtils.COURSE_LIST_KEY, courseList);
  }

  static int getRandomNumber() {
    return math.Random().nextInt(15);
  }
}