/*
 * autor: filipuzzi, fernando rafael
 * versión: 20231121
 **/

package ar.com.hdcm.catalogoLibros.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import ar.com.hdcm.catalogoLibros.util.Utils;
import javafx.scene.control.Alert;


public class ConfigFactory 
{
	Catalogo catalogo;
	String fileCatalog=null;
	//
	
	public Catalogo CrearNuevoCatalogo() {
		return catalogo=new Catalogo();
	}
		
	public Catalogo getCatalogo() {
		return catalogo;
	}

	public String getFileCatalog() {
		return fileCatalog;
	}

	public void setFileCatalog(String fileCatalog) {
		this.fileCatalog = fileCatalog;
	}

	//
	
	public Catalogo AbrirCatalogo(File fileCatalogo) throws IOException, ClassNotFoundException 
	{ 
		ObjectInputStream in=null;
		FileInputStream fileIn=null;
		try 
	    {   
			fileIn = new FileInputStream(fileCatalogo); 
	    	in= new ObjectInputStream(fileIn); 
	        catalogo= (Catalogo) in.readObject();
	    }  
	    catch(IOException ex) 
	    { 
	    	throw ex; 
	    }
	    finally
	    {
	    	if(in!=null)
	    		in.close();
	    	if(fileIn!=null)
	    		fileIn.close();
	    }
	    return catalogo;
	} 
	
	public void GuardarCatalogo(File fileCatalogo) throws IOException 
    { 
        ObjectOutputStream outputStream = null; 
        try 
        { 
            outputStream = new ObjectOutputStream(new FileOutputStream(fileCatalogo)); 
            outputStream.writeObject( catalogo); 
            outputStream.flush(); 
        } 
        catch(IOException ex) 
        { 
            throw ex; 
        } 
        finally 
        { 
            if(outputStream != null) 
            { 
               outputStream.close(); 
            }
        } 
    }
	
	public void ImportarEntradas(File fileImportacionEntradas) throws IOException, ClassNotFoundException 
	{ 
		int lineas=0;
		int actualizados=0;
		//FileReader fr=null;
		FileInputStream fr=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
	    try 
	    {   
	    	//fr =new FileReader(fileImportacionEntradas);
	    	fr = new FileInputStream(fileImportacionEntradas);
	    	isr = new InputStreamReader(fr, StandardCharsets.ISO_8859_1);
	    	br=new BufferedReader(isr); 
	    	
	    	String linea;
	    	while((linea=br.readLine())!=null)  
	    	{
	    		String [] campos=linea.split(";",-1);
	    		lineas++;
	    		
	    		boolean actualizado=false;
	    		if(campos.length>=10)
	    		{
		    		String nombre=campos[0];
		    		String URL=campos[1];
		    		String titulo=Utils.SetCadena(campos[2]);
		    		String autor=Utils.SetCadena(campos[3]);
		    		String editorial=Utils.SetCadena(campos[4]);
		    		String mesEdicion=campos[5];
		    		String añoEdicion=campos[6];
		    		String nroEdicion=campos[7];
		    		String ISBN10=Utils.SetCadena(campos[8]);
		    		String ISBN13=Utils.SetCadena(campos[9]);
		    				    		
		    		Entrada entrada=catalogo.BuscarEntrada(nombre);
		    		
		    		if(entrada!=null)
		    		{
		    			Libro libro=entrada.getFicha();
		    			if(libro==null)
		    			{
		    				libro=new Libro();
		    				entrada.setFicha(libro);
		    			}
		    			
		    			libro.setTitulo(titulo);
						libro.setAutor(autor);
						libro.setEditorial(editorial);
						libro.setISBN10(ISBN10);
						libro.setISBN13(ISBN13);
						libro.setActualizado(true);
						
						actualizados++;
						actualizado=true;
		    		}
		    		
		    		if(!actualizado) System.out.println(linea);
	    		}
	    	}
	    }catch(IOException ex) 
	    { 
	        ex.printStackTrace(); 
	        throw ex;
	    }
	    finally
	    {
	    	if(br!=null)
	    		br.close();
	    	if(isr!=null)
				isr.close();
	    	if(fr!=null)
	    		fr.close();
	    }
	    
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Proceso de restauración terminado");
	    alert.setContentText(String.format("Cantidad de registros %d de %d lineas", actualizados, lineas));
	    alert.showAndWait();
	} 
	
	public void ExportarEntradas(File fileExportacionEntradas) throws IOException 
	{
		PrintStream ps = null; 
        try 
        { 
            ps=new PrintStream(new FileOutputStream(fileExportacionEntradas), true);
            
            for(Entrada entrada:catalogo.entradas)
            {
            	if(entrada.getFicha()!=null && entrada.getFicha().isActualizado())
            	{
            		ps.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", 
            							entrada.getNombre(),
            							entrada.getURL(),
            							entrada.getFicha().getTitulo(),
            							entrada.getFicha().getAutor(),
            							entrada.getFicha().getEditorial(),
            							entrada.getFicha().getMesEdicion(),
            							entrada.getFicha().getAñoEdicion(),
            							entrada.getFicha().getNroEdicion(),
            							entrada.getFicha().getISBN10(),
            							entrada.getFicha().getISBN13()
            							);
            	}
            }
            
            ps.flush();
        } 
        catch(IOException ex) 
        { 
            ex.printStackTrace();
            throw ex;
        } 
        finally 
        {            
            if(ps != null) 
            { 
                ps.close(); 
            } 
        }
	}

}
