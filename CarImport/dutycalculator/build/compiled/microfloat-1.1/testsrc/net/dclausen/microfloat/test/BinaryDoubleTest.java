// $Id: BinaryDoubleTest.java,v 1.2 2004/08/03 05:00:13 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;

public abstract class BinaryDoubleTest extends DoubleTest {
  
  public BinaryDoubleTest(String name) {
    super(name);
  }
  
  public final long execute(long d1, long d2) {
    double r1 = doNative(l2d(d1), l2d(d2));
    double r2 = l2d(doMicro(d1, d2));
    return error(r1, r2);
  }
  
  public final String toString(long d1, long d2) {
    return "double " + getName() + "(" + l2d(d1) + ", " + l2d(d2)
           + "):\n  " + doNative(l2d(d1), l2d(d2))
           + "\n  " + l2d(doMicro(d1, d2));
  }
  
  public abstract double doNative(double d1, double d2);

  public abstract long doMicro(long d1, long d2);
  
  public static final BinaryDoubleTest add = new BinaryDoubleTest("add") {
    
    public strictfp double doNative(double d1, double d2) {
      return d1 + d2;
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.add(d1, d2);
    }
    
  };
  
  // TODO: combinations
  
  public static final BinaryDoubleTest compare = new BinaryDoubleTest("compare") {
    
    public double doNative(double d1, double d2) {
      int r = Double.compare(d1, d2);
      if (r < 0) {
        return -1;
      } else if (r == 0) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      int r = MicroDouble.compare(d1, d2);
      if (r < 0) {
        return MicroDouble.NEGATIVE_ONE;
      } else if (r == 0) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };
  
  public static final BinaryDoubleTest div = new BinaryDoubleTest("div") {
    
    public strictfp double doNative(double d1, double d2) {
      return d1 / d2;
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.div(d1, d2);
    }
    
  };
  
  public static final BinaryDoubleTest eq = new BinaryDoubleTest("eq") {
    
    public double doNative(double d1, double d2) {
      if (d1 == d2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      if (MicroDouble.eq(d1, d2)) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };
  
  public static final BinaryDoubleTest ge = new BinaryDoubleTest("ge") {
    
    public double doNative(double d1, double d2) {
      if (d1 >= d2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      if (MicroDouble.ge(d1, d2)) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };
  
  public static final BinaryDoubleTest gt = new BinaryDoubleTest("gt") {
    
    public double doNative(double d1, double d2) {
      if (d1 > d2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      if (MicroDouble.gt(d1, d2)) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };
  
  public static final BinaryDoubleTest ieeeRemainder = new BinaryDoubleTest("IEEEremainder") {
    
    public double doNative(double d1, double d2) {
      return Math.IEEEremainder(d1, d2);
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.IEEEremainder(d1, d2);
    }
    
  };
  
  public static final BinaryDoubleTest le = new BinaryDoubleTest("le") {
    
    public double doNative(double d1, double d2) {
      if (d1 <= d2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      if (MicroDouble.le(d1, d2)) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };
  
  public static final BinaryDoubleTest lt = new BinaryDoubleTest("lt") {
    
    public double doNative(double d1, double d2) {
      if (d1 < d2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      if (MicroDouble.lt(d1, d2)) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };
    
  // TODO: log(x,x)

  public static final BinaryDoubleTest max = new BinaryDoubleTest("max") {
    
    public double doNative(double d1, double d2) {
      return Math.max(d1, d2);
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.max(d1, d2);
    }
    
  };
  
  public static final BinaryDoubleTest min = new BinaryDoubleTest("min") {
    
    public double doNative(double d1, double d2) {
      return Math.min(d1, d2);
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.min(d1, d2);
    }
    
  };
  
  public static final BinaryDoubleTest mod = new BinaryDoubleTest("mod") {
    
    public strictfp double doNative(double d1, double d2) {
      return (d1 % d2);
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.mod(d1, d2);
    }
    
  };

  public static final BinaryDoubleTest mul = new BinaryDoubleTest("mul") {
    
    public strictfp double doNative(double d1, double d2) {
      return (d1 * d2);
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.mul(d1, d2);
    }
    
  };

  public static final BinaryDoubleTest ne = new BinaryDoubleTest("ne") {
    
    public double doNative(double d1, double d2) {
      if (d1 != d2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public long doMicro(long d1, long d2) {
      if (MicroDouble.ne(d1, d2)) {
        return MicroDouble.ZERO;
      } else {
        return MicroDouble.ONE;
      }
    }
    
  };

  // TODO: percentChange
  
  // TODO: percentTotal
  
  // TODO: permutations
  
  /**
   * Note that some changes were made to the Math.pow function in JDK1.5.
   * MicroDouble has been updated to return results consistent with
   * JDK1.5, which means this test may throw some false errors if run
   * on a 1.4 JVM.
   */
  public static final BinaryDoubleTest pow = new BinaryDoubleTest("pow") {
    
    public double doNative(double d1, double d2) {
      return Math.pow(d1, d2);
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.pow(d1, d2);
    }
    
  };
  
  public static final BinaryDoubleTest sub = new BinaryDoubleTest("sub") {
    
    public strictfp double doNative(double d1, double d2) {
      return d1 - d2;
    }
    
    public long doMicro(long d1, long d2) {
      return MicroDouble.sub(d1, d2);
    }
    
  };

  
}
