package coneccion.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConectaDb {

	private final String JNDI_BDMULTICAMBIO= "java:/comp/env/jdbc/bdmulticambio";

	public Connection getConnection() {

		Connection cn = null;

		
		try {
			
			Context contexto = new InitialContext();
			DataSource datasource = (DataSource) contexto.lookup(JNDI_BDMULTICAMBIO);
			cn = datasource.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return cn;
	}
}
