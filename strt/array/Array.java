package strt.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import util.utils;

public class Array {

    // 1.两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for(int i = 1; i <= nums.length - 1; i++) {
            if(map.containsKey(target - nums[i])) return new int[]{i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // 15.三数之和
    // 两种方法：set去重，判断前后来去重
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        // List<List<Integer>> results = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i <= nums.length - 3; i++) {
            if(i >= 1 && nums[i] == nums[i - 1]) continue;  // 去重
            int left = i + 1, right = nums.length - 1;
            while(left < right) {   // (不是二分)
                int sum = nums[left] + nums[right];
                if(sum < -nums[i]) {
                    left++;
                } else if(sum > -nums[i]) {
                    right--;
                } else {
                    // results.add(Arrays.asList(i, left, right));
                    // while(left < right && nums[left] == nums[left + 1]) left++;
                    // while(left < right && nums[right] == nums[right - 1]) right--;
                    // right--;
                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }
        List<List<Integer>> results = new ArrayList<>();
        for(List<Integer> result : set) {
            results.add(result);
        }
        return results;
    }

    // 49.字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        return null;
    }
    









    



    public void run() {
        
    }
    
    public static void main(String[] args) {
        new Array().run();
    }


}