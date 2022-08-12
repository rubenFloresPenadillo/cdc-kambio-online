package service.impl;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;
import model.dao.DaoOperacionCliente;
import model.dao.impl.DaoOperacionClienteImpl;
import service.ProcesoCancelaOperacionSchedulerService;

public class ProcesoCancelarOperacionSchedulerServiceImpl  implements ProcesoCancelaOperacionSchedulerService{

    private DaoOperacionCliente daoOperacionCliente;
    private Session session;

    public ProcesoCancelarOperacionSchedulerServiceImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();

        daoOperacionCliente = new DaoOperacionClienteImpl(session);
    }

	@Override
	public void closeSesion() {
		daoOperacionCliente.closeSesion();
	}

	@Override
	public void executeCancelaOperacion() throws Exception {
		
		
	}
	
	

}

