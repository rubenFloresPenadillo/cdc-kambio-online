package util.types;

public enum ParametroGeneralType {

	IND_APLICA_TRANS_INTER_SOLES("indAplicaTransfInterbancariaSoles"),
	IND_APLICA_TRANS_INTER_DOLAR("indAplicaTransfInterbancariaDolar"),
	IND_TIEMPO_LIMITE_TRANS_MINUTOS("indTiempoLimiteTransferenciaMinutos"),
	HORARIO_ATENCION_LUNES_A_VIERNES("horarioLunesAViernesHH24MM"),
	HORARIO_ATENCION_SABADOS("horarioSabadoHH24MM"),
	MONTO_BASE_USD_ORIGEN_FONDO("montoBaseUSDOrigenFondo");

	private String valor;
	
	private ParametroGeneralType(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
