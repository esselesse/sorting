package ru.mail.polis.bench;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort;
import ru.mail.polis.sort.QuickSortTriple;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

/**
 * Created by Nechaev Mikhail
 * Since 20/11/16.
 */

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class AverageTimeBench {

    MergeSort<Integer> mergeSort = new MergeSort<>();
    HeapSort<Integer> heapSort = new HeapSort<>();
    QuickSort<Integer> quickSort = new QuickSort<>();
    QuickSortTriple<Integer> quickSortTriple = new QuickSortTriple<>();
    CountingSort<IntKeyObject> countingSort = new CountingSort<>();
    LSDSort<SimpleString> lsdSort = new LSDSort<>();
    LSDSort<SimpleInteger> lsdSort1 = new LSDSort<>();

    int index;

    int[][] intArray;
    int[] intTemp;

    Integer[][] integerArray;
    Integer[] integerTemp;

    IntKeyObject[][] intKeyObjectArray;
    IntKeyObject[] intKeyObjectTemp;

    SimpleInteger[][] simpleIntegerArray;
    SimpleInteger[] simpleIntegerArrayTemp;

    SimpleString[][] simpleStringArray;
    SimpleString[] simpleStringArrayTemp;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        intArray = new int[10][100];
        integerArray = new Integer[10][100];
        intKeyObjectArray = new IntKeyObject[10][100];
        simpleIntegerArray = new SimpleInteger[10][100];
        simpleStringArray = new SimpleString[10][100];
        for (int i = 0; i < 10; i++) {
            //define arrays here
            intArray[i] = SortUtils.generateArray(100);
            integerArray[i] = SortUtils.generateWrappedIntegerArray(100);
            intKeyObjectArray[i] = SortUtils.generateWrappedIntKeyObjectArray(100);
            simpleIntegerArray[i] = SortUtils.generateSimpleIntegerArray(100);
            simpleStringArray[i] = SortUtils.generateSimpleStringArray(100);
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        intTemp = Arrays.copyOf(intArray[index], intArray[index].length);
        integerTemp = Arrays.copyOf(integerArray[index], integerArray[index].length);
        intKeyObjectTemp = Arrays.copyOf(intKeyObjectArray[index], intKeyObjectArray[index].length);
        simpleIntegerArrayTemp = Arrays.copyOf(simpleIntegerArray[index], simpleIntegerArray[index].length);
        simpleStringArrayTemp = Arrays.copyOf(simpleStringArray[index], simpleStringArray[index].length);
        index = (index + 1) % 10;
    }

    @Benchmark
    public void measureBubbleSort() {
        BubbleSort.sort(intTemp);
    }

    @Benchmark
    public void measureMergeSort() {
        mergeSort.sort(integerTemp);
    }

    @Benchmark
    public void measureHeapSort() {
        heapSort.sort(integerTemp);
    }

    @Benchmark
    public void measureQuickSort() {
        quickSort.sort(integerTemp);
    }

    @Benchmark
    public void measureQuickSortTriple() {
        quickSortTriple.sort(integerTemp);
    }

    @Benchmark
    public void measureCountingSort() {
        countingSort.sort(intKeyObjectTemp);
    }


    @Benchmark
    public void measureLSDSort1() {
        lsdSort1.sort(simpleIntegerArrayTemp);
    }

    @Benchmark
    public void measureLSDSort() {
        lsdSort.sort(simpleStringArrayTemp);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AverageTimeBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
