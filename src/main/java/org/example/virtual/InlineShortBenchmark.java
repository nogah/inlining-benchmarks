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
public class InlineShortBenchmark {

   private Short s1 = Short.valueOf((short) 17);
   private Short s2 = Short.valueOf((short) 15000);

   @Benchmark
   public long subtractShort() {
      return Subtract.subtract(s1, s2);
   }

   @Benchmark
   public long noInlineSubtractShort() {
      return Subtract.subtractNoInline(s1, s2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractShort() {
      return Subtract.subtractNoInlineAvoidVirtual(s1, s2);
   }

}
