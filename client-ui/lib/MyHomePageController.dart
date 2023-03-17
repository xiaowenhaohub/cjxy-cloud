import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/components/wrapper/KeepAliveWrapper.dart';
import 'package:my_fist_flutter/constant.dart';
import 'package:my_fist_flutter/components/CustomAnimatedBottomBar.dart';
import 'package:my_fist_flutter/main.dart';
import 'package:my_fist_flutter/pages/friendsPage/FriendsPage.dart';

import 'package:my_fist_flutter/pages/home/HomePage.dart';
import 'package:my_fist_flutter/pages/moments/MomentsPage.dart';

import 'fitness_app/fitness_app_home_screen.dart';
import 'fitness_app/fitness_app_theme.dart';
import 'fitness_app/models/tabIcon_data.dart';
import 'fitness_app/my_diary/my_diary_screen.dart';

class MyHomePageController extends StatefulWidget {
  const MyHomePageController({Key? key}) : super(key: key);
  @override
  // ignore: library_private_types_in_public_api
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePageController>
    with SingleTickerProviderStateMixin {
  //页面索引
  int _currentIndex = 0;
  //bottom未选择时颜色
  final _inactiveColor = Constant.nearlyDarkBlue;
  //bottom
  List titles = ["首页", "好友", "校园圈", "直播"];
  late PageController _pageController;
  AnimationController? animationController;
  List<TabIconData> tabIconsList = TabIconData.tabIconsList;

  late final List<Widget> _page;

  @override
  void initState() {
    this._pageController =
        PageController(initialPage: _currentIndex, keepPage: true);
    tabIconsList.forEach((TabIconData tab) {
      tab.isSelected = false;
    });
    tabIconsList[0].isSelected = true;

    animationController = AnimationController(
        duration: const Duration(milliseconds: 600), vsync: this);

    _page = [
      HomePage(animationController!),
      // KeepAliveWrapper(child: HomePage(animationController!)),
      KeepAliveWrapper(child: MomentsPage(animationController!)),
      KeepAliveWrapper(child: FriendPage(animationController!)),
    ];

    super.initState();
  }

  @override
  void dispose() {
    animationController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder<bool>(
        future: getData(),
        builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
          if (snapshot.hasError) {
            // return const SizedBox();
            return const Center(child: CircularProgressIndicator());
          } else {
            // return _page[_currentIndex];
            return PageView(
              physics: NeverScrollableScrollPhysics(), // 禁止左右滑动切换页面
              controller: _pageController,
              children: _page,
            );
          }
        },
      ),
      bottomNavigationBar: _buildBottomBar(),
    );
  }

  Future<bool> getData() async {
    await Future<dynamic>.delayed(const Duration(microseconds: 200));
    return true;
  }

  Widget _buildBottomBar() {
    return CustomAnimatedBottomBar(
      containerHeight: 56,
      backgroundColor: AppTheme.white,
      selectedIndex: _currentIndex,
      showElevation: true,
      itemCornerRadius: 24,
      curve: Curves.easeInOut,
      onItemSelected: (index) {
        animationController?.reverse().then<dynamic>((data) {
          if (!mounted) {
            return;
          }
        });
        setState(() {
          _currentIndex = index;
          _pageController.jumpToPage(index);
        });
      },
      items: <MyBottomNavigationBarItem>[
        MyBottomNavigationBarItem(
          icon: const Icon(Icons.home),
          title: Text(titles[0]),
          activeColor: const Color(0xff333366),
          inactiveColor: _inactiveColor,
          textAlign: TextAlign.center,
        ),
        MyBottomNavigationBarItem(
          icon: const Icon(Icons.looks),
          title: Text(
            titles[2],
          ),
          activeColor: const Color(0xff333366),
          inactiveColor: _inactiveColor,
          textAlign: TextAlign.center,
        ),
        MyBottomNavigationBarItem(
          icon: const Icon(Icons.message),
          title: Text(titles[1]),
          activeColor: const Color(0xff333366),
          inactiveColor: _inactiveColor,
          textAlign: TextAlign.center,
        ),
      ],
    );
  }
}
