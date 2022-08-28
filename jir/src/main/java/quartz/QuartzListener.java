package quartz;

import org.quartz.ee.servlet.QuartzInitializerListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
@WebListener
public class QuartzListener extends QuartzInitializerListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
        	/*Tarea para Cancelar Operacion INICIADA por limite de tiempo*/
            Scheduler schedulerCancelaOperacion = factory.getScheduler();
            JobDetail jobDetailCancelaOperacion = JobBuilder.newJob(FinalizaTransaccion.class).build();
            Trigger triggerCancelaOperacion = TriggerBuilder.newTrigger().withIdentity("TrgCancelaOperacionIniciada").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0/30 * * * ? *")).startNow().build();
            schedulerCancelaOperacion.scheduleJob(jobDetailCancelaOperacion, triggerCancelaOperacion);
            schedulerCancelaOperacion.start();
            
            /*Tarea para ejecutar envio de CPE al PSE u OSE*/
            Scheduler schedulerFacturacionElectronica = factory.getScheduler();
            JobDetail jobDetailFacturacionElectronica = JobBuilder.newJob(FacturacionElectronica.class).build();
            Trigger triggerFacturacionElectronica = TriggerBuilder.newTrigger().withIdentity("TrgEnvioComprobantesElectronicos").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0/3 * * * ? *")).startNow().build();
            schedulerFacturacionElectronica.scheduleJob(jobDetailFacturacionElectronica, triggerFacturacionElectronica);
            schedulerFacturacionElectronica.start();
 
        } catch (Exception e) {
            ctx.log("Error en el job.", e);
            
        }
    }
}
