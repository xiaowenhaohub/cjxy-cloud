import 'package:cached_network_image/cached_network_image.dart';
import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:my_fist_flutter/main.dart';
import 'package:my_fist_flutter/model/MomentModel.dart';
import '../../../AppTheme.dart';

class MomentCard extends StatefulWidget {
  MomentModel momentModel;
  MomentCard({super.key, required this.momentModel});

  @override
  State<MomentCard> createState() => _MomentCardState();
}

class _MomentCardState extends State<MomentCard> {
  String baseUrl = "http://43.132.148.227/api/mi/file/view/";

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 5, right: 5, bottom: 5),
      child: Container(
        width: MediaQuery.of(context).size.width,
        decoration: BoxDecoration(
          color: AppTheme.white,
          boxShadow: <BoxShadow>[
            BoxShadow(
                color: AppTheme.grey.withOpacity(0.2),
                offset: Offset(1.1, 1.1),
                blurRadius: 10.0),
          ],
        ),
        child: Column(
          children: [index(), body(), bottom()],
        ),
      ),
    );
  }

  Widget index() {
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
          SizedBox(
            width: 200,
            child: Text(
              widget.momentModel.nickName,
              style: const TextStyle(
                  color: AppTheme.red,
                  fontSize: 15,
                  fontWeight: FontWeight.bold),
            ),
          ),
          const Expanded(child: SizedBox()),
        ],
      ),
    );
  }

  Widget body() {
    return LayoutBuilder(builder: (context, constrains) {
      return Container(
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
                      softWrap: true,
                      textAlign: TextAlign.left,
                      overflow: TextOverflow.ellipsis,
                      maxLines: 3,
                      style: const TextStyle(fontSize: 15, color: Colors.black),
                    ),
                  ),
                  showPicture()
                ],
              ),
            )
          ],
        ),
      );
    });
  }

  Widget bottom() {
    return LayoutBuilder(builder: (context, constrains) {
      return Container(
        height: 30,
        width: constrains.maxWidth - 20,
        decoration: const BoxDecoration(
          border: Border(
              top: BorderSide(
            width: 0.1, //宽度
            color: AppTheme.nearlyBlack, //边框颜色
          )),
        ),
        child: Row(children: [
          bottomIcon("20", Icons.launch, () {}),
          bottomIcon("20", Icons.menu, () {}),
          bottomIcon(widget.momentModel.likedCount.toString(),
              Icons.thumb_up_alt_outlined, () {
            setState(() {
              widget.momentModel.liked = true;
            });
          }),
        ]),
      );
    });
  }

  Widget bottomIcon(String name, IconData iconData, Function onTap) {
    Icon icon = Icon(iconData, size: 15, color: HexColor("#696969"));

    if (iconData == Icons.thumb_up_alt_outlined && widget.momentModel.liked) {
      icon = Icon(Icons.thumb_up, size: 15, color: AppTheme.red);
      onTap = () {};
    }

    return Expanded(
      child: Container(
        alignment: Alignment.center,
        height: 30,
        child: InkWell(
          onTap: (() {
            onTap();
          }),
          child: Row(mainAxisAlignment: MainAxisAlignment.center, children: [
            icon,
            SizedBox(
              width: 5,
            ),
            Text(
              name,
              style: TextStyle(
                  fontSize: 12,
                  color: HexColor("#696969"),
                  fontWeight: FontWeight.bold),
            )
          ]),
        ),
      ),
    );
  }

  Widget showPicture() {
    if (widget.momentModel.picture == null) {
      return const SizedBox();
    }

    List<Widget> list = [];
    for (int i = 0;
        i <
            (widget.momentModel.picture!.length < 3
                ? widget.momentModel.picture!.length
                : 2);
        i++) {
      list.add(Container(
        constraints: BoxConstraints(maxHeight: 100),
        child: CachedNetworkImage(
          imageUrl: baseUrl + widget.momentModel.picture[i],
        ),
      ));
    }

    return Row(
      children: list,
    );
  }
}
