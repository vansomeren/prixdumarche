// $Id: UnaryDoubleTest.java,v 1.2 2004/08/03 05:00:14 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;
import com.imsl.math.Sfun;

public abstract class UnaryDoubleTest extends DoubleTest {

  public UnaryDoubleTest(String name) {
    super(name);
  }
  
  public final long execute(long d1, long d2) {
    double r1 = doNative(l2d(d1));
    double r2 = l2d(doMicro(d1));
    return error(r1, r2);
  }
  
  public final String toString(long d1, long d2) {
    return "double " + getName() + "(" + l2d(d1) 
           + "):\n  " + doNative(l2d(d1))
           + "\n  " + l2d(doMicro(d1));
  }
  
  public abstract double doNative(double d);

  public abstract long doMicro(long d);

  public static final UnaryDoubleTest abs = new UnaryDoubleTest("abs") {
    
    public double doNative(double d) {
      return Math.abs(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.abs(d);
    }
    
  };
  
  public static final UnaryDoubleTest acos = new UnaryDoubleTest("acos") {
    
    public double doNative(double d) {
      return Math.acos(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.acos(d);
    }
    
  };

  public static final UnaryDoubleTest acosh  = new UnaryDoubleTest("acosh") {
    
    public double doNative(double d) {
      return Sfun.acosh(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.acosh(d);
    }
    
  };
  
  
  public static final UnaryDoubleTest asin = new UnaryDoubleTest("asin") {
    
    public double doNative(double d) {
      return Math.asin(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.asin(d);
    }
    
  };
  
  public static final UnaryDoubleTest asinh = new UnaryDoubleTest("asinh") {
    
    public double doNative(double d) {
      double r = Sfun.asinh(d);
      // there appears to be a sign bug in Sfun.asinh for negative arguments
      // with magnitude <= 1.0
      if ((d < 0) && (d >= -1)) {
        r = -r;
      }
      return r;
    }
    
    public long doMicro(long d) {
      return MicroDouble.asinh(d);
    }
    
  };
  
  public static final UnaryDoubleTest atan = new UnaryDoubleTest("atan") {
    
    public double doNative(double d) {
      return Math.atan(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.atan(d);
    }
    
  };
  
  public static final UnaryDoubleTest atanh = new UnaryDoubleTest("atanh") {
    
    public double doNative(double d) {
      return Sfun.atanh(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.atanh(d);
    }
    
  };
  
  
  public static final UnaryDoubleTest byteValue = new UnaryDoubleTest("byteValue") {

    public double doNative(double d) {
      return (byte) d;
    }
    
    public long doMicro(long d) {
      return MicroDouble.longToDouble(MicroDouble.byteValue(d));
    }
    
  };  
  
  public static final UnaryDoubleTest ceil = new UnaryDoubleTest("ceil") {
    
    public double doNative(double d) {
      return Math.ceil(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.ceil(d);
    }
    
  };
  
  public static final UnaryDoubleTest cos = new UnaryDoubleTest("cos") {
    
    public double doNative(double d) {
      return Math.cos(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.cos(d);
    }
    
  };
  
  public static final UnaryDoubleTest cosh = new UnaryDoubleTest("cosh") {
    
    public double doNative(double d) {
      return Math.cosh(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.cosh(d);
    }
    
  };
  
  public static final UnaryDoubleTest exp = new UnaryDoubleTest("exp") {
    
    public double doNative(double d) {
      return Math.exp(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.exp(d);
    }
    
  };

  public static final UnaryDoubleTest expm1 = new UnaryDoubleTest("expm1") {
    
    public double doNative(double d) {
      return Math.expm1(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.expm1(d);
    }
    
  };
  
  public static final UnaryDoubleTest factorial = new UnaryDoubleTest("factorial") {
    
    public double doNative(double d) {
      return Sfun.fact((int) d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.factorial(MicroDouble.intToDouble(MicroDouble.intValue(d)));
    }
    
  };
  
  public static final UnaryDoubleTest floatValue = new UnaryDoubleTest("floatValue") {
    
    public double doNative(double d) {
      return (float) d;
    }
    
    public long doMicro(long d) {
      return MicroDouble.floatToDouble(MicroDouble.floatValue(d));
    }
    
  };
  
  public static final UnaryDoubleTest floor = new UnaryDoubleTest("floor") {
    
    public double doNative(double d) {
      return Math.floor(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.floor(d);
    }
    
  };
  
  /*
   * TODO: Sfun.gamma is not consistent with FDLIBM gamma.  Need to 
   * find a better reference implementation for testing.
   */
  
  public static final UnaryDoubleTest gamma = new UnaryDoubleTest("gamma") {
    
    public double doNative(double d) {
      return Sfun.gamma(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.gamma(d);
    }
    
  };
  
  // TODO: gradiansToRadians

  public static final UnaryDoubleTest intValue = new UnaryDoubleTest("intValue") {
    
    public double doNative(double d) {
      return (int) d;
    }
    
    public long doMicro(long d) {
      return MicroDouble.intToDouble(MicroDouble.intValue(d));
    }
    
  };
  
  public static final UnaryDoubleTest isInfinite = new UnaryDoubleTest("isInfinite") {
    
    public double doNative(double d) {
      return Double.isInfinite(d) ? 1 : 0;
    }
    
    public long doMicro(long d) {
      return MicroDouble.isInfinite(d) ? MicroDouble.ONE : MicroDouble.ZERO;
    }
    
  };
  
  public static final UnaryDoubleTest isNaN = new UnaryDoubleTest("isNaN") {
    
    public double doNative(double d) {
      return Double.isNaN(d) ? 1 : 0;
    }
    
    public long doMicro(long d) {
      return MicroDouble.isNaN(d) ? MicroDouble.ONE : MicroDouble.ZERO;
    }
    
    
  };

  public static final UnaryDoubleTest isZero = new UnaryDoubleTest("isZero") {
    
    public double doNative(double d) {
      return (d == 0d) ? 1 : 0;
    }
    
    public long doMicro(long d) {
      return MicroDouble.isZero(d) ? MicroDouble.ONE : MicroDouble.ZERO;
    }
    
  };

  /*
   * TODO: Sfun.logGamma is not consistent with FDLIBM lgamma.  Need to 
   * find a better reference implementation for testing.
   */

  public static final UnaryDoubleTest lgamma = new UnaryDoubleTest("lgamma") {
    
    public double doNative(double d) {
      return Sfun.logGamma(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.lgamma(d);
    }
    
  };
  
  public static final UnaryDoubleTest log = new UnaryDoubleTest("log") {
    
    public double doNative(double d) {
      return Math.log(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.log(d);
    }
    
  };
  
  public static final UnaryDoubleTest log10 = new UnaryDoubleTest("log10") {
    
    public double doNative(double d) {
      return Math.log10(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.log10(d);
    }
    
  };

  
  public static final UnaryDoubleTest log1p = new UnaryDoubleTest("log1p") {
    
    public double doNative(double d) {
      return Math.log1p(d);
    }
    
    public long doMicro(long d) {
      return MicroDouble.log1p(d);
    }
    
  };
  
  public static final UnaryDoubleTest negate = new UnaryDoubleTest("negate") {
    
    public double doNative(double d) {
      return d * -1;
    }
    
    public long doMicro(long d) {
      return MicroDouble.negate(d);
    }
    
  };
  
  /**
   * Note that there is a 
   * <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4831589">bug in Math.rint(double)</a>
   */
  public static final UnaryDoubleTest rint = new UnaryDoubleTest("rint") {
    
    public double doNative(double d) {
      return Math.rint(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.rint(d);
    }
    
  };
  
  public static final UnaryDoubleTest shortValue = new UnaryDoubleTest("shortValue") {

    public double doNative(double d) {
      return (short) d;
    }
    
    public long doMicro(long d) {
      return MicroDouble.longToDouble(MicroDouble.shortValue(d));
    }
    
  };  
  
  public static final UnaryDoubleTest sin = new UnaryDoubleTest("sin") {
    
    public double doNative(double d) {
      return Math.sin(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.sin(d);
    }
    
  };
  
  public static final UnaryDoubleTest sinh = new UnaryDoubleTest("sinh") {
    
    public double doNative(double d) {
      return Math.sinh(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.sinh(d);
    }
    
  };
  
  
  public static final UnaryDoubleTest sqrt = new UnaryDoubleTest("sqrt") {
    
    public double doNative(double d) {
      return Math.sqrt(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.sqrt(d);
    }
    
  };
  
  /**
   * Note that some changes were made to the Math.tan function in JDK1.5.
   * MicroDouble has been updated to return results consistent with
   * JDK1.5, which means this test may throw some false errors if run
   * on a 1.4 JVM.
   */
  public static final UnaryDoubleTest tan = new UnaryDoubleTest("tan") {
    
    public double doNative(double d) {
      return Math.tan(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.tan(d);
    }
    
  };
  
  public static final UnaryDoubleTest tanh = new UnaryDoubleTest("tanh") {
    
    public double doNative(double d) {
      return Math.tanh(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.tanh(d);
    }
    
  };
  
  
  public static final UnaryDoubleTest toDegrees = new UnaryDoubleTest("toDegrees") {
    
    public strictfp double doNative(double d) {
      return Math.toDegrees(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.toDegrees(d);
    }
    
  };
  
  // TODO: toGradians
  
  public static final UnaryDoubleTest toRadians = new UnaryDoubleTest("toRadians") {
    
    public double doNative(double d) {
      return Math.toRadians(d); 
    }
    
    public long doMicro(long d) {
      return MicroDouble.toRadians(d);
    }
    
  };
  
  // TODO: truncate

  
}
