import com.sun.lwuit.Command;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.html.DocumentRequestHandler;
import com.sun.lwuit.html.HTMLComponent;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.list.DefaultListCellRenderer;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;

public class AboutForm extends Form implements ActionListener {
private Label[] textLabel;
private FinancialCalc fCalc;
private Image image;
private Label imageLabel;
private Command exit,home;
private HTMLComponent htmlc;

public AboutForm(FinancialCalc fCalc){

super("About");
this.fCalc = fCalc;
setCyclicFocus(false);
HttpRequestHandler handler = new HttpRequestHandler();
htmlc = new HTMLComponent(handler);

htmlc.setPage("file:///about.html");
System.out.println(htmlc.getTitle());
GridLayout gLayout = new GridLayout(1,1);
this.setLayout(gLayout);
//this.getStyle().setBgColor(0xcccccc);
this.addComponent(htmlc);
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

Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setBgColor(0x000000);
s.setFgColor(0xffffff);
s.setFont(font);
this.setSoftButtonStyle(s);
this.setTitleStyle(s);
home = new Command("Home");
exit = new Command("Exit");
this.addCommand(exit);
this.addCommand(home);
this.addCommandListener(this);
}

public void actionPerformed(ActionEvent ae) {
if(ae.getCommand()==exit){
fCalc.destroyApp(true);
}
if(ae.getCommand()==home){
fCalc.startApp();
}
}

}
