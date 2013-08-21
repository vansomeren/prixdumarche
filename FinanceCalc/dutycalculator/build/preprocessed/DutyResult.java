import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.table.TableLayout;

public class DutyResult extends Form implements ActionListener {

private Label crspValue,customsValue,importDuty,exciseValue,exciseDuty,vatValue,vatDuty,idfDuty,totalDuty;
private Label[] textLabel;
private Command back,exit;
private TableLayout.Constraint constraint;
private double cValue,iDuty,eValue,eDuty,vValue,vDuty,vat,idf,total,depreciation,age;
private Style lStyle;
private Currency[] money;
private String curr;
private DutyForm dForm;
private FinancialCalc fCalc;
private int crsp;

public DutyResult(FinancialCalc fCalc,String c,String a,String title){
super("Duty Calculator Results");

this.fCalc = fCalc;
Display.init(this);
setCRSP(c);
setAge(a);
setDepreciationRate();
setCustomValue();
setImportDuty();
setExciseValue();
setExciseDuty();
setVatValue();
setVat();
setIdf();
setTotal();

textLabel = new Label[9];
textLabel[0] = new Label("CRSP Value");
textLabel[1] = new Label("Customs Value");
textLabel[2] = new Label("Import Duty");
textLabel[3] = new Label("Excise Value");
textLabel[4] = new Label("Excise Duty");
textLabel[5] = new Label("VAT Value");
textLabel[6] = new Label("VAT");
textLabel[7] = new Label("IDF");
textLabel[8] = new Label("Total Duty");
//curr = new String();
lStyle = new Style();
lStyle.setFgColor(0xff9900);
lStyle.setBgTransparency(0);
lStyle.setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));
money = new Currency[9];


crspValue = new Label(getCurrency(c+".00"));
crspValue.setAlignment(RIGHT);
crspValue.setUnselectedStyle(lStyle);

customsValue = new Label(getCurrency(Double.toString(cValue)));
customsValue.setAlignment(RIGHT);
customsValue.setUnselectedStyle(lStyle);

importDuty = new Label(getCurrency(Double.toString(iDuty)));
importDuty.setAlignment(RIGHT);
importDuty.setUnselectedStyle(lStyle);

exciseValue = new Label(getCurrency(Double.toString(eValue)));
exciseValue.setAlignment(RIGHT);
exciseValue.setUnselectedStyle(lStyle);

exciseDuty = new Label(getCurrency(Double.toString(eDuty)));
exciseDuty.setAlignment(RIGHT);
exciseDuty.setUnselectedStyle(lStyle);

vatValue = new Label(getCurrency(Double.toString(vValue)));
vatValue.setAlignment(RIGHT);
vatValue.setUnselectedStyle(lStyle);

vatDuty = new Label(getCurrency(Double.toString(vat)));
vatDuty.setAlignment(RIGHT);
vatDuty.setUnselectedStyle(lStyle);

idfDuty = new Label(getCurrency(Double.toString(idf)));
idfDuty.setAlignment(RIGHT);
idfDuty.setUnselectedStyle(lStyle);

totalDuty = new Label(getCurrency(Double.toString(total)));
totalDuty.setAlignment(RIGHT);
totalDuty.setUnselectedStyle(lStyle);

back = new Command("Back");
exit = new Command("Exit");
TableLayout layout = new TableLayout(10, 2);
this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[0]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,crspValue);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[1]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,customsValue);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[2]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,importDuty);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[3]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,exciseValue);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[4]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,exciseDuty);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[5]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,vatValue);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[6]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,vatDuty);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[7]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,idfDuty);

this.setLayout(layout);
constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,textLabel[8]);

constraint = layout.createConstraint();
constraint.setWidthPercentage(50);
this.addComponent(constraint,totalDuty);
this.addCommand(exit);
this.addCommand(back);
this.addCommandListener(this);

Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setFont(font);
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
this.setTitleStyle(s);
this.setSoftButtonStyle(s);

}

public void setAge(String a){
    age = Double.parseDouble(a);
}
public double getAge(){
    return age;
}
public void setCRSP(String c){
    crsp = Integer.parseInt(c);
    }
public int getCRSP(){
    return crsp;
}
public void setDepreciationRate(){
    double a;
    a = this.getAge();

    if(a<=0.5){
        depreciation = 0.05;}
    else if(a<=1){
        depreciation = 0.1;}
    else if(a<=2){
        depreciation = 0.15;}
     else if(a<=3){
        depreciation = 0.2;}
    else if(a<=4){
        depreciation = 0.3;}
    else if(a<=5){
        depreciation = 0.4;}
    else if(a<=6){
        depreciation = 0.5;}
    else if(a<=7){
        depreciation = 0.6;}
    else if(a<=8){
        depreciation = 0.7;}
    }
public double getDepreciationRate(){
    return depreciation;
}
public void setCustomValue(){
 double d,c;
 c = this.getCRSP();
 d = this.getDepreciationRate();
 cValue = (c/1.25)*(1-d)/(1.25*1.2*1.16);
 
 }

public double getCustomValue(){
    return cValue;
}

public void setExciseValue(){
    double c,i;
    c = this.getCustomValue();
    i = this.getImportDuty();
    eValue = c+i;
    
}

public double getExciseValue(){
 return eValue;   
}

public void setExciseDuty(){
    double e;
    e = this.getExciseValue();
    eDuty = e*0.2;
    
}

public double getExciseDuty(){
return eDuty;
}

public void setVatValue(){
    double ev,ed;
    ev = this.getExciseValue();
    ed = this.getExciseDuty();
    vValue = ev+ed;
    
}
public double getVatValue(){
 return vValue;   
}

public void setImportDuty(){
    double c;
    c = this.getCustomValue();
    iDuty = c*0.25;
 }

public double getImportDuty(){
return iDuty;
}

public void setVat(){
    double v;
    v = this.getVatValue();
    vat = v*0.16;
}

public double getVat(){

    return vat;
}

public void setIdf(){
    double c;
    c = this.getCustomValue();
    idf = 2.25*c/100;
}
public double getIdf(){
    return idf;
}

public void setTotal(){
    double e,i,v,d;
    e = this.getExciseDuty();
    i = this.getImportDuty();
    v = this.getVat();
    d = this.getIdf();
    total = e+i+v+d;
}
public double getTotal(){
    return total;
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

if(ae.getCommand()==back){
dForm = new DutyForm(fCalc);
dForm.show();
}

}

}
