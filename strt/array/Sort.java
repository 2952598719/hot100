package strt.array;

import java.util.Random;

import util.utils;

public class Sort {
    
    // 冒泡、插入、选择均稳定
    // 归并稳定，快排不稳定，希尔不稳定，堆不稳定，计数稳定，桶由内层决定，基数稳定

    // 912.排序数组
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    // 冒泡排序
    public void bubbleSort(int[] nums) {
        // i表示冒泡轮数（共需要冒n次），j表示数组位置
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 1; j <= nums.length - i; j++) {
                if(nums[j - 1] > nums[j]) swap(nums, j - 1, j);
            }
        }
    }

    // 插入排序
    public void insertSort(int[] nums) {
        // 一开始假设nums[0]已经排序好，不断地把后面的插入其中
        // i表示当前想插入的元素
        for(int i = 1; i <= nums.length - 1; i++) {
            int temp = nums[i], j;
            for(j = i - 1; j >= 0; j--) {
                if(nums[j] > temp) nums[j + 1] = nums[j];
                else break;
            }
            nums[j + 1] = temp;
        }
    }
    // 希尔排序
    // 就是给insertSort套了一层gap，并将insertSort中的1换成gap
    public void shellSort(int[] nums) {
        for(int gap = nums.length / 2; gap > 0; gap /= 2) {
            for(int i = gap; i <= nums.length - 1; i++) {
                int temp = nums[i], j;
                for(j = i - gap; j >= 0; j -= gap) {
                    if(nums[j] > temp) nums[j + gap] = nums[j];
                    else break;
                }
                nums[j + gap] = temp;
            }
        }
    }

    // 选择排序
    public void selectSort(int[] nums) {
        // i表示数组位置，每次从后面选择一个最小的放到i上
        for(int i = 0; i <= nums.length - 1; i++) {
            int minPos = i;
            for(int j = i + 1; j <= nums.length - 1; j++) {
                if(nums[minPos] > nums[j]) minPos = j;
            }
            swap(nums, i, minPos);
        }
    }

    // 快排
    public void quickSort(int[] nums, int start, int end) {
        if(start >= end) return;
        int pivot = partition(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
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

    // 归并排序
    // 适合于局部有序，全局无序的情况
    public void mergeSort(int[] nums, int[] result, int start, int end) {
        if(start >= end) return;

        int midPos = start + (end - start) / 2;
        mergeSort(nums, result, start, midPos);
        mergeSort(nums, result, midPos + 1, end);

        int leftPos = start, rightPos = midPos + 1, pos = start;
        while(leftPos <= midPos && rightPos <= end) {
            if(nums[leftPos] < nums[rightPos]) {
                result[pos] = nums[leftPos];
                leftPos++;
            } else {
                result[pos] = nums[rightPos];
                rightPos++;
            }
            pos++;
        }
        while(leftPos <= midPos) {
            result[pos] = nums[leftPos];
            leftPos++;
            pos++;
        }
        while(rightPos <= end) {
            result[pos] = nums[rightPos];
            rightPos++;
            pos++;
        }
        for(pos = start; pos <= end; pos++) {
            nums[pos] = result[pos];
        }
    }

    // 堆排序
    // 堆是一个完全二叉树
    public void heapSort(int[] nums) {
        for(int i = nums.length / 2 - 1; i >= 0; i--) {     // 从最后一个父节点开始
            maxHeapify(nums, i, nums.length - 1);
        }
        for(int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            maxHeapify(nums, 0, i - 1);
        }
    }
    public void maxHeapify(int[] nums, int start, int end) {
        int parent = start, child = parent * 2 + 1;
        while(child <= end) {
            if(child <= end - 1 && nums[child] < nums[child + 1]) child = child + 1;
            if(nums[parent] > nums[child]) return;
            else {
                swap(nums, parent, child);
                parent = child;
                child = parent * 2 + 1;
            }
        }
    }

    // 计数排序：开辟一个和数组范围k相等的空间，逐个放入，并统计不为0的个数，则可以O(n+k)得到排序结果。如果k很大，这种方法不值当

    // 桶排序：计数排序的优化，将范围划分为不同的桶，再将元素放到对应桶中，然后针对每个桶做排序（快排）
    // 比如范围为0~100，可以划分为5个桶，然后遍历数组，放到桶中。

    // 基数排序
    // 从低位开始，逐位排序


    // 排序链表
    

    public void run() {
        Random random = new Random();
        int[] nums = new int[100];
        for(int i = 1; i <= 100; i++) {
            nums[i - 1] = random.nextInt(200) - 100;
        }
        sortArray(nums);
        for(int i : nums) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        new Sort().run();
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
