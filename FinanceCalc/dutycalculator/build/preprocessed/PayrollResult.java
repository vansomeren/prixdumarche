import com.sun.lwuit.Command;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.table.TableLayout;

public class PayrollResult extends Form implements ActionListener {

private Label grossPay,personalRelief,insuranceRelief,payrollTax,nssfDeduction,
        nhifDeduction,totalDeduction,taxableIncome,netPay;
private Label[] textLabel;
private Command back,exit;
private TableLayout.Constraint constraint;
private double gPay,pRelief,iRelief,paye,nssf,nhif,tDeduction,tIncome,nPay;
private Style lStyle;
private Currency[] money;
private String curr;
private PayrollForm pForm;
private FinancialCalc fCalc;

public PayrollResult(FinancialCalc fCalc,String gross, String pension, String insurance){
super("PAYE Calculator Results");
this.fCalc = fCalc;
setGrossPay(gross);
setNssf(pension);
setTaxableIncome();
setPersonalRelief();
setNhif();
setInsuranceRelief(insurance);
setNssf(pension);
setPaye();
setTotalDeduction();
setNetPay();

textLabel = new Label[9];
textLabel[0] = new Label("Gross Pay");
textLabel[1] = new Label("NSSF");
textLabel[2] = new Label("Taxable Income");
textLabel[3] = new Label("PAYE");
textLabel[4] = new Label("NHIF");
textLabel[5] = new Label("Total Deductions");
textLabel[6] = new Label("Personal Relief");
textLabel[7] = new Label("Insurance Relief");
textLabel[8] = new Label("Net Pay");

lStyle = new Style();
lStyle.setFgColor(0xff9900);
lStyle.setBgTransparency(0);
lStyle.setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));




grossPay = new Label(getCurrency(Double.toString(gPay)));
grossPay.setAlignment(RIGHT);
grossPay.setUnselectedStyle(lStyle);

insuranceRelief = new Label(getCurrency(Double.toString(iRelief)));
insuranceRelief.setAlignment(RIGHT);
insuranceRelief.setUnselectedStyle(lStyle);

nssfDeduction = new Label(getCurrency(Double.toString(nssf)));
nssfDeduction.setAlignment(RIGHT);
nssfDeduction.setUnselectedStyle(lStyle);

payrollTax = new Label(getCurrency(Double.toString(paye)));
payrollTax.setAlignment(RIGHT);
payrollTax.setUnselectedStyle(lStyle);

nhifDeduction = new Label(getCurrency(Double.toString(nhif)));
nhifDeduction.setAlignment(RIGHT);
nhifDeduction.setUnselectedStyle(lStyle);

totalDeduction = new Label(getCurrency(Double.toString(tDeduction)));
totalDeduction.setAlignment(RIGHT);
totalDeduction.setStyle(lStyle);

taxableIncome = new Label(getCurrency(Double.toString(tIncome)));
taxableIncome.setAlignment(RIGHT);
taxableIncome.setStyle(lStyle);

netPay = new Label(getCurrency(Double.toString(nPay)));
netPay.setAlignment(RIGHT);
netPay.setStyle(lStyle);

personalRelief = new Label(getCurrency(Double.toString(pRelief)));
personalRelief.setAlignment(RIGHT);
personalRelief.setStyle(lStyle);

back = new Command("Back");
exit = new Command("Exit");
TableLayout layout = new TableLayout(10, 2);
this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[0]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,grossPay);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[1]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,nssfDeduction);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[2]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,taxableIncome);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[3]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,payrollTax);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[4]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,nhifDeduction);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[5]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,totalDeduction);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[6]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,personalRelief);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[7]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,insuranceRelief);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[8]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,netPay);

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
this.addCommandListener(this);
}

public void setGrossPay(String s){
gPay = Double.parseDouble(s);
}
public double getGrossPay(){
return gPay;
}
public void setPersonalRelief(){
pRelief = 1162.00;
}
public double getPersonalRelief(){
return pRelief;
}
public void setInsuranceRelief(String s){
double d;
d = Double.parseDouble(s);
iRelief = (d*0.15);
}
public double getInsuranceRelief(){
return iRelief;
}
public void setTaxableIncome(){
tIncome = gPay - nssf;
}
public double getTaxableIncome(){
return tIncome;
}
public void setNhif(){
if(gPay<1500)
nhif = 30;
else if(gPay<2000)
    nhif=40;
else if(gPay<3000)
    nhif=60;
else if(gPay<4000)
    nhif=80;
else if(gPay<5000)
    nhif=100;
else if(gPay<6000)
    nhif=120;
else if(gPay<7000)
    nhif=140;
else if(gPay<8000)
    nhif=160;
else if(gPay<9000)
    nhif=180;
else if(gPay<10000)
    nhif=200;
else if(gPay<11000)
    nhif=220;
else if(gPay<12000)
    nhif=240;
else if(gPay<13000)
    nhif=260;
else if(gPay<14000)
    nhif=280;
else if(gPay<15000)
    nhif=300;
else if (gPay>15000)
    nhif=320;
}
public double getNhif(){
return nhif;
}
public void setNssf(String s){
nssf = Double.parseDouble(s);
}
public double getNssf(){
return nssf;
}
public void setPaye(){

if(tIncome<10165)
paye = 0.1*tIncome;
else if(tIncome<19741)
    paye = 1016 + (0.15*(tIncome-10165));
else if(tIncome<29317)
    paye = 2542 +(0.2*(tIncome-19741));
else if(tIncome<38893)
    paye = 4367 + (0.25*(tIncome-29317));
else
    paye=6761 + (0.3*(tIncome-38893)) ;

}
public double getPaye(){
return paye;
}
public void setTotalDeduction(){
tDeduction = nhif+paye;
}
public double getTotalDeduction(){
return tDeduction;
}
public void setNetPay(){
if(paye>pRelief){
nPay = tIncome-tDeduction+iRelief+pRelief;
}
else{
nPay = tIncome-nhif;}
}
public double getNetPay(){
return nPay;
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

if (ae.getCommand()==back) {
pForm = new PayrollForm(fCalc);
pForm.showBack();
}
}

}
