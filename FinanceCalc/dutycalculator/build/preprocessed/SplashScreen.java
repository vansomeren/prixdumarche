
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.plaf.Style;


public class SplashScreen extends Form {

private Image image = null;

public SplashScreen(){
super("Loading .....");
try {
image = Image.createImage("/splash.png");
}
catch(Exception e){}
this.setBgImage(image);
Font font = Font.createSystemFont
(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
 Style s = new Style();
 s.setFont(font);
 s.setBgColor(0xff);
 s.setFgColor(0xffffff);
 this.setTitleStyle(s);

}
}