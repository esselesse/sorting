package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

import ru.mail.polis.structures.IntKeyObject;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class CountingSort<T extends IntKeyObject> implements Sort<T> {

    public CountingSort() {

    }

    @Override
    public void sort(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if(max.compareTo(array[i])<0){
                max=array[i];
            }
        }

        int[] countMass = new int[max.getKey()+1];
        for (int i = 0; i < array.length; i++) {
            countMass[array[i].getKey()]++;
        }

        T[] res = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        for (int i = 0; i <= max.getKey(); i++) {
            countMass[i] += countMass[i-1];
        }

        for (int i = res.length - 1; i >= 0; i--) {
            res[countMass[array[i].getKey()]--]=array[i];
        }

        System.arraycopy(res,0,array,0,array.length);
    }

//    public static void main(String[] args) {
//        System.out.println(CountingSort.class.getSimpleName());
//        Integer[] arr = SortUtils.generateWrappedIntegerArray(100);
//        System.out.println(Arrays.toString(arr));
//        new CountingSort<Integer>().sort(arr);
//        System.out.println(Arrays.toString(arr));
////        System.out.println("Quick = " + count_Quick + ", Insertion = " + count_Inser);
//    }
}
