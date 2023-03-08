import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/api/SocialApi.dart';
import 'package:my_fist_flutter/main.dart';

import '../../model/FriendModel.dart';

class FriendPage extends StatefulWidget {
  AnimationController animationController;

  FriendPage(this.animationController, {Key? key}) : super(key: key);

  @override
  State<FriendPage> createState() => _FriendPageState();
}

class _FriendPageState extends State<FriendPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.white,
      child: Scaffold(
        body: Column(children: [
          getAppBar(),
          MediaQuery.removePadding(
            context: context,
            removeTop: true,
            child: Expanded(child: getFriendList()),
          )
        ]),
      ),
    );
  }

  Widget getAppBar() {
    return Container(
      height: 100,
      width: MediaQuery.of(context).size.width,
      color: HexColor("#3C979F"),
      child: const Center(
        child: SafeArea(
            child: Text(
          "好友",
          style: TextStyle(color: AppTheme.white, fontSize: 20),
        )),
      ),
    );
  }

  Widget getFriendList() {
    return FutureBuilder(
        future: SocialApi.getFriendList(),
        builder:
            (BuildContext context, AsyncSnapshot<List<FriendModel>> snapshot) {
          if (!snapshot.hasData) {
            return const Center(
                child: CircularProgressIndicator(
              color: AppTheme.white,
            ));
          }

          List<Widget> friendList = [];
          snapshot.data!.forEach((item) {
            friendList.add(friendWindow(item.alias, item.avatar));
          });

          return ListView(
            children: friendList,
          );
        });
  }

  onTap() {
    // Navigator.push(
    //   context,
    //   MaterialPageRoute(builder: (context) => const Department()),
    // );
  }

  Widget friendWindow(String nickName, String avatar) {
    return Ink(
      color: AppTheme.white,
      child: InkWell(
        onTap: () => onTap(),
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
                      image: NetworkImage(avatar),
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
                  nickName,
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
