// $Id: FloatTest.java,v 1.1 2004/08/01 18:01:55 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;

public abstract strictfp class FloatTest extends Test {
  
  public FloatTest(String name) {
    super(name);
  }
  
  public final long execute(long d1, long d2) {
    return execute((int) d1, (int) d2);
  }
  
  public final String toString(long d1, long d2) {
    return toString((int) d1, (int) d2);
  }
  
  public abstract long execute(int d1, int d2);
  
  public abstract String toString(int f1, int f2);

  public static final FloatTest intValue = new FloatTest("intValue(f)") {
    
    public long execute(int f1, int f2) {
      int r1 = (int) i2f(f1);
      int r2 = MicroFloat.intValue(f1);
      return Math.abs(r1 - r2);
    }
    
    public String toString(int f1, int f2) {
      return "float intValue(" + i2f(f1) 
             + "):\n  " + ((int) i2f(f1)) 
             + "\n  " + MicroFloat.intValue(f1);
    }
    
  };

  public static final FloatTest intToFloat = new FloatTest("intToFloat") {

    public long execute(int f1, int f2) {
      float r1 = (float) f1;
      float r2 = i2f(MicroFloat.intToFloat(f1));
      return error(r1, r2);
    }
    
    public String toString(int f1, int f2) {
      return "float intToDouble(" + f1
             + "):\n  " + ((float) f1)
             + "\n  " + i2f(MicroFloat.intToFloat(f1));
    }

  };
  
  public static final FloatTest parseFloat = new FloatTest("parseFloat") {

    public long execute(int f1, int f2) {
      String s = Float.toString(i2f(f1));
      float r1 = Float.parseFloat(s);
      float r2 = i2f(MicroFloat.parseFloat(s));
      return error(r1, r2);
    }
    
    public String toString(int f1, int f2) {
      float r1 = i2f(f1);
      return "float parseFloat(" + r1
             + "):\n  " + r1
             + "\n  " + i2f(MicroFloat.parseFloat(Float.toString(r1)));
    }

  };
  
  public static final FloatTest round = new FloatTest("round(f)") {
    
    public long execute(int f1, int f2) {
      int r1 = Math.round(i2f(f1));
      int r2 = MicroFloat.round(f1);
      return Math.abs(r1 - r2);
    }
    
    public String toString(int f1, int f2) {
      return "float round(" + i2f(f1) 
             + "):\n  " + Math.round(i2f(f1))
             + "\n  " + MicroFloat.round(f1);
    }
    
  };
  
  public static final FloatTest toString = new FloatTest("toString(f)") {

    public long execute(int f1, int f2) {
      String s1 = Float.toString(i2f(f1));
      String s2 = MicroFloat.toString(f1);
      if (s1.equals(s2)) {
        return 0;
      }
      float r1 = Float.parseFloat(s1);
      float r2 = Float.parseFloat(s2);
      long err = error(r1, r2);
      if (err == 0) {
        // The 2 strings are different, but similar enough that they 
        // parse back to the same value.  I'm not going to get too picky
        // about the lengths.  For simplicity's sake, consider both
        // results equally valid.
        return 0;
      }
      return err;
    }
    
    public String toString(int f1, int f2) {
      String s1 = Float.toString(i2f(f1));
      String s2 = MicroFloat.toString(f1);
      return "float toString(" + s1
             + "):\n  " + s1
             + "\n  " + s2;
    }

  };

  public static final FloatTest stringRoundTrip = new FloatTest("stringRoundTrip(f)") {

    public long execute(int f1, int f2) {
      String s = MicroFloat.toString(f1);
      int r1 = MicroFloat.parseFloat(s);
      long err = error(i2f(f1), i2f(r1));
      if (err == 0) {
        return 0;
      }
      return Long.MAX_VALUE; // any difference here is considered a serious error
    }
    
    public String toString(int f1, int f2) {
      String s1 = Float.toString(i2f(f1));
      String s2 = MicroFloat.toString(f1);
      return "float toString(" + s1
             + "):\n  " + s1
             + "\n  " + s2;
    }

  };
  
}
