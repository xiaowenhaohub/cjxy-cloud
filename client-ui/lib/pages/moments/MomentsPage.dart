import 'package:flutter/material.dart';
import 'package:my_fist_flutter/api/SocialApi.dart';
import 'package:my_fist_flutter/model/MomentModel.dart';
import 'package:my_fist_flutter/pages/moments/components/MomentCard.dart';

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

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.white,
      child: Scaffold(
        appBar: getAppBar(),
        body: FutureBuilder(
          future: SocialApi.getMoments(10, 0),
          builder: (BuildContext context,
              AsyncSnapshot<List<MomentModel>> snapshot) {
            if (!snapshot.hasData) {
              return Padding(
                padding: const EdgeInsets.all(10.0),
                child: SizedBox(
                    width: MediaQuery.of(context).size.width,
                    height: 50,
                    child: const Center(
                      child: CircularProgressIndicator(
                        color: AppTheme.nearlyBlack,
                      ),
                    )),
              );
            }
            momentsList = snapshot.data!;
            return RefreshIndicator(
              onRefresh: _onRefresh,
              child: ListView.builder(
                itemBuilder: _renderRow,
                itemCount: momentsList.length,
              ),
            );
          },
        ),
      ),
    );
  }

  Widget _renderRow(BuildContext context, int index) {
    return MomentCard(momentModel: momentsList[index]);
  }

  ///下拉刷新方法,为list重新赋值
  Future<void> _onRefresh() async {
    print('refresh');
    List<MomentModel> data = await SocialApi.getMoments(10, 0);
    setState(() {
      momentsList = data;
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
      elevation: 5,
      actions: [
        IconButton(
          onPressed: () {
            // Navigator.push(
            //   context,
            //    MaterialPageRoute(builder: (context) => SearchPage()),
            // );
          },
          icon: Icon(Icons.add),
        ),
      ],
    );
  }
}
