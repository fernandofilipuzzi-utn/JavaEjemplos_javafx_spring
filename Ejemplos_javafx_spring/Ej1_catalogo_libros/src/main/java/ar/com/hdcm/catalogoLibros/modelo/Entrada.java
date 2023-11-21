package ar.com.hdcm.catalogoLibros.modelo;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entrada  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String tipoFichero;//mimetype
	private String url;
	private Libro ficha;
	
	private transient List<Coincidencia> coincidencias=new ArrayList<>();
	
	public Entrada() 
	{		
	}
	
	public Entrada(String nombre, String mimeType, String url) 
	{
		this.nombre=nombre;
		this.tipoFichero=mimeType;
		this.url=url;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipoFichero() {
		return tipoFichero;
	}
	public void setTipoFichero(String mimeType) {
		this.tipoFichero = mimeType;
	}
	
	public String getURL() {
		return url;
	}
	public void setURL(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Libro getFicha() {
		return ficha;
	}

	public void setFicha(Libro ficha) {
		this.ficha = ficha;
	}

	public List<Coincidencia> getCoincidencias() 
	{
		if(this.coincidencias==null)
			this.coincidencias=new ArrayList<Coincidencia>();
		
		return coincidencias;
	}

	public void setCoincidencias(List<Coincidencia> coincidencias) {
		this.coincidencias = coincidencias;
	}
	
	public boolean tieneCoincidencias() {
		
		if(this.coincidencias==null)
			return false;	
			
		return coincidencias.size()>0;
	}
	
}
