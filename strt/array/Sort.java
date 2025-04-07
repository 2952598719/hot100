package strt.array;

import java.util.Random;

import util.utils;

public class Sort {

    // 快排
    public void sort(int[] nums) {
        QuickSort(nums, 0, nums.length - 1);
    }
    public void QuickSort(int[] nums, int start, int end) {
        if(start >= end) return;
        int pivot = partition(nums, start, end);
        QuickSort(nums, start, pivot - 1);
        QuickSort(nums, pivot + 1, end);
    }
    public int partition(int[] nums, int start, int end) {
        if(start >= end) return start;
        // Tools.swap(nums, start, random.nextInt(start + 1, end + 1));
        int pivotValue = nums[start];

        int left = start + 1, right = end;
        while(left <= right) {
            while(left <= right && nums[left] < pivotValue) left++;
            while(left <= right && nums[right] > pivotValue) right--;
            if(left > right) break;
            utils.swap(nums, left, right);
            left++;
            right--;
        }
        utils.swap(nums, start, right);
        return right;
    }
    

    public void run() {
        Random random = new Random();
        int[] nums = new int[100];
        for(int i = 1; i <= 100; i++) {
            nums[i - 1] = random.nextInt(200) - 100;
        }
        sort(nums);
        for(int i : nums) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        new Sort().run();
    }

}
