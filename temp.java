import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import util.utils;

public class temp {

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

    // 215.数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        // 方法1：快排
        // partialQuickSort(nums, 0, nums.length - 1, k);
        // return nums[k - 1];     // 注意其实是数组中的第k - 1个数，快排过程也要注意

        // 方法2：堆
        // 大顶堆也可以，全放进去之后poll出k-1个，剩下的堆顶就是第k大的了
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 默认是小顶堆
        for(int i : nums) {
            pq.offer(i);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
    void partialQuickSort(int[] nums, int start, int end, int k) {
        if(start >= end) return;
        int pivot = ascendPartition(nums, start, end);
        if(pivot == k - 1) return;
        else if(pivot < k - 1) partialQuickSort(nums, pivot + 1, end, k);
        else partialQuickSort(nums, start, pivot - 1, k);
    }
    public int ascendPartition(int[] nums, int start, int end) {
        if(start >= end) return start;
        int pivotValue = nums[start];
        int left = start + 1, right = end;
        while(left <= right) {
            while(left <= right && nums[left] > pivotValue) left++;
            while(left <= right && nums[right] < pivotValue) right--;
            if(left > right) break;
            utils.swap(nums, left, right);
            left++;
            right--;
        }
        utils.swap(nums, start, right);
        return right;
    }
    
}
