import java.io.*;
import java.util.*;
import java.lang.Math;
import java.net.*; 

public class parity_remote 
{
	
	public static void main(String args[]) throws IOException,EOFException
	{
		MyClient mc=new MyClient();
	}
	
	
}

class MyClient
{ 
	public String str="",str2="";
	int l,count=0,parity;
	int ascii[];
	String bin[];
	DataInputStream din;
	DataOutputStream dout;
    MyClient() throws UnknownHostException, IOException,EOFException
    {  
    Socket s=new Socket("localhost",3333);  
    din=new DataInputStream(s.getInputStream());  
    dout=new DataOutputStream(s.getOutputStream());  
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
    str=br.readLine();  
    calculate();
    System.out.println("Server: "+str2);
    dout.close();  
    s.close();  
    }
    
    void calculate() throws IOException,EOFException
    {
    	l=str.length();
    	ascii=new int[l];
    	bin=new String[l];
    	for(int i=0;i<l;i++)
		{
			ascii[i]=str.charAt(i);
			bin[i]=Integer.toBinaryString(ascii[i]);
		}
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<bin[i].length();j++)
			{
				if(bin[i].charAt(j)=='1')
					count++;
			}
		}
		dout.writeInt(l);
		dout.flush();
		random_error();
		for(int i=0;i<l;i++)
		{
		 dout.writeUTF(bin[i]);  
		 dout.flush();
		}
		dout.writeInt(count%2);
		dout.flush();
		
		str2=din.readUTF();
    }
    
    public void random_error()
    {
    	int n= (int )(Math.random() * l + 0);
    	int len=bin[n].length();
    	int n1= (int )(Math.random() * len + 0);
    	System.out.println(bin[n]);
		int hh=Character.getNumericValue(bin[n].charAt(n1));
		hh=1-hh;
		StringBuilder myName = new StringBuilder(bin[n]);
		myName.setCharAt(n1, (char)(48+hh));
		bin[n]=myName.toString();
		System.out.println(bin[n]);
    }
}








