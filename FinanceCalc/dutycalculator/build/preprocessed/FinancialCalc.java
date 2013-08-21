import com.sun.lwuit.*;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.MIDlet;

public class FinancialCalc extends MIDlet implements Runnable {

private Image[] items;
private Content[] contents;
private List mainList;
private String[] descriptions ;
private MainForm mainForm;
private int width;
private MortgageForm mForm;
private Thread t;
private SplashScreen splashForm;
private LoanForm lForm;
private PayrollForm pForm;
private DutyForm dForm;
private Command exit,help,about;
private RetirementForm rForm;

public FinancialCalc(){
    
}
public void startApp()  {
Display.init(this);

try {
Resources r = Resources.open("/myTheme.res");
UIManager.getInstance().setThemeProps(r.getTheme(
r.getThemeResourceNames()[0])
);
} catch (java.io.IOException e) {
}

mainForm = new MainForm(this);
mainForm.show();

}

public void pauseApp() {

    }

public void destroyApp(boolean unconditional) {
notifyDestroyed();
}

public List getList(Image[] images, String[] texts)
{

contents = new Content[6];
for(int i = 0; i < 6; i++)
{

contents[i] = new Content(images[i], texts[i]);
}
return new List(contents);
}



public void run() {

    splashForm.show();

}

}
