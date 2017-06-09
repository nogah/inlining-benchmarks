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
public class InlineIntBenchmark {

   private Integer i1 = Integer.valueOf(17);
   private Integer i2 = Integer.valueOf(15000);

   @Benchmark
   public long subtractInteger() {
      return Subtract.subtract(i1, i2);
   }

   @Benchmark
   public long noInlineSubtractInteger() {
      return Subtract.subtractNoInline(i1, i2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractInteger() {
      return Subtract.subtractNoInlineAvoidVirtual(i1, i2);
   }

}
