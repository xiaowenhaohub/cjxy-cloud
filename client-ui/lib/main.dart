import 'dart:io';
import 'package:common_utils/common_utils.dart';
import 'package:flutter_smart_dialog/flutter_smart_dialog.dart';
import 'package:my_fist_flutter/constant.dart';
import 'package:my_fist_flutter/pages/login/LoginScreen.dart';
import 'package:my_fist_flutter/service/UserService.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:my_fist_flutter/MyHomePageController.dart';

import 'AppTheme.dart';
import 'NavigationHomeScreen.dart';
import 'model/UserInfo.dart';

void main() async {
  SharedPreferences.setMockInitialValues({});
  final List<DeviceOrientation> orientations = [
    DeviceOrientation.portraitUp,
    DeviceOrientation.portraitDown
  ];
  LogUtil.init(isDebug: true);
  WidgetsFlutterBinding.ensureInitialized();
  await SystemChrome.setPreferredOrientations(orientations)
      .then((value) => runApp(MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      statusBarIconBrightness: Brightness.dark,
      statusBarBrightness:
          !kIsWeb && Platform.isAndroid ? Brightness.dark : Brightness.light,
      systemNavigationBarColor: Colors.white,
      systemNavigationBarDividerColor: Colors.transparent,
      systemNavigationBarIconBrightness: Brightness.dark,
    ));
    return FutureBuilder(
        future: UserService.getUserInfoByLocal(),
        builder: (BuildContext context, AsyncSnapshot<UserInfo> snapshot) {
          if (!snapshot.hasData) {
            return const Center(
                child: CircularProgressIndicator(
              color: AppTheme.white,
            ));
          }
          return ChangeNotifierProvider<UserInfo>(
            create: (BuildContext context) {
              return snapshot.data!;
            },
            child: MaterialApp(
              debugShowCheckedModeBanner: false,
              title: 'Flutter Demo',
              theme: ThemeData(
                  primarySwatch: Colors.blue,
                  textTheme: AppTheme.textTheme,
                  platform: TargetPlatform.iOS,
                  elevatedButtonTheme: ElevatedButtonThemeData(
                    style: ElevatedButton.styleFrom(
                      elevation: 0,
                      primary: Constant.kPrimaryColor,
                      shape: const StadiumBorder(),
                      maximumSize: const Size(double.infinity, 56),
                      minimumSize: const Size(double.infinity, 56),
                    ),
                  ),
                  inputDecorationTheme: InputDecorationTheme(
                    filled: true,
                    fillColor: Constant.kPrimaryLightColor,
                    iconColor: Constant.kPrimaryColor,
                    prefixIconColor: Constant.kPrimaryColor,
                    contentPadding: EdgeInsets.symmetric(
                        horizontal: Constant.defaultPadding,
                        vertical: Constant.defaultPadding),
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(30)),
                      borderSide: BorderSide.none,
                    ),
                  )),
              // home:  ,
              home: Consumer<UserInfo>(
                builder: (context, userInfo, child) {
                  if (!userInfo.isLogin!) {
                    return const LoginScreen();
                  } else {
                    return const NavigationHomeScreen();
                  }
                },
              ),
              builder: EasyLoading.init(builder: FlutterSmartDialog.init()),
            ),
          );
        });
  }
}

class HexColor extends Color {
  HexColor(final String hexColor) : super(_getColorFromHex(hexColor));

  static int _getColorFromHex(String hexColor) {
    hexColor = hexColor.toUpperCase().replaceAll('#', '');
    if (hexColor.length == 6) {
      hexColor = 'FF' + hexColor;
    }
    return int.parse(hexColor, radix: 16);
  }
}
