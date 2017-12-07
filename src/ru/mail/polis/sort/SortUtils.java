package ru.mail.polis.sort;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;

public class SortUtils {

    private static final Random r = ThreadLocalRandom.current();

    public static void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }


    public static <T> void swap(T[] a, int i, int j) {
        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static int[] generateArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(a, i, j);
        }
        return a;
    }

    public static Integer[] generateWrappedIntegerArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int i = array.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(array, i, j);
        }
        return array;
    }

    public static IntKeyStringValueObject[] generateWrappedIntKeyObjectArray(int n) {
        IntKeyStringValueObject[] array = new IntKeyStringValueObject[n];
        for (int i = 0; i < n; i++) {
            array[i] = new IntKeyStringValueObject(i, Integer.toString(i));
        }
        for (int i = n-1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(array, i, j);
        }
        return array;
    }

    public static boolean isArraySorted(int[] a) {
        boolean isSorted = true;
        for (int i = 0; i < a.length - 1 && isSorted; i++) {
            isSorted = a[i] <= a[i + 1];
        }
        return isSorted;
    }

    public static <T extends Comparable<? super T>> boolean isArraySorted(T[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = array[i].compareTo(array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static <T> boolean isArraySorted(T[] array, Comparator<T> comparator) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = comparator.compare(array[i], array[i + 1]) <= 0;
        }
        return isSorted;
    }


}
