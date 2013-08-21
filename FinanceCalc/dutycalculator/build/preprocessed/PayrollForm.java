
import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.table.TableLayout;


public class PayrollForm extends Form implements ActionListener {
private Label[] textLabel;
private TextField[] textField;
private Button calc,reset;
private Image calcImage,resetImage;
private TableLayout.Constraint constraint;
private Command home,exit,help;
private PayrollResult p;
private MainForm mForm;
private FinancialCalc fCalc;
private PayrollHelp pHelp;
private int msg;
private ErrorDialog errDialog;


public PayrollForm(FinancialCalc fCalc){

super("Payroll Calculator");
this.fCalc = fCalc;
this.setCyclicFocus(false);
textLabel = new Label[5];
textLabel[0] = new Label("Gross Salary");
textLabel[1] = new Label("NSSF Contribution");
textLabel[2] = new Label("Insurance Premium");

textField = new TextField[5];
textField[0] = new TextField();
textField[1] = new TextField();
textField[2] = new TextField();

for(int i=0;i<3;i++){
textField[i].setInputMode("123");
textField[i].setAlignment(RIGHT);
textField[i].setConstraint(TextField.DECIMAL);
textField[i].setText("0");
textField[i].setT9Text("Enter");
}

Container mainContainer = new Container();
Container buttonContainer = new Container();
BoxLayout bLayout = new BoxLayout(BoxLayout.Y_AXIS);
this.setLayout(bLayout);
GridLayout mgLayout = new GridLayout(6,1);
GridLayout bgLayout = new GridLayout(1,2);
mainContainer.setLayout(mgLayout);
buttonContainer.setLayout(bgLayout);

try {
     calcImage = Image.createImage("/calc.png");
     resetImage = Image.createImage("/reset.png");
 }
catch(Exception e){}
calc = new Button("Calculate",calcImage);
calc.setAlignment(CENTER);
calc.setTextPosition(BOTTOM);
calc.addActionListener(this);

reset = new Button("Reset",resetImage);
reset.setAlignment(CENTER);
reset.setTextPosition(BOTTOM);
reset.addActionListener(this);

for (int i=0;i<3;i++){
textField[i].setT9Text("Enter");
mainContainer.addComponent(textLabel[i]);
mainContainer.addComponent(textField[i]);
}


buttonContainer.addComponent(calc);
buttonContainer.addComponent(reset);
this.addComponent(mainContainer);
this.addComponent(buttonContainer);

 home = new Command("Home");
 exit = new Command("Exit");
 help = new Command("Help");
 this.addCommand(exit);
 this.addCommand(home);
 this.addCommand(help);

Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setFont(font);
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
this.setTitleStyle(s);
this.setSoftButtonStyle(s);
DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
 this.setMenuCellRenderer(dlcr);
 Style mStyle = new Style();
 mStyle.setFgColor(0x000000);

mStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN
,Font.SIZE_MEDIUM));
dlcr.setStyle(mStyle);
dlcr.setSelectedStyle(mStyle);
Style sbStyle = new Style();
sbStyle.setBgColor(0x000000);
sbStyle.setFgColor(0xffffff);
sbStyle.setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));
dlcr.setSelectedStyle(sbStyle);
UIManager.getInstance().setComponentStyle(
"SoftButton", mStyle);

this.addCommandListener(this);

}
public int getMod(int n,int m){

int remainder = 0;
int i,number,divisor;
number =n;
divisor = m;
while(number>=divisor){
    number = number-divisor;
}
remainder = number;
return remainder;
}

public String getCurrency(String amount){
String money = null;
String[] subString;
String postfix,prefix;
money = "";
int i,position,index,modulus;
position = amount.indexOf(".");
index = (int) position/3;
modulus = getMod(position,3);
subString = new String[index];

postfix = amount.substring(position+1, position+2);
prefix = amount.substring(0, modulus);
for(i=0;i<index;i++){
subString[i]=amount.substring((position-(3*(i+1))), position-(3*i));

}
subString[0] = subString[0] + ".";
for(i=1;i<index;i++){
subString[i] = subString[i]+",";
}
for(i=(index-1);i>=0;i--){
money = money + subString[i];
}
if(modulus!=0)
money = prefix + ","+ money + postfix;
else
money = money + postfix;
return money;
}

public void setErrMsg(){
msg=0;
for(int i=0;i<3;i++){
if(textField[i].getText().equals("")){
msg=msg+1;
}
}
}


public void actionPerformed(ActionEvent ae) {
String gross;
String pension,insurance;

if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}
if(ae.getCommand()==home){
fCalc.startApp();
}
if(ae.getCommand()==help){
pHelp = new PayrollHelp(fCalc);
pHelp.show();
}
if(ae.getSource()==calc){
setErrMsg();
if(msg>0){
errDialog = new ErrorDialog("Enter all the values");
}else{
gross = textField[0].getText();
pension = textField[1].getText();
insurance = textField[2].getText();
p = new PayrollResult(fCalc,gross,pension,insurance);
p.show();
}
}
if(ae.getSource()==reset){

for(int i=0;i<2;i++){
textField[i].setText("0"); 
}

}

if (ae.getCommand()==home) {
fCalc.startApp();
}
    }
}
