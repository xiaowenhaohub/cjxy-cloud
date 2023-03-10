class ListUtils {
// 数组拷贝
  static List<T> copyWithList<T>(List<T> list) {
    List<T> copyList = [];
    for (var item in list) {
      copyList.add(item);
    }
    return copyList;
  }
}
