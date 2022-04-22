package fecha.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import util.types.CadenasType;



public class FechaUtil implements Serializable{

	private static final long serialVersionUID = -8872708810326952309L;

	public static String formatoFechaHora(Date fechaInput){
		DateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return fechaInput == null  ? CadenasType.VACIO.getValor() : formatoDate.format(fechaInput);
	}
	
	public static String formatoFecha(Date fechaInput){
		DateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
		return fechaInput == null  ? CadenasType.VACIO.getValor() : formatoDate.format(fechaInput);
	}
	
	public static String formatoFechaDiaMes(Date fechaInput){
		DateFormat formatoDate = new SimpleDateFormat("dd/MM");
		return fechaInput == null  ? CadenasType.VACIO.getValor() : formatoDate.format(fechaInput);
	}
	
	public static String formatoFechaNombreArchivo(Date fechaInput){
		DateFormat formatoDate = new SimpleDateFormat("ddMMyyyy");
		return fechaInput == null  ? CadenasType.VACIO.getValor() : formatoDate.format(fechaInput);
	}
	
	public static Boolean cumpleHorarioAtencion(String valorParametroHorarioLaV, String valorParametroHorarioSaba){
		
		Boolean resultado = Boolean.FALSE;
		LocalDate localDateHoy = LocalDate.now();
		
		if (localDateHoy.getDayOfWeek() == DayOfWeek.MONDAY  || localDateHoy.getDayOfWeek() == DayOfWeek.TUESDAY || localDateHoy.getDayOfWeek() == DayOfWeek.WEDNESDAY ||
				localDateHoy.getDayOfWeek() == DayOfWeek.THURSDAY || localDateHoy.getDayOfWeek() == DayOfWeek.FRIDAY ) {
			
		} else if(localDateHoy.getDayOfWeek() == DayOfWeek.SATURDAY ) {
			
		} else {
			resultado = Boolean.FALSE;
		}

		return resultado;
	}
    
}
