package numeros.util;

import java.io.Serializable;

import util.types.NumerosType;



public class ValidacionesNumeros implements Serializable{

	private static final long serialVersionUID = 1496202564977879961L;

	public static Boolean esCeroONuloEntero(Integer valorClaseEntera){
		if (valorClaseEntera == null || (valorClaseEntera).equals(NumerosType.NUMERO_MINIMO_CERO.getValor()) ) {
			return true;
		}
		return false;
	}
	
	public static Boolean esCeroONuloDecimal(Double valorClaseDecimal){
		if (valorClaseDecimal == null || Double.compare(valorClaseDecimal, new Double(NumerosType.NUMERO_MINIMO_CERO.getValor().toString())) == 0 ) {
			return true;
		}
		return false;
	}
	
}
