package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class LSDSort<T extends Numerical> implements Sort<T> {

    public LSDSort() {

    }

    @Override
    public void sort(T[] array) {
        final int maxDigit = array[0].getDigitMaxValue() + 1;
        int digitCount = array[0].getDigitCount();
        for (int i = 1; i < array.length; i++) {
            if (array[i].getDigitCount() > digitCount) {
                digitCount = array[i].getDigitCount();
            }
        }

        for (int i = 0; i < digitCount; i++) {
            int[] count = new int[maxDigit];
            for (int j = 0; j < array.length; j++) {
                count[array[j].getDigit(i)]++;
            }
            for (int j = 1; j < maxDigit; j++) {
                count[j] += count[j - 1];
            }

            T[] res = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
            for (int j = array.length - 1; j >= 0; j--) {
                res[--count[array[j].getDigit(i)]] = array[j];
            }
            System.arraycopy(res, 0, array, 0, array.length);
        }
    }

    public static void main(String[] args) {
        System.out.println(LSDSort.class.getSimpleName());
        SimpleInteger[] arr = SortUtils.generateSimpleIntegerArray(10);
        System.out.println(Arrays.toString(arr));
        new LSDSort<>().sort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(LSDSort.class.getSimpleName());
        SimpleString[] arr1 = SortUtils.generateSimpleStringArray(10);
        for (SimpleString s: arr1) {
            System.out.print(s.getData()+" ");
        }
        new LSDSort<>().sort(arr1);
        System.out.println();
        for (SimpleString s: arr1) {
            System.out.print(s.getData()+" ");
        }

    }
}
