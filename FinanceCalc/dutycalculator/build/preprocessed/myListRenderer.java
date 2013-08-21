
import com.sun.lwuit.Component;
import com.sun.lwuit.Font;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.list.ListCellRenderer;
import com.sun.lwuit.plaf.UIManager;


public class myListRenderer extends Label implements ListCellRenderer {


public myListRenderer()
{

super();

}

public Component getListCellRendererComponent(List list,
Object value, int index, boolean isSelected)
{

Content entry = (Content)value;
setIcon(entry.getIcon());
setText(entry.getText());
this.setTextPosition(LEFT);
setGap(10);
getStyle().setBgTransparency((byte)32);
getStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));

if(isSelected)
{
getStyle().setBgColor(0xffffff);
getStyle().setFgColor(0xffffff);

}
else
{
getStyle().setBgColor(0xffffff);
getStyle().setFgColor(0xff);
getStyle().setBgTransparency(255);
}

return this;
}
//initialize for drawing focus
public Component getListFocusComponent(List list)
{
setText("");
setIcon(null);
getStyle().setBgColor(0xff00);
getStyle().setBgTransparency(255);
return this;
}


}