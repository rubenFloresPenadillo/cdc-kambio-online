package util.constantes;

public class Constants {

	private Constants() {
	}

	public static final String FACTURACION_ELECTRONICA_FORMATO_DE_PDF = "A4";
	public static final short FACTURACION_ELECTRONICA_SUNAT_TRANSACTION = 1;
	//1 = SOLES, 2 = DOLARES, 3 = EUROS
	public static final short FACTURACION_ELECTRONICA_MONEDA = 1; 
	public static final Double FACTURACION_ELECTRONICA_PORCENTAJE_DE_IGV = 18.00; 
	
	public static final short FACTURACION_ELECTRONICA_FLAG_DETRACCION = 0; 
	public static final short FACTURACION_ELECTRONICA_FLAG_ENVIO_CLIENTE = 1;
	public static final short FACTURACION_ELECTRONICA_FLAG_ENVIO_SUNAT = 1;	
	
	
	public static final String FACTURACION_ELECTRONICA_UNIDAD_MEDIDA = "NIU";
	// 9 = Inafecto - Operación Onerosa
	public static final short FACTURACION_ELECTRONICA_TIPO_DE_IGV = 9; 
	// Total del IGV de la lInea
	public static final Double FACTURACION_ELECTRONICA_IGV_DE_LA_LINEA = 0.00; 
	public static final short FACTURACION_ELECTRONICA_FLAG_ANTICIPO_REGULACION = 0; 
	
	
	//Tipos de comprobantes, valores externos
	public static final short FACTURACION_ELECTRONICA_TIPO_COMPROBANTE_FACTURA = 1;
	public static final short FACTURACION_ELECTRONICA_TIPO_COMPROBANTE_BOLETA = 2;
	public static final short FACTURACION_ELECTRONICA_TIPO_COMPROBANTE_NOTA_DE_CREDITO = 3;
	public static final short FACTURACION_ELECTRONICA_TIPO_COMPROBANTE_NOTA_DE_DEBITO = 4;
	
	//Tipo de documento
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_RUC = "6";
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_DNI = "1";
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_VARIOS = "-";
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_CARNE_EXTRANJERIA = "4";
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_PASAPORTE = "7";
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_CEDULA_DIPLOMATICA_IDENTIDAD = "A";
	public static final String FACTURACION_ELECTRONICA_TIPO_DOCUMENTO_NO_DOMICILIADO_SIN_RUC= "0";
	
//  RUTA para enviar documentos
	public static final String FACTURACION_ELECTRONICA_RUTA = "https://api.pse.pe/api/v1/b54a8e608f7a45eca725a88ae1ed51f9044369c00c68493b95786ec77e754a95";
    
//  TOKEN para enviar documentos    
	public static final String FACTURACION_ELECTRONICA_TOKEN = "eyJhbGciOiJIUzI1NiJ9.IjM2NjUxOWZiNjIzZDQ0ZWI4YmU4ZTkwNjUzYTU1ZTI3ODk5MTJmM2FmZWM1NGE3ODgxMTgyZGZkN2ZhZjM3MWEi.UjRTGr5Iv0P15118TAIV5psVossbNN20rf84ot6CIMI";
                 
	
	
	
}
