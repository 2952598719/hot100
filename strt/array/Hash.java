package strt.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hash {

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
        Set<List<Integer>> set = new HashSet<>();   // 两个List，只要内容相同，hashCode就相同
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
    // Map的键不能为int[]（因为数组的hashCode没有重写，只受内存位置影响），因此如果想用统计str内字母个数的方法，需要转为String
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            List<String> list = map.getOrDefault(new String(c), new ArrayList<>());
            list.add(str);
            map.put(str, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    // 128.最长连续序列
    // 先用set去重，然后遍历set
    // 为什么是O(n)，因为!set.contains(i - 1)去重，使得如果不是序列第一个元素，就continue
    public int longestConsecutive(int[] nums) {
        int maxCount = 0;
        Set<Integer> set = new HashSet<>();
        for(int i : nums) set.add(i);
        for(int i : set) {
            if(!set.contains(i - 1)) {
                int count = 1, cur = i + 1;
                while(set.contains(cur)) {
                    count++;
                    cur++;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }



    public void run() {
        groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
    
    public static void main(String[] args) {
        new Hash().run();
    }


}