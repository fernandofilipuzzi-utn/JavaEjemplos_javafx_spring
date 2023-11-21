package ar.com.hdcm.catalogoLibros.prueba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pojo implements Serializable {

	private String nombre ;
	public List<String> listado=new ArrayList<String>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
