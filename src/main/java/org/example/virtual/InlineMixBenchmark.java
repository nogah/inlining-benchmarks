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
public class InlineMixBenchmark {

   private Short s1 = Short.valueOf((short) 17);
   private Short s2 = Short.valueOf((short) 15000);
   private Integer i1 = Integer.valueOf(17);
   private Integer i2 = Integer.valueOf(15000);
   private Long l1 = Long.valueOf(17L);
   private Long l2 = Long.valueOf(15000L);

   @Benchmark
   public long subtractShort() {
      return Subtract.subtract(s1, s2);
   }

   @Benchmark
   public long subtractInteger() {
      return Subtract.subtract(i1, i2);
   }

   @Benchmark
   public long subtractLong() {
      return Subtract.subtract(l1, l2);
   }

   @Benchmark
   public long subtractShortInt() {
      return Subtract.subtract(s1, s2) * Subtract.subtract(i1, i2);
   }

   @Benchmark
   public long subtractIntLong() {
      return Subtract.subtract(i1, i2) * Subtract.subtract(l1, l2);
   }

   @Benchmark
   public long subtractShortLong() {
      return Subtract.subtract(s1, s2) * Subtract.subtract(l1, l2);
   }

   @Benchmark
   public long subtractShortIntLong() {
      return Subtract.subtract(i1, i2) * Subtract.subtract(l1, l2) * Subtract.subtract(s1, s2);
   }

   @Benchmark
   public long noInlineSubtractShort() {
      return Subtract.subtractNoInline(s1, s2);
   }

   @Benchmark
   public long noInlineSubtractInteger() {
      return Subtract.subtractNoInline(i1, i2);
   }

   @Benchmark
   public long noInlineSubtractLong() {
      return Subtract.subtractNoInline(l1, l2);
   }

   @Benchmark
   public long noInlineSubtractShortInt() {
      return Subtract.subtractNoInline(s1, s2) * Subtract.subtractNoInline(i1, i2);
   }

   @Benchmark
   public long noInlineSubtractShortLong() {
      return Subtract.subtractNoInline(s1, s2) * Subtract.subtractNoInline(l1, l2);
   }

   @Benchmark
   public long noInlineSubtractIntLong() {
      return Subtract.subtractNoInline(i1, i2) * Subtract.subtractNoInline(l1, l2);
   }

   @Benchmark
   public long noInlineSubtractShortIntLong() {
      return Subtract.subtractNoInline(i1, i2) * Subtract.subtractNoInline(l1, l2) * Subtract.subtractNoInline(s1, s2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractShort() {
      return Subtract.subtractNoInlineAvoidVirtual(s1, s2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractInteger() {
      return Subtract.subtractNoInlineAvoidVirtual(i1, i2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractLong() {
      return Subtract.subtractNoInlineAvoidVirtual(l1, l2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractShortInt() {
      return Subtract.subtractNoInlineAvoidVirtual(s1, s2) * Subtract.subtractNoInlineAvoidVirtual(i1, i2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractShortLong() {
      return Subtract.subtractNoInlineAvoidVirtual(s1, s2) * Subtract.subtractNoInlineAvoidVirtual(l1, l2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractIntLong() {
      return Subtract.subtractNoInlineAvoidVirtual(i1, i2) * Subtract.subtractNoInlineAvoidVirtual(l1, l2);
   }

   @Benchmark
   public long noInlineNoVirtualSubtractShortIntLong() {
      return Subtract.subtractNoInlineAvoidVirtual(i1, i2) * Subtract.subtractNoInlineAvoidVirtual(l1, l2) * Subtract.subtractNoInlineAvoidVirtual(s1, s2);
   }

   @Benchmark
   public long subtractShortIntLong24() {
      // 8 : 8 : 8
      return
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2) +
            Subtract.subtract(i1, i2) + Subtract.subtract(l1, l2) + Subtract.subtract(s1, s2);

   }

   @Benchmark
   public long noInlineNoVirtualSubtractShortIntLong24() {
      // 8 : 8 : 8
      long result = 0L;
      for (int i = 0; i < 8; i++) {
         result += Subtract.subtractNoInlineAvoidVirtual(i1, i2) + Subtract.subtractNoInlineAvoidVirtual(l1, l2) + Subtract.subtractNoInlineAvoidVirtual(s1, s2);
      }

      return result;
   }

   @Benchmark
   public long noInlineSubtractShortIntLongEqualProbability24() {
      // 8 : 8 : 8
      long result = 0L;
      for (int i = 0; i < 8; i++) {
         result += Subtract.subtractNoInline(i1, i2) + Subtract.subtractNoInline(l1, l2) + Subtract.subtractNoInline(s1, s2);
      }

      return result;
   }

   @Benchmark
   public long noInlineSubtractShortIntLongDominantLong24() {
      // 22 : 1 : 1
      long result = 0L;
      for (int i = 0; i < 21; i++) {
         result += Subtract.subtractNoInline(l1, l2);
      }

      return result + Subtract.subtractNoInline(l1, l2) + Subtract.subtractNoInline(s1, s2) + Subtract.subtractNoInline(i1, i2);
   }

   @Benchmark
   public long noInlineSubtractShortIntLongAlmostDominantLong24() {
      // 21 : 2 : 1
      long result = 0L;
      for (int i = 0; i < 21; i++) {
         result += Subtract.subtractNoInline(l1, l2);
      }

      return result + Subtract.subtractNoInline(s1, s2) + Subtract.subtractNoInline(s1, s2) + Subtract.subtractNoInline(i1, i2);
   }

}
