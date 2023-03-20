import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:my_fist_flutter/AppTheme.dart';
import 'package:my_fist_flutter/components/TextInput/CustomTextInput.dart';
import 'package:my_fist_flutter/main.dart';
import 'package:my_fist_flutter/model/CommentModel.dart';
import 'package:my_fist_flutter/model/MomentModel.dart';

class SendComment extends StatelessWidget {
  SendComment({super.key, required this.onEditingCompleteText});
  final ValueChanged onEditingCompleteText;
  final TextEditingController controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Positioned(
      bottom: 0,
      child: Container(
          width: MediaQuery.of(context).size.width,
          height: 70,
          color: Color(0xFFF4F4F4),
          padding: EdgeInsets.only(left: 16, top: 8, bottom: 8, right: 16),
          child: Container(
            // decoration: BoxDecoration(color: Colors.white),
            child: TextField(
              controller: controller,
              // autofocus: true,
              style: TextStyle(fontSize: 16),
              //设置键盘按钮为发送
              textInputAction: TextInputAction.send,
              keyboardType: TextInputType.multiline,
              onEditingComplete: () {
                //点击发送调用
                print('onEditingComplete');
                onEditingCompleteText(controller.text);
                // Navigator.pop(context);
                controller.clear();
              },
              decoration: const InputDecoration(
                fillColor: AppTheme.white,
                hintText: '请输入评论的内容',
                isDense: true,
                contentPadding:
                    EdgeInsets.only(left: 10, top: 5, bottom: 5, right: 10),
                border: OutlineInputBorder(
                  gapPadding: 0,
                  borderSide: BorderSide(
                    width: 0,
                    style: BorderStyle.none,
                  ),
                ),
              ),

              minLines: 1,
              maxLines: 5,
            ),
          )),
    );
  }
}
