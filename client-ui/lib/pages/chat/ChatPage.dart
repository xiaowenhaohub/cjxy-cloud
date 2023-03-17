import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/api/MessageApi.dart';
import 'package:my_fist_flutter/api/WebSocketApi.dart';
import 'package:my_fist_flutter/components/TextInput/CustomTextInput.dart';
import 'package:my_fist_flutter/model/FriendModel.dart';
import 'package:my_fist_flutter/model/MessageModel.dart';
import 'package:my_fist_flutter/model/UserInfo.dart';
import 'package:my_fist_flutter/model/WebSocketResult.dart';
import 'package:my_fist_flutter/utils/ListUtils.dart';

import '../../main.dart';
import '../../utils/EventStreamController.dart';

class ChatPage extends StatefulWidget {
  FriendModel friendModel;
  UserInfo userInfo;

  ChatPage(this.friendModel, this.userInfo, {super.key});

  @override
  State<ChatPage> createState() => _ChatPageState();
}

class _ChatPageState extends State<ChatPage> {
  late TextEditingController textEditingController;
  ScrollController _scrollController = ScrollController();
  List<Widget> messageList = [];

  late String message;
  @override
  void initState() {
    messageWindowsList();
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
          body: Column(children: [
            Expanded(
              child: ListView(
                controller: _scrollController,
                padding: EdgeInsets.all(15.0),
                children: messageList,
              ),
            ),
            bottomInputWindows(),
          ]),
        ),
      ),
    );
  }

  void addMessage(WebSocketResult webSocketResult) {
    MessageModel message = MessageModel(
        fromUserAccount: webSocketResult.fromAccount,
        toUserAccount: webSocketResult.toAccount,
        content: webSocketResult.data);
    List<Widget> list = messageList;
    list.add(messageWindows(message));
    setState(() {
      messageList = list;
    });
  }

  AppBar getAppBar() {
    return AppBar(
      iconTheme: IconThemeData(
        color: HexColor("#3C4F6D"), //修改颜色
      ),
      backgroundColor: AppTheme.white,
      title: Text(
        widget.friendModel.alias,
        style: const TextStyle(color: Colors.black),
      ),
      shadowColor: AppTheme.white,
      elevation: 5,
    );
  }

  void messageWindowsList() {
    MessageApi.getMessageList(widget.friendModel.userAccount, 0, 12)
        .then((data) {
      List<Widget> list = [];
      data.forEach((item) {
        list.add(messageWindows(item));
      });
      setState(() {
        messageList = list;
      });

      toButton();
    });

    EventStreamController.noticeStream.listen((webSocketResult) {
      LogUtil.v("webSocketResult");
      LogUtil.v(webSocketResult);
      if (webSocketResult.code == 200 &&
          webSocketResult.fromAccount == widget.friendModel.userAccount) {
        MessageModel message = MessageModel(
            fromUserAccount: webSocketResult.fromAccount,
            toUserAccount: webSocketResult.toAccount,
            content: webSocketResult.data);
        List<Widget> list = ListUtils.copyWithList(messageList);
        list.add(messageWindows(message));
        setState(() {
          messageList = list;
        });

        toButton();
      }
    });
    // return FutureBuilder(
    //     future:
    //         MessageApi.getMessageList(widget.friendModel.userAccount, 0, 10),
    //     builder:
    //         (BuildContext context, AsyncSnapshot<List<MessageModel>> snapshot) {
    //       if (!snapshot.hasData) {
    //         return const Center(
    //             child: CircularProgressIndicator(
    //           color: AppTheme.white,
    //         ));
    //       }

    //       List<Widget> messageList = [];
    //       snapshot.data!.forEach((item) {
    //         messageList.add(messageWindows(item));
    //       });
    //       return ListView(
    //         padding: EdgeInsets.all(15.0),
    //         children: messageList,
    //       );
    //     });
  }

  Widget messageWindows(MessageModel messageModel) {
    String avatar = '';
    TextDirection textDirection = TextDirection.rtl;
    Color color = AppTheme.white;
    Color textColor = Colors.black;
    if (messageModel.fromUserAccount == widget.friendModel.userAccount) {
      avatar = widget.friendModel.avatar;
      textDirection = TextDirection.ltr;
    } else {
      avatar = widget.userInfo.picture!;
      color = HexColor("#2F87F5");
      textColor = AppTheme.white;
    }

    return Padding(
      padding: const EdgeInsets.only(bottom: 10),
      child: Container(
        alignment: Alignment.topRight,
        height: 50,
        width: MediaQuery.of(context).size.width,
        child: Row(
          textDirection: textDirection,
          children: [
            Flexible(
              child: ClipOval(
                child: Container(
                  child: Image(
                    image: NetworkImage(avatar),
                    height: 40,
                    width: 40,
                    fit: BoxFit.cover,
                  ),
                ),
              ),
            ),
            SizedBox(width: 10),
            Flexible(
              child: Container(
                  padding: const EdgeInsets.all(5),
                  decoration: BoxDecoration(
                    color: color,
                    borderRadius: BorderRadius.circular(10),
                  ),
                  child: Text(
                    messageModel.content,
                    style: TextStyle(
                      fontSize: 18,
                      color: textColor,
                    ),
                  )),
            ),
          ],
        ),
      ),
    );
  }

  Widget bottomInputWindows() {
    return Container(
      height: 100,
      decoration: const BoxDecoration(
        color: AppTheme.white,
        boxShadow: [
          BoxShadow(
              color: Colors.black12,
              offset: Offset(5.0, 0), //阴影xy轴偏移量
              blurRadius: 10.0, //阴影模糊程度
              spreadRadius: 1.0 //阴影扩散程度
              )
        ],
      ),
      child: Column(children: [
        Row(
          children: [
            Expanded(
              child: Container(
                margin: const EdgeInsets.all(10),
                constraints: const BoxConstraints(
                  maxHeight: 100.0,
                  minHeight: 50.0,
                ),
                decoration: const BoxDecoration(
                    color: AppTheme.white,
                    borderRadius: BorderRadius.all(Radius.circular(8))),
                child: CustomTextInput(
                  textEditingController: textEditingController,
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
                  color: HexColor("#0B9B8A"),
                ),
                child: Material(
                  color: Colors.transparent,
                  child: InkWell(
                      onTap: (() => sendMessage()),
                      borderRadius: BorderRadius.circular(25.0),
                      child: const Center(
                        child: Text(
                          "发送",
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

  void sendMessage() {
    WebSocketApi.sendMessage(
        widget.friendModel.userAccount, textEditingController.text);
    MessageModel message = MessageModel(
        fromUserAccount: widget.userInfo.account.toString(),
        toUserAccount: widget.friendModel.userAccount,
        content: textEditingController.text);
    List<Widget> list = ListUtils.copyWithList(messageList);
    list.add(messageWindows(message));
    setState(() {
      messageList = list;
    });
    textEditingController.clear();
    toButton();
  }

  void toButton() {
    /// 延迟500毫秒，再进行滑动
    Future.delayed(Duration(milliseconds: 50), () {
      _scrollController.jumpTo(_scrollController.position.maxScrollExtent);
    });
  }
}
