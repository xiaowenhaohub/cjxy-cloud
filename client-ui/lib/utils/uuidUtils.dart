import 'package:uuid/uuid.dart';

class UUID {
  static const _uid = Uuid();
  static String uuid() {
    return _uid.v1();
  }
}
