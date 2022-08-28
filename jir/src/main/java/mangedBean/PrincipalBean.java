package mangedBean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import dto.AudiTpTipoCambiDto;
import dto.TpEntraDto;
import dto.TpSuscrDto;
import dto.TpTipoCambiDto;
import fecha.util.FechaUtil;
import loggerUtil.LoggerUtil;
import numeros.util.ValidacionesNumeros;
import service.ServiceEntrada;
import service.ServiceSuscripcion;
import service.ServiceTipoCambio;
import service.impl.ServiceEntradaImpl;
import service.impl.ServiceSuscripcionImpl;
import service.impl.ServiceTipoCambioImpl;
import util.types.CadenasType;
import util.types.DivisaType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="principalBean")
@ViewScoped
public class PrincipalBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CANTIDAD_ENVIO_INICIAL = 100;
//	private String resultadoProcesoError;
	private Double tipoCambioCompraDolar;
	private Double tipoCambioVentaDolar;
	private Double tipoCambioUsado;
	private BigDecimal cantidadEnvio;
	private BigDecimal cantidadRecibo;
	private Boolean mostrarCompra;
	private List<TpEntraDto> listaUltimasEntradas;
	private static final Integer NUMERO_ULTIMAS_ENTRADAS = 3;
	private static final Integer NUMERO_RESULTADOS_TIPO_CAMBIO = 7;
//	private LineChartModel animacionLineaTipoCambio;
	private LineChartModel modeloTipoCambio;
	private String correoSuscripcion;
	private String resultadoProcesoError;
	private String textoDolarSolSuperior;
	private String textoDolarSolInferior;
	
	/*Flag para mostrar*/
//	private Boolean mostrarCuentas;
//	private Boolean mostrartransferencia;
//	private Boolean mostrarVerificacion;
//	private String valorNombre;
//	private Integer indCompleDatos;
//	private Integer codigoUsuario;
//	private Integer codigoCliente;
//	private Integer codOperClie;
//	private Integer codPerfUsua;
	
	
	public PrincipalBean() {
		LoggerUtil.getInstance(); //Para habilitar el log debe estar descomentado
//		operacionClienteFormulario = new TpOperaClienDto();
		System.out.println("Entro al constructor PrincipalBean");
		tipoCambioCompraDolar = new Double(-1D);
		tipoCambioVentaDolar = new Double(-1D);
		listaUltimasEntradas = new LinkedList<TpEntraDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
//		resultadoProcesoError = CadenasType.VACIO.getValor();

	}

    @PostConstruct
    public void init() {
    	
    	try {
    		getTipoCambioDolar();
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    	
		mostrarCompra = Boolean.TRUE;
		tipoCambioUsado = new Double(tipoCambioCompraDolar);
		cantidadEnvio = new BigDecimal(CANTIDAD_ENVIO_INICIAL);
		textoDolarSolSuperior = "DÓLARES";
		textoDolarSolInferior = "  SOLES";
		cargarValoresEnvioReciboA();
		
    }
    
    public void getTipoCambioDolar() {
    	LoggerUtil.getInstance().getLogger().info("info servicio ServiceTipoCambioImpl");
//    	LoggerUtil.getInstance().getLogger().warn("warn servicio ServiceTipoCambioImpl");
//    	LoggerUtil.getInstance().getLogger().error("error servicio ServiceTipoCambioImpl");
//    	LoggerUtil.getInstance().getLogger().fatal("fatal servicio ServiceTipoCambioImpl");
//    	LoggerUtil.getInstance().getLogger().debug("debug servicio ServiceTipoCambioImpl");
    	
		ServiceTipoCambio serviceTipoCambio = new ServiceTipoCambioImpl();
		LoggerUtil.getInstance().getLogger().info("Fin servicio ServiceTipoCambioImpl");
		TpTipoCambiDto tpTipoCambiDtoTempo = new TpTipoCambiDto();
		tpTipoCambiDtoTempo.getTpDivisByCodDivi().setCodDivi(DivisaType.DOLAR.getCodDivi());
		tpTipoCambiDtoTempo.getTpDivisByCodDiviCamb().setCodDivi(DivisaType.SOL.getCodDivi());
		tpTipoCambiDtoTempo = serviceTipoCambio.getTipoCambio(tpTipoCambiDtoTempo);

		if(!ValidacionesNumeros.esCeroONuloDecimal(tpTipoCambiDtoTempo.getValCambComp()) &&  !ValidacionesNumeros.esCeroONuloDecimal(tpTipoCambiDtoTempo.getValCambVent()) ) {
			tipoCambioCompraDolar = new Double(tpTipoCambiDtoTempo.getValCambComp());
			tipoCambioVentaDolar = new Double(tpTipoCambiDtoTempo.getValCambVent());
		}
		
    }
    
    public void cargarValoresEnvioReciboA() {
    	if(cantidadEnvio != null) {
        	if(mostrarCompra) {
        		cantidadRecibo = cantidadEnvio.multiply(new BigDecimal(tipoCambioCompraDolar));
        		cantidadRecibo = cantidadRecibo.setScale(2, RoundingMode.HALF_UP);
        		
        	}else {
        		cantidadRecibo = cantidadEnvio.divide(new BigDecimal(tipoCambioVentaDolar), 2, RoundingMode.HALF_UP);
        	}
    	}
    	

    }
    
    public void cargarValoresEnvioReciboB() {
    	if(cantidadRecibo != null) {
        	if(mostrarCompra) {
        		cantidadEnvio = cantidadRecibo.divide(new BigDecimal(tipoCambioCompraDolar), 2, RoundingMode.HALF_UP);
        	}else{
    	    	cantidadEnvio = cantidadRecibo.multiply(new BigDecimal(tipoCambioVentaDolar));
    	    	cantidadEnvio = cantidadEnvio.setScale(2, RoundingMode.HALF_UP);
        	}
    	}
    	

    }
    
    // Los montons al rotar se calculan en base a la cantidad de envio
    public void rotarVista() {
    	mostrarCompra = !mostrarCompra;
    	if(mostrarCompra) {
    		tipoCambioUsado = new Double(tipoCambioCompraDolar);
    		textoDolarSolSuperior = "DÓLARES";
    		textoDolarSolInferior = "  SOLES";
    	}else {
    		tipoCambioUsado = new Double(tipoCambioVentaDolar);
    		textoDolarSolSuperior = "  SOLES";
    		textoDolarSolInferior = "DÓLARES";
    	}
    	
    	cargarValoresEnvioReciboA();
    }

    public void procesarCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INDEX.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    
    public void getUltimasEntradas() throws IOException {
    	ServiceEntrada serviceEntrada = new ServiceEntradaImpl();
    	listaUltimasEntradas = serviceEntrada.getUltimasEntradas(NUMERO_ULTIMAS_ENTRADAS);
    	
    	for(TpEntraDto temp: listaUltimasEntradas) {
    		
    		
    		String mimeType;
    	    if (temp.getNomImaPrev().endsWith("png")) {
    	        mimeType = "image/png";
    	    } else if (temp.getNomImaPrev().endsWith("jpg") || temp.getNomImaPrev().endsWith("jpeg")) {
    	        mimeType = "image/jpeg";
    	    } else {
    	        mimeType = "application/octet-stream";
    	    }
    	    
    	    temp.setImaEntrPrev("data:"+mimeType+";base64,"+temp.getImaEntrPrev());
    	    
    	}
    }

    
   
    private void creaModeloLineal() {

 
    	modeloTipoCambio = inicializarDataModelo();
    	modeloTipoCambio.setAnimate(true);
    	modeloTipoCambio.setLegendPosition("se");
//        lineModel2.setShowDatatip(false);
//        lineModel2.setShowPointLabels(true);
    	modeloTipoCambio.getAxes().put(AxisType.X, new CategoryAxis("DÃ­a"));
        Axis yAxis = modeloTipoCambio.getAxis(AxisType.Y);
        yAxis.setLabel("Soles");
        yAxis.setMin(3.10);
        yAxis.setMax(3.45);

    }
    
    private LineChartModel inicializarDataModelo() {
    	
        LineChartModel model = new LineChartModel();
 
        ServiceTipoCambio serviceTipoCambio = new ServiceTipoCambioImpl();
        List<AudiTpTipoCambiDto> resultadoTemporal = new LinkedList<AudiTpTipoCambiDto>() ;
        resultadoTemporal = serviceTipoCambio.getTipoCambiosVariacion(NUMERO_RESULTADOS_TIPO_CAMBIO);
        
        ChartSeries compra = new ChartSeries();
        compra.setLabel("Compra");
        
        ChartSeries venta = new ChartSeries();
        venta.setLabel("Venta");
        
        Collections.reverse(resultadoTemporal);
        
        for (AudiTpTipoCambiDto temp : resultadoTemporal) {
        	compra.set(FechaUtil.formatoFechaDiaMes(temp.getFecModiRegi()), temp.getValCambComp());
        	venta.set(FechaUtil.formatoFechaDiaMes(temp.getFecModiRegi()), temp.getValCambVent());
        }
 
        model.addSeries(compra);
        model.addSeries(venta);
 
        return model;
    }
    
    public void procesarEnviarSuscripcion() {
    	
    	System.out.println("Ingreso a procesarEnviarSuscripcion");
		String result = null;
   
    	ServiceSuscripcion ServiceSuscripcion = new ServiceSuscripcionImpl();
    	TpSuscrDto tpSuscrDto = new TpSuscrDto();

    	tpSuscrDto.setEmaSusc(correoSuscripcion);
    	tpSuscrDto.setUsuApliCrea(String.valueOf(correoSuscripcion));
    	tpSuscrDto.setFecCreaRegi(new Date());
    	tpSuscrDto.setIndEsta(RegistroActivoType.ACTIVO.getLlave());

    	result = ServiceSuscripcion.insertUpdate(tpSuscrDto);

        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	correoSuscripcion = CadenasType.VACIO.getValor();
        	PrimeFaces.current().executeScript("operacionEnviarSucripcionExitosa();");
        	
        }else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().executeScript("procesoConError();");
        }
    }
    
	public Double getTipoCambioCompraDolar() {
		return tipoCambioCompraDolar;
	}

//	public void setTipoCambioCompraDolar(Double tipoCambioCompraDolar) {
//		this.tipoCambioCompraDolar = tipoCambioCompraDolar;
//	}

	public Double getTipoCambioVentaDolar() {
		return tipoCambioVentaDolar;
	}

	public void setTipoCambioVentaDolar(Double tipoCambioVentaDolar) {
		this.tipoCambioVentaDolar = tipoCambioVentaDolar;
	}

	public Boolean getMostrarCompra() {
		return mostrarCompra;
	}

	public void setMostrarCompra(Boolean mostrarCompra) {
		this.mostrarCompra = mostrarCompra;
	}

	public BigDecimal getCantidadEnvio() {
		return cantidadEnvio;
	}

	public void setCantidadEnvio(BigDecimal cantidadEnvio) {
		this.cantidadEnvio = cantidadEnvio;
	}

	public BigDecimal getCantidadRecibo() {
		return cantidadRecibo;
	}

	public void setCantidadRecibo(BigDecimal cantidadRecibo) {
		this.cantidadRecibo = cantidadRecibo;
	}

	public Double getTipoCambioUsado() {
		return tipoCambioUsado;
	}

	public void setTipoCambioUsado(Double tipoCambioUsado) {
		this.tipoCambioUsado = tipoCambioUsado;
	}

	public List<TpEntraDto> getListaUltimasEntradas() {
		return listaUltimasEntradas;
	}

	public void setListaUltimasEntradas(List<TpEntraDto> listaUltimasEntradas) {
		this.listaUltimasEntradas = listaUltimasEntradas;
	}

	public LineChartModel getModeloTipoCambio() {
		return modeloTipoCambio;
	}

	public void setModeloTipoCambio(LineChartModel modeloTipoCambio) {
		this.modeloTipoCambio = modeloTipoCambio;
	}

	public String getCorreoSuscripcion() {
		return correoSuscripcion;
	}

	public void setCorreoSuscripcion(String correoSuscripcion) {
		this.correoSuscripcion = correoSuscripcion;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getTextoDolarSolSuperior() {
		return textoDolarSolSuperior;
	}

	public void setTextoDolarSolSuperior(String textoDolarSolSuperior) {
		this.textoDolarSolSuperior = textoDolarSolSuperior;
	}

	public String getTextoDolarSolInferior() {
		return textoDolarSolInferior;
	}

	public void setTextoDolarSolInferior(String textoDolarSolInferior) {
		this.textoDolarSolInferior = textoDolarSolInferior;
	}
	
	
}
