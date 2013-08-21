/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.NumberFormatException;
import com.sun.lwuit.*;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.animations.Transition3D;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.events.FocusListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.DateField;
import javax.microedition.midlet.*;

    
/**
 * @author van
 */
public class CarImport extends MIDlet implements ActionListener {
    //SplashScreen
    private Form frmSplash;
    //GridMenu
    private Form frmMainmenu;
    private Button btnNormal;
    private Button btnEx;
    private Button btnVat;
    private Button btnFaq;
    private Command cmdExit;
    private Button btnAbout;
    private Button btnMachinery;
    private Image imgNormal;
    private Image imgEx;
    private Image imgVat;
    private Image imgFaq;
    private Image imgAbout;
    private Image imgMachinery;
    private Command cmdHelp;
    //normal
    private Form frmNormal;
    private TextArea normal;
    //expertraites
    private Form frmEx;
    private TextArea expertraites;
    //FAQ
    private Form frmFAQ;
    private TextArea faq;
    //result
    private Form frmresult;
    //machinery
    private Form frmmachinery;
    private TextArea machinery;
    //dutycalculator
    private Form frmCalc;
    private Label previous;
    private Label months;
    private Label depreciation;
    private Label bodytype;
    private Label vehiclemake;
    private Label vehiclemodel;
    private Label fueltype;
    private Label crsp;
    private CheckBox register;
    private Calendar cal;
    private DateField dt;
    private ComboBox month;
    private String[] mon={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private ComboBox year;
    private String[] years={"2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012"};
    private ComboBox type;
   //rivate String[] types={"P/Up-D/Cab","P/Up-S/Cab","S/Wagon/Sedan","Saloon/Sedan"};
    private ComboBox fuel;
    private String [] fuels={"Petrol","Diesel"};
    private TextField dep;
    private ComboBox make;
    private String[] makes;
    private ComboBox model;
    private String[] models;
        
    
    private TextField sales;
    private String[] sale;
    //results
    private Label customs;
    private Label imports;
    private Label execise;
    private Label vat;
    private Label total;
    private Label idf;
    private Label reg;
    private Label Total;
    private TextField custom;
    private TextField Import;
    private TextField Vat;
    private TextField Excess;
    private TextField totals;
    private TextField Idf;
    private TextField Reg;
    private TextField Totals;
    //about
    private Form frmabout;
    private TextArea about;
    //formulaes
    int depreciate;
    double importduty,excessduty,vatduty,totale,idffee,regfee,Totale,customevalue,retail;
    double Importduty = 25,IDF=2.25,VAT=16,excess=20;
    private Form frmcalc;
    private TextArea calc;
    private TextField current;
    private TextField yea;
    private String response;
    private String[] pString;
    private Form frmHelp;
    private TextArea help;
    
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

models = new String[size-1];
sale = new String[size-1];
for (int j=1;j<size;j++)
{
   first = pString[j].indexOf(",");
   mid = pString[j].indexOf(",",first+1);
   last = pString[j].indexOf(",",mid+1);
   models[j-1] = pString[j].substring(first+1,mid);
   sale[j-1] = pString[j].substring(mid+1,last);
  
}

}

    
    public void startApp() {
     Display.init(this);
        
        try{
            Resources res=Resources.open(("/LWUITtheme.res"));
            UIManager.getInstance().setThemeProps(res.getTheme("LWUITDefault"));
        }
        catch(IOException ex)
        {
            Alert uiManAlert=new Alert("UIManager error",ex.getMessage(),null,AlertType.ERROR);
            uiManAlert.setTimeout(50);
        }
        Splash();
        
        }
     void Splash(){
        frmSplash=new  Form();
        frmSplash.setLayout(new BorderLayout());
        try{
            frmSplash.getStyle().setBgImage(Image.createImage("/ndai.png"));
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        frmSplash.show();
        frmSplash.setTransitionOutAnimator(Transition3D.createCube(1000, true));
      
        
        GridMenu();
          
    }
     void GridMenu(){
         //final String choice=null;
         frmMainmenu=new Form("Importation Procedure");
        frmMainmenu.setLayout(new BorderLayout());
         Container ctnmain=new Container(new GridLayout(3,2));
         btnNormal=new Button();
         btnEx=new Button();
         btnVat=new Button();
         btnFaq=new Button();
         btnAbout=new Button();
         btnMachinery=new Button();
         
         cmdHelp=new Command("Help");
         cmdExit=new Command("Exit");
         try {
             imgNormal=Image.createImage("/meter.png");
             imgEx=Image.createImage("/note.png");
             imgVat=Image.createImage("/file.png");
             imgFaq=Image.createImage("/view.png");
             imgAbout=Image.createImage("/contact.png");
             imgMachinery=Image.createImage("/chat.png");  
         } catch (Exception e) {
             e.printStackTrace();
         }
         btnNormal.setIcon(imgNormal);
         btnEx.setIcon(imgEx);
         btnVat.setIcon(imgVat);
         btnFaq.setIcon(imgFaq);
         btnMachinery.setIcon(imgMachinery);
         btnAbout.setIcon(imgAbout);    
         //adding text
         btnNormal.setText("Normal Vehicle");
         btnNormal.setTextPosition(Button.BOTTOM);
         btnNormal.setAlignment(btnNormal.CENTER);
         btnEx.setText("Expertraits ");
         btnEx.setTextPosition(Button.BOTTOM);
         btnEx.setAlignment(btnEx.CENTER);
         btnVat.setText("Import Duty Calculator");
         btnVat.setTextPosition(Button.BOTTOM);
         btnVat.setAlignment(btnVat.CENTER);
         btnFaq.setText("FAQs");
         btnFaq.setTextPosition(Button.BOTTOM);
         btnFaq.setAlignment(btnFaq.CENTER);
         btnMachinery.setText("Importing Machinery");
         btnMachinery.setTextPosition(Button.BOTTOM);
         btnMachinery.setAlignment(btnMachinery.CENTER);
         btnAbout.setText("About Us");
         btnAbout.setTextPosition(Button.BOTTOM);
         btnAbout.setAlignment(btnAbout.CENTER);
         //adding Components
         ctnmain.addComponent(btnNormal);
         btnNormal.addActionListener(this);
         ctnmain.addComponent(btnEx);
         btnEx.addActionListener(this);
         ctnmain.addComponent(btnMachinery);
         btnMachinery.addActionListener(this);
         ctnmain.addComponent(btnVat);
         btnVat.addActionListener(this);
         ctnmain.addComponent(btnFaq);
         btnFaq.addActionListener(this);
         ctnmain.addComponent(btnAbout);
         btnAbout.addActionListener(this);
         frmMainmenu.addCommand(cmdHelp);
         frmMainmenu.addCommand(new Command("Exit"){       
            public void actionPerformed(ActionEvent e)
                {   
                    notifyDestroyed();                    
                }} );
         frmMainmenu.addComponent(BorderLayout.CENTER,ctnmain);
         frmMainmenu.show();
         }
     void Normal(){
         frmNormal=new Form("Normal Procedure");
         frmNormal.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         
         normal=new TextArea(30,30);
         normal.setText("There are three main  regulations that you must follow when exporting or importing vehicles:\n\n"+
                 "1.Age Limit: Vehicles manufactured before the year 2001 (i.e above 8yrs old) are not allowed for importation into Kenya.\n\n"+
                 "2.Left Hand Drive Vehicles: All left hand drive vehicles are not allowed for registration unless they have a special purpose e.g. Ambulances etc.\n\n"+
                 "3.Road Worthiness: All used vehicles imported into Kenya must be inspected to ensure road worthiness, safety and other requirements.\n\n"+
                 "4.Kenya Revenue Authority shall not accept a Certificate of Export\n"+
                 "issued by Dubai Police or any other authority as a substitute for a foreign Logbook.");
         normal.setConstraint(TextArea.UNEDITABLE);
         frmNormal.addComponent(normal);
           frmNormal.addCommand(new Command("Back"){       
              
            public void actionPerformed(ActionEvent e)
                {   
                    GridMenu();
                }} );
         frmNormal.show();
         
     }
     void Ex(){
         frmEx=new Form("Expertraites");
         frmEx.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         
         expertraites=new TextArea(30,30);
         expertraites.setText("Importing a personal vehicle is highly restricted and time-consuming,"+
                 " but often significantly cheaper than purchasing a vehicle locally.\n\n"+
                 " 1.Expatriates may import their personal vehicle from outside Kenya without paying duty provided that:\n\n"+
                 "• The vehicle is more than three months old but less than 8 years old, and has been registered in the owner’s name for more than three months\n\n"+
                 "• The vehicle is less than 2,500 cc\n\n"+"• The vehicle is not sold within 12 months of import\n\n"+"• The owner is a first-time expatriate in Kenya and has a valid work permit\n\n"+
                 "• The owner is 18 years of age or older\n\n"+"2.When clearing customs, the following documentation is required:\n\n"+"• Import declaration form, clean report, and clean report certificate\n\n"+
                 "• Authority to import vehicle letter\n\n"+"• Log book and service history\n\n"+"• Certificate of registration (from own country) indicating engine and chassis number, first date of registration, engine capacity, and history of vehicle ownership\n\n"
                 +"• Certificate of local value (from own country)\n\n"+"• Passport and valid work permit\n\n"+"• C-15 form (available from customs");
         expertraites.setConstraint(TextArea.UNEDITABLE);
         frmEx.addComponent(expertraites);
         frmEx.addCommand(new Command("Back"){       
              
            public void actionPerformed(ActionEvent e)
                {   
                    GridMenu();
                }} );
         frmEx.show();
         
     }
     void FAQ(){
         frmFAQ=new Form("FAQs");
         frmFAQ.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         faq=new TextArea(30,30);
         faq.setText("We all have a couple of questions for the people involved with bringing in our vehicles in Kenya, here are some of the major Q&As\n\n"+
                 "Q1. What is the procedure and the requirements for importation into Kenya and clearance through customs\n\n"+
                 "Ans: To import any commodity into Kenya, an importer will have to enlist the services of a clearing agent who will process the import\n"+
                 " documentation through Kenya Customs electronically on the Simba 2005 system and clear the goods on your behalf.\n\n"+
                 "An import declaration fee (IDF) of 2.25% of the CIF Value subject to a minimum of Ksh. 5,000.00 is payable.\n\n Customs"+
                 " will assess duty payable depending on the value of the item(s) and the duty rate applicable.\n\n"+
                 "Q2. What is the maximum age of second hand motor vehicles allowed into the country?\n\n"+
                 "Ans: Motor vehicles of over 8 years old are not allowed into Kenya as per the KS 1515:2000\n"+"quality standard by the Kenya Bureau of Standards."+
                 " Kenya Customs enforces this requirement. This year, we are allowing vehicles manufactured in the year 2001 and thereafter.\n\n"+
                 "Q3. How much duty can I expect to pay on importation of a second hand motor vehicle?\n\n"+
                 "Ans: The duty payable on the importation of a motor vehicle is as follows:\n\n"+".Import Duty: 25% of the CIF value of the vehicle\n\n"+
                 ".Excise Duty: 20% of the (CIF value + Import Duty\n\n"+".VAT: 16% of the (CIF value + Import Duty + Excise Duty\n\n"+".IDF: 2.25% of the CIF value or Ksh. 5,000, whichever is higher, is payable.\n\n"+
                 ".CIF – This is the customs value of the vehicle i.e. the Cost, Insurance & Freight paid for the vehicle.\n\n"+
                 ".The CIF value of the vehicle is also deduced from the Current Retail Selling Price (CRSP) of the vehicle\n\n");
         faq.setEditable(false);
         frmFAQ.addComponent(faq);
         frmFAQ.addCommand(new Command("Back"){       
              
            public void actionPerformed(ActionEvent e)
                {   
                    GridMenu();
                }} );
         frmFAQ.show();
     }
     void Calc(){
         frmCalc=new Form("Duty Calculator");
         frmCalc.setLayout(new BorderLayout());
         Container ctncalc=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         //adding labels
     
         months=new Label("Year of Registration");
         depreciation=new Label("Depreciation");
         bodytype=new Label("Body Type");
         vehiclemake=new Label("Vehicle Make");
         vehiclemodel=new Label("Vehicle model");
         fueltype=new Label("Fuel Type");
         crsp=new Label("Current Retail Selling Price");
         //adding checkbox
         register=new CheckBox();
         //combobox
         makes = split(this.readMake(),";");
                  month=new ComboBox(mon);
         year=new ComboBox(years);
         type=new ComboBox();
         fuel=new ComboBox(fuels);
         make=new ComboBox(makes);
         make.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String s =make.getSelectedItem().toString();
                setModel(s);
                for(int i=0;i<models.length;i++){
                model.addItem(models[i]);    
                }
                
                
            }
        });
         model=new ComboBox();
         model.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                int index = model.getSelectedIndex();
                sales.setText(sale[index]);
            }
        });
         //adding textfields
         sales=new TextField();
         yea=new TextField();
         yea.setConstraint(TextField.NUMERIC);
         dep=new TextField();
         dep.setMaxSize(10);
         dep.setEditable(false);
         dep.addFocusListener(new FocusListener() {
          
            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String Current,Year;
                int currentyear=2012;
                int years=0;
                int sum;
                //Current=current.getText();
                Year=(String) year.getSelectedItem();
                //currentyear=Integer.parseInt(Current);
                years=Integer.parseInt(Year);
                sum=currentyear-years;
                if(sum >0 && sum <1){
                    dep.setText("0.1");
                }else if (sum > 1 &&  sum < 2 ){
                    dep.setText("0.15");
             
              }else if(sum>=2 && sum < 3){
                dep.setText("0.2");
              }else if(sum>=3 && sum <4){
             dep.setText("0.3");
             }else if(sum>=4 && sum < 5){
             dep.setText("0.4");
         }else if(sum>=5 && sum< 6){
             dep.setText("0.5");
         }else if(sum>=6 && sum <7){
             dep.setText("0.6");
         }else if(sum>=7 && sum<8){
                dep.setText("0.7");
            }else{
            Dialog.show("Error","Older than 8 years","Ok","Cancel");
         }
            }
                 

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                
            }
         });
         
         //adding components
         ctncalc.addComponent(months);
         ctncalc.addComponent(year);
         ctncalc.addComponent(depreciation);
         ctncalc.addComponent(dep);
         ctncalc.addComponent(vehiclemake);
         ctncalc.addComponent(make);
         ctncalc.addComponent(vehiclemodel);
         ctncalc.addComponent(model);
         ctncalc.addComponent(fueltype);
         ctncalc.addComponent(fuel);
         ctncalc.addComponent(crsp);
         ctncalc.addComponent(sales);
         frmCalc.addCommand(new Command("Calculate"){
             public void actionPerformed(ActionEvent e){
                 if(dep.equals("")||sale.equals("")){
                     Dialog.show("Error", "Please Fill all the fields", "Ok","Cancel");
                 }
                 frmCalc.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, false, 1000));
                 Result();
             }
         });
         frmCalc.addCommand(new Command("Back"){
             public void actionPerformed(ActionEvent e){
                 
                 GridMenu();
             }
         });
         frmCalc.addCommand(new Command("Help"){
             public void actionPerformed(ActionEvent e){
                 Help();
             }
         }
                 );
         
         
         frmCalc.addComponent(BorderLayout.CENTER,ctncalc);
         frmCalc.show();    
     }
     void Result(){
         frmresult=new Form("Import Duty");
         frmresult.setLayout(new BorderLayout());
         Container ctnresult= new Container(new BoxLayout(BoxLayout.Y_AXIS));
         //adding labels
         customs=new Label("Customs Value");
         imports=new Label("import duty");
         execise=new Label("Execise Duty");
         vat=new Label("Vat Duty");
         total=new Label("Total Taxes");
         String query="----------------------------------------------";
         idf=new Label("IDF Fees");
         reg=new Label("Registration Fees");
         String query1="---------------------------------------------";
         Total=new Label("Grand Total");
         //adding TextFields
         custom=new TextField();
         custom.setMaxSize(20);
         
         custom.setEditable(false);
         custom.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                //2342f
                String retail,Dep;
                int Retail;
                float deps;
                int index = model.getSelectedIndex();
                retail=sale[index];                
                Dep=dep.getText();
                Retail=Integer.parseInt(retail);
                deps=Float.parseFloat(Dep);
                if(Retail!=0 ||deps!=0){
                    try{
                        int cduty = (int)((Retail/1.25)*(1-deps)/(1.25*1.2*1.16));
                        System.out.println(cduty);
                        custom.setText(Integer.toString(cduty)); 
                    }catch(NumberFormatException e){
                        
                    }
                   
                }
                
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
         Import=new TextField();
         Import.setMaxSize(20);
         Import.setEditable(false);
         Import.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String customvalue;
                double Customvalue=0.0;
                customvalue=custom.getText();
                Customvalue= Float.parseFloat(customvalue);
                if(Customvalue!=0){
                    Import.setText((Customvalue*0.25)+"");
                }
                
                
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
         Excess=new TextField();
         Excess.setMaxSize(20);
         Excess.setEditable(false);
         Excess.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String cv,id;
                double CV=0;
                double ID=0;
                double sum=0;
                cv=custom.getText();
                id=Import.getText();
                CV=Float.parseFloat(cv);
                ID=Float.parseFloat(id);
                sum=CV+ID;
                if(sum!=0){
                    Excess.setText((sum*0.2)+"");
                    
                }
                
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
         Vat=new TextField();
         Vat.setMaxSize(20);
         Vat.setEditable(false);
         Vat.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String ev,ed,cv,id;
                double EV=0;
                double ED=0;
                double CV=0;
                double ID=0;
                double sum=0;
                ed=Excess.getText();
                cv=custom.getText();
                id=Import.getText();
                ED=Float.parseFloat(ed);
                 CV=Float.parseFloat(cv);
                ID=Float.parseFloat(id);
                sum=CV+ID+ED;
                if (sum!=0 || ED!=0){
                Vat.setText((sum*0.16)+"");    
                
            } 
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
         totals=new TextField();
         totals.setMaxSize(2);
         totals.setEditable(false);
         totals.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                 String ID,ED,VAT;
                double id,ed,vat,sum=0;
                ID=Import.getText();
                ED=Excess.getText();
                VAT=Vat.getText();
                id=Float.parseFloat(ID);
                ed=Float.parseFloat(ED);
                vat=Float.parseFloat(VAT);
                sum=id+ed+vat;
                if(sum!=0){
                    totals.setText((sum)+"");
                }
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
               
               
                
            }
        });
         Idf=new TextField();
         Idf.setMaxSize(20);
         Idf.setEditable(false);
         Idf.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet
                String CV;
                double cv=0;
                CV=custom.getText();
                cv=Float.parseFloat(CV);
                if(cv!=0){
                    Idf.setText((cv*0.0225)+"");
                }
         ;
                
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });        
        
         Reg=new TextField();
         Reg.setMaxSize(20);
         Reg.setEditable(false);
         Totals=new TextField();
         Totals.setMaxSize(20); 
         Totals.setEditable(false);
         Totals.addFocusListener(new FocusListener() {

            public void focusGained(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
                String IDF,TOTAL;
                double idf=0;
                double total=0;
                double sum=0;
                IDF=Idf.getText();
                TOTAL=totals.getText();
                idf=Float.parseFloat(IDF);
                total=Float.parseFloat(TOTAL);
                sum=idf+total;
                if(sum!=0){
                   Totals.setText((sum)+""); 
                }
                
            }

            public void focusLost(Component cmpnt) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
         //adding components
         ctnresult.addComponent(customs);
         ctnresult.addComponent(custom);
         ctnresult.addComponent(imports);
         ctnresult.addComponent(Import);
         ctnresult.addComponent(execise);
         ctnresult.addComponent(Excess);
         ctnresult.addComponent(vat);
         ctnresult.addComponent(Vat);
         
          ctnresult.addComponent(idf);
         ctnresult.addComponent(Idf);
         ctnresult.addComponent(total);
         ctnresult.addComponent(totals);
        
         //ctnresult.addComponent(reg);
         //ctnresult.addComponent(Reg);
         ctnresult.addComponent(Total);
         ctnresult.addComponent(Totals);
         frmresult.addCommand(new Command("Back"){
             public void actionPerformed(ActionEvent e){
                 Calc();
             }
         }
                 );
         frmresult.addComponent(BorderLayout.CENTER,ctnresult);
         frmresult.show();
        
      
        
         
         
     }
     void About(){
         frmabout=new Form("About us");
         about=new TextArea(5,30);
         about.setText("Name:Van Someren Erik Desmond\n\n"+"Email:eriksomeren@gmail.com\n\n"+"Mobile:+254728118599\n\n"+"App Name:Gari Imports\n\n"
                 +"Ver:1.1"+"Year:2012");
         about.setEditable(false);
         frmabout.addComponent(about);
         frmabout.addCommand(new Command("Back"){
             public void actionPerformed(ActionEvent e){
                 GridMenu();
             }
         });
         frmabout.show();
    }
     void machinery(){
         frmmachinery=new Form("Importation of machinery");
         frmmachinery.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //textarea\
         machinery=new TextArea(30,30);
         machinery.setText("Importation machinery into Kenya is Zero rated which means no duty is charged on all kinds of machinery,as long as:\n\n\n"+
                 "1.You have the right documents filled and signed\n\n\n"+
                 "2.The IDF forms are filled at are up to date\n\n\n"+
                 "3.The machinery has met the KRA rules and regulations");
        machinery.setEditable(false);
        frmmachinery.addComponent(machinery);
        frmmachinery.addCommand(new Command("Back"){

            public void actionPerformed(ActionEvent e) {
               GridMenu();
            }});
        frmmachinery.show();
            
        }
     void Help(){
         frmHelp=new Form();
         frmHelp.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         help=new TextArea(30,30);
         help.setText("Select the Make of the Vehicle,the model and the Year and Month\n\n"
       + " when it was Manufactured, then click the Calculate button");
         frmHelp.addComponent(help);
         frmHelp.addCommand(new Command("Back"){
             public void actionPerformed(ActionEvent ae){
                 Calc();
             }
         });
         frmHelp.show();
        
     }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent ae) {
      //  throw new UnsupportedOperationException("Not supported yet.");
        if(ae.getSource()==btnNormal){
            Normal();
        }else if(ae.getSource()==btnEx){
            Ex();
        }else if(ae.getSource()==btnFaq){
            FAQ();
        }else if(ae.getSource()==btnVat){
            Calc();
        }else if(ae.getSource()==btnAbout){
            About();
        }else if(ae.getSource()==btnMachinery){
            machinery();
        }
        }
    }

