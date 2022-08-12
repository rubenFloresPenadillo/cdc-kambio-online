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
            Scheduler scheduler = factory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(FinalizaTransaccion.class).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TrgFinaliza").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0/1 * * * ? *")).startNow().build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            ctx.log("Error en el job.", e);
            
        }
    }
}