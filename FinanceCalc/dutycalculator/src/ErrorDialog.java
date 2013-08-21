
import com.sun.lwuit.Command;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Font;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;


public class ErrorDialog extends Dialog implements ActionListener {

private Command exit;
private TextArea msgLabel;
private String msg;

public ErrorDialog(String msg){
super("Error");
this.msg = msg;
msgLabel = new TextArea(null,3);
msgLabel.setEditable(false);
msgLabel.getSelectedStyle().setBorder(null);
msgLabel.getUnselectedStyle().setBorder(null);
msgLabel.setText(msg);
this.addComponent(msgLabel);
exit = new Command("Exit");
this.addCommand(exit);
this.addCommandListener(this);
Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Style s = new Style();
s.setBgColor(0xff);
s.setFgColor(0xffffff);
s.setFont(font);
this.setTitleStyle(s);

this.show();
}
public void actionPerformed(ActionEvent ae) {
if(ae.getCommand()==exit){
this.dispose();
}
}

}
