// $Id: DoubleTest.java,v 1.1 2004/08/01 18:01:54 Dave Exp $
package net.dclausen.microfloat.test;

import net.dclausen.microfloat.*;

public abstract class DoubleTest extends Test {
  
  public DoubleTest(String name) {
    super(name);
  }

  public abstract long execute(long d1, long d2);
  
  public abstract String toString(long d1, long d2);

  public static final DoubleTest longValue = new DoubleTest("longValue") {
    
    public long execute(long d1, long d2) {
      long r1 = (long) l2d(d1);
      long r2 = MicroDouble.longValue(d1);
      return Math.abs(r1 - r2);
    }
    
    public String toString(long d1, long d2) {
      return "double longValue(" + l2d(d1) 
             + "):\n  " + ((long) l2d(d1)) 
             + "\n  " + MicroDouble.longValue(d1);
    }
    
  };
  
  public static final DoubleTest longToDouble = new DoubleTest("longToDouble") {

    public long execute(long d1, long d2) {
      double r1 = (double) d1;
      double r2 = l2d(MicroDouble.longToDouble(d1));
      return error(r1, r2);
    }
    
    public String toString(long d1, long d2) {
      return "double longToDouble(" + d1
             + "):\n  " + ((double) d1)
             + "\n  " + l2d(MicroDouble.longToDouble(d1));
    }

  };
  
  public static final DoubleTest parseDouble = new DoubleTest("parseDouble") {

    public long execute(long d1, long d2) {
      String s = Double.toString(l2d(d1));
      double r1 = Double.parseDouble(s);
      double r2 = l2d(MicroDouble.parseDouble(s));
      return error(r1, r2);
    }
    
    public String toString(long d1, long d2) {
      double r1 = l2d(d1);
      return "double parseDouble(" + r1
             + "):\n  " + r1
             + "\n  " + l2d(MicroDouble.parseDouble(Double.toString(r1)));
    }

  };
  
  // TODO: random?
  
  /**
   * Note that there is a 
   * <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4831589">bug in Math.rint(double)</a>
   */
  public static final DoubleTest round = new DoubleTest("round") {
    
    public long execute(long d1, long d2) {
      long r1 = Math.round(l2d(d1));
      long r2 = MicroDouble.round(d1);
      return Math.abs(r1 - r2);
    }
    
    public String toString(long d1, long d2) {
      return "double round(" + l2d(d1) 
             + "):\n  " + Math.round(l2d(d1))
             + "\n  " + MicroDouble.round(d1);
    }
    
  };
  
  // TODO: scalbn (this is used by many other functions, so it is probably
  //       not necessary to test it separately
  
  public static final DoubleTest toString = new DoubleTest("toString") {

    public long execute(long d1, long d2) {
      String s1 = Double.toString(l2d(d1));
      String s2 = MicroDouble.toString(d1);
      if (s1.equals(s2)) {
        return 0;
      }
      double r1 = Double.parseDouble(s1);
      double r2 = Double.parseDouble(s2);
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
    
    public String toString(long d1, long d2) {
      String s1 = Double.toString(l2d(d1));
      String s2 = MicroDouble.toString(d1);
      return "double toString(" + s1
             + "):\n  " + s1
             + "\n  " + s2;
    }

  };
  
  public static final DoubleTest stringRoundTrip = new DoubleTest("stringRoundTrip") {

    public long execute(long d1, long d2) {
      String s = MicroDouble.toString(d1);
      long r1 = MicroDouble.parseDouble(s);
      long err = error(l2d(d1), l2d(r1));
      if (err == 0) {
        return 0;
      }
      return Long.MAX_VALUE; // any difference here is considered a serious error
    }
    
    public String toString(long d1, long d2) {
      String s1 = Double.toString(l2d(d1));
      String s2 = MicroDouble.toString(d1);
      return "double toString(" + s1
             + "):\n  " + s1
             + "\n  " + s2;
    }

  };
  
}
