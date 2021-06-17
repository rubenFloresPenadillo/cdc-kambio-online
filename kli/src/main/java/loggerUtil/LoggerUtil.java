package loggerUtil;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*Como demora mucho al generar el archivo del logger entonces se comenta las llamadas a la clase LoggerUtil, esto el día 8/12/2017
 * Solo teniamos 3 regisros , Ingreso al sistema , Cerró sesión y MENSAJE_SISTEMA_ERROR_LOGIN_CREDENCIALES("Credenciales invalidas."), 
 * esto en las clases AccesoBean, PedidoVentaBean y ProductoMantenimientoBean
 * 
 */
public class LoggerUtil implements Serializable{

	private static final long serialVersionUID = 1598002051183347157L;

	private Logger logger = LogManager.getLogger(LoggerUtil.class);

	private static LoggerUtil loggerUtil = null;
	
	public static LoggerUtil getInstance() {
		if (loggerUtil == null) {
			loggerUtil = new LoggerUtil();
		}
		return loggerUtil;
	}

	public Logger getLogger() {
		return logger;
	}

}

