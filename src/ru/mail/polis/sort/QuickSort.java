package ru.mail.polis.sort;

import java.util.*;

import ru.mail.polis.structures.IntKeyObject;

/**
 * Created by esselesse on 03.10.2017.
 */
public class QuickSort <T extends Comparable<T>> extends AbstractSortOnComparisons<T>   {
    static int n;



//    private void quickSort(T[] array, int a, int b){
//        if(a>=b) return;
//
//        int index = partition(array, a, b);
//        quickSort(array, a, index);
//        quickSort(array, index+1, b);
//    }

    private void quickSort(T[] array, int left, int right) {
        if (right - left < 45) {
            insertionSort(array, left, right);
        } else {
            if (left >= right) return;
            int idx = partition(array, left, right);
            quickSort(array, left, idx);
            quickSort(array, idx + 1, right);
        }
    }

    private void insertionSort(T[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left && lesser(array[j], array[j - 1]); j--) {
                swap(array, j, j - 1);
            }
        }
    }


    private int partition(T[] array, int left, int right){
        int tempIndex = rand(left, right);
        T base = array[tempIndex];
        int i = left, j = right;
        while (i<=j){
            while(lesser(array[i], base)) {
                i++;
            }
            while(greater(array[j],base)) {
                j--;
            }
            if(i<=j) {
                swap(array, i++, j--);
            }
        }
        return j;
    }

    protected void swap(T[] array, int j, int i) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int rand(int min, int max) {
        max = max - min + 1;
        return (int) (Math.random() * max) + min;
    }

    @Override
    public void sort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        System.out.println(QuickSort.class.getSimpleName());
        Integer[] arr = SortUtils.generateWrappedIntegerArray(10000);
        System.out.println(Arrays.toString(arr));
        new QuickSort<Integer>().sort(arr);
        System.out.println(Arrays.toString(arr));
//        System.out.println("Quick = " + count_Quick + ", Insertion = " + count_Inser);
    }
}
