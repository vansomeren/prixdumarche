
import com.sun.lwuit.Button;
import com.sun.lwuit.ComboBox;
import com.sun.lwuit.Command;
import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.events.FocusListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.table.TableLayout;

public class MortgageForm extends Form implements FocusListener,ActionListener {

private Label[] textLabel;
private TextField[] textField;
private Button calc,reset;
private Image calcImage,resetImage;
private TableLayout.Constraint constraint;
private String value;
private MortgageResult m;
private ComboBox frequency;
private FinancialCalc fCalc;
private Command home,help,exit;
private MainForm mForm;
private String principal,months,interest,deposit;
private MortgageHelp mHelp;
private int msg;
private ErrorDialog errDialog;


public MortgageForm(FinancialCalc fCalc){
 super("Mortgage Calculator");
 this.fCalc = fCalc;
 Display.init(this);

 frequency = new ComboBox();
 frequency.addItem("Monthly");
 frequency.addItem("Quarterly");
 frequency.addItem("Yearly");
 
 textLabel = new Label[5];
 textLabel[0] = new Label("Current Value");
 textLabel[1] = new Label("Deposit Amount");
 textLabel[2] = new Label("Annual Interest %");
 textLabel[3] = new Label("Number of Years");
 textLabel[4] = new Label("Frequency");

 textField = new TextField[5];
 textField[0] = new TextField();
 textField[1] = new TextField();
 textField[2] = new TextField();
 textField[3] = new TextField();
 textField[4] = new TextField();
 
 for(int i=0;i<5;i++){
 textField[i].setInputModeOrder(new String[] {"123"});
 textField[i].setAlignment(RIGHT);
 textField[i].setConstraint(TextField.NUMERIC);
 textField[i].setT9Text("Enter");
 }


Container mainContainer = new Container();
Container buttonContainer = new Container();
BoxLayout bLayout = new BoxLayout(BoxLayout.Y_AXIS);
this.setLayout(bLayout);
GridLayout mgLayout = new GridLayout(8,1);
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
 
 for (int i=0;i<4;i++){
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
 this.addCommandListener(this);
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
}

public void reset(){
for(int i=0;i<5;i++){
textField[i].setText("0");
}
}
public void focusGained(Component c) {
       
}

public void focusLost(Component c) {


}

public void setErrMsg(){
msg=0;
for(int i=0;i<4;i++){
if(textField[i].getText().equals("")){
msg=msg+1;
}
}
}


public void actionPerformed(ActionEvent ae) {
int d,p,n;
double i;
String rate;
if(ae.getSource()==calc){
setErrMsg();
if(msg>0){
errDialog = new ErrorDialog("Enter all the values");
}else{
months = textField[3].getText();
deposit = textField[1].getText();
principal = textField[0].getText();
interest = textField[2].getText();
i = Double.parseDouble(interest)/1200;
d = Integer.parseInt(deposit);
p = Integer.parseInt(principal);
n = p - d;
principal = Integer.toString(n);
rate = Double.toString(i);
m = new MortgageResult(fCalc,months,principal,rate);
m.show();
}
}
if(ae.getSource()==reset){
reset();
}

if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}
if (ae.getCommand()==home) {
fCalc.startApp();
}
if(ae.getCommand()==help){
mHelp = new MortgageHelp(fCalc);
mHelp.show();
}
}

  
}
