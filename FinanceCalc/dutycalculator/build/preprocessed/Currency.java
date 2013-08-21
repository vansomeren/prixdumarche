
public class Currency {
private String money;
String prefix,postfix;
String[] subString;

public Currency(String s){

setMoney(s);
}

public String getMoney(){
    return money;
}
public void setMoney(String s){
    money = "";
    int i,position,index,modulus;
    position = s.indexOf(".");
    index = (int) position/3;
    modulus = getMod(position,3);
    subString = new String[index];

    postfix = s.substring(position+1, position+2);
    prefix = s.substring(0, modulus);
    for(i=0;i<index;i++){
    subString[i]=s.substring((position-(3*(i+1))), position-(3*i));
    
    }
    subString[0] = subString[0] + ".";
    for(i=1;i<index;i++){
    subString[i] = subString[i]+",";
    }
    for(i=(index-1);i>0;i--){
    money = money + subString[i];
    }
    if(modulus!=0)
    money = prefix + ","+ money + postfix;
    else
    money = money + postfix;
    

}
public int getMod(int n,int m){

int remainder = 0;
int i,number,divisor;
number =n;
divisor = m;
while(number>=divisor){
    number = number-divisor;
}
remainder = number;
return remainder;
}
}

