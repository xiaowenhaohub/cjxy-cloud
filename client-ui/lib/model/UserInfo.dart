import 'package:flutter/cupertino.dart';

class UserInfo extends ChangeNotifier {
  /// 学号
  dynamic account;

  /// 密码
  dynamic password;

  dynamic nickName;

  /// 院校
  dynamic institute;

  /// 专业
  dynamic specialty;

  /// 学生班级
  dynamic classes;

  /// 学生姓名
  dynamic realName;

  /// 头像
  dynamic picture;

  /// 1 登录 0 未登录
  bool? isLogin;

  UserInfo(
      {this.account,
      this.password,
      this.institute,
      this.specialty,
      this.classes,
      this.realName,
      this.picture,
      this.isLogin,
      this.nickName});

  UserInfo.fromJson(Map<String, dynamic> json) {
    account = json['account'];
    password = json['password'];
    institute = json['institute'];
    specialty = json['specialty'];
    classes = json['classes'];
    picture = json['picture'];
    realName = json['realName'];
    nickName = json['nickName'];
  }
  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['account'] = account;
    data['password'] = password;
    data['institute'] = institute;
    data['specialty'] = specialty;
    data['classes'] = classes;
    data['realName'] = realName;
    data['picture'] = picture;
    data['isLogin'] = isLogin;
    data['nickName'] = nickName;
    return data;
  }

  void changLoginStatus({required bool isLogin}) {
    this.isLogin = isLogin;
    notifyListeners();
  }
}
