package quartz;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dto.TpParamGenerDto;
import loggerUtil.LoggerUtil;
import numeros.util.ValidacionesNumeros;
import service.ServiceOperacionCliente;
import service.ServiceParametroGeneral;
import service.impl.ServiceOperacionClienteImpl;
import service.impl.ServiceParametroGeneralImpl;
import util.types.NumerosType;
import util.types.ParametroGeneralType;
import util.types.RegistroActivoType;

public class FinalizaTransaccion implements Job{
	
	private Integer valorTiempoLimite = NumerosType.NUMERO_MINIMO_CERO.getValor();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		LoggerUtil.getInstance().getLogger().info("Ejecutando Proceso para cancelar operacion por limite de tiempo, hora: "+new Date());

		if (ValidacionesNumeros.esCeroONuloEntero(valorTiempoLimite)  ) {
			
			LoggerUtil.getInstance().getLogger().info("Ingreso a la BBDD para obtener el valorTiempoLimite.");
			
			ServiceParametroGeneral serviceParametroGeneralValorTiempo = new ServiceParametroGeneralImpl();
			TpParamGenerDto tempporalValorTiempo = new TpParamGenerDto();
			tempporalValorTiempo.setNomParaGene(ParametroGeneralType.IND_TIEMPO_LIMITE_TRANS_MINUTOS.getValor());
			tempporalValorTiempo.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
			List<TpParamGenerDto> listaParametroValorTiempo = serviceParametroGeneralValorTiempo.getParametrosGenerales(tempporalValorTiempo);
			if(listaParametroValorTiempo!=null && !listaParametroValorTiempo.isEmpty()) {
				valorTiempoLimite  =  Integer.parseInt(listaParametroValorTiempo.get(NumerosType.NUMERO_MINIMO_CERO.getValor()).getValParaGene());
			}
			
			LoggerUtil.getInstance().getLogger().info("Finalizo consulta a la BBDD para obtener el valorTiempoLimite, valor de: "+valorTiempoLimite);
		}
		
		
		if (!ValidacionesNumeros.esCeroONuloEntero(valorTiempoLimite)  ) {
			
			LoggerUtil.getInstance().getLogger().info("valorTiempoLimiteAplicacion: "+valorTiempoLimite);
			ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
	    	String result = serviceOperacionCliente.cancelarOperacionPorLimiteDeTiempo(valorTiempoLimite);
	    	LoggerUtil.getInstance().getLogger().info(result);
			
		}
		
	}
	
	

}
