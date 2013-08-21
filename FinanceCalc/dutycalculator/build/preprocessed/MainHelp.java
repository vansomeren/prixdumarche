
import com.sun.lwuit.Command;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;


public class MainHelp extends Form implements ActionListener {

private MainForm mForm;
private FinancialCalc fCalc;
private TextArea help;
private Style lStyle,s;
private Command exit,back;

public MainHelp(FinancialCalc fCalc){
super("Help");
this.fCalc = fCalc;
this.setCyclicFocus(false);
help = new TextArea(null,6);
help.getSelectedStyle().setBorder(null);
help.getUnselectedStyle().setBorder(null);
help.setText("FinancialCalc is an application for performing various financial calculations."+"\n"
       + "It is designed for the Kenyan market since these calculations are based on Kenyan Laws."
       + "\n" +
       "1. Loan Calculator is used to calculate the payment for a loan based on constant payments and a constant interest rate."
       + "\n" +"\n"+
       "2. Mortgage Calculator is used to calculate the monthly payment for a mortgage."+
       "\n" +"\n"+
       "3. Car duty calculator is used to calculate all the duties for an imported vehicle according to KRA Laws." +
       "\n" +"\n"+
       "4.PAYE Calculator is used to calculate payroll deductions according to Kenyan Laws."+
       "\n"+"\n"+
       "5. Pension Calculator is used to calculate the amount of pension payable to a retiree based on constant monthly contributions to a pension fund."
       );
Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setFont(font);
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
this.setTitleStyle(s);
this.setSoftButtonStyle(s);
exit = new Command("Exit");
back = new Command("Back");
this.addCommandListener(this);
this.addComponent(help);
this.addCommand(exit);
this.addCommand(back);
}

public void actionPerformed(ActionEvent ae) {

if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}

if(ae.getCommand()==back){
fCalc.startApp();
}

}



}
