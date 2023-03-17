import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';

import '../../model/MenuItem.dart';
import 'SelectWidget.dart';

class TitleView extends StatelessWidget {
  final String titleTxt;
  final String subTxt;
  final AnimationController? animationController;
  final Animation<double>? animation;

  const TitleView(
      {Key? key,
      this.titleTxt: "",
      this.subTxt: "",
      this.animationController,
      this.animation})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: animationController!,
      builder: (BuildContext context, Widget? child) {
        return FadeTransition(
          opacity: animation!,
          child: Transform(
            transform: new Matrix4.translationValues(
                0.0, 30 * (1.0 - animation!.value), 0.0),
            child: Container(
              child: Padding(
                padding: const EdgeInsets.only(left: 24, right: 24),
                child: Row(
                  children: <Widget>[
                    Expanded(
                      child: Text(
                        titleTxt,
                        textAlign: TextAlign.left,
                        style: TextStyle(
                          fontFamily: AppTheme.fontName,
                          fontWeight: FontWeight.w500,
                          fontSize: 18,
                          letterSpacing: 0.5,
                          color: AppTheme.lightText,
                        ),
                      ),
                    ),

                    SelectWidget(
                      items: [
                        MenuItems(label: "2021-2022-2", value: '2021-2022-2'),
                        MenuItems(label: "2021-2022-1", value: '2021-2022-1'),
                        MenuItems(label: "刘备", value: '3'),
                        MenuItems(label: "圆头儿子", value: '4'),
                        MenuItems(label: "大头爸爸", value: '5'),
                        MenuItems(label: "小头妈妈", value: '6'),
                      ],
                      value: 1,
                      valueChanged: (value) {
                        print(value);
                      },
                    ),

                    // InkWell(
                    //   highlightColor: Colors.transparent,
                    //   borderRadius: BorderRadius.all(Radius.circular(4.0)),
                    //   onTap: () {},
                    //   child: Padding(
                    //     padding: const EdgeInsets.only(left: 8),
                    //     child: Row(
                    //       children: <Widget>[
                    //         Text(
                    //           subTxt,
                    //           textAlign: TextAlign.left,
                    //           style: TextStyle(
                    //             fontFamily: AppTheme.fontName,
                    //             fontWeight: FontWeight.normal,
                    //             fontSize: 16,
                    //             letterSpacing: 0.5,
                    //             color: AppTheme.nearlyDarkBlue,
                    //           ),
                    //         ),
                    //         SizedBox(
                    //           height: 38,
                    //           width: 26,
                    //           child: Icon(
                    //             Icons.arrow_forward,
                    //             color: AppTheme.darkText,
                    //             size: 18,
                    //           ),
                    //         ),
                    //       ],
                    //     ),
                    //   ),
                    // )
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}
