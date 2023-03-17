import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/api/SocialApi.dart';
import 'package:my_fist_flutter/api/UserApi.dart';
import 'package:my_fist_flutter/main.dart';
import 'package:my_fist_flutter/pages/chat/ChatPage.dart';
import 'package:my_fist_flutter/pages/friendsPage/components/SearchPage.dart';

import '../../model/FriendModel.dart';
import '../../model/UserInfo.dart';

class FriendPage extends StatefulWidget {
  AnimationController animationController;

  FriendPage(this.animationController, {Key? key}) : super(key: key);

  @override
  State<FriendPage> createState() => _FriendPageState();
}

class _FriendPageState extends State<FriendPage> {
  List<Widget> friendList = [];

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 100)).then((value) => getDate());
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.white,
      child: Scaffold(
        appBar: getAppBar(),
        body: RefreshIndicator(
          onRefresh: getDate,
          child: Column(children: [
            MediaQuery.removePadding(
              context: context,
              removeTop: true,
              child: Expanded(
                  child: ListView(
                children: friendList,
              )),
            )
          ]),
        ),
      ),
    );
  }

  ///下拉刷新方法,为list重新赋值
  Future<void> getDate() async {
    List<FriendModel> list = await SocialApi.getFriendList();
    List<Widget> friendList = [];
    list.forEach((item) {
      friendList.add(friendWindow(item));
    });
    setState(() {
      this.friendList = friendList;
    });
  }

  AppBar getAppBar() {
    return AppBar(
      iconTheme: IconThemeData(
        color: HexColor("#3C4F6D"), //修改颜色
      ),
      backgroundColor: AppTheme.white,
      title: const Text(
        "我的好友",
        style: TextStyle(color: Colors.black),
      ),
      shadowColor: AppTheme.white,
      elevation: 5,
      actions: [
        IconButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => SearchPage()),
            );
          },
          icon: Icon(Icons.add),
        ),
      ],
    );
  }
  // Widget getAppBar() {
  //   return Container(
  //     height: 100,
  //     width: MediaQuery.of(context).size.width,
  //     color: HexColor("#3C979F"),
  //     child: const Center(
  //       child: SafeArea(
  //           child: Text(
  //         "好友",
  //         style: TextStyle(color: AppTheme.white, fontSize: 20),
  //       )),
  //     ),
  //   );
  // }

  onTap(FriendModel friendModel) async {
    UserInfo userInfo = await UserApi.getUserInfoByLocal();
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => ChatPage(friendModel, userInfo)),
    );
  }

  Widget friendWindow(FriendModel friendModel) {
    return Ink(
      color: AppTheme.white,
      child: InkWell(
        onTap: () => onTap(friendModel),
        child: Container(
          padding: EdgeInsets.all(10),
          height: 80,
          child: Row(
            children: [
              SizedBox(width: 10),
              Flexible(
                child: ClipOval(
                  child: Container(
                    child: Image(
                      image: NetworkImage(friendModel.avatar),
                      height: 50,
                      width: 50,
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
              ),
              SizedBox(width: 10),
              Flexible(
                child: Container(
                    child: Text(
                  friendModel.alias,
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                )),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
