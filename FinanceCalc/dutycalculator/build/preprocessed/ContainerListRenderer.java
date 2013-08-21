
import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Font;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.list.ListCellRenderer;
import com.sun.lwuit.table.TableLayout;

public class ContainerListRenderer extends Container implements ListCellRenderer {



public Component getListCellRendererComponent(List list,Object value, int index, boolean isSelected){

Content entry = (Content) value;

Label Title,Icon;
TableLayout layout = new TableLayout(1,2);
TableLayout.Constraint constraint;
setLayout(layout);

String calc = entry.getText();
Image image = entry.getIcon();
Title = new Label(calc);
Icon = new Label(image);
Icon.setAlignment(RIGHT);
Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
Title.getStyle().setFont(font);
constraint = layout.createConstraint();
constraint.setWidthPercentage(75);
addComponent(constraint,Title);

constraint = layout.createConstraint();
constraint.setWidthPercentage(25);
addComponent(constraint,Icon);



getStyle().setBgTransparency(255);

if(isSelected)
{
getStyle().setBgColor(0xffff99);
getStyle().setFgColor(0xffffff);
Title.getStyle().setBgColor(0xffff99);
Icon.getStyle().setBgColor(0xffff99);

}

else
{

Title.getStyle().setBgColor(0xffffff);
Icon.getStyle().setBgColor(0xffffff);
getStyle().setBgColor(0xffffff);
getStyle().setFgColor(0x000000);
//getStyle().setBgTransparency(255);
}

return this;
}

public Component getListFocusComponent(List list) {

getStyle().setFgColor(0xff9900);
getStyle().setBgTransparency(255);

return this;

}

}
