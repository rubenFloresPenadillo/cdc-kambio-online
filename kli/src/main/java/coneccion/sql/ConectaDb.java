package coneccion.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConectaDb {

	private final String JNDI_BDKAMBIOONLINE= "java:/comp/env/jdbc/bdkambioonline";

	public Connection getConnection() {

		Connection cn = null;

		
		try {
			
			Context contexto = new InitialContext();
			DataSource datasource = (DataSource) contexto.lookup(JNDI_BDKAMBIOONLINE);
			cn = datasource.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return cn;
	}
}
