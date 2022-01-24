package cadenas.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.types.CadenasType;



public class ValidacionesString implements Serializable{

	private static final long serialVersionUID = -1243061952367522395L;
	
	private static Pattern REGEX_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String ORIGINAL  = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT  = "AaEeIiOoUuNnUu";

	public static Boolean esNuloOVacio(String valorClaseString){
		if (valorClaseString == null || valorClaseString.trim().equals(CadenasType.VACIO.getValor())) {
			return true;
		}
		return false;
	}
	
    public static Boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }
	
    public static Boolean validarEmail(String cadenaEmail) {
	   	 Matcher mtch = REGEX_EMAIL.matcher(cadenaEmail);
	//	 if (mtch.matches()) {
	//	     return true;
	//	 } else {
	//		 return false;
	//	 }
		 return mtch.find();
    }
    

	public static String eliminarAccentos(String str) {
		
		if (str == null) {
		    return null;
		}
		
		char[] array = str.toCharArray();
		for (int index = 0; index < array.length; index++) {
		    int pos = ORIGINAL.indexOf(array[index]);
		    if (pos > -1) {
		        array[index] = REPLACEMENT.charAt(pos);
		    }
		}
		
		return new String(array);
	}
}
