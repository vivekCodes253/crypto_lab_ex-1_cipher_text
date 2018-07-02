/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author 4082
 */
public class crypto1 {

    static int dictsize = 16;
    public static String replace(String a,int inde,char newval)
    {
        String ret;
        //inde = inde+1;
       //System.out.print(inde);
     
        ret = (a.substring(0,inde));
        ret = ret +(newval);
        if(inde+1<a.length());
        ret=ret+(a.substring(inde+1));
        
        return ret;
    }
    
    public static String decrypt(String inp, int key)
    {
        int a ='a';
          for(int i =0; i<inp.length();i++)
        {
            if((int)inp.charAt(i)>='a')
                a ='a';
            else
                a ='A';
            char val = (char)((((((int)inp.charAt(i))-a)-key+26)%26)+a);       // first the ascii, then make it in the 26 format
            if((int)inp.charAt(i)==' ')
            val=' ';
            //int mid = ((((((int)inp.charAt(i))-key)%26)+a));
         
            
            inp = replace(inp,i,val);
        }
          
          return inp;
    }
    
    public static void sop(String s)
    {
        System.out.println(s);
    }
    
    public static String[] getDictionary()
    {
        String[] myDict = new String[30];
        myDict[0] = "hello";
        myDict[1] = "world";
        myDict[2] = "this";
        myDict[3] = "sentence";
        myDict[4] = "word";
        myDict[5] = "computer";
        myDict[6] = "is";
        myDict[7] = "good";
        myDict[8] = "very";
        myDict[9] = "class";
        myDict[10]="best";
        myDict[11]="professor";
        myDict[12]="he";
        myDict[13]="ok";
        myDict[14]="that";
        myDict[15]="fine";
        
        return myDict;
        
    }
    
    public static String[] getWords(String enc)
    {
 
        StringTokenizer t = new StringTokenizer(enc);
        String word ="";
        String[] ret = new String[10];
        int i=0;
        while(t.hasMoreTokens())
        {
            word = t.nextToken();
            ret[i++] = enc;
            //System.out.println(word);
        }
        
    

      /*  int i = 0;
        String[] ret = new String[10];
        while(enc.length()>0)
        {
            try{
                    ret[i++] = enc.substring(0, enc.indexOf(" ")); 
                    enc = enc.substring(enc.indexOf(" ")+1);
                    sop(enc);
                }
            catch(Exception e){
                break;
            }
            sop(ret[i]);
        }
        ret[i++] = enc;
        return ret;*/

        return ret;
        
    }
    
    public static int matches(String[] aw, String b)
    {
        int ct = 0;
       
        String[] bw = getWords(b);
        for(int i=0;i<aw.length;i++)
            for(int j=0;j<bw.length;j++)
                if(aw[i].equals(bw[j]))
                    ct++;
        return ct/2;
    }

    public static Boolean dictmatch(String s,String[] dictioary)
    {
        for(int i = 0  ; i<dictsize;i++)
        {
            if(dictioary[i].equals(s))
                return true;
        }


        return false;
    }
    
    public static void findKey(String enc, String[] dictionary)     //ENC is my encoded string 
    {
      int ct;
       
        String decrypted;
        for(int i = 0; i<26;i++)
        {
            ct = 0;
            StringTokenizer t = new StringTokenizer(enc);
            String word ="";
            String[] ret = new String[10];

            while(t.hasMoreTokens())
            {
                
                word = t.nextToken();
                decrypted = decrypt(word,i);
                if(dictmatch(decrypted,dictionary))
                    ct++;
            
            }



            if(ct>=1)
            {
                sop(" Key could be :"+i);
                break;
            }
                
            
        }

            
    }
    
    public static void main(String[] args) 
    {
        String inp,inpx;
        String[] dictionary = getDictionary();
        
        
        int key,a;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter text:");
        inp = in.nextLine();
        System.out.println("Enter key:");
        key = in.nextInt();
        //assuming only small
        for(int i =0; i<inp.length();i++)
        {
            if((int)inp.charAt(i)>='a')
                a ='a';
            else
                a ='A';
            
            char val = (char)((((((int)inp.charAt(i))-a)+key)%26)+a);
            if((int)inp.charAt(i)==' ')
            val=' ';
            inp = replace(inp,i,val);
        }
       sop("Encrypted value:" + inp);
     
       String decrypted = decrypt(inp,key);
       sop("Decrypted value:" +decrypted);
       
      findKey(inp,dictionary);
    }
    
    
    
    
    
    
}