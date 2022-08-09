package quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class FinalizaTransaccion implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("CERRANDO TRANSACCION NO FINALIZADA");
		//LOGICA DEL JOB
	}

}
