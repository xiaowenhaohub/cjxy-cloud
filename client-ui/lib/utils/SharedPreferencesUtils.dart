
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SharedPreferencesUtils {


  static const String COURSE_LIST_KEY = "courseList";

  static const String TOKEN_KEY = "access_token";

  static const String PASSWORD_KEY = "auth_password";

  static const String USER_KEY = "userInfo";

  /// 设置存储
  static setStorage(String key,dynamic value) async  {
    SharedPreferences _storage = await SharedPreferences.getInstance();
    String type;
    // 监测value的类型 如果是Map和List,则转换成JSON，以字符串进行存储
    if(value is Map||value is List){
      type='String';
      value=JsonEncoder().convert(value);
    }
    // 否则 获取value的类型的字符串形式
    else{
      type=value.runtimeType.toString();
    }

    // 根据value不同的类型 用不同的方法进行存储
    switch (type) {
      case 'String':
        _storage.setString(key, value);
        break;
      case 'int':
        _storage.setInt(key, value);
        break;
      case 'double':
        _storage.setDouble(key, value);
        break;
      case 'bool':
        _storage.setBool(key, value);
        break;
    }
  }
  /// 获取存储 注意：返回的是一个Future对象 要么用await接收 要么在.then中接收
  static Future<dynamic> getStorage(String key) async {
    SharedPreferences _storage = await SharedPreferences.getInstance();
    // 获取key对应的value
    dynamic value=_storage.get(key);
    if(_isJson(value)){
      return JsonDecoder().convert(value);
    }else{
      // 不是 则直接返回
      return value;
    }
  }
  /// 是否包含某个key
  static Future<bool> hasKey(String key)async{
    SharedPreferences _storage = await SharedPreferences.getInstance();
    return _storage.containsKey(key);
  }
  /// 删除key指向的存储 如果key存在则删除并返回true，否则返回false
  static Future<bool> removeStorage(String key)async{
    SharedPreferences _storage = await SharedPreferences.getInstance();
    if(await hasKey(key)){
      await _storage.remove(key);
      return true;
    }else{
      return false;
    }
    // return  _storage.remove(key);
  }

  /// 清空存储 并总是返回true
  static Future<bool> clear()async{
      SharedPreferences _storage = await SharedPreferences.getInstance();
    _storage.clear();
    return true;
  }

  /// 获取所有的key 类型为Set<String>
  static Future<Set<String>> getKeys()async{
    SharedPreferences _storage = await SharedPreferences.getInstance();
    return _storage.getKeys();
  }


  // 判断是否是JSON字符串
  static _isJson(dynamic value){
    try{
      // 如果value是一个json的字符串 则不会报错 返回true
      JsonDecoder().convert(value);
      return true;
    }catch(e){
      // 如果value不是json的字符串 则报错 进入catch 返回false
      return false;
    }
  }


}