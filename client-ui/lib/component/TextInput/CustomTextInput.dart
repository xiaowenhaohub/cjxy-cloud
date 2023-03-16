import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import '../../AppTheme.dart';

typedef void CaretMoved(bool hasFocus);
typedef void TextChanged(String text);
typedef void OnSubmitted(String text);

// Helper widget to track caret position.
class CustomTextInput extends StatefulWidget {
  CustomTextInput(
      {Key? key,
      this.textEditingController,
      this.onCaretMoved,
      this.onTextChanged,
      this.hint,
      this.label,
      this.inputBorder,
      this.isObscured = false,
      this.onSubmitted})
      : super(key: key);
  final CaretMoved? onCaretMoved;
  final TextChanged? onTextChanged;
  final String? hint;
  final String? label;
  final InputBorder? inputBorder;
  final TextEditingController? textEditingController;
  final bool? isObscured;
  final OnSubmitted? onSubmitted; //键盘回车监听

  @override
  _CustomTextInput createState() => _CustomTextInput();
}

class _CustomTextInput extends State<CustomTextInput> {
  final GlobalKey _fieldKey = GlobalKey();
  late TextEditingController _textController;
  late FocusNode _nameFocusNode;
  late Timer _debounceTimer;
  @override
  initState() {
    _textController = widget.textEditingController ?? TextEditingController();
    _nameFocusNode = FocusNode();
    _nameFocusNode.addListener(() {
      // 失去焦点
      widget.onCaretMoved!(_nameFocusNode.hasFocus);
    });
    _textController.addListener(() {
      if (widget.onTextChanged != null) {
        widget.onTextChanged!(_textController.text);
      }
    });
    super.initState();
  }

  String getAndClearMessage() {
    String message = _textController.text;
    _textController.clear();
    return message;
  }

  @override
  Widget build(BuildContext context) {
    return TextField(
      maxLines: null,
      decoration: InputDecoration(
        hintText: widget.hint,
        labelText: widget.label,
        fillColor: AppTheme.nearlyWhite,
        border: widget.inputBorder ??
            const OutlineInputBorder(
              ///设置边框四个角的弧度
              borderRadius: BorderRadius.all(Radius.circular(20)),

              ///用来配置边框的样式
              borderSide: BorderSide(
                ///设置边框的颜色
                color: AppTheme.notWhite,

                ///设置边框的粗细
                width: 2,
              ),
            ),

        ///设置输入框可编辑时的边框样式
        enabledBorder: const OutlineInputBorder(
          ///设置边框四个角的弧度
          borderRadius: BorderRadius.all(Radius.circular(20)),

          ///用来配置边框的样式
          borderSide: BorderSide(
            ///设置边框的颜色
            color: AppTheme.notWhite,

            ///设置边框的粗细
            width: 2.0,
          ),
        ),

        ///用来配置输入框获取焦点时的颜色
        focusedBorder: const OutlineInputBorder(
          ///设置边框四个角的弧度
          borderRadius: BorderRadius.all(Radius.circular(20)),

          ///用来配置边框的样式
          borderSide: BorderSide(
            ///设置边框的颜色
            color: AppTheme.notWhite,

            ///设置边框的粗细
            width: 2.0,
          ),
        ),
      ),
      key: _fieldKey,
      onSubmitted: widget.onSubmitted ??
          (text) {
            print('submit $text');
          },
      focusNode: _nameFocusNode,
      controller: _textController,
      obscureText: widget.isObscured!,
      style: const TextStyle(color: Color(0xFF03073C), fontSize: 15),
    );
  }
}
