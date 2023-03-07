import '../utils/http/HttpRequest.dart';

class TestApi {
  static test(data) {
    return HttpRequest.get("/test/12");
  }
}
