package org.example.virtual;

import org.openjdk.jmh.annotations.CompilerControl;

public class Subtract {

   static long subtract(Number n1, Number n2) {
      return n1.longValue() - n2.longValue();
   }

   @CompilerControl(CompilerControl.Mode.DONT_INLINE)
   static long subtractNoInline(Number n1, Number n2) {
      return n1.longValue() - n2.longValue();
   }

   @CompilerControl(CompilerControl.Mode.DONT_INLINE)
   static long subtractNoInlineAvoidVirtual(Number n1, Number n2) {
      if (n1 instanceof Long) {
         return n1.longValue() - n2.longValue();
      }
      return n1.longValue() - n2.longValue();
   }
}
