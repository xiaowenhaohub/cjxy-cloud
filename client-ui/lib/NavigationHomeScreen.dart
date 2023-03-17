// ignore_for_file: library_private_types_in_public_api

import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/MyHomePageController.dart';
import 'package:my_fist_flutter/components/HomeDrawer.dart';

import 'custom_drawer/drawer_user_controller.dart';

class NavigationHomeScreen extends StatefulWidget {
  const NavigationHomeScreen({Key? key}) : super(key: key);

  @override
  _NavigationHomeScreenState createState() => _NavigationHomeScreenState();
}

class _NavigationHomeScreenState extends State<NavigationHomeScreen> {
  Widget? screenView;
  DrawerIndex? drawerIndex;

  @override
  void initState() {
    drawerIndex = DrawerIndex.HOME;
    screenView = MyHomePageController();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.white,
      child: SafeArea(
        top: false,
        bottom: false,
        child: Scaffold(
          body: DrawerUserController(
            screenIndex: drawerIndex,
            drawerWidth: MediaQuery.of(context).size.width * 0.5,
            onDrawerCall: (DrawerIndex drawerIndexdata) {
              changeIndex(drawerIndexdata);
              //callback from drawer for replace screen as user need with passing DrawerIndex(Enum index)
            },
            screenView: screenView,
          ),
        ),
      ),
    );
  }

  void changeIndex(DrawerIndex drawerIndexdata) {
    if (drawerIndex != drawerIndexdata) {
      drawerIndex = drawerIndexdata;
      switch (drawerIndex) {
        case DrawerIndex.HOME:
          setState(() {
            screenView = MyHomePageController();
          });
          break;
        case DrawerIndex.Help:
          setState(() {
            screenView = MyHomePageController();
          });
          break;
        case DrawerIndex.FeedBack:
          setState(() {
            screenView = MyHomePageController();
          });
          break;
        case DrawerIndex.Invite:
          setState(() {
            screenView = MyHomePageController();
          });
          break;
        default:
          break;
      }
    }
  }
}
