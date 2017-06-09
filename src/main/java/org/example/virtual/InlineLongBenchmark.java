package org.example.virtual;

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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
@State(Scope.Thread)
public class InlineLongBenchmark {

   private Long l1 = Long.valueOf(17L);
   private Long l2 = Long.valueOf(15000L);

   @Benchmark
   public long subtractLong() {
      return Subtract.subtract(l1, l2);
   }

   @Benchmark
   public long noInlineSubtractLong() {
      return Subtract.subtractNoInline(l1, l2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractLong() {
      return Subtract.subtractNoInlineAvoidVirtual(l1, l2);
   }

}
