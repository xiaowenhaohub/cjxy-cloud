import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/api/CommentApi.dart';
import 'package:my_fist_flutter/main.dart';
import 'package:my_fist_flutter/model/CommentModel.dart';
import 'package:my_fist_flutter/model/MomentModel.dart';

class MomentDetails extends StatefulWidget {
  MomentModel momentModel;

  MomentDetails(this.momentModel, {super.key});

  @override
  State<MomentDetails> createState() => _MomentDetailsState();
}

class _MomentDetailsState extends State<MomentDetails> {
  final ScrollController _scrollController = ScrollController();
  String baseUrl = "http://43.132.148.227/api/mi/file/view/";
  List<CommentModel> commentList = [];
  @override
  void initState() {
    super.initState();
    _scrollController.addListener(() {
      if (_scrollController.position.pixels ==
          _scrollController.position.maxScrollExtent) {}
    });
  }

  Future<MomentModel> getDate() async {
    return widget.momentModel;
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Container(
          color: AppTheme.white,
          child: Scaffold(
            backgroundColor: AppTheme.greyWith.withOpacity(0.5),
            appBar: getAppBar(),
            body: FutureBuilder(
              future: getDate(),
              builder:
                  (BuildContext context, AsyncSnapshot<MomentModel> snapshot) {
                if (!snapshot.hasData) {
                  return Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: SizedBox(
                        width: MediaQuery.of(context).size.width,
                        height: 20,
                        child: const Center(
                          child: SizedBox(
                            width: 20,
                            height: 20,
                            child: CircularProgressIndicator(
                              color: AppTheme.nearlyBlack,
                            ),
                          ),
                        )),
                  );
                }
                return RefreshIndicator(
                  onRefresh: _onRefresh,
                  child: LayoutBuilder(
                    builder: (context, constrains) {
                      return ListView(
                        children: [
                          userWidget(),
                          contentWidget(),
                          commentWidget(),
                          SizedBox(
                            height: 500,
                          )
                        ],
                      );
                    },
                  ),
                );
              },
            ),
          ),
        ),
      ],
    );
  }

  // Widget contentWidget() {
  //   return Container(
  //     width: MediaQuery.of(context).size.width,
  //   );
  // }
  Widget userWidget() {
    return Container(
      color: AppTheme.white,
      height: 60,
      child: Row(
        children: [
          const SizedBox(width: 10),
          Flexible(
            child: ClipOval(
              child: Container(
                child: Image(
                  image: NetworkImage(widget.momentModel.avatar),
                  height: 40,
                  width: 40,
                  fit: BoxFit.cover,
                ),
              ),
            ),
          ),
          const SizedBox(width: 10),
          Container(
            width: 200,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  widget.momentModel.nickName,
                  style: const TextStyle(
                      color: Colors.black,
                      fontSize: 15,
                      fontWeight: FontWeight.bold),
                ),
                Text(
                  widget.momentModel.createTime.toString().substring(0, 16),
                  style: const TextStyle(
                      color: AppTheme.deactivatedText,
                      fontSize: 10,
                      fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ),
          const Expanded(child: SizedBox()),
        ],
      ),
    );
  }

  Widget contentWidget() {
    return LayoutBuilder(builder: (context, constrains) {
      return Container(
        color: Colors.white,
        padding: EdgeInsets.only(bottom: 8),
        width: constrains.maxWidth,
        child: Column(
          children: [
            Container(
              padding: const EdgeInsets.only(left: 10, right: 10),
              width: constrains.maxWidth,
              child: Column(
                children: [
                  Container(
                    width: constrains.maxWidth,
                    child: Text(
                      widget.momentModel.content,
                      style: const TextStyle(fontSize: 15, color: Colors.black),
                    ),
                  ),
                  Container(
                    width: constrains.maxWidth,
                    child: showPicture(),
                  )
                ],
              ),
            )
          ],
        ),
      );
    });
  }

  Widget showPicture() {
    if (widget.momentModel.picture == null) {
      return const SizedBox();
    }

    List<Widget> list = [];

    widget.momentModel.picture.forEach((element) {
      list.add(Container(
        margin: EdgeInsets.only(bottom: 5),
        constraints: BoxConstraints(
            maxWidth: (MediaQuery.of(context).size.width - 40) / 2),
        child: CachedNetworkImage(
          imageUrl: baseUrl + element,
        ),
      ));
    });

    return Wrap(
      direction: Axis.horizontal,
      alignment: WrapAlignment.start,
      crossAxisAlignment: WrapCrossAlignment.start,
      spacing: 10,
      children: list,
    );
  }

  //评论
  Widget commentWidget() {
    return Container(
      padding: const EdgeInsets.only(top: 5),
      margin: const EdgeInsets.only(top: 10),
      color: Colors.white,
      width: MediaQuery.of(context).size.width,
      child: Column(
        children: [
          Container(
            width: MediaQuery.of(context).size.width,
            padding: const EdgeInsets.only(left: 10, bottom: 5),
            margin: EdgeInsets.only(bottom: 10),
            // color: AppTheme.nearlyBlack,
            decoration: const BoxDecoration(
              border: Border(
                bottom: BorderSide(
                  width: 0.1, //宽度
                  color: AppTheme.grey, //边框颜色
                ),
              ),
            ),
            child: const Text(
              "评论",
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          showComment(),
        ],
      ),
    );
  }

  Future<List<CommentModel>> getData() async {
    List<CommentModel> list = await CommentApi.getComment("12", null);
    commentList = list;
    return commentList;
  }

  Widget showComment() {
    return FutureBuilder(
      future: getData(),
      builder:
          (BuildContext context, AsyncSnapshot<List<CommentModel>> snapshot) {
        if (!snapshot.hasData) {
          return Container(
            padding: EdgeInsets.only(top: 10),
            width: MediaQuery.of(context).size.width,
            height: 100,
            child: Center(
                child: Column(
              children: const [
                SizedBox(
                  height: 60,
                  child: Image(
                    image: AssetImage('assets/images/shafa.png'),
                  ),
                ),
                Text(
                  "还没有人评论哦~快来抢沙发",
                  style:
                      TextStyle(fontSize: 11, color: AppTheme.deactivatedText),
                )
              ],
            )),
          );
        }
        // return Container();

        return Column(
          children: _renderRow(),
        );
      },
    );
  }

  List<Widget> _renderRow() {
    List<Widget> listWidget = [];
    commentList.forEach((element) {
      listWidget.add(
        Container(
          color: AppTheme.white,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  const SizedBox(width: 10),
                  Flexible(
                    child: ClipOval(
                      child: Container(
                        child: Image(
                          image: NetworkImage(element.avatar),
                          height: 30,
                          width: 30,
                          fit: BoxFit.cover,
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(width: 10),
                  Container(
                    width: 200,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          element.nickName,
                          style: const TextStyle(
                            color: AppTheme.nearlyBlack,
                            fontSize: 13,
                          ),
                        ),
                        Text(
                          element.createTime.toString().substring(0, 16),
                          style: const TextStyle(
                            color: AppTheme.deactivatedText,
                            fontSize: 10,
                          ),
                        ),
                      ],
                    ),
                  ),
                  const Expanded(child: SizedBox()),
                ],
              ),
              Container(
                margin: const EdgeInsets.only(left: 50, top: 5, bottom: 5),
                width: MediaQuery.of(context).size.width,
                decoration: const BoxDecoration(
                  border: Border(
                    bottom: BorderSide(width: 0.07),
                  ),
                ),
                child: Text(
                  element.content,
                  style: const TextStyle(fontWeight: FontWeight.w500),
                ),
              )
            ],
          ),
        ),
      );
    });

    return listWidget;
  }

  ///下拉刷新方法,为list重新赋值
  Future<void> _onRefresh() async {}

  AppBar getAppBar() {
    return AppBar(
      backgroundColor: Colors.white, // 背景颜色设置为透明
      shadowColor: AppTheme.nearlyBlack,
      elevation: 0.5,
      leading: InkWell(
        borderRadius: new BorderRadius.circular(10),
        onTap: () {
          Navigator.of(context).pop();
        },
        child: Row(children: const [
          Icon(
            Icons.chevron_left,
            size: 25,
          ),
          Text(
            "返回",
            style: TextStyle(color: Colors.black, fontWeight: FontWeight.w400),
          ),
        ]),
      ),
      iconTheme: IconThemeData(
        color: HexColor("#3C4F6D"), //修改颜色
      ),
      title: const Text(
        "正文",
        style: TextStyle(color: Colors.black),
      ),
      actions: [
        // IconButton(
        //   onPressed: () {},
        //   icon: Icon(Icons.add),
        // ),
      ],
    );
  }
}
