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

public class RetirementForm extends Form implements ActionListener{

private Label[] textLabel;
private TextField[] textField;
private Button calc,reset;
private Image calcImage,resetImage;
private TableLayout.Constraint constraint;
private String value;
private RetirementResult result;
private FinancialCalc fCalc;
private Command home,help,exit;
private MainForm mForm;
private String currentAmount,contribution,employerAmount,rate,period;
private MainHelp mHelp;
private int msg;
private ErrorDialog errDialog;

public RetirementForm(FinancialCalc fCalc){
 super("Pension Calculator");
 this.fCalc = fCalc;
 Display.init(this);
 this.setCyclicFocus(false);
 
 textLabel = new Label[5];
 textLabel[0] = new Label("Currently Saved");
 textLabel[1] = new Label("Contribution");
 textLabel[2] = new Label("Employers Amount");
 textLabel[3] = new Label("Return Rate %");
 textLabel[4] = new Label("Years to Retire");

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
 textField[i].setT9Text("Enter");
 }


Container mainContainer = new Container();
Container buttonContainer = new Container();
BoxLayout bLayout = new BoxLayout(BoxLayout.Y_AXIS);
this.setLayout(bLayout);
GridLayout mgLayout = new GridLayout(5,2);
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
 
 for (int i=0;i<5;i++){
 mainContainer.addComponent(textLabel[i]);
 mainContainer.addComponent(textField[i]);
 }

 buttonContainer.addComponent(calc);
 buttonContainer.addComponent(reset);
 this.addComponent(mainContainer);
 this.addComponent(buttonContainer);
 Font font = Font.createSystemFont
 (Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
 Style s = new Style();
 s.setFont(font);
 s.setBgColor(0x000000);
 s.setFgColor(0xffffff);
 this.setTitleStyle(s);
 this.setSoftButtonStyle(s);
 home = new Command("Home");
 exit = new Command("Exit");
 help = new Command("Help");
 this.addCommand(exit);
 this.addCommand(help);
 this.addCommand(home);
 this.addCommandListener(this);
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

public void focusGained(Component c) {

}

public void focusLost(Component c) {


}

public void setErrMsg(){
msg=0;
for(int i=0;i<5;i++){
if(textField[i].getText().equals("")){
msg=msg+1;
}
}
}

public void actionPerformed(ActionEvent ae) {
double e,c,a,r;
int p;
if(ae.getSource()==calc){
setErrMsg();
if(msg>0){
errDialog = new ErrorDialog("Enter all the values");
}else{
employerAmount = textField[2].getText();
contribution = textField[1].getText();
currentAmount = textField[0].getText();
period = textField[4].getText();
rate = textField[3].getText();
e = Double.parseDouble(employerAmount);
c = Double.parseDouble(contribution);
p = Integer.parseInt(period);
r = Double.parseDouble(rate);

a = Double.parseDouble(currentAmount);
result = new RetirementResult(fCalc,currentAmount,contribution,employerAmount,rate,period);
result.show();
}
}
if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}
if (ae.getCommand()==home) {
fCalc.startApp();
}
if (ae.getCommand()==help) {
mHelp=new MainHelp(fCalc);
mHelp.show();
}
}





}
