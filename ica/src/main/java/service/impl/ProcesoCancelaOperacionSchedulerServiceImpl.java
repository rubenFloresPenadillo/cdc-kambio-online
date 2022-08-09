package service.impl;

import java.text.ParseException;

//import org.apache.commons.lang.StringUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import loggerUtil.LoggerUtil;
import service.ProcesoCancelaOperacionSchedulerService;

//import com.ibatis.common.logging.Log;
//import com.ibatis.common.logging.LogFactory;

//import biz.belcorp.ssicc.dao.Constants;
//import biz.belcorp.ssicc.dao.GenericoDAO;
//import biz.belcorp.ssicc.dao.model.ParametroPais;
//import biz.belcorp.ssicc.service.spusicc.seg.ProcesoSEGBloqueoEliminacionUsuarioService;

/**
 * @author cbazalar
 *
 */
public class ProcesoCancelaOperacionSchedulerServiceImpl extends SchedulerFactoryBean {


	private ProcesoCancelaOperacionSchedulerService procesoCancelaOperacionSchedulerService; 
	
	/**
	 * @param codigoPais
	 * @param codigoSistema
	 * @param codigoParametro
	 * @param nombreParametro
	 * @return
	 */
//	private String obtenerParametroPais(String codigoPais, String codigoSistema, String codigoParametro, String nombreParametro) {
//		ParametroPais parametroPais = new ParametroPais();
//		parametroPais.setCodigoPais(codigoPais);
//		parametroPais.setCodigoSistema(codigoSistema);
//		if (StringUtils.isNotBlank(codigoParametro))
//			parametroPais.setCodigoParametro(codigoParametro);
//		
//		if (StringUtils.isNotBlank(nombreParametro))
//			parametroPais.setNombreParametro(nombreParametro);
//		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
//		String valorParametro = pPais.getValorParametro();
//		
//		return valorParametro;
//	}

	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
//		log.debug("is running "+super.isRunning());
		//Recuperamos el codigo Pais
//		String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT, null);		
		
		//Verificamos si es Necesario que se ejecute el Quartz para el Proceso de Reactivaciones de Gerentes de Directorio
		String indicadorEjecucion = "SI";	
//		log.debug("indicadorEjecucion " + indicadorEjecucion);
		
		if("SI".equals(indicadorEjecucion)) {
			if(!super.isRunning()){
//				log.debug("Empezando running ");
				
				setCronExpression();
				super.setAutoStartup(true);
				super.afterPropertiesSet();
			}	
		}
	}
	
	public void setCronExpression() throws ParseException, SchedulerException, Exception {
//		log.debug("Entrando .... ProcesoGENReactivacionesGerentesDirectorioSchedulerServiceImpl");
		LoggerUtil.getInstance().getLogger().info("Entrando .... ProcesoCancelaOperacionSchedulerServiceImpl");
		
			//Recuperamos el Periodo de Ejecucion de Reactivaciones de Gerentes de Directorio
//		    String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT, null);	
//		    String periodoEjecucion = obtenerParametroPais(codigoPais, Constants.SISTEMA_SEG, null, Constants.SEG_NOMBRE_PARAMETRO_JOBBLOQUEO);
			
		String periodoEjecucion = "0 0,2 0 ? * * *";
		
		    //setea el nuevo cron expresion				
			//hora envio - crono envio IPM
//			log.debug("is running " + periodoEjecucion);
			LoggerUtil.getInstance().getLogger().info("is running " + periodoEjecucion);
//			if(StringUtils.isNotEmpty(periodoEjecucion)){			
				//interfazGENReactivacionesGerentesDirectorioCronTrigger.setCronExpression(periodoEjecucion);
				
				MethodInvokingJobDetailFactoryBean tarea = new MethodInvokingJobDetailFactoryBean();
                tarea.setTargetObject(this.procesoCancelaOperacionSchedulerService);
                tarea.setTargetMethod("executeCancelaOperacion");
                tarea.setName("TR01_ProcesoCancelaOperacion");
                tarea.setConcurrent(false);
                tarea.afterPropertiesSet();
				
				CronTriggerBean cronTrigger = new CronTriggerBean();
				cronTrigger.setBeanName("CR01_TR01_ProcesoCancelaOperacion");
                cronTrigger.setCronExpression(periodoEjecucion);
                cronTrigger.afterPropertiesSet();
                
                
    			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.scheduleJob((JobDetail) tarea.getObject(), cronTrigger);
				scheduler.start();
//			}	
			
	}

	/**
	 * @throws Exception
	 */
	public void afterPropertiesBaseSet() throws Exception {
		super.afterPropertiesSet();
	}

	public ProcesoCancelaOperacionSchedulerService getProcesoCancelaOperacionSchedulerService() {
		return procesoCancelaOperacionSchedulerService;
	}

	public void setProcesoCancelaOperacionSchedulerService(
			ProcesoCancelaOperacionSchedulerService procesoCancelaOperacionSchedulerService) {
		this.procesoCancelaOperacionSchedulerService = procesoCancelaOperacionSchedulerService;
	}


	
	
}
