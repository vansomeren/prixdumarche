import com.sun.lwuit.Command;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;


public class PayrollHelp extends Form implements ActionListener {

private MainForm mForm;
private FinancialCalc fCalc;
private TextArea help;
private Style lStyle,s;
private Command exit,back;
private PayrollForm pForm;

public PayrollHelp(FinancialCalc fCalc){
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
help.setText("PAYE Calculator is used to calculate payroll deductions according to Kenyan Laws."+
       "\n"+
       "Enter the Gross pay in the First Text Field, the NSSF or other pension contribution in the Second Text Field" +
       "and the Monthly Life Insurance Premium if you have any Life Insuance Policy in the third Text Field. " +
       "These Values will be used to calculate" +
       "the net pay and any statutory deductions"
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
pForm = new PayrollForm(fCalc);
pForm.show();
}

}



}

