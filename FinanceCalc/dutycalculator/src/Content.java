import com.sun.lwuit.Image;

class Content
{

private Image letter;
private String text;

public Content(Image i, String s)
{
letter = i;
text = s;

}
public Image getIcon()
{
return letter;
}
public String getText()
{
return text;
}

}