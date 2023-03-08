import 'package:common_utils/common_utils.dart';

import '../model/MessageModel.dart';
import '../utils/HttpRequest.dart';

class MessageApi {
  /// 获取好友列表
  static Future<List<MessageModel>> getMessageList(
      String account, int startIndex, int pageSize) async {
    Map<String, Object> map = {};
    map['startIndex'] = startIndex;
    map['pageSize'] = pageSize;
    map['account'] = account;
    var response = await HttpRequest.post("/social/message/get", data: map);
    List<MessageModel> messageList = [];
    response["data"].forEach((item) {
      messageList.add(MessageModel.fromJson(item));
    });
    return messageList;
  }
}
