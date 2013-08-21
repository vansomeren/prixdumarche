
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.table.TableLayout;


public class RetirementResult extends Form implements ActionListener{

private Label totalContribution,totalEarnings,totalPension;
private Label[] textLabel;
private Command back,exit,home;
private TableLayout.Constraint constraint;
private Style lStyle;
private Currency[] money;
private String curr;
private double current,rate,contribution,employer,total;
private int months,period;
private RetirementForm rForm;
private FinancialCalc fCalc;
private MainForm main;

public RetirementResult(FinancialCalc fCalc,String a,String c,String e,String r,String p){
super("Pension Calculator Results");
this.fCalc=fCalc;
current = Double.parseDouble(a);
contribution = Integer.parseInt(c);
rate = Double.parseDouble(r);
rate = rate/1200;
employer = Double.parseDouble(e);
period = Integer.parseInt(p);
period = period*12;
setPension();
textLabel = new Label[4];
textLabel[0] = new Label("Your Total Contribution");
textLabel[1] = new Label("Earnings from Pension Fund");
textLabel[2] = new Label("Total Pension");

lStyle = new Style();
lStyle.setFgColor(0xff9900);
lStyle.setBgTransparency(0);
lStyle.setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));


totalContribution = new Label(getCurrency(Double.toString(contribution*period)));
totalContribution.setAlignment(RIGHT);
totalContribution.setUnselectedStyle(lStyle);

totalEarnings = new Label(getCurrency(Double.toString((total - (contribution*period)))));
totalEarnings.setAlignment(RIGHT);
totalEarnings.setUnselectedStyle(lStyle);

totalPension = new Label(getCurrency(Double.toString(total)));
totalPension.setAlignment(RIGHT);
totalPension.setUnselectedStyle(lStyle);

home = new Command("Home");
back = new Command("Back");
exit = new Command("Exit");

TableLayout layout = new TableLayout(4, 2);
this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[0]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,totalContribution);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[1]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,totalEarnings);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[2]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,totalPension);

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
this.addCommand(exit);
this.addCommand(back);
this.addCommand(home);
this.addCommandListener(this);


}

public double getPension(){
return total;
}

public void setPension(){
total =  ((power(1+rate,period)-1)/rate)*(contribution+employer);
total = total + current;

}

public double power(double M,int N){
double p=1;
int i=0;

for(i=0;i<N;i++){
p = p*M;
}
return p;
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
if (position == -1){
    position = amount.length();
}
index = (int) position/3;
modulus = getMod(position,3);

postfix = amount.substring(position+1, position+2);
prefix = amount.substring(0, modulus);
if(position<3){
    subString = new String[1];
    subString[0] = amount;
}
else {
subString = new String[index];
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
}
return money;
}

public void actionPerformed(ActionEvent ae) {

if(ae.getCommand() == back){
rForm = new RetirementForm(fCalc);
rForm.show();
}
if(ae.getCommand() == exit){
fCalc.destroyApp(true);
}
if(ae.getCommand() == home){
main = new MainForm(fCalc);
main.show();
}

}

}
