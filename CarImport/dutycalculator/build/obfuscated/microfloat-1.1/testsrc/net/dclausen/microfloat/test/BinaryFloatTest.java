// $Id: BinaryFloatTest.java,v 1.2 2004/08/03 05:00:13 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;

public abstract class BinaryFloatTest extends FloatTest {
  
  private static final int NEGATIVE_ONE = MicroFloat.negate(MicroFloat.ONE);
  
  public BinaryFloatTest(String name) {
    super(name);
  }
  
  public final long execute(int f1, int f2) {
    float r1 = doNative(i2f(f1), i2f(f2));
    float r2 = i2f(doMicro(f1, f2));
    return error(r1, r2);
  }
  
  public final String toString(int f1, int f2) {
    return "float " + getName() + "(" + i2f(f1) + ", " + i2f(f2)
           + "):\n  " + doNative(i2f(f1), i2f(f2))
           + "\n  " + i2f(doMicro(f1, f2));
  }
 
  public abstract float doNative(float f1, float f2);

  public abstract int doMicro(int f1, int f2);
  
  public static final BinaryFloatTest add = new BinaryFloatTest("add(f)") {
    
    public strictfp float doNative(float f1, float f2) {
      return f1 + f2;
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.add(f1, f2);
    }
    
  };
  
  public static final BinaryFloatTest compare = new BinaryFloatTest("compare(f)") {
    
    public float doNative(float f1, float f2) {
      int r = Float.compare(f1, f2);
      if (r < 0) {
        return -1;
      } else if (r == 0) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      int r = MicroFloat.compare(f1, f2);
      if (r < 0) {
        return NEGATIVE_ONE;
      } else if (r == 0) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };
    
  public static final BinaryFloatTest div = new BinaryFloatTest("div(f)") {
    
    public strictfp float doNative(float f1, float f2) {
      return f1 / f2;
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.div(f1, f2);
    }
    
  };
  
  public static final BinaryFloatTest eq = new BinaryFloatTest("eq(f)") {
    
    public float doNative(float f1, float f2) {
      if (f1 == f2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      if (MicroFloat.eq(f1, f2)) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };  
  
  public static final BinaryFloatTest ge = new BinaryFloatTest("ge(f)") {
    
    public float doNative(float f1, float f2) {
      if (f1 >= f2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      if (MicroFloat.ge(f1, f2)) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };  
  
  public static final BinaryFloatTest gt = new BinaryFloatTest("gt(f)") {
    
    public float doNative(float f1, float f2) {
      if (f1 > f2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      if (MicroFloat.gt(f1, f2)) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };  
  
  public static final BinaryFloatTest le = new BinaryFloatTest("le(f)") {
    
    public float doNative(float f1, float f2) {
      if (f1 <= f2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      if (MicroFloat.le(f1, f2)) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };  
  
  public static final BinaryFloatTest lt = new BinaryFloatTest("lt(f)") {
    
    public float doNative(float f1, float f2) {
      if (f1 < f2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      if (MicroFloat.lt(f1, f2)) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };  
  
  public static final BinaryFloatTest max = new BinaryFloatTest("max(f)") {
    
    public float doNative(float f1, float f2) {
      return Math.max(f1, f2);
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.max(f1, f2);
    }
    
  };
  
  public static final BinaryFloatTest min = new BinaryFloatTest("min(f)") {
    
    public float doNative(float f1, float f2) {
      return Math.min(f1, f2);
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.min(f1, f2);
    }
    
  };
  
  public static final BinaryFloatTest mod = new BinaryFloatTest("mod(f)") {
    
    public strictfp float doNative(float f1, float f2) {
      return f1 % f2;
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.mod(f1, f2);
    }
    
  };
  
  public static final BinaryFloatTest mul = new BinaryFloatTest("mul(f)") {
    
    public strictfp float doNative(float f1, float f2) {
      return f1 * f2;
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.mul(f1, f2);
    }
    
  };
  
  public static final BinaryFloatTest ne = new BinaryFloatTest("ne(f)") {
    
    public float doNative(float f1, float f2) {
      if (f1 != f2) {
        return 0;
      } else {
        return 1;
      }
    }
    
    public int doMicro(int f1, int f2) {
      if (MicroFloat.ne(f1, f2)) {
        return MicroFloat.ZERO;
      } else {
        return MicroFloat.ONE;
      }
    }
    
  };  
  
  public static final BinaryFloatTest sub = new BinaryFloatTest("sub(f)") {
    
    public strictfp float doNative(float f1, float f2) {
      return f1 - f2;
    }
    
    public int doMicro(int f1, int f2) {
      return MicroFloat.sub(f1, f2);
    }
    
  };
  
}
