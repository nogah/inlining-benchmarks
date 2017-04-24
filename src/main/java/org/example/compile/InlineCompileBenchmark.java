package org.example.compile;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/*
The goal of the benchmark to see if Hotspot inlines before or after the callee is inlined
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {
      "-XX:-UseCompressedOops",
      "-XX:+UnlockDiagnosticVMOptions",
      "-XX:+TraceClassLoading",
      "-XX:+LogCompilation",
      "-XX:CompileCommand=print,*InlineCompileBenchmark.*",
      "-XX:CompileCommand=print,*Subtract.*"
})
@State(Scope.Thread)
public class InlineCompileBenchmark {

   private Integer i1 = Integer.valueOf(17);
   private Integer i2 = Integer.valueOf(159343);
   private Long l1 = Long.valueOf(17L);
   private Long l2 = Long.valueOf(159343);

   @Benchmark
   public long subtractInteger() {
      return Subtract.subtract(i1, i2);
   }

   @Benchmark
   public long subtractLong() {
      return Subtract.subtract(
            l1,
            l2);
   }

   @Benchmark
   public long subtractBoth() {
      return Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2);
   }

}
