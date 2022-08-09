package service.impl;

import java.util.Date;

import loggerUtil.LoggerUtil;
import service.ProcesoCancelaOperacionSchedulerService;

//@Service("spusicc.procesoRECEnviarInfoInterRapidisimoService")
//@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCancelaOperacionServiceImpl implements ProcesoCancelaOperacionSchedulerService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";

	@Override
	public void executeCancelaOperacion() throws Exception {
		
		LoggerUtil.getInstance().getLogger().info("Se ehjecuta el metodo a las "+new Date());
	}
	
}

