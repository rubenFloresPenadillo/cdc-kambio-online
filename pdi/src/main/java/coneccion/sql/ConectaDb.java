package coneccion.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import loggerUtil.LoggerUtil;

public class ConectaDb {

	private final String JNDI_BBDD= "java:/comp/env/jdbc/bdpreciso";

	public Connection getConnection() {

		Connection cn = null;

		
		try {
			
			Context contexto = new InitialContext();
			DataSource datasource = (DataSource) contexto.lookup(JNDI_BBDD);
			
			cn = datasource.getConnection();
			LoggerUtil.getInstance().getLogger().info("Catalogo: "+cn.getCatalog());
			LoggerUtil.getInstance().getLogger().info("Esquema: "+cn.getSchema());
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return cn;
	}
}
