// $Id: TestRunner.java,v 1.3 2009/09/19 20:19:57 dave Exp $
package net.dclausen.microfloat.test;

import java.util.*;
import java.text.*;
import java.io.*;

/**
 * Execute TestRunner.main to start a thread which will continuously run
 * tests agains MicroDouble, printing the results to System.out.
 * <p>
 * Some of the tests here use com.imsl.math.Sfun as the reference 
 * implementation (when java.lang.Math is missing the function).  In
 * some cases, Sfun uses a different algorithm than FDLIBM, and the results
 * sometimes differ.  gamma and lgamma are the biggest offenders.  acosh
 * also, to a lesser degree.
 * My code strives to emulate FDLIBM,
 * so I don't consider it an error if my results match FDLIBM but not Sfun.
 * More research is needed to figure out if some of Sfun's algorithms might
 * be better than those in FDLIBM.
 */
public class TestRunner extends Thread {
  
  static {
    // initialize the test classes
    Test t1;
    t1 = UnaryFloatTest.abs;
    t1 = UnaryDoubleTest.abs;
    t1 = BinaryDoubleTest.pow;
  }

  public static void main(String[] args) {
    new TestRunner();
  }

  public static double l2d(long l) {
    return Double.longBitsToDouble(l);
  }
  
  public static long d2l(double d) {
    return Double.doubleToLongBits(d);
  }

  private static final Collection interestingNumbers = new HashSet();;

  private static void addInteresting1(double d) {
    interestingNumbers.add(Double.valueOf(d));
  }
  
  private static void addInteresting0(double d) {
    addInteresting1(d);
    addInteresting1(-1.0 * d);
    addInteresting1(1.0 / d);
    addInteresting1(-1.0 / d);
  }
  
  private static void addInteresting(double d) {
    addInteresting0(d);
    addInteresting0(d + Double.MIN_VALUE);
    addInteresting0(d - Double.MIN_VALUE);
    addInteresting0(d * 1.1);
    addInteresting0(d * 1.2);
    addInteresting0(d * 1.3);
    addInteresting0(d * 1.4);
    addInteresting0(d * 1.5);
    addInteresting0(d * 1.6);
    addInteresting0(d * 1.7);
    addInteresting0(d * 1.8);
    addInteresting0(d * 1.9);
    addInteresting0(d * 2.0);
    addInteresting0(d * 3.0);
    addInteresting0(d * 4.0);
    addInteresting0(d * 5.0);
    addInteresting0(d * 6.0);
    addInteresting0(d * 7.0);
    addInteresting0(d * 8.0);
    addInteresting0(d * 9.0);
    addInteresting0(d * 10.0);
    addInteresting0(d / 1.1);
    addInteresting0(d / 1.2);
    addInteresting0(d / 1.3);
    addInteresting0(d / 1.4);
    addInteresting0(d / 1.5);
    addInteresting0(d / 1.6);
    addInteresting0(d / 1.7);
    addInteresting0(d / 1.8);
    addInteresting0(d / 1.9);
    addInteresting0(d / 2.0);
    addInteresting0(d / 3.0);
    addInteresting0(d / 4.0);
    addInteresting0(d / 5.0);
    addInteresting0(d / 6.0);
    addInteresting0(d / 7.0);
    addInteresting0(d / 8.0);
    addInteresting0(d / 9.0);
    addInteresting0(d / 10.0);
    addInteresting0(Math.sqrt(d));
    addInteresting0(Math.sqrt(d) * 1.5);
    addInteresting0(Math.sqrt(d) * 3.1);
    addInteresting0(d * d);
    addInteresting0(d * d * d);
    addInteresting0(d * d * d * d);
    addInteresting0(d * d * d * d * d);
    addInteresting0(d * d * 1.5);
    addInteresting0(d * d * 3.1);
  }

  static {
    addInteresting(0);
    addInteresting(1);
    addInteresting(2);
    addInteresting(0.1);
    addInteresting(0.01);
    addInteresting(0.001);
    addInteresting(0.0001);
    addInteresting(0.00001);
    addInteresting(Double.NaN);
    addInteresting(Double.POSITIVE_INFINITY);
    addInteresting(Double.MIN_VALUE);
    addInteresting(Double.MAX_VALUE);
    addInteresting(Float.MIN_VALUE);
    addInteresting(Float.MAX_VALUE);
  }

  private static final Double[] fixedArgs;

  static {
    fixedArgs = (Double[]) interestingNumbers.toArray(new Double[0]);
    System.out.println("Interesting numbers: " + Arrays.toString(fixedArgs));
  }

  private final Random r = new Random();
  private int fixedA, fixedB;

  public TestRunner() {
    super("Tester");
    setPriority(MIN_PRIORITY);
    start();
  }

  private class Count {
    
    private long count = 0;
    
    public void increment() {
      count++;
    }

    public String toString() {
      return NumberFormat.getInstance().format(count);
    }
    
  }
  
  public class CountMap extends HashMap {
    
    public void increment(String key) {
      Count count = (Count) get(key);
      if (count == null) {
        count = new Count();
        put(key, count);
      }
      count.increment();
    }

    public void dump(PrintStream out) {
      Iterator i = entrySet().iterator();
      while (i.hasNext()) {
        Map.Entry e = (Map.Entry) i.next();
        out.println("  " + e.getKey() + ": " + e.getValue());
      }
    }
    
  }

  private long nextA() {
    if (fixedA == fixedArgs.length) {
      return r.nextLong();
    }
    return (Double.doubleToRawLongBits(fixedArgs[fixedA]));
  }

  private long nextB() {
    if (fixedB == fixedArgs.length) {
      return r.nextLong();
    }
    long val = Double.doubleToRawLongBits(fixedArgs[fixedB++]);
    if (fixedB == fixedArgs.length) {
      System.out.println("+");
      fixedA++;
      if (fixedA < fixedArgs.length) {
        fixedB = 0;
      } else {
        System.exit(0);
        System.out.println("Done with fixed args");
      }
    }
    return val;
  }
  
  public void run() {
    long t0 = System.currentTimeMillis();
    ArrayList list = new ArrayList();
    list.addAll(Test.testMap.values());
    
    // these tests are not reliable.
    System.out.println("Removing gamma test");
    list.remove(Test.get("gamma"));
    System.out.println("Removing lgamma test");
    list.remove(Test.get("lgamma"));
    System.out.println("Removing factorial test");
    list.remove(Test.get("factorial"));
    
    Test[] tests = (Test[]) list.toArray(new Test[list.size()]);
    CountMap[] errors = new CountMap[3];
    errors[0] = new CountMap();
    errors[1] = new CountMap();
    errors[2] = new CountMap();
    System.out.println("Starting tests...");
    System.out.println("  date: " + new Date());
    System.out.println("  tests: " + list);
    System.out.println();
    Random r = new Random();
    long count = 0;
    MessageFormat fmt = new MessageFormat("{0} tests in {1} min: {2} tests/min");
    while (true) {
      long d1 = nextA();
      long d2 = nextB();
      for (int i=0; i<tests.length; i++) {
        Test test = tests[i];
        try {
          long error = test.execute(d1, d2);
          if (error != 0) {
            int idx = (int) (error - 1);
            if ((idx >= errors.length) || (idx < 0)) {
              idx = errors.length - 1;
            }
            errors[idx].increment(test.getName());
            if (error != 1) {
              System.out.println();
              System.out.println(error + "-ulp error: " + test.toString(d1, d2));
            }
          }
        } catch (Throwable t) {
          t.printStackTrace(System.out);
        }
      }
      count++;
      if ((count % 100000) == 0) {
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println(new Date());
        long t1 = System.currentTimeMillis();
        long min = (t1 - t0) / (60 * 1000);
        long rate = count * 60 * 1000 / (t1 - t0);
        System.out.println(fmt.format(new Object[]{
                           new Long(count), new Long(min), new Long(rate) }));
        for (int i=0; i<errors.length; i++) {

          System.out.println((i + 1) 
                             + ((i == errors.length -1) ? "+ " : "") 
                             + "-ulp errors:");
          errors[i].dump(System.out);
        }
      }
    }
  }
  
}
