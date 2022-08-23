package coneccion.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author parainformaticos.com
 */
public class ConectaDbOld {

    private final String database;

    public Connection getConnection() {
        Connection cn = null;

        try {
            Class.forName("org.postgresql.Driver").newInstance();
            cn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + database,
                    "postgres", "D4nielD4niel");
            
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        return cn;
    }

    public ConectaDbOld() {
        this.database = "bdtienda"; 
    }

    public ConectaDbOld(String database) {
        this.database = database;
    }
}

