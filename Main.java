//test class
class Main {
  public static void main(String[] args) {
    MyMap<String, Integer> map = new MyMap<String, Integer>();
    map.put("abc", 9);
    map.put("rty", 546);
    map.remove("abc");
    System.out.println(map.get("abc"));
  }
}