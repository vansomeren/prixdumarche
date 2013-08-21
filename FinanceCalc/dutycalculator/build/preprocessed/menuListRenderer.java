import com.sun.lwuit.Component;
import com.sun.lwuit.Font;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.list.ListCellRenderer;

public class menuListRenderer extends Label implements ListCellRenderer{

public menuListRenderer()
{

super();

}


public Component getListCellRendererComponent(List list,
Object value, int index, boolean isSelected)
{

Content entry = (Content)value;
setIcon(entry.getIcon());
setText(entry.getText());
setGap(30);
getStyle().setBgTransparency((byte)32);
getStyle().setFont(Font.createSystemFont(Font.FACE_PROPORTIONAL,
Font.STYLE_PLAIN,Font.SIZE_MEDIUM));

if(isSelected)
{
getStyle().setBgColor(0xffcc00);
getStyle().setFgColor(0xffffff);

}
else
{
getStyle().setBgColor(0xffffff);
getStyle().setFgColor(0x000000);
}

return this;
}
//initialize for drawing focus
public Component getListFocusComponent(List list)
{
setText("");
setIcon(null);
getStyle().setBgColor(0xff);
getStyle().setBgTransparency(100);
return this;
}


}
