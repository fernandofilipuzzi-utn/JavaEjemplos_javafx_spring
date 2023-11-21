package ar.com.hdcm.catalogoLibros.modelo;

import java.io.Serializable;

import ar.com.hdcm.catalogoLibros.util.Utils;

public class Libro  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String titulo="";
	private String autor="";
	private String editorial="";
	private int mesEdicion;
	private int añoEdicion;
	private int nroEdicion;
	private String ISBN10="";
	private String ISBN13="";
	
	//web referencia.
	//ISBN10
	//ISBN13
	//imagen
	//pais
	
	private boolean actualizado;
	
	public Libro()
	{		
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = Utils.SetCadena(titulo);
	}

	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor =Utils.SetCadena( autor);
	}

	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = Utils.SetCadena(editorial);
	}

	public int getMesEdicion() {
		return mesEdicion;
	}
	public void setMesEdicion(int mesEdicion) {
		this.mesEdicion = mesEdicion;
	}

	public int getNroEdicion() {
		return nroEdicion;
	}
	public void setNroEdicion(int nroEdicion) {
		this.nroEdicion = nroEdicion;
	}

	public String getISBN10() {
		return ISBN10;
	}
	public void setISBN10(String ISBN10) {
		this.ISBN10 = Utils.SetCadena(ISBN10);
	}
	
	public String getISBN13() {
		return this.ISBN13;
	}
	public void setISBN13(String ISBN13) {
		this.ISBN13 = Utils.SetCadena(ISBN13);
	}

	public int getAñoEdicion() {
		return añoEdicion;
	}
	public void setAñoEdicion(int añoEdicion) {
		this.añoEdicion = añoEdicion;
	}

	public boolean isActualizado() {
		return actualizado;
	}
	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}
}
