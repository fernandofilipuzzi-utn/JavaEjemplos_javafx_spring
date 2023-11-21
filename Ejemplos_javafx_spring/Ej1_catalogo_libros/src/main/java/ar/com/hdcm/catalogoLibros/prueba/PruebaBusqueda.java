package ar.com.hdcm.catalogoLibros.prueba;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PruebaBusqueda {

	public static void main(String[] args) throws IOException {
		
		//listar archivos en java
		System.out.println("hola mundo!");
		
		String path="F:\\data\\workspaces\\workspace-biblioteca\\biblioteca\\book\\libros";
		
		List<String> busqueda = new ArrayList<String>();
		
		File carpeta = new File(path);
	    
		File[] archivos;
	    
	    if(carpeta.exists()) 
	    {
	        if(carpeta.isDirectory()) 
	        {
	            archivos = carpeta.listFiles();
	            for(int i=0; i<archivos.length; i++) 
	            {
	                //if(archivos[i].getName().endsWith(ext))
	                {
	                	//String mimeType=Files.probeContentType(archivos[i].getName());
	                	
	                	String mimeType=archivos[i].toURL().openConnection().getContentType();
	                	
	                	String nombre=archivos[i].getName();
	                	String pathAbsoluto=archivos[i].getAbsolutePath();
	                			
	                	//String str = String.format("%30s %30s", nombre, mimeType);
	                	//System.out.println(str);
	                	
	                	PDDocument doc=null;
	                	try
	                	{
	                	
		                	if(mimeType.contains("application/pdf"))
		                	{
			                	String phrase ="adaptive system";//"@f:/VALOR";
			                	
			                	doc = Loader.loadPDF(archivos[i]);  // new PDDocument.load(pathAbsoluto);
			                	PDFTextStripper findPhrase = new PDFTextStripper();
			                    String text = findPhrase.getText(doc);
			                    String PDF_content = text;
			                    
			                    if( PDF_content.contains(phrase) )
			                    {
			                    //	String result = PDF_content.contains(phrase) ? "Yes" : "No";
			                    	//System.out.println(result);
			                    	
			                    	String str = String.format("%30s", nombre);
			                    	busqueda.add(str);			                    	
			                    }
			                    //doc.close();			                 
		                	}
	                	}
	                	catch(Exception e)
	                	{
	                		String str = String.format("%30s", nombre);
	                    	busqueda.add(str);
	                	}
	                	finally
	                	{
	                		if(doc!=null)
	                			doc.close();
	                	}
	                }
	                
	                for(String linea:busqueda)
	                {
	                	System.out.println(linea);
	                }
	            }
	        }
	    }
	}

}
