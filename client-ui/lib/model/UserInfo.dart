
import 'package:flutter/cupertino.dart';

class UserInfo extends ChangeNotifier {

  /// 学号
  String? account;

  /// 密码
  String? password;

  /// 院校
  String? institute;

  /// 专业
  String? specialty;

  /// 学生班级
  String? classes;

  /// 学生姓名
  String? realName;

  /// 1 登录 0 未登录
  String? isLogin;

  UserInfo({this.account, this.password, this.institute, this.specialty,
      this.classes, this.realName, this.isLogin});


  UserInfo.fromJson(Map<String, dynamic> json) {
    account = json['account'];
    password = json['password'];
    institute = json['institute'];
    specialty = json['specialty'];
    classes = json['classes'];
    realName = json['realName'];
    isLogin = json['isLogin'];
  }
  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['account'] = account;
    data['password'] = password;
    data['institute'] = institute;
    data['specialty'] = specialty;
    data['classes'] = classes;
    data['realName'] = realName;
    data['isLogin'] = isLogin;
    return data;
  }


  void changLoginStatus({required String isLogin}){
    this.isLogin = isLogin;
    notifyListeners();
  }


}