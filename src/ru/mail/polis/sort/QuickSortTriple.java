package ru.mail.polis.sort;

import java.util.*;

//import ru.mail.polis.bench.PartitionSortBench;

/**
 * Created by esselesse on 06.12.2017.
 */

public class QuickSortTriple<T extends Comparable<T>> extends AbstractSortOnComparisons<T>  {
    private int[] partition(T[] a, int left, int right) {
        swap(a, left, rand(left, right));

        T item = a[left];

        int minA = left;
        int maxA = right;
        int i = left + 1;

        while (i <= maxA) {
            if (lesser(a[i], item)) {
                swap(a, i++, minA++);
            } else if (greater(a[i], item)) {
                swap(a, i, maxA--);
            } else {
                i++;
            }
        }

        int result[] = new int[2];
        result[0] = minA;
        result[1] = maxA;
        return result;
    }

    private void quickSort(T[] a, int left, int right) {
        if (left >= right) return;
        int[] idx = partition(a, left, right);
        quickSort(a, left, idx[0] - 1);
        quickSort(a, idx[1] + 1, right);
    }

    @Override
    public void sort(T[] array) {
        quickSort(array, 0, array.length - 1);
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

    public static void main(String[] args) {
        System.out.println(QuickSortTriple.class.getSimpleName());
        Integer[] arr = SortUtils.generateWrappedIntegerArray(500);
        System.out.println(Arrays.toString(arr));
        new QuickSortTriple<Integer>().sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}