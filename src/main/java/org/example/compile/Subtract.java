package org.example.compile;

import org.openjdk.jmh.annotations.CompilerControl;

public class Subtract {
   @CompilerControl(CompilerControl.Mode.DONT_INLINE)
   static long subtract(Number n1, Number n2) {
      return
            n1.longValue()
            *
            n2.longValue();
   }
}
