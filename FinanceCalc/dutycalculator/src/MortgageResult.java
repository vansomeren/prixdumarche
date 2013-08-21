
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


public class MortgageResult extends Form implements ActionListener{

private Label paymentAmount,totalAmount,interestAmount,
        spacer;
private Label[] textLabel;
private Command back,exit,home;
private TableLayout.Constraint constraint;
private Style lStyle;
private Currency[] money;
private String curr;
private int principal,total,interest;
private double rate,payment;
private int months;
private MortgageForm mForm;
private FinancialCalc fCalc;
private MainForm main;

public MortgageResult(FinancialCalc fCalc,String m,String p,String r){
super("Mortgage Calculator Results");
this.fCalc = fCalc;
principal = Integer.parseInt(p);
months = Integer.parseInt(m);
months=months*12;
rate = Double.parseDouble(r);

setPayment();
setTotal();
setInterest();
textLabel = new Label[7];
textLabel[0] = new Label("Monthly Payment");
textLabel[1] = new Label("Total Payment");
textLabel[2] = new Label("Total Interest");
textLabel[3] = new Label("With Additional Payments");
textLabel[4] = new Label("Interest Saving");
textLabel[5] = new Label("Payoff Earlier by");

lStyle = new Style();
lStyle.setFgColor(0xff9900);
lStyle.setBgTransparency(0);
lStyle.setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));
money = new Currency[3];

paymentAmount = new Label(getCurrency(Double.toString(payment)));
paymentAmount.setAlignment(RIGHT);
paymentAmount.setUnselectedStyle(lStyle);

totalAmount = new Label(getCurrency(total+".00"));
totalAmount.setAlignment(RIGHT);
totalAmount.setUnselectedStyle(lStyle);

interestAmount = new Label(getCurrency(interest+".00"));
interestAmount.setAlignment(RIGHT);
interestAmount.setUnselectedStyle(lStyle);


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
this.addComponent(constraint,paymentAmount);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[1]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,totalAmount);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[2]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,interestAmount);

Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setFont(font);
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
this.setTitleStyle(s);
this.setMenuStyle(s);

this.addCommand(exit);
this.addCommand(back);
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

public double getPayment(){
    return payment;
}

public void setPayment(){
    payment = principal*power((1+rate),months)*rate;
    payment = payment/(power((1+rate),months)-1);

}

public double getTotal(){
    return total;
}

public void setTotal(){
    total = (int) payment*months;
}

public double getInterest(){
    return interest;
}

public void setInterest(){
    interest = total - principal;
}

public double power(double M,int N){
    double p=1;
    int i=0;

    for(i=0;i<N;i++){
    p = p*M;
    }
    return p;
}

public double f(double x, int n)
{
    double m;
        // 170 was determined with trial and error to be the limit
        // that yielded accuracy to 12 digits.
        if (n > 170)
                return 1;

        m = (double)((n-1)/2)+1.0;
        return (n) + m*m*x/f(x, n+1);
}

public double ln(double y)
{
        double x = y-1;
        return x / f(x, 1);
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
    mForm = new MortgageForm(fCalc);
    mForm.show();
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
