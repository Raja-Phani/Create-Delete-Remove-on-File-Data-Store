package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class CRD 
{
	private File f1;
	Scanner sc=new Scanner(System.in);
	
	public CRD(String path)                        //Constructor for file path.It creates itself on C Drive 
	{
		f1=new File(path);
		if(!f1.mkdir())                           
		{
			System.out.println("Can't Create File");
		}
		else
		{
			System.out.println("File Created at "+path);
		}
	}
	
	public void create(String path)                //Write the key,values in a file
	{
		FileWriter fw;
		String key,val="";
		String[] name_val;
		JSONObject obj = new JSONObject();
		System.out.println("Enter File Name: ");
		key=sc.next();							  //Creating File
		System.out.println("Enter the key and value in format key,value. Press e to end : ");
		while(f1.length()<1024*1020*1024)								  //Size of File Shouldn't exceeds 1GB
		{
			val=sc.next();
			if(val.equals('e'))
			{
				break;
			}
			else
			{
				name_val=val.split(",",2);
				try
				{
						obj.put(name_val[0], name_val[1]);
						if(name_val[0].length()<32 && name_val[1].length()<(16*1024*1024))       //Key Should be 32 Chars & Val Should be 16Kb
						{
							System.out.println(name_val[0]+"-"+name_val[1]);
						}
						else
						{
							System.out.println("Size of key and value exceeds");
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						break;
					}
					catch(JSONException e)
					{
						System.out.println("Unable to load JSON");
						e.printStackTrace();
					}		
			}
		}
		try
		{
			fw=new FileWriter(path+"/"+key+".txt");		
			fw.write(obj.toString());
			System.out.println("Json object : \n"+ obj);
			fw.flush();
			fw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void read(String f1_path,String key)               //To Read the File Data
	{
		String path=f1_path+"/"+key+".txt";
		String line=null;
		FileReader fr;
		try
		{
            fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
	        while((line = br.readLine())!=null)
	        {
	        	System.out.println(line);
	            br.close();
	            fr.close();
	        }
	    }
		catch(FileNotFoundException fe)
		{
			System.out.print("File with key" +key+" doesn't exists");
	        fe.printStackTrace();
	    }
		catch(IOException e)
		{
			System.out.print("Error reading file"+path);
	        e.printStackTrace();
	    }
	}
	
	public void delete(String f1_path, String key) 		     //To Delete key 
	{
        String path = f1_path + "/" + key + ".txt";

        try 
        {
            Files.deleteIfExists(Paths.get(path));
            System.out.println("File with key " +key+" deleted successfully");
        } 
        catch(NoSuchFileException e) 
        {
            System.out.println("No such file/directory exists");
        } 
        catch(DirectoryNotEmptyException e) 
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }
    }
	
	public static void main(String[] args) throws IOException
	{
        String def_path = "C:/DCR";        
        String path = "";
        int opt = 0;                           
        String key;
        Scanner scan = new Scanner(System.in);

        System.out.println("Specify the path of data store. give d as input :");
        path = scan.next();
        if(path.equals("d")) path = def_path;

        CRD ds = new CRD(path);

        while(opt!=4)
        {
            System.out.println("Select the option for \n1.Create \n2.Read \n3.Delete \n4.Quit");
            opt = scan.nextInt();
            switch (opt) 
            {
                case 1:
                    ds.create(path);
                    break;
                case 2:
                    System.out.print("Enter the key to read : ");
                    key = scan.next();
                    ds.read(path, key);
                    break;
                case 3:
                    System.out.print("Enter the key to delete file: ");
                    key = scan.next();
                    ds.delete(path, key);
                    break;
                default:
                    System.out.print("Incorrect Option");
                    break;
            }
        }
    }
}


