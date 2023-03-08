import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import '../../component/ClassSchedule.dart';
import '../../component/ui_view/title_view.dart';
import '../../fitness_app/fitness_app_theme.dart';
import 'package:http/http.dart' as http;

class HomePage extends StatefulWidget {
  AnimationController animationController;

  HomePage(this.animationController, {Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> with TickerProviderStateMixin {
  ScrollController? scrollController = ScrollController();

  Animation<double>? topBarAnimation;

  List<Widget> listViews = [];
  double topBarOpacity = 0.0;
  @override
  void initState() {
    topBarAnimation = Tween(begin: 0.0, end: 1.0).animate(CurvedAnimation(
        parent: widget.animationController,
        curve: const Interval(0, 0.5, curve: Curves.fastOutSlowIn)));
    addAllListData();
    scrollController!.addListener(() {
      if (scrollController!.offset >= 24) {
        if (topBarOpacity != 1.0) {
          setState(() {
            topBarOpacity = 1.0;
          });
        }
      } else if (scrollController!.offset <= 24 &&
          scrollController!.offset >= 0) {
        if (topBarOpacity != scrollController!.offset / 24) {
          setState(() {
            topBarOpacity = scrollController!.offset / 24;
          });
        }
      } else if (scrollController!.offset <= 0) {
        if (topBarOpacity != 0.0) {
          setState(() {
            topBarOpacity = 0.0;
          });
        }
      }
    });

    super.initState();
  }

  Future<bool> getData() async {
    await Future<dynamic>.delayed(const Duration(milliseconds: 50));
    return true;
  }

  void addAllListData() {
    int count = 9;
    listViews.add(
      TitleView(
        titleTxt: '我的课表',
        // subTxt: 'Details',
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController,
            curve:
                Interval((1 / count) * 0, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController,
      ),
    );

    listViews.add(
      ClassSchedule(
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController,
            curve:
                Interval((1 / count) * 1, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.background,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        body: Stack(
          children: <Widget>[
            getMainListViewUI(),
            getAppBarUI(),
            SizedBox(
              height: MediaQuery.of(context).padding.bottom,
            )
          ],
        ),
      ),
    );
  }

  Widget getMainListViewUI() {
    return FutureBuilder(
        future: getData(),
        builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
          if (!snapshot.hasData) {
            return const SizedBox();
          }
          return ListView.builder(
              controller: scrollController,
              padding: EdgeInsets.only(
                top: AppBar().preferredSize.height +
                    MediaQuery.of(context).padding.top +
                    24,
                bottom: MediaQuery.of(context).padding.bottom,
              ),
              scrollDirection: Axis.vertical,
              itemCount: listViews.length,
              itemBuilder: (context, index) {
                widget.animationController.forward();
                return listViews[index];
              });
        });
  }

  Widget getAppBarUI() {
    return Column(
      children: [
        AnimatedBuilder(
            animation: widget.animationController,
            builder: (BuildContext context, Widget? child) {
              return FadeTransition(
                opacity: topBarAnimation!,
                child: Transform(
                  transform: Matrix4.translationValues(
                      0.0, 30 * (1.0 - topBarAnimation!.value), 0.0),
                  child: Container(
                    decoration: BoxDecoration(
                      color: AppTheme.white.withOpacity(topBarOpacity),
                      // borderRadius: const BorderRadius.only(
                      //   bottomLeft: Radius.circular(32.0),
                      // ),
                      boxShadow: <BoxShadow>[
                        BoxShadow(
                            color:
                                AppTheme.grey.withOpacity(0.4 * topBarOpacity),
                            offset: const Offset(1.1, 1.1),
                            blurRadius: 10.0),
                      ],
                    ),
                    child: Column(
                      children: <Widget>[
                        SizedBox(
                          height: MediaQuery.of(context).padding.top,
                        ),
                        Padding(
                          padding: EdgeInsets.only(
                              left: 16,
                              right: 16,
                              top: 16 - 8.0 * topBarOpacity,
                              bottom: 12 - 8.0 * topBarOpacity),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: <Widget>[
                              Expanded(
                                child: Padding(
                                  padding: const EdgeInsets.only(
                                      top: 8, right: 8, bottom: 8, left: 50),
                                  child: Text(
                                    '昌吉学院',
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                      fontFamily: FitnessAppTheme.fontName,
                                      fontWeight: FontWeight.w500,
                                      fontSize: 10 + 6 - 6 * topBarOpacity,
                                      letterSpacing: 1.2,
                                      color: FitnessAppTheme.darkerText,
                                    ),
                                  ),
                                ),
                              ),
                              SizedBox(
                                height: 38,
                                width: 38,
                                child: InkWell(
                                  highlightColor: Colors.transparent,
                                  borderRadius: const BorderRadius.all(
                                      Radius.circular(32.0)),
                                  onTap: () {},
                                  child: const Center(
                                    child: Icon(
                                      Icons.keyboard_arrow_left,
                                      color: FitnessAppTheme.grey,
                                    ),
                                  ),
                                ),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(
                                  left: 8,
                                  right: 8,
                                ),
                                child: Row(
                                  children: <Widget>[
                                    Padding(
                                      padding: const EdgeInsets.only(right: 8),
                                      child: Icon(
                                        Icons.calendar_today,
                                        color: FitnessAppTheme.grey,
                                        size: 18,
                                      ),
                                    ),
                                    Text(
                                      '15 周',
                                      textAlign: TextAlign.left,
                                      style: TextStyle(
                                        fontFamily: FitnessAppTheme.fontName,
                                        fontWeight: FontWeight.normal,
                                        fontSize: 18,
                                        letterSpacing: -0.2,
                                        color: FitnessAppTheme.darkerText,
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                              SizedBox(
                                height: 38,
                                width: 38,
                                child: InkWell(
                                  highlightColor: Colors.transparent,
                                  borderRadius: const BorderRadius.all(
                                      Radius.circular(32.0)),
                                  onTap: () {},
                                  child: Center(
                                    child: Icon(
                                      Icons.keyboard_arrow_right,
                                      color: FitnessAppTheme.grey,
                                    ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                        )
                      ],
                    ),
                  ),
                ),
              );
            }),
      ],
    );
  }
}
