
import com.sun.lwuit.Command;
import com.sun.lwuit.Component;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.events.FocusListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;

public class MainForm extends Form implements ActionListener,FocusListener{

private FinancialCalc fCalc;
private Image[] items;
private Content[] contents;
private List mainList;
private String[] descriptions ;
private int width;
private MortgageForm mForm;
private LoanForm lForm;
private MainHelp mHelp;
private AboutForm aForm;
private PayrollForm pForm;
private DutyForm dForm;
private RetirementForm rForm;
private Command exit,help,about;
private Label fTitle;
private Image icon;

public MainForm(FinancialCalc fCalc){
super("Financial Calculator");
this.fCalc = fCalc;
Display.init(this);
this.setCyclicFocus(false);
fTitle = new Label();
Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);

mForm = new MortgageForm(fCalc);
pForm = new PayrollForm(fCalc);
dForm = new DutyForm(fCalc);
rForm = new RetirementForm(fCalc);
mHelp = new MainHelp(fCalc);
aForm = new AboutForm(fCalc);
width = this.getWidth();
descriptions = new String[6];
items = new Image[6];
descriptions[0] = "Loan";
descriptions[1] = "Mortgage";
descriptions[2] = "Car Duty";
descriptions[3] = "PAYE";
descriptions[4] = "Pension";
descriptions[5] = "Currency";
try
{

items[0] = Image.createImage("/myarrow.png");
items[1] = Image.createImage("/myarrow.png");
items[2] = Image.createImage("/myarrow.png");
items[3] = Image.createImage("/myarrow.png");
items[4] = Image.createImage("/myarrow.png");
items[5] = Image.createImage("/myarrow.png");
icon = Image.createImage("/payroll.png");
}
catch(java.io.IOException ioe){}
fTitle.setAlignment(CENTER);
fTitle.setIcon(icon);
BorderLayout b = new BorderLayout();
this.setLayout(b);
mainList = getList(items, descriptions);
ContainerListRenderer renderer = new ContainerListRenderer();
mainList.setListCellRenderer(renderer);
mainList.setPreferredSize(new Dimension(width,
mainList.getPreferredH()));
mainList.setSmoothScrolling(false);
mainList.addActionListener(this);

this.addComponent(BorderLayout.NORTH,fTitle);
this.addComponent(BorderLayout.CENTER,mainList);
exit = new Command("Exit");
help = new Command("Help");
about = new Command("About");
this.addCommand(exit);
this.addCommand(help);
this.addCommand(about);


this.getMenuStyle().setBgColor(0xffffff);
DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
this.setMenuCellRenderer(dlcr);
Style mStyle = new Style();
mStyle.setFgColor(0x000000);

mStyle.setFont(font);
dlcr.setStyle(mStyle);
dlcr.setSelectedStyle(mStyle);
Style sbStyle = new Style();
sbStyle.setBgColor(0x000000);
sbStyle.setFgColor(0xffffff);
sbStyle.setFont(font);
dlcr.setSelectedStyle(sbStyle);
UIManager.getInstance().setComponentStyle(
"SoftButton", mStyle);



Style s = new Style();
s.setFont(font);
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
this.setTitleStyle(s);
this.setSoftButtonStyle(s);
this.addCommandListener(this);
this.addFocusListener(this);


}

public List getList(Image[] images, String[] texts)
{

contents = new Content[5];
for(int i = 0; i < 5; i++)
{

contents[i] = new Content(images[i], texts[i]);
}
return new List(contents);
}

public void actionPerformed(ActionEvent ae) {

int index =-1;

if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}

if(ae.getCommand()==help){
mHelp.show();
}

if(ae.getCommand()==about){
aForm.show();
}

if(ae.getSource()== mainList){
index = mainList.getSelectedIndex();

 }

switch(index){

case 0:
lForm = new LoanForm(fCalc);
lForm.show();
break;

case 1:
mForm.show();
break;

case 2:
dForm.show();
break;

case 3:
pForm.show();
break;

case 4:
rForm.show();
break;

}

}
public void focusGained(Component c) {

}

public void focusLost(Component c) {

}
}
