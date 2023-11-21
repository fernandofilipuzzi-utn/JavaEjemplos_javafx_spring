package ar.com.hdcm.catalogoLibros.util;

public class Utils {
	
	public static String SetCadena(String valor)
	{
		if(valor==null || valor.trim().toLowerCase().equals("null") )
			return "";
		return valor;	
	}
	
}
