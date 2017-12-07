package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.*;



/**
 * Created by esselesse on 06.12.2017.
 */

public class MergeSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T>  {


    @Override
    public void sort(T[] array) {
        int n=array.length;
        //int[] tempMass = new int[n];
        mergeSort(array, 0, n-1);

    }

    private void mergeSort(T[] array, int left, int right) {
        if (left>=right) return;
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    private void merge(T[] array, int left, int mid, int right) {

        T[] temp = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        for (int i = left; i <= right; i++) {
            //tempIndexMass[i]=indexMass[i];
            temp[i]=array[i];
        }
        int i = left;
        int j = mid+1;
        for (int k = left; k <= right; k++) {
            if (i>mid) {
                //indexMass[k] = tempIndexMass[j++];
                array[k]=temp[j++];
            }
            else if(j>right) {
                //indexMass[k] = tempIndexMass[i++];
                array[k]=temp[i++];
            }
            else if(lesser(temp[j], temp[i])) {
                //else if(mass[tempIndexMass[j]][0]<mass[tempIndexMass[i]][0]) {
                //indexMass[k] = tempIndexMass[j++];
                array[k]=temp[j++];
            }
            else {
                //indexMass[k] = tempIndexMass[i++];
                array[k]=temp[i++];
            }
        }
    }

    protected void swap(T[] array, int j, int i) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(MergeSort.class.getSimpleName());
        Integer[] arr = SortUtils.generateWrappedIntegerArray(100);
        System.out.println(Arrays.toString(arr));
        new MergeSort<Integer>().sort(arr);
        System.out.println(Arrays.toString(arr));
//        System.out.println("Quick = " + count_Quick + ", Insertion = " + count_Inser);
    }
}