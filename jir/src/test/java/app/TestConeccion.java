package app;

import coneccion.sql.ConectaDb;
import coneccion.sql.ConectaDbOld;

public class TestConeccion {

	public static void main(String[] args) {
		
		ConectaDbOld cn = new ConectaDbOld();
		ConectaDb cn2 = new ConectaDb();
		
		if (cn2.getConnection() != null) {
			System.out.println("=)");			
		}else {
			System.out.println("=(");
		}

		}

}
