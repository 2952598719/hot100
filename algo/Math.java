package algo;

public class Math {

    // 136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        // 方法1：异或法
        int num = nums[0];
        for(int i = 1; i <= nums.length - 1; i++) {
            num ^= nums[i];
        }
        return num;
        // 方法2：哈希表法
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for(int i : nums) {
        //     map.put(i, map.getOrDefault(i, 0) + 1);
        // }
        // for(Entry<Integer, Integer> entry : map.entrySet()) {
        //     if(entry.getValue() == 1) {
        //         return entry.getKey();
        //     }
        // }
        // return 0;
    }
    
    // 287.寻找重复数
    public int findDuplicate(int[] nums) {
        // 方法1：排序+遍历
        // 方法2：如果只有n个数，则会形成一个链表，n+1则这个链表成环。本题的方法就是找到其中开始成环的那个元素，因为有两条边指向它
        // 成环的判断就是快慢指针
        int slow = nums[0], fast = nums[nums[0]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int newPtr = 0;
        while(newPtr != slow) {
            slow = nums[slow];
            newPtr = nums[newPtr];
        }
        return newPtr;
    }

}
