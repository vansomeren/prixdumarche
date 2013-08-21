// $Id: Test.java,v 1.2 2004/08/03 05:00:14 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;
import java.util.*;

public abstract strictfp class Test {
  
  public static final HashMap testMap = new HashMap();
  
  public static Test get(String name) {
    return (Test) testMap.get(name);
  }
  
  private final String name;
  
  public Test(String name) {
    this.name = name;
    if (testMap.containsKey(name)) {
      throw new IllegalArgumentException(name);
    }
    testMap.put(name, this);
  }
  
  public String getName() {
    return name;
  }
  
  public String toString() {
    return name;
  }

  public abstract long execute(long d1, long d2);
  
  public abstract String toString(long d1, long d2);
  
  public static long d2l(double d) {
    return Double.doubleToLongBits(d);
  }
  
  public static double l2d(long l) {
    return Double.longBitsToDouble(l);
  }

  public static int f2i(float f) {
    return Float.floatToIntBits(f);
  }
  
  public static float i2f(int i) {
    return Float.intBitsToFloat(i);
  }
  
  protected static long error(double d1, double d2) {
    if (Double.compare(d1, d2) == 0) {
      return 0;
    }
    if (Double.isNaN(d1) || Double.isInfinite(d1)
        || Double.isNaN(d2) || Double.isInfinite(d2)) {
          return Long.MAX_VALUE;
    }
    return Math.abs(d2l(d1) - d2l(d2));
  }
  
  protected static long error(float f1, float f2) {
    if (Float.compare(f1, f2) == 0) {
      return 0;
    }
    if (Float.isNaN(f1) || Float.isInfinite(f1)
        || Float.isNaN(f2) || Float.isInfinite(f2)) {
          return Long.MAX_VALUE;
    }
    return Math.abs(f2i(f1) - f2i(f2));
  }
  

}
