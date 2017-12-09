package ru.mail.polis.bench;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class SimpleStringSortBench {
    LSDSort<SimpleString> lsdSort = new LSDSort<>();
    LSDSort<SimpleInteger> lsdSort1 = new LSDSort<>();


    SimpleString[] temp;
    SimpleInteger[] temp1;

    @Setup(value = Level.Invocation)
    public void setUpInvocation(){
        temp = SortUtils.generateSimpleStringArray(1000);
        temp1 = SortUtils.generateSimpleIntegerArray(1000);
    }

    @Benchmark
    public void measureSimpleStringSortBench(){
        lsdSort.sort(temp);
        lsdSort1.sort(temp1);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SimpleStringSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}