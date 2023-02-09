
class QueryCourseDTO {

  // 周次
  String weekly;

  // 学期
  String semester;

  // 院校
  String institute;

  // 班级
  String classes;

  QueryCourseDTO(this.weekly, this.semester, this.institute, this.classes);

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['weekly'] = this.weekly;
    data['semester'] = this.semester;
    data['institute'] = this.institute;
    data['classes'] = this.classes;
    return data;
  }

}