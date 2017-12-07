package ru.mail.polis.sort;

import java.util.*;



/**
 * Created by esselesse on 06.12.2017.
 */

public class HeapSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T>  {


    @Override
    public void sort(T[] array) {
        heapify(array);
        for (int i=array.length-1; i>=0; i--){
            swap(array, 0, i);
            hippy(array, i, 0);
        }
    }

    private void heapify(T[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            hippy(array, array.length, i);
    }

    private void hippy(T[] array, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && greater(array[left],array[largest]))
            largest = left;

        if (right < n && greater(array[right], array[largest]))
            largest = right;

        if (largest != i)
        {
            swap(array, i, largest);
            hippy(array, n, largest);
        }
    }

    protected void swap(T[] array, int j, int i) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(HeapSort.class.getSimpleName());
        Integer[] arr = SortUtils.generateWrappedIntegerArray(50);
        System.out.println(Arrays.toString(arr));
        new HeapSort<Integer>().sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}