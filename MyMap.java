import java.util.*;

class MyMap<K, V> {
  List<Node<K, V>> map; // bucket array
  int capacity;
  int size;
  final int INITIAL_CAPACITY = 5;

  public MyMap() {
    this.map = new ArrayList<>();
    this.capacity = INITIAL_CAPACITY;
    for (int i = 0; i < INITIAL_CAPACITY; i++) {
      map.add(null);
    }
  }

  
  public void put(K key, V value) {
    int bucketIndex = getBucketIndex(key);
    Node head = map.get(bucketIndex);
    while (head != null) {
      if (head.key.equals(key)) {
        head.value = value;
        return;
      }
      head = head.next;
    }
    size++;
    Node<K, V> newEntry = new Node<K, V>(key, value);
    head = map.get(bucketIndex);
    newEntry.next = head;
    map.set(bucketIndex, newEntry);

    double loadFactor = (1.0 * size) / capacity;
    if(loadFactor > 0.7){
      reHash();
    }
  }

  void reHash(){
    List<Node<K, V>> temp = map;
    map = new ArrayList<>();
    capacity *= 2;
    for(int i=0;i<capacity;i++)
      map.add(null);
    for(int i=0;i<temp.size();i++){
      Node<K, V> head = temp.get(i);
      while(head != null){
        put(head.key, head.value);
        head = head.next;
      }
    }
  }

  
  public void remove(K key) {
    int bucketIndex = getBucketIndex(key);
    Node<K, V> head = map.get(bucketIndex);
    Node<K, V> prev = null;
    while (head != null) {
      if (head.key.equals(key)) {
        if (prev == null) {
          map.set(bucketIndex, head.next);
        } else {
          prev.next = head.next;
        }
        head.next = null;
        size--;
        break;
      }
      prev = head;
      head = head.next;
    }
  }

  public V get(K key) {
    int bucketIndex = getBucketIndex(key);
    Node<K, V> head = map.get(bucketIndex);
    while (head != null) {
      if (head.key.equals(key)) {
        return head.value;
      }
      head = head.next;
    }
    return null;
  }

  int getBucketIndex(K key) {
    int hashCode = key.hashCode();
    return hashCode % capacity;
  }


  
  class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}