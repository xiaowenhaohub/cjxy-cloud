import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/api/AuthApi.dart';
import 'package:my_fist_flutter/api/UserApi.dart';
import 'package:my_fist_flutter/constant.dart';
import 'package:my_fist_flutter/model/UserInfo.dart';
import 'package:my_fist_flutter/service/UserService.dart';
import 'package:provider/provider.dart';

class LoginForm extends StatefulWidget {
  const LoginForm({
    Key? key,
  }) : super(key: key);

  @override
  State<LoginForm> createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  String loading = '1';
  String? account = null;
  String? password = null;

  @override
  Widget build(BuildContext context) {
    return Form(
      child: Column(
        children: [
          TextFormField(
            keyboardType: TextInputType.number,
            textInputAction: TextInputAction.next,
            cursorColor: Constant.kPrimaryColor,
            onSaved: (email) {},
            onChanged: (account) {
              LogUtil.v(account);
              this.account = account;
            },
            decoration: InputDecoration(
              hintText: "学号",
              prefixIcon: Padding(
                padding: EdgeInsets.all(Constant.defaultPadding),
                child: Icon(Icons.person),
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(vertical: Constant.defaultPadding),
            child: TextFormField(
              textInputAction: TextInputAction.done,
              obscureText: true,
              cursorColor: Constant.kPrimaryColor,
              onChanged: (password) {
                this.password = password;
              },
              decoration: InputDecoration(
                hintText: "密码",
                prefixIcon: Padding(
                  padding: EdgeInsets.all(Constant.defaultPadding),
                  child: Icon(Icons.lock),
                ),
              ),
            ),
          ),
          SizedBox(height: Constant.defaultPadding),
          Hero(
            tag: "login_btn",
            child: Consumer<UserInfo>(
              builder: (context, userInfo, child) {
                return ElevatedButton(
                  onPressed: () async {
                    try {
                      LogUtil.v(password);
                      LogUtil.v(account);
                      if (account == null ||
                          account == '' ||
                          password == null ||
                          password == '') {
                        EasyLoading.showInfo('账号或密码为空');
                        return;
                      }
                      setState(() {
                        loading = '0';
                      });

                      bool isSuccess =
                          // await AuthApi.login("1945829064", "(jiang.4234)");
                          await AuthApi.login(account!, password!);
                      if (!isSuccess) {
                        return;
                      }
                      isSuccess = await UserApi.queryUserInfo();
                      if (!isSuccess) {
                        return;
                      }
                      userInfo.changLoginStatus(isLogin: true);
                    } finally {
                      setState(() {
                        loading = '1';
                      });
                    }
                  },
                  child: buttonStatus(),
                );
              },
            ),
          ),
          SizedBox(height: Constant.defaultPadding),
          // AlreadyHaveAnAccountCheck(
          //   press: () {
          //     Navigator.push(
          //       context,
          //       MaterialPageRoute(
          //         builder: (context) {
          //           return SignUpScreen();
          //         },
          //       ),
          //     );
          //   },
          // ),
        ],
      ),
    );
  }

  Widget buttonStatus() {
    if (loading == '1') {
      return Text("登录");
    }
    return const Center(
        child: CircularProgressIndicator(
      color: AppTheme.white,
    ));
  }
}
