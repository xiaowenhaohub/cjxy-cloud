import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
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
            decoration: InputDecoration(
              hintText: "学号",
              prefixIcon: Padding(
                padding:  EdgeInsets.all(Constant.defaultPadding),
                child: Icon(Icons.person),
              ),
            ),
          ),
          Padding(
            padding:  EdgeInsets.symmetric(vertical: Constant.defaultPadding),
            child: TextFormField(
              textInputAction: TextInputAction.done,
              obscureText: true,
              cursorColor: Constant.kPrimaryColor,
              decoration: InputDecoration(
                hintText: "密码",
                prefixIcon: Padding(
                  padding:  EdgeInsets.all(Constant.defaultPadding),
                  child: Icon(Icons.lock),
                ),
              ),
            ),
          ),
          SizedBox(height: Constant.defaultPadding),
          Hero(
            tag: "login_btn",
            child: Consumer<UserInfo>(
              builder: (context ,userInfo, child) {
                return ElevatedButton(
                  onPressed: () {
                    setState(() {
                      loading = '0';
                    });
                    UserService.login("1945829064", "(jiang.4234)").then((value){
                      userInfo.changLoginStatus(isLogin: value.isLogin!);
                      setState(() {
                        loading = '1';
                      });
                    });
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
    return const Center(child: CircularProgressIndicator(
      color: AppTheme.white,
    ));

  }
}
