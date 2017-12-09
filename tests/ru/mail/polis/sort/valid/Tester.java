package ru.mail.polis.sort.valid;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.mail.polis.sort.AbstractSortOnComparisons;
import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.SortUtils;

import ru.mail.polis.sort.*;


@RunWith(value = Parameterized.class)
public class Tester {

    private static AbstractSortOnComparisons<Integer> quickSort;
    private static AbstractSortOnComparisons<Integer> quickSortTriple;
    private static AbstractSortOnComparisons<Integer> heapSort;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.err.println("=== Running " + description.getMethodName());
        }
    };

    @Parameterized.Parameter
    public int[] array;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][]{
            {0},
            {0, 0, 0, 0},
            {4, 3, 2, 1},
            {0, 1, 1, 0},
            {1},
            {Integer.MAX_VALUE, 0, 0, Integer.MIN_VALUE},
            SortUtils.generateArray(1),
            SortUtils.generateArray(10),
            SortUtils.generateArray(100),
            SortUtils.generateArray(1000),
            SortUtils.generateArray(10000),
        });
    }

    @BeforeClass //Перед всеми запусками тестов - should be static
    public static void init() {
        quickSort = new QuickSort<Integer>();
        quickSortTriple = new QuickSortTriple<Integer>();
        heapSort = new HeapSort<Integer>();
    }

    @Test
    public void test01_checkBubbleSort() throws IOException {
        Assert.assertTrue(SortUtils.isArraySorted(BubbleSort.sort(array)));
    }

    @Test
    public void test02_checkQuickSort() throws IOException {
        Integer[] arr = IntStream.of(array).boxed().toArray(Integer[]::new);
        quickSort.sort(arr);
        Assert.assertTrue(SortUtils.isArraySorted(arr));
    }

    @Test
    public void test03_checkQuickSortTriple() {
        Integer[] arr = IntStream.of(array).boxed().toArray(Integer[]::new);
        quickSortTriple.sort(arr);
        Assert.assertTrue(SortUtils.isArraySorted(arr));
    }

    @Test
    public void test04_checkHeapSort() {
        Integer[] arr = IntStream.of(array).boxed().toArray(Integer[]::new);
        heapSort.sort(arr);
        Assert.assertTrue(SortUtils.isArraySorted(arr));
    }
}
