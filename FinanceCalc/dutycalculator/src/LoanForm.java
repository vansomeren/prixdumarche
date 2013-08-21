
import com.sun.lwuit.Button;
import com.sun.lwuit.ComboBox;
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



public class LoanForm extends Form implements ActionListener {

private Label[] textLabel;
private TextField[] textField;
private Button calc,reset;
private Image calcImage,resetImage;
private GridLayout layout;
private Command home,help,exit;
private ComboBox frequency;
private LoanResult l;
private MainForm mForm;
private FinancialCalc fCalc;
private String months,principal,interest;
private LoanResult lResult;
private LoanHelp lHelp;
private int msg;
private ErrorDialog errDialog;


public LoanForm(FinancialCalc fCalc) {

 super("Loan Calculator");
 Display.init(this);
 this.fCalc = fCalc;
 textLabel = new Label[5];
 textLabel[0] = new Label("Loan Amount");
 textLabel[1] = new Label("Annual Interest %");
 textLabel[2] = new Label("Number of Years");
 textLabel[3] = new Label("Frequency");
 textLabel[4] = new Label("Extra Payment");

 textField = new TextField[5];
 textField[0] = new TextField();
 textField[1] = new TextField();
 textField[2] = new TextField();
 textField[3] = new TextField();
 textField[4] = new TextField();

 for(int i=0;i<5;i++){
 textField[i].setInputMode("123");
 textField[i].setAlignment(RIGHT);
 textField[i].setConstraint(TextField.DECIMAL);
 textField[i].setT9Text("Enter");;
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
 
 frequency = new ComboBox();
 frequency.addItem("Monthly");
 frequency.addItem("Quarterly");
 frequency.addItem("Yearly");

 for (int i=0;i<3;i++){
 mainContainer.addComponent(textLabel[i]);
 mainContainer.addComponent(textField[i]);
 }
 
 
 buttonContainer.addComponent(calc);
 buttonContainer.addComponent(reset);
 this.addComponent(mainContainer);
 this.addComponent(buttonContainer);

Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style fStyle = new Style();
fStyle.setFgColor(0xffffff);
fStyle.setBgColor(0x000000);

fStyle.setFont(font);
this.setSoftButtonStyle(fStyle);
this.setTitleStyle(fStyle);

this.getMenuStyle().setBgColor(0xffffff);
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

home = new Command("Home");
exit = new Command("Exit");
help = new Command("Help");
this.addCommandListener(this);
this.addCommand(exit);
this.addCommand(home);
this.addCommand(help);


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

if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}
if(ae.getCommand()==home){
fCalc.startApp();
}
if(ae.getCommand()==help){
lHelp = new LoanHelp(fCalc);
lHelp.show();
}
if(ae.getSource()== calc){
double d,p,i,n;
String rate;
if(ae.getSource()==calc){
setErrMsg();
if(msg>0){
errDialog = new ErrorDialog("Enter all the values");
}else{

months = textField[2].getText();
principal = textField[0].getText();
interest = textField[1].getText();
i = Double.parseDouble(interest)/1200;
//p = Double.parseDouble(principal);
//principal = Double.toString(p);
rate = Double.toString(i);
lResult = new LoanResult(fCalc,months,principal,rate);
lResult.show();
}
}
}


}
}




