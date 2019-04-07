package com.example.zed.algorithm;

public class SortAlgorithm {
    public static int[] insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public static int[] stoogeSort(int[] array,int i,int j){
        if (array[i] > array[j]){
            int t = array[i];
            array[i] = array[j];
            array[j] = t;
        }
        if ((j - i + 1) >= 3 ) {
            int t = (j - i + 1) / 3;
            stoogeSort(array, i, j - t);
            stoogeSort(array, i + t, j);
            stoogeSort(array, i, j - t);
        }
        return array;
    }

    public static void quickSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        quickSort(arr, head, j);
        quickSort(arr, i, tail);
    }
}
