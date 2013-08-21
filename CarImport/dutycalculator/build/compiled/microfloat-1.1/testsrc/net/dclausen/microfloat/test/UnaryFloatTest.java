// $Id: UnaryFloatTest.java,v 1.1 2004/08/01 18:01:56 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;

public abstract strictfp class UnaryFloatTest extends FloatTest {
  
  public UnaryFloatTest(String name) {
    super(name);
  }
  
  public final long execute(int f1, int f2) {
    float r1 = doNative(i2f(f1));
    float r2 = i2f(doMicro(f1));
    return error(r1, r2);
  }
  
  public final String toString(int f1, int f2) {
    return "float " + getName() + "(" + i2f(f1) 
           + "):\n  " + doNative(i2f(f1))
           + "\n  " + i2f(doMicro(f1));
  }
  
  public abstract float doNative(float f);

  public abstract int doMicro(int f);
  
  public static final UnaryFloatTest abs = new UnaryFloatTest("abs(f)") {
    
    public float doNative(float f) {
      return Math.abs(f);
    }
    
    public int doMicro(int f) {
      return MicroFloat.abs(f);
    }
    
  };

  public static final UnaryFloatTest byteValue = new UnaryFloatTest("byteValue(f)") {

    public float doNative(float f) {
      return (byte) f;
    }
    
    public int doMicro(int f) {
      return MicroFloat.intToFloat(MicroFloat.byteValue(f));
    }
    
  };  
  
  public static final UnaryFloatTest ceil = new UnaryFloatTest("ceil(f)") {
    
    public float doNative(float f) {
      return (float) Math.ceil(f);
    }
    
    public int doMicro(int f) {
      return MicroFloat.ceil(f);
    }
    
  };
    
  public static final UnaryFloatTest floor = new UnaryFloatTest("floor(f)") {
    
    public float doNative(float f) {
      return (float) Math.floor(f);
    }
    
    public int doMicro(int f) {
      return MicroFloat.floor(f);
    }
    
  };
    
  public static final UnaryFloatTest isInfinite = new UnaryFloatTest("isInfinite(f)") {
    
    public float doNative(float f) {
      return Float.isInfinite(f) ? 1 : 0;
    }
    
    public int doMicro(int f) {
      return MicroFloat.isInfinite(f) ? MicroFloat.ONE : MicroFloat.ZERO;
    }
    
  };
  
  public static final UnaryFloatTest isNaN = new UnaryFloatTest("isNaN(f)") {
    
    public float doNative(float f) {
      return Float.isNaN(f) ? 1 : 0;
    }
    
    public int doMicro(int f) {
      return MicroFloat.isNaN(f) ? MicroFloat.ONE : MicroFloat.ZERO;
    }
    
  };
  
  public static final UnaryFloatTest isZero = new UnaryFloatTest("isZero(f)") {
    
    public float doNative(float f) {
      return (f == 0f) ? 1 : 0;
    }
    
    public int doMicro(int f) {
      return MicroFloat.isZero(f) ? MicroFloat.ONE : MicroFloat.ZERO;
    }
    
  };
  
  public static final UnaryFloatTest negate = new UnaryFloatTest("negate(f)") {
    
    public float doNative(float f) {
      return -f;
    }
    
    public int doMicro(int f) {
      return MicroFloat.negate(f);
    }
    
  };
  
  public static final UnaryFloatTest rint = new UnaryFloatTest("rint(f)") {
    
    public float doNative(float f) {
      return (float) Math.rint(f);
    }
    
    public int doMicro(int f) {
      return MicroFloat.rint(f);
    }
    
  };
  
  public static final UnaryFloatTest shortValue = new UnaryFloatTest("shortValue(f)") {

    public float doNative(float f) {
      return (short) f;
    }
    
    public int doMicro(int f) {
      return MicroFloat.intToFloat(MicroFloat.shortValue(f));
    }
    
  };  
  
  // TODO: truncate
  
}
