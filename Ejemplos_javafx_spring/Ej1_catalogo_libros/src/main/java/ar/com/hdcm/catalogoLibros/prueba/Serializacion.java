package ar.com.hdcm.catalogoLibros.prueba;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializacion {
	
	private static void saveGame(Pojo pojo, String filePath) throws IOException 
    { 
        ObjectOutputStream outputStream = null; 
        try 
        { 
            outputStream = new ObjectOutputStream(new FileOutputStream(filePath)); 
            outputStream.writeObject(pojo); 
        } 
        catch(FileNotFoundException ex) 
        { 
            ex.printStackTrace(); 
        } 
        catch(IOException ex) 
        { 
            ex.printStackTrace(); 
        } 
        finally 
        { 
            try 
            { 
                if(outputStream != null) 
                { 
                    outputStream.flush(); 
                    outputStream.close(); 
                } 
            } 
            catch(IOException ex) 
            { 
                ex.printStackTrace(); 
            } 
        } 
    } 

    public static Pojo loadGame(String filePath) throws IOException, ClassNotFoundException 
    { 
        try 
        { 
            FileInputStream fileIn = new FileInputStream(filePath); 
            ObjectInputStream in = new ObjectInputStream(fileIn); 
            return (Pojo) in.readObject(); 
        } 
        catch(FileNotFoundException ex) 
        { 
            ex.printStackTrace(); 
        } 
        catch(IOException ex) 
        { 
            ex.printStackTrace(); 
        }
        return null;
    } 
	
	public static void main(String[] args) throws IOException {
		
		Pojo pojo = new Pojo();
		pojo.setNombre("hola");
		pojo.listado.add("linea 1");
		pojo.listado.add("linea 2");
		
		saveGame(pojo, "valores.dat");
		
		Pojo pojo2=null;
		try
		{
			pojo2=loadGame("valores.dat") ;
			System.out.println(pojo2.getNombre());
			
			for(String linea: pojo.listado)
				System.out.println(linea);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
