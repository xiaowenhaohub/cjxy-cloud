import 'dart:io';

import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_smart_dialog/flutter_smart_dialog.dart';
import 'package:my_fist_flutter/api/FileApi.dart';
import 'package:my_fist_flutter/api/SocialApi.dart';
import 'package:my_fist_flutter/main.dart';
import 'package:my_fist_flutter/utils/uuidUtils.dart';
import 'package:wechat_assets_picker/wechat_assets_picker.dart';
import '../../../AppTheme.dart';

class AddMomentPage extends StatefulWidget {
  const AddMomentPage({super.key});

  @override
  State<AddMomentPage> createState() => _AddMomentPageState();
}

class _AddMomentPageState extends State<AddMomentPage> {
  late TextEditingController textEditingController;
  FocusNode _commentFocus = FocusNode();
  List<File> fileList = [];

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 100)).then((value) {
      FocusScope.of(context).requestFocus(_commentFocus);
    });
    textEditingController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: getAppBar(),
      body: Container(
        color: Colors.white,
        child: Column(
          children: [
            textWidget(),
            pictureWidget(),
          ],
        ), //修改颜色
      ),
    );
  }

  Widget textWidget() {
    return Container(
      height: 150,
      width: MediaQuery.of(context).size.width,
      child: input(),
    );
  }

  Widget pictureWidget() {
    List<Widget> list = [];

    fileList.forEach((file) {
      list.add(Container(
        height: 100,
        width: 100,
        margin: EdgeInsets.all(5),
        child: Image.file(
          file,
          fit: BoxFit.cover,
        ),
      ));
    });
    list.add(InkWell(
      onTap: () => _pickImage(),
      child: Container(
        margin: EdgeInsets.all(5),
        height: 100,
        width: 100,
        color: HexColor("#F6F6F6"),
        child: Icon(
          Icons.add,
          size: 30,
          color: HexColor("#6D6D6D"),
        ),
      ),
    ));

    return Container(
      width: MediaQuery.of(context).size.width,
      padding: const EdgeInsets.all(10),
      child: Wrap(
        alignment: WrapAlignment.start,
        children: list,
      ),
    );
  }

  AppBar getAppBar() {
    return AppBar(
        iconTheme: IconThemeData(),
        backgroundColor: AppTheme.white,
        title: const Text(
          "发表文字",
          style: TextStyle(color: Colors.black),
        ),
        shadowColor: AppTheme.white,
        elevation: 0,
        actions: [
          IconButton(
            onPressed: () {
              saveMoments();
            },
            icon: Icon(Icons.check),
          ),
        ]);
  }

  Widget input() {
    return TextField(
      cursorColor: AppTheme.green,
      maxLines: null,
      focusNode: _commentFocus,
      decoration: const InputDecoration(
        fillColor: Colors.white,
        border: OutlineInputBorder(
          ///设置边框四个角的弧度
          borderRadius: BorderRadius.all(Radius.circular(0)),

          ///用来配置边框的样式
          borderSide: BorderSide.none,
        ),

        ///设置输入框可编辑时的边框样式
        enabledBorder: OutlineInputBorder(
          ///设置边框四个角的弧度
          borderRadius: BorderRadius.all(Radius.circular(0)),

          ///用来配置边框的样式
          borderSide: BorderSide.none,
        ),

        ///用来配置输入框获取焦点时的颜色
        focusedBorder: OutlineInputBorder(
          ///设置边框四个角的弧度
          borderRadius: BorderRadius.all(Radius.circular(0)),

          ///用来配置边框的样式
          borderSide: BorderSide.none,
        ),
      ),
      controller: textEditingController,
      style: const TextStyle(color: Color(0xFF03073C), fontSize: 15),
    );
  }

  void _pickImage() async {
    final List<AssetEntity>? assets =
        await AssetPicker.pickAssets(context, maxAssets: 10);
    if (assets == null) {
      return;
    }
    List<File> list = [];

    for (var item in assets) {
      File? file = await item.file;
      if (file != null) {
        list.add(file);
      }
    }
    setState(() {
      fileList = list;
    });
  }

  void saveMoments() async {
    if (fileList.isEmpty && textEditingController.text == '') {
      SmartDialog.showToast("内容不能为空");
      return;
    }
    SmartDialog.showLoading();
    if (textEditingController.text == '') {
      textEditingController.text = " ";
    }

    String picture = '';
    if (fileList != []) {
      picture = await FileApi.uploadManyImage(fileList);
    }
    bool result =
        await SocialApi.savaMoment(textEditingController.text, picture);

    LogUtil.v(result);
    if (result) {
      Navigator.of(context).pop();
    }
    SmartDialog.dismiss();
  }
}
