/*
 * autor: filipuzzi, fernando rafael
 * versión: 20231121
 **/

package ar.com.hdcm.catalogoLibros.modelo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class Catalogo implements Serializable{
	private static final long serialVersionUID = 1L;

	String docDirPath;
	
	boolean isRecursivo=true;
	List<Entrada> entradas=new ArrayList<Entrada>();
	
	int actualizados=0;
	int noActualizados=0;
	int directorios=0;
	
	public Catalogo ()
	{
		docDirPath= System.getProperty("user.dir");
	}
	
	public List<Entrada> ActualizarDocsFromDir()
	{
		if(entradas==null) entradas=new ArrayList<Entrada>();
		
		actualizados=0;
		noActualizados=0;
		directorios=0;
		
		File carpetaRaiz = new File(docDirPath);
		
		ActualizarDocsFromDir(carpetaRaiz);
		
		return entradas;
	}
	
	private List<Entrada> ActualizarDocsFromDir(File archivoRaiz)
	{
		File[] archivos;
	    
	    if(archivoRaiz.exists() && archivoRaiz.isDirectory()) 
	    {
	    	archivos = archivoRaiz.listFiles();
	    	
	        for(int n=0; n<archivos.length; n++) 
	        {
	        	File archivo=archivos[n];
	        	
	        	if(archivo.isDirectory()==false)
        		{
		        	String nombre=archivo.getName();
		        	
		        	String mimeType="-";		        	
		        	try
		        	{
		        		mimeType=archivo.toURL().openConnection().getContentType();
		        	}
		        	catch(Exception e)
		        	{
		        		e.printStackTrace();
		        	}
		        	
	            	String url=archivo.getAbsolutePath();
	            	            	
	            	//creando la entrada del libro
	            	Entrada entrada=new Entrada(nombre, mimeType, url);           	

	            	Libro ficha=new Libro();
	            	entrada.setFicha(ficha);
	            	
	            	//catalogando con el nombre del fichero
	            	ficha.setTitulo(nombre.replaceAll("_", " ").replace(".pdf", "").replace(".", " "));
	            	
	            	//buscando si existe
	            	Entrada buscado=BuscarEntrada(entrada.getNombre());
	            	
	            	if(buscado==null)
	            	{
	            		entradas.add(entrada);
	            		actualizados++;
	            	}
	            	else
	            	{
	            		noActualizados++;
	            	}
	        	}		        	
	        	else if(isRecursivo==true)
	        	{
	        		directorios++;
	        		ActualizarDocsFromDir(archivo);
	        	}
	        
            }
	    }	    
	    return entradas;
	}
	
	public String getDocDirPath() {
		return docDirPath;
	}

	public void setDocDirPath(String docDirPath) {
		this.docDirPath = docDirPath;
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}
	
	public void BusquedaEnDocumento(Entrada entrada, String frase)
	{
		entrada.getCoincidencias().clear();
		
		if(entrada.getTipoFichero()!=null && entrada.getTipoFichero().contains("application/pdf"))
    	{
        	PDDocument doc=null;
        	try
        	{
        		String path=getDocDirPath()+"/"+entrada.getNombre();
        		File docFile = new File(path);	
        		
                doc = Loader.loadPDF(docFile);
                
                PDFTextStripper textStripper=new PDFTextStripper(); 
                
                int nroPag=1;
                while(nroPag<doc.getNumberOfPages())
                {
                	 textStripper.setStartPage(nroPag); 
                     textStripper.setEndPage(nroPag);
                     
                     String contentPageText = textStripper.getText(doc); 
                     if( contentPageText.contains(frase) )
                     {
                    	  Coincidencia c=new Coincidencia();
		                  c.setPagina(nroPag);
		                  contentPageText.contains(frase);
		                  
		                  entrada.getCoincidencias().add(c);
                     }
                     
                     nroPag++;
                }
        	}
        	catch(Exception e)
        	{
        	}
        	finally
        	{
        		if(doc!=null)
					try {
						doc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
        	}			                 
    	}
	}
	
	public List<Entrada> BusquedaEnDocumentos(String frase)
	{
		for(Entrada entrada:entradas)
		{
			
			BusquedaEnDocumento(entrada, frase);
		}
		
		return null;
	}
	
	
	public Entrada BuscarEntrada(String nombre)
	{
		for(Entrada entrada:entradas)
		{
			if(entrada.getNombre().equals(nombre))
			{
				return entrada;
			}
		}
		return null;
	}
	
	public Entrada BuscarEntradaPorUrl(String url)
	{
		for(Entrada entrada:entradas)
		{
			
			String url1= entrada.getURL().replace("\\", "/").replace("\\\\", "/").replace("F:", "");
			String url2= url.replace("\\", "/").replace("\\\\", "/").replace("F:", "");
			
			if(url1.equals(url2))
			{
				return entrada;
			}
			
		}
		return null;
	}
	
	/*
	public List<Entrada> Listar()
	{
		//String path="F:\\data\\workspaces\\workspace-biblioteca\\biblioteca\\book\\libros";
		String path="F:/data/workspaces/workspace-biblioteca/biblioteca/book/libros";
		
		List<Entrada> listado = new ArrayList<Entrada>();	
		
		
		File carpeta = new File(path);
		File[] archivos;
	    
	    if(carpeta.exists() && carpeta.isDirectory()) 
	    {
	    	archivos = carpeta.listFiles();
	        for(int i=0; i<archivos.length; i++) 
	        {
	        	String nombre=archivos[i].getName();
	        	
	        	String mimeType="-";
	        	try
	        	{
	        		mimeType=archivos[i].toURL().openConnection().getContentType();
	        	}
	        	catch(Exception e)
	        	{
	        		
	        	}
            	String url=archivos[i].getAbsolutePath();
            	            	
            	Entrada entrada=new Entrada(nombre, mimeType, url);
            	listado.add(entrada);
            }
	    }
	    
	    return listado;
	}
*/
}
