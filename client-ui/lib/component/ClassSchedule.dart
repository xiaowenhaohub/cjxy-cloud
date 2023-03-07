import 'dart:convert';
import 'package:flutter/cupertino.dart';
import 'package:my_fist_flutter/api/CourseApi.dart';
import 'package:my_fist_flutter/service/CourseService.dart';
import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/model/QueryCourseDTO.dart';
import 'dart:math' as math;
import '../../main.dart';

class ClassSchedule extends StatefulWidget {
  final AnimationController? animationController;
  final Animation<double>? animation;

  const ClassSchedule({Key? key, this.animationController, this.animation})
      : super(key: key);

  @override
  State<ClassSchedule> createState() => _ClassScheduleState();
}

class _ClassScheduleState extends State<ClassSchedule>
    with SingleTickerProviderStateMixin {
  AnimationController? classDetailAnimationController;

  Lesson? lessonDetail;

  @override
  void initState() {
    classDetailAnimationController =
        AnimationController(duration: const Duration(seconds: 1), vsync: this);
    lessonDetail = Lesson("", "", "", "", 0);

    super.initState();
  }

  Future<List<List<Lesson>>> getData() async {
    return CourseApi.getCourseList();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: getData(),
        builder:
            (BuildContext context, AsyncSnapshot<List<List<Lesson>>> snapshot) {
          // courseRow("一二节", courseList[0]),
          // courseRow("三四节", courseList[1]),
          // courseRow("五六节", courseList[2]),
          // courseRow("七八节", courseList[3]),
          // courseRow("九十节", courseList[4]),
          List<Widget> _list = [
            myWeekDayRow(),
            courseRow("一二节"),
            courseRow("三四节"),
            courseRow("五六节"),
            courseRow("七八节"),
            courseRow("九十节"),
            Container(
              height: 10,
            )
          ];

          if (snapshot.hasData) {
            List<List<Lesson>> courseList = snapshot.data!;
            _list = [
              myWeekDayRow(),
              courseRow("一二节", lessonRow: courseList[0]),
              courseRow("三四节", lessonRow: courseList[1]),
              courseRow("五六节", lessonRow: courseList[2]),
              courseRow("七八节", lessonRow: courseList[3]),
              courseRow("九十节", lessonRow: courseList[4]),
            ];
          }

          return AnimatedBuilder(
            animation: widget.animationController!,
            builder: (BuildContext context, Widget? child) {
              return FadeTransition(
                opacity: widget.animation!,
                child: Transform(
                  transform: Matrix4.translationValues(
                      0.0, 30 * (1.0 - widget.animation!.value), 0.0),
                  child: Padding(
                    padding: const EdgeInsets.only(
                        left: 20, right: 20, top: 16, bottom: 18),
                    child: Container(
                      decoration: BoxDecoration(
                        color: AppTheme.white,
                        borderRadius: const BorderRadius.only(
                            topLeft: Radius.circular(8.0),
                            bottomLeft: Radius.circular(8.0),
                            bottomRight: Radius.circular(8.0),
                            topRight: Radius.circular(8.0)),
                        boxShadow: <BoxShadow>[
                          BoxShadow(
                              color: AppTheme.grey.withOpacity(0.2),
                              offset: Offset(1.1, 1.1),
                              blurRadius: 10.0),
                        ],
                      ),
                      child: Stack(children: [
                        Column(
                          children: _list,
                        ),
                        Center(
                            child: ClassDatail(
                                classDetailAnimationController!, lessonDetail!))
                      ]),
                    ),
                  ),
                ),
              );
            },
          );
        });
  }

  Widget courseRow(String text, {List<Lesson>? lessonRow}) {
    List<Color> listColor = [
      HexColor('#6c7197').withOpacity(0.2),
      HexColor('#4f8a83').withOpacity(0.2),
      HexColor('#e76278').withOpacity(0.2),
      HexColor('#fac699').withOpacity(0.2),
      HexColor('#712164').withOpacity(0.2),
      HexColor('#f88aaf').withOpacity(0.2),
      HexColor('#edfcc2').withOpacity(0.2),
      HexColor('#60bdaf').withOpacity(0.2),
      HexColor('#b7ae9d').withOpacity(0.2),
      HexColor('#fc6472').withOpacity(0.2),
      HexColor('#4ed5c7').withOpacity(0.2),
      HexColor('#dd3497')..withOpacity(0.2),
      HexColor('#fff5f7').withOpacity(0.2),
      HexColor('#097168').withOpacity(0.2),
      HexColor('#d9ef8b').withOpacity(0.2),
    ];

    List<Widget> _lessonRow = [];

    for (int i = 0; i < 7; i++) {
      if (lessonRow != null && lessonRow[i].courseName != "") {
        _lessonRow.add(GestureDetector(
          onTap: () {
            setState(() {
              lessonDetail = lessonRow[i];
            });
            showAboutDialog(
                context: context,
                applicationIcon: Container(
                  width: 200,
                  height: 100,
                  color: Colors.red,
                ));

            // classDetailAnimationController!.forward();
          },
          child: Padding(
            padding: const EdgeInsets.only(left: 2),
            child: Container(
              width: 37,
              height: 37,
              decoration: BoxDecoration(
                color: listColor[lessonRow[i].colorIndex!],
                borderRadius: const BorderRadius.only(
                    topLeft: Radius.circular(8.0),
                    bottomLeft: Radius.circular(8.0),
                    bottomRight: Radius.circular(8.0),
                    topRight: Radius.circular(8.0)),
              ),
              padding: const EdgeInsets.all(2),
              child: Text(
                lessonRow[i].courseName!,
                style: const TextStyle(
                    fontSize: 8,
                    fontFamily: AppTheme.fontName,
                    letterSpacing: -0.2,
                    color: AppTheme.grey),
              ),
            ),
          ),
        ));
      } else {
        _lessonRow.add(Padding(
          padding: const EdgeInsets.only(left: 2),
          child: Container(
            width: 35,
            height: 35,
            decoration: BoxDecoration(
                // color: Colors.blue
                ),
          ),
        ));
      }
    }

    return Padding(
        padding: EdgeInsets.only(left: 5.0, right: 5.0, top: 10.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Container(
              height: 20 * widget.animation!.value,
              width: 2,
              decoration: BoxDecoration(
                color: HexColor('#87A0E5').withOpacity(0.5),
                borderRadius: BorderRadius.all(Radius.circular(4.0)),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 4, bottom: 3),
              child: Text(
                text,
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontFamily: AppTheme.fontName,
                  fontWeight: FontWeight.w600,
                  fontSize: 12,
                  letterSpacing: -0.2,
                  color: AppTheme.darkerText,
                ),
              ),
            ),
            ..._lessonRow
          ],
        ));
  }

  Widget myWeekDayRow() {
    List<Widget> _list = [];
    List<String> weekDay = ["一", "二", "三", "四", "五", "六", "日"];

    for (int i = 0; i < 7; i++) {
      _list.add(
        Padding(
          padding: const EdgeInsets.only(left: 2),
          child: Container(
            width: 35,
            child: Text(
              weekDay[i],
              textAlign: TextAlign.center,
              style: TextStyle(
                fontFamily: AppTheme.fontName,
                fontWeight: FontWeight.w600,
                fontSize: 12,
                letterSpacing: -0.2,
                color: AppTheme.darkerText,
              ),
            ),
          ),
        ),
      );
    }

    return Padding(
        padding: EdgeInsets.only(left: 5.0, right: 5.0, top: 10.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Container(
              height: 20 * widget.animation!.value,
              width: 2,
              decoration: BoxDecoration(
                color: HexColor('#f88aaf').withOpacity(0.5),
                borderRadius: BorderRadius.all(Radius.circular(4.0)),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 4, bottom: 3, right: 10),
              child: Text(
                "星期",
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontFamily: AppTheme.fontName,
                  fontWeight: FontWeight.w600,
                  fontSize: 12,
                  letterSpacing: -0.2,
                  color: AppTheme.darkerText,
                ),
              ),
            ),
            ..._list
          ],
        ));
  }
}

class ClassDatail extends StatefulWidget {
  AnimationController animationController;
  Lesson lesson;

  ClassDatail(this.animationController, this.lesson);

  @override
  State<ClassDatail> createState() => _ClassDatailState();
}

class _ClassDatailState extends State<ClassDatail> {
  Animation? animation;
  @override
  void initState() {
    animation = Tween(begin: 0.0, end: 1.0).animate(CurvedAnimation(
        parent: widget.animationController,
        curve: const Interval(0, 0.5, curve: Curves.easeInSine)));
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: AnimatedBuilder(
          animation: animation!,
          builder: (context, child) {
            return Transform(
              transform: Matrix4.translationValues(
                  MediaQuery.of(context).size.width -
                      MediaQuery.of(context).size.width * animation!.value,
                  0.0,
                  0.0),
              child: Container(
                width: 250,
                height: 140,
                margin: EdgeInsets.only(top: 100),
                decoration: BoxDecoration(
                  color: AppTheme.white,
                  borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(8.0),
                      bottomLeft: Radius.circular(8.0),
                      bottomRight: Radius.circular(8.0),
                      topRight: Radius.circular(8.0)),
                  boxShadow: <BoxShadow>[
                    BoxShadow(
                        color: AppTheme.grey.withOpacity(0.2),
                        offset: Offset(1.1, 1.1),
                        blurRadius: 10.0),
                  ],
                ),
                child: Column(
                  children: [
                    Expanded(
                        flex: 3,
                        child: Container(
                          child: Center(
                            child: Text("${widget.lesson.courseName}"),
                          ),
                        )),
                    Expanded(
                      flex: 1,
                      child: MaterialButton(
                        onPressed: () {
                          widget.animationController.reverse();
                        },
                        child: Container(
                          width: 250,
                          color: HexColor("#6666CC"),
                          child: Center(
                              child: Text(
                            "确认",
                            style: TextStyle(color: AppTheme.white),
                          )),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            );
          }),
    );
  }
}

class Lesson {
  ///课程名称
  String? courseName;

  ///任课老师
  String? teacherName;

  ///课程周次
  String? weekly;

  ///课程教室
  String? courseRoom;

  int? colorIndex;

  Lesson(this.courseName, this.teacherName, this.weekly, this.courseRoom,
      this.colorIndex);

  Lesson.fromJson(Map<String, dynamic> json) {
    courseName = json['courseName'];
    teacherName = json['teacherName'];
    weekly = json['weekly'];
    courseRoom = json['courseRoom'];
    colorIndex = json['colorIndex'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['courseName'] = courseName;
    data['teacherName'] = teacherName;
    data['weekly'] = weekly;
    data['courseRoom'] = courseRoom;
    data['colorIndex'] = colorIndex;
    return data;
  }
}
