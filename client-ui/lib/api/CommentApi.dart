import 'package:common_utils/common_utils.dart';
import 'package:my_fist_flutter/model/CommentModel.dart';
import 'package:my_fist_flutter/utils/HttpRequest.dart';

class CommentApi {
  /// 获取评论
  static Future<List<CommentModel>> getComment(
      String friendCircleId, String? rootCommentId) async {
    Map<String, dynamic> data = {
      "friendCircleId": friendCircleId,
      "rootCommentId": rootCommentId,
      "pageNum": 0,
      "pageSize": 10
    };
    var response = await HttpRequest.post("/social/comment/get", data: data);
    List<CommentModel> commentList = [];
    response["data"].forEach((item) {
      commentList.add(CommentModel.fromJson(item));
    });
    return commentList;
  }

  /// 发表评论
  static Future<bool> sendComment(
      String friendCircleId, String? rootCommentId, String content) async {
    Map<String, dynamic> data = {
      "friendCircleId": friendCircleId,
      "rootCommentId": rootCommentId,
      "content": content
    };
    var response = await HttpRequest.post("/social/comment/save", data: data);
    if (response["success"]) {
      return true;
    }
    return false;
  }
}
