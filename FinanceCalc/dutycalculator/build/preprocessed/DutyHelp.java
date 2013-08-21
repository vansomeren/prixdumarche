
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;


public class DutyHelp extends Form implements ActionListener {


private TextArea help;
private Style lStyle,s;

public DutyHelp(){

help = new TextArea(null,6);
help.getSelectedStyle().setBorder(null);
help.getUnselectedStyle().setBorder(null);
help.setText("Select the Make of the Vehicle,the model and the Year and Month"
       + " when it was Manufactured, then click the Calculate button");

this.addComponent(help);
}

public void actionPerformed(ActionEvent ae) {


}

}
