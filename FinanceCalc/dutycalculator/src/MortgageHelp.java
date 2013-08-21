import com.sun.lwuit.Command;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;


public class MortgageHelp extends Form implements ActionListener {

private MainForm mForm;
private FinancialCalc fCalc;
private TextArea help;
private Style lStyle,s;
private Command exit,back;
private PayrollForm pForm;
private MortgageForm mgForm;

public MortgageHelp(FinancialCalc fCalc){
super("Help");
this.fCalc = fCalc;
help = new TextArea(null,6);
Font f = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style style = new Style();
style.setFont(f);
help.setUnselectedStyle(style);
help.getSelectedStyle().setBorder(null);
help.getUnselectedStyle().setBorder(null);
help.setText("Mortgage Calculator is used to calculate the monthly payment for a mortgage " +
        "based on constant monthly payment and constant interest."+
       "\n"+
       "Enter the Present Value of the house in the First Text Field, the Deposit amount in the Second Text Field" +
       "the interest rate in the third Text Field. " +
       "and the number of years"
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
mgForm = new MortgageForm(fCalc);
mgForm.show();
}

}



}
