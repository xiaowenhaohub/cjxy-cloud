import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';

//文本输入框
class IowTextField extends StatefulWidget {
  final TextEditingController textController; //文本内容
  final double height; //文本输入框高度
  final double white; //文本输入框宽度
  final TextStyle? style; //输入框中文字样式
  final onChanged; //输入监听
  final onSubmitted; //键盘回车监听
  final hintText; //提示文字
  final Widget? keyName; //属性容器
  final Widget? suffixWidget; //后缀容器
  final bool suffixShow; //后缀容器是否展示
  final bool enabled; //禁用
  final double keyNameWidth; //属性容器宽度
  //设置键盘弹出时类型
  final TextInputType textInputType; //文本框属性
  final int maxLines; //最大显示行

  IowTextField(
      {required this.textController,
      this.onChanged,
      this.onSubmitted,
      this.hintText,
      this.textInputType = TextInputType.text,
      this.height = 50,
      this.white = 300,
      this.keyName,
      this.maxLines = 1,
      this.keyNameWidth = 60,
      this.suffixWidget,
      this.style,
      this.suffixShow = true,
      this.enabled = true});

  @override
  _IowTextFieldState createState() => _IowTextFieldState();
}

class _IowTextFieldState extends State<IowTextField> {
  TextEditingController textController = new TextEditingController();
  FocusNode _focusNode = FocusNode();

  @override
  void initState() {
    textController = widget.textController;
    super.initState();
    //添加监听
    _focusNode.addListener(() {
      if (!_focusNode.hasFocus) {
        print("----失去焦点了----");
      } else {
        print("----获得焦点了----");
      }
    });
  }

  @override
  void dispose() {
    _focusNode.unfocus();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(left: 6, right: 6),
      decoration: const BoxDecoration(
        color: Colors.white,
      ),
      alignment: Alignment.center,
      height: widget.height,
      // margin: EdgeInsets.fromLTRB(24, 9, 9, 12),
      padding: EdgeInsets.only(left: 6, right: 6),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Expanded(
              flex: 8,
              child: TextField(
                enabled: widget.enabled,
                controller: textController,
                maxLines: widget.maxLines,
                cursorColor: Colors.black,
                focusNode: _focusNode,
                onChanged: widget.onChanged ??
                    (value) {
                      print("正在输入内容：$value");
                    },
                onSubmitted: widget.onSubmitted ??
                    (text) {
                      print('submit $text');
                    },
                keyboardType: widget.textInputType,
                textAlignVertical: TextAlignVertical.center, //添加图标后会有一个小的向上偏移
                style: widget.style ?? TextStyle(fontSize: 14),
                decoration: InputDecoration(
                    contentPadding: const EdgeInsets.only(left: 0), //内容内边距，影响高度
                    hintText: widget.hintText,
                    hintStyle: TextStyle(fontSize: 14),
                    border: InputBorder.none,
                    isCollapsed: true, //相当于高度包裹的意思，必须设置为true，不然有默认奇妙的最小高度
                    fillColor: Colors.white, //背景颜色，必须结合filled: true,才有效
                    filled: true, //必须设置为true，fillColor才有效
                    isDense: true,
                    icon: Padding(
                      padding: EdgeInsets.only(right: 0),
                      child: SizedBox(
                        width: widget.keyNameWidth,
                        child: widget.keyName,
                      ),
                    ),
                    suffixIcon: Offstage(
                      offstage: widget.enabled
                          ? (textController.text != "" ? false : true)
                          : true,
                      child: GestureDetector(
                        onTap: () {
                          //addPostFrameCallback是 StatefulWidge 渲染结束的回调，只会被调用一次
                          SchedulerBinding.instance.addPostFrameCallback((_) {
                            textController.text = "";
                          });
                        },
                        child: Padding(
                          padding: EdgeInsets.all(0),
                          child: Icon(
                            Icons.cancel_outlined,
                            size: 18,
                            color: Colors.black12,
                          ),
                        ),
                      ),
                    )),
              )),
          Visibility(
            visible: widget.suffixShow,
            child: Expanded(
                flex: 1,
                child: widget.suffixWidget ??
                    Container(
                      child: Text("widget"),
                    )),
          )
        ],
      ),
    );
  }
}
