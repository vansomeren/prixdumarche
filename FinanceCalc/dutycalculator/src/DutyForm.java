
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
import com.sun.lwuit.events.SelectionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.list.DefaultListModel;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.table.TableLayout;
import com.sun.lwuit.util.Resources;
import java.io.InputStream;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


public class DutyForm extends Form implements SelectionListener,FocusListener,ActionListener{

private Label[] textLabel;
private TextField[] textField;
private Button calc,reset;
private Image calcImage,resetImage;
private TableLayout.Constraint constraint;
private Command back,exit,help;
private ComboBox make,model,month,year;
private String response;
private String[] pString;
private String[] makeTypes,carModels,carPrices;
private DutyResult d;
private int sYear,eYear;
private int[] yearList;
private String string,price;
private String[] modelTypes;
private String[] monthList = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
private FinancialCalc fCalc;
private MainForm mForm;
private DutyHelp dHelp;
private String[] carTypes;
private DefaultListModel myListModel;

public DutyForm(FinancialCalc fCalc){

super("Vehicle Duty Calculator");

this.fCalc = fCalc;
Display.init(this);
this.setCyclicFocus(false);
Calendar c = Calendar.getInstance();

eYear = c.get(Calendar.YEAR);
sYear = eYear-7;


yearList = new int[eYear-sYear+1];
for(int j=0;j<(eYear-sYear+1);j++){
    yearList[j] = sYear + j;
}
string = this.readMake();
makeTypes=this.split(string,";");

textLabel = new Label[6];
textLabel[0] = new Label("Make");
textLabel[1] = new Label("Model");
textLabel[2] = new Label("Month");
textLabel[3] = new Label("Year");
textLabel[4] = new Label("Fuel Type");
textLabel[5] = new Label("Body Type");


textField = new TextField[6];
textField[0] = new TextField();
textField[1] = new TextField();
textField[2] = new TextField();
textField[3] = new TextField();
textField[4] = new TextField();
textField[5] = new TextField();

for(int i=0;i<6;i++){
textField[i].setConstraint(TextField.DECIMAL);
textField[i].setAlignment(RIGHT);
textField[i].setT9Text("Enter");
}
String[] str={"Select"};
make = new ComboBox();
model = new ComboBox();
month = new ComboBox();
year = new ComboBox();
model.addItem(str[0]);
make.addSelectionListener(this);
model.addSelectionListener(this);
month.addSelectionListener(this);
year.addSelectionListener(this);

make.addFocusListener(this);
month.addFocusListener(this);
year.addFocusListener(this);
model.addFocusListener(this);

make.addActionListener(this);
month.addActionListener(this);
year.addActionListener(this);
model.addActionListener(this);
for(int j=0;j<makeTypes.length;j++){
make.addItem(makeTypes[j]);
}
for(int j=0;j<monthList.length;j++){
    month.addItem(monthList[j]);
}
for(int j=0;j<yearList.length;j++){
    year.addItem(Integer.toString(yearList[j]));
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
Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setFont(font);
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
this.setTitleStyle(s);
this.setSoftButtonStyle(s);

mainContainer.addComponent(textLabel[0]);
mainContainer.addComponent(make);

mainContainer.addComponent(textLabel[1]);
mainContainer.addComponent(model);

mainContainer.addComponent(textLabel[2]);
mainContainer.addComponent(month);

mainContainer.addComponent(textLabel[3]);
mainContainer.addComponent(year);

buttonContainer.addComponent(calc);
buttonContainer.addComponent(reset);

this.addComponent(mainContainer);
this.addComponent(buttonContainer);

dHelp = new DutyHelp();
exit = new Command("Exit");
back = new Command("Back");
help = new Command("Help");
this.addCommandListener(this);
this.addCommand(exit);
this.addCommand(back);
this.addCommand(help);

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

}

    

public void focusGained(Component c) {

String mString;
int alpha=0,omega=0;

if(c==model){
mString=make.getSelectedItem().toString();
setModel(mString);
myListModel = new DefaultListModel(carModels);
model.setModel(myListModel);
/*
for(int j=1;j<carModels.length;j++){
    model.addItem(carModels[j]);

}
*/
}
}

public void focusLost(Component c) {
        
 }

public void selectionChanged(int arg0, int arg1) {
        
 }

public void setModel(String make){
String s,t;
int first,mid,last,end,alpha,omega;
response = this.readModel();
alpha=response.indexOf(make+":");
omega=response.indexOf(make+":", alpha+1);
s=response.substring(alpha,omega);

alpha=s.indexOf(":");
t=s.substring(alpha+1);

pString = split(t,";");

int size = pString.length-1;
carTypes = new String[size-1];
carModels = new String[size-1];
carPrices = new String[size-1];
for (int j=1;j<size;j++)
{
   first = pString[j].indexOf(",");
   mid = pString[j].indexOf(",",first+1);
   last = pString[j].indexOf(",",mid+1);
   carModels[j-1] = pString[j].substring(first+1,mid);
   carPrices[j-1] = pString[j].substring(mid+1,last);
  
}

}


public void setNull(){

response = null;
pString = null;
carTypes = null;
carModels = null;
carPrices = null;


}

public void actionPerformed(ActionEvent ae) {
Calendar cal = Calendar.getInstance();
int index,curMonth,curYear,mfgMonth,mfgYear;
double numOfYears;

int carAge;
if(ae.getSource()==calc){
mfgYear = Integer.parseInt(year.getSelectedItem().toString());
mfgMonth = month.getSelectedIndex();
curMonth = cal.get(Calendar.MONTH)+12;
curYear = cal.get(Calendar.YEAR)-1;
numOfYears = ((curYear-mfgYear)*12)+(curMonth-mfgMonth);
numOfYears=numOfYears/12;


index = model.getSelectedIndex();
price = carPrices[index+1];
d = new DutyResult(fCalc,price,Double.toString(numOfYears),carModels[index]);
d.show();
setNull();
}

if(ae.getSource()==reset){

for(int i=0;i<5;i++){
textField[i].setText("0");
}
}
if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}
if (ae.getCommand()==back) {
fCalc.startApp();
}
if (ae.getCommand()==help) {
dHelp = new DutyHelp();
dHelp.show();
}
}

private String readMake(){

InputStream is = getClass().getResourceAsStream("make.txt");
StringBuffer sb = new StringBuffer();
try{
int chars, i = 0;
while ((chars = is.read()) != -1){
sb.append((char) chars);
}

}catch (Exception e){}

return sb.toString();
}

private String[] split(String string, String separator) {
Vector nodes = new Vector();

int index = string.indexOf(separator);
while(index>=0) {
nodes.addElement( string.substring(0, index) );
string = string.substring(index+separator.length());
index = string.indexOf(separator);
}
// Get the last node
nodes.addElement( string );

// Create splitted string array
String[] result = new String[ nodes.size() ];
if( nodes.size()>0 ) {
for(int loop=0; loop<nodes.size(); loop++)
{
result[loop] = (String)nodes.elementAt(loop);

}

}

return result;
}

public String readModel(){

String s;
InputStream is = getClass().getResourceAsStream("car.csv");
StringBuffer sb = new StringBuffer();
try{
int chars, i = 0;
while ((chars = is.read()) != -1){
sb.append((char) chars);
}

}catch (Exception e){}

s = sb.toString();
return s;
}



}
