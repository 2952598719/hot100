package strt.array;

import java.util.HashMap;
import java.util.Map;

public class Other {
    
    // 146.LRU缓存
    class LRUCache {

        class Node {
            int key, value;
            Node prev, next;
            public Node() {}
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        int capacity;
        Map<Integer, Node> map;   // 存储key对应的Node
        Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if(!map.containsKey(key)) {
                return -1;
            }

            Node cur = map.get(key);
            delete(cur);
            addFirst(cur);
            return cur.value;
        }

        public void put(int key, int value) {
            Node cur;
            if(map.containsKey(key)) {
                cur = map.get(key);
                cur.value = value;
                delete(cur);
            } else {
                cur = new Node(key, value);
            }
            addFirst(cur);
            map.put(key, cur);

            if(map.size() > capacity) {
                Node tobeDelete = tail.prev;
                delete(tobeDelete);
                map.remove(tobeDelete.key);
            }
        }

        // 工具方法
        public void delete(Node cur) {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }
        public void addFirst(Node cur) {
            cur.prev = head;
            cur.next = head.next;
            head.next.prev = cur;
            head.next = cur;
        }

    }
    
}
