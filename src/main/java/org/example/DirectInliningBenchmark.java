package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
@State(Scope.Thread)
public class DirectInliningBenchmark {

    private Integer i = Integer.valueOf(17);

    @Benchmark
    public int directGet() {
        return i.intValue();
    }

    @Benchmark
    public int inlinedGet() {
        return inlinableGet(i);
    }

    private int inlinableGet(Integer integer) {
        return integer.intValue();
    }

    @Benchmark
    public int uninlinedGet() {
        return uninlinableGet(i);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int uninlinableGet(Integer integer) {
        return integer.intValue();
    }

}
