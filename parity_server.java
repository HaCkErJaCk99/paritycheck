import java.io.*;
import java.util.*;
import java.lang.Math;
import java.net.*; 

public class parity_server 
{
	public static void main(String args[]) throws IOException
	{
		MyServer ms=new MyServer();
	}
	
}

class MyServer
{  
	public StringBuilder str=new StringBuilder();
	int l,count=0,parity;
	int ascii[];
	String bin[];
	DataInputStream din;
	DataOutputStream dout;
	
	MyServer() throws IOException
    {  
    ServerSocket ss=new ServerSocket(3333);  
    Socket s=ss.accept();  
    din=new DataInputStream(s.getInputStream());  
    dout=new DataOutputStream(s.getOutputStream());  
    l=din.readInt();
    bin=new String[l];
    ascii=new int[l];
    for(int i=0;i<l;i++)
    {
    bin[i]=din.readUTF();
    ascii[i]=Integer.parseInt(bin[i], 2);
    char ch=(char)ascii[i];
    str.append(ch);
    }
    for(int i=0;i<l;i++)
	{
		for(int j=0;j<bin[i].length();j++)
		{
			if(bin[i].charAt(j)=='1')
				count++;
		}
	}
    parity=count%2;
    count=din.readInt();
    if(count==parity)
    {
    dout.writeUTF("Data Recevied Succesfully");
    dout.flush(); 
    System.out.println(str);
    }
    else 
    {
    	System.out.println(str);
    	dout.writeUTF("Data Corrupted Retransmit");
        dout.flush();
    }
    
    din.close();  
    s.close();  
    ss.close(); 
    }
	}





