import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:my_fist_flutter/api/UserApi.dart';
import 'package:my_fist_flutter/model/UserInfo.dart';

import '../../../AppTheme.dart';
import '../../../component/TextInput/CustomTextInput.dart';
import '../../../main.dart';
import '../../../model/FriendModel.dart';

class SearchPage extends StatefulWidget {
  const SearchPage({super.key});

  @override
  State<SearchPage> createState() => _SearchPageState();
}

class _SearchPageState extends State<SearchPage> {
  late TextEditingController textEditingController;
  ScrollController _scrollController = ScrollController();
  List<Widget> messageList = [];

  Widget searchUserInfo = Container();
  String? message;
  @override
  void initState() {
    super.initState();

    textEditingController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      behavior: HitTestBehavior.translucent,
      onTap: () {
        // 触摸收起键盘
        FocusScope.of(context).requestFocus(FocusNode());
      },
      child: Container(
        color: HexColor("#DFE2E3"),
        child: Scaffold(
          appBar: getAppBar(),
          body: Column(
            children: [
              inputWindows(),
              // Expanded(
              //   child: ListView(
              //     padding: EdgeInsets.all(15.0),
              //   ),
              // ),
              searchUserInfo
            ],
          ),
        ),
      ),
    );
  }

  AppBar getAppBar() {
    return AppBar(
      iconTheme: IconThemeData(
        color: HexColor("#3C4F6D"), //修改颜色
      ),
      backgroundColor: AppTheme.white,
      title: const Text(
        "添加好友",
        style: TextStyle(color: Colors.black),
      ),
      shadowColor: AppTheme.white,
      elevation: 1,
    );
  }

  Widget inputWindows() {
    return Container(
      height: 80,
      decoration: const BoxDecoration(
        color: AppTheme.white,
      ),
      child: Column(children: [
        Row(
          children: [
            Expanded(
              child: Container(
                margin: const EdgeInsets.all(10),
                constraints: const BoxConstraints(
                  maxHeight: 100.0,
                  minHeight: 40.0,
                ),
                decoration: const BoxDecoration(
                    color: AppTheme.white,
                    borderRadius: BorderRadius.all(Radius.circular(8))),
                child: CustomTextInput(
                  textEditingController: textEditingController,
                  hint: "输入学号",
                  onCaretMoved: (bool hasFocus) {
                    LogUtil.v(hasFocus, tag: "onCaretMoved");
                  },
                  onTextChanged: (String value) {
                    message = value;
                  },
                  onSubmitted: (text) {
                    LogUtil.v("onSubmitted");
                  },
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 5),
              child: Container(
                height: 40,
                width: 50,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(15.0),
                  color: HexColor("#7D8BAE"),
                ),
                child: Material(
                  color: Colors.transparent,
                  child: InkWell(
                      onTap: (() {
                        searchUser();
                      }),
                      borderRadius: BorderRadius.circular(25.0),
                      child: const Center(
                        child: Text(
                          "搜索",
                          style: TextStyle(color: AppTheme.white),
                        ),
                      )),
                ),
              ),
            )
          ],
        ),
      ]),
    );
  }

  void searchUser() async {
    if (message == null) {
      return;
    }
    UserInfo? userInfo = await UserApi.searchUser(message!);
    if (userInfo.account == null) {
      LogUtil.v("未找到用户");
      return;
    }
    setState(() {
      searchUserInfo = friendWindow(userInfo);
    });
    EasyLoading.showInfo('搜索成功');
  }

  Widget friendWindow(UserInfo userInfo) {
    return Container(
      color: AppTheme.white,
      padding: EdgeInsets.all(10),
      height: 80,
      child: Row(
        children: [
          SizedBox(width: 10),
          Flexible(
            child: ClipOval(
              child: Container(
                child: Image(
                  image: NetworkImage(userInfo.picture!),
                  height: 50,
                  width: 50,
                  fit: BoxFit.cover,
                ),
              ),
            ),
          ),
          const SizedBox(width: 10),
          Flexible(
            child: Container(
                child: Text(
              userInfo.nickName!,
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            )),
          ),
          Expanded(child: Container()),
          Ink(
            color: AppTheme.white,
            child: InkWell(
              onTap: () {},
              child: Container(
                height: 30,
                width: 40,
                decoration: BoxDecoration(
                    border: Border.all(width: 0.5),
                    borderRadius: BorderRadius.circular(10)),
                child: const Center(
                    child: Text(
                  "添加",
                  style: TextStyle(fontWeight: FontWeight.bold),
                )),
              ),
            ),
          )
        ],
      ),
    );
  }
}
