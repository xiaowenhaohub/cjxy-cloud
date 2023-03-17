import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_smart_dialog/flutter_smart_dialog.dart';
import 'package:my_fist_flutter/api/SocialApi.dart';
import 'package:my_fist_flutter/model/MomentModel.dart';
import 'package:my_fist_flutter/pages/moments/components/AddMomentPage.dart';
import 'package:my_fist_flutter/pages/moments/components/MomentCard.dart';
import 'package:my_fist_flutter/utils/ListUtils.dart';

import '../../AppTheme.dart';
import '../../main.dart';

class MomentsPage extends StatefulWidget {
  AnimationController animationController;
  MomentsPage(this.animationController, {super.key});

  @override
  State<MomentsPage> createState() => _MomentsPageState();
}

class _MomentsPageState extends State<MomentsPage> {
  List<MomentModel> momentsList = []; //列表要展示的数据
  final ScrollController _scrollController = ScrollController();
  int pageNum = 0;
  int pageSize = 5;

  bool isLoading = false;
  Widget loadingWidget = Container();

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(() {
      if (_scrollController.position.pixels ==
          _scrollController.position.maxScrollExtent) {
        if (!isLoading) {
          print('滑动到了最底部');
          _getMore();
        }
      }
    });
  }

  Future<List<MomentModel>> getDate() async {
    List<MomentModel> data = await SocialApi.getMoments(pageSize, pageNum);
    if (momentsList.isEmpty) {
      momentsList.addAll(data);
    }
    pageNum = momentsList.length;

    return data;
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Container(
          color: AppTheme.white,
          child: Scaffold(
            backgroundColor: AppTheme.greyWith,
            appBar: getAppBar(),
            body: FutureBuilder(
              future: getDate(),
              builder: (BuildContext context,
                  AsyncSnapshot<List<MomentModel>> snapshot) {
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
                  child: ListView.builder(
                    controller: _scrollController,
                    itemBuilder: _renderRow,
                    itemCount: momentsList.length,
                  ),
                );
              },
            ),
          ),
        ),
        Positioned(
          left: 0,
          right: 0,
          bottom: 8,
          child: loadingWidget,
        )
      ],
    );
  }

  Widget _renderRow(BuildContext context, int index) {
    return MomentCard(momentModel: momentsList[index]);
  }

  ///下拉刷新方法,为list重新赋值
  Future<void> _onRefresh() async {
    print('refresh');
    List<MomentModel> data = await SocialApi.getMoments(5, 0);
    pageNum = 0;
    setState(() {
      momentsList = data;
    });
  }

  ///上拉加载更多
  void _getMore() async {
    isLoading = true;
    setState(() {
      loadingWidget = SizedBox(
        width: MediaQuery.of(context).size.width,
        height: 20,
        child: const Center(
          child: SizedBox(
            width: 20,
            height: 20,
            child: CircularProgressIndicator(
              color: AppTheme.lightText,
            ),
          ),
        ),
      );
    });
    List<MomentModel> list = await getDate();
    if (list.isEmpty) {
      SmartDialog.showToast("已到底");
      setState(() {
        loadingWidget = SizedBox();
      });
      isLoading = false;
      pageNum -= pageSize;
      return;
    }
    setState(() {
      momentsList.addAll(list);
      isLoading = false;
      loadingWidget = SizedBox();
    });
  }

  AppBar getAppBar() {
    return AppBar(
      iconTheme: IconThemeData(
        color: HexColor("#3C4F6D"), //修改颜色
      ),
      backgroundColor: AppTheme.white,
      title: const Text(
        "校园圈",
        style: TextStyle(color: Colors.black),
      ),
      shadowColor: AppTheme.white,
      elevation: 0.5,
      actions: [
        IconButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => AddMomentPage()),
            ).then((value) => _onRefresh());
          },
          icon: Icon(Icons.add),
        ),
      ],
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    _scrollController.dispose();
  }
}
