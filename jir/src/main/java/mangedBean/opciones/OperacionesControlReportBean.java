package mangedBean.opciones;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpClienDto;
import dto.TpOperaClienDto;
import fecha.util.FechaUtil;
import loggerUtil.LoggerUtil;
import service.ServiceOperacionCliente;
import service.impl.ServiceOperacionClienteImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

@ManagedBean(name="operacionesControlReportBean")
@ViewScoped
public class OperacionesControlReportBean {

	private String resultadoProcesoError;
	private List<TpOperaClienDto> listaOperacionesControl;
//	private TpOperaClienDto operacionSeleccionada;
//	private Integer codigoCliente;
	private String valorNombre;
	private String ideUsuaEmai;
	private String codigoUnicoOperacionBuscar;	
	private Date fechaDesde;
	private Date fechaHasta;
	
	public OperacionesControlReportBean() {
//		operacionSeleccionada = new TpOperaClienDto();
		listaOperacionesControl = new LinkedList<TpOperaClienDto>();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		System.out.println("Entro al constructor OperacionesControlBean");
		
	}
	
    /**
     * Realiza su ejecuci锟絥 despues del Constructor de la clase OperacionesControlBean.
     */
    @PostConstruct
    public void init() {
    	
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
//    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
    	
//    	getOperacionesCliente();
    	fechaDesde = new Date();
    	
    }
    
    public void getOperacionesCliente() {
    	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
    	TpOperaClienDto tpOperaClienDto = new TpOperaClienDto();
    	tpOperaClienDto.setFecFiltroDesde(fechaDesde);
    	tpOperaClienDto.setFecFiltroHasta(fechaHasta);
//    	tpOperaClienDto.getTpClien().setCodClie(codigoCliente);
    	
    	listaOperacionesControl = serviceOperacionCliente.getOperacionesCliente(tpOperaClienDto);
    	
//    	listaOperacionesControl = listaOperacionesCliente.stream()          
//                .filter(x -> x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OOPERACION_CANCELADA.getIdElemento()) ||
//                		x.getTpEstadOpera().getCodEstaOper().equals(ElementosTablasType.ESTADO_OOPERACION_FINALIZADA.getIdElemento()))  
//                .collect(Collectors.toList());
//    	
    }

//    public void mostrarDialogConfirmacionFinalizarOperacion(TpOperaClienDto tpOperaClienDto) {
//    	
//    	operacionSeleccionada = tpOperaClienDto;
//    	System.out.println("Se debe cambiar el estado de: "+tpOperaClienDto.getCodOperClie());
//    	PrimeFaces.current().executeScript("mostrarPopupConfirmaFinalizar();");
//    	
//    	   
//    }
//    
//    public void mostrarDialogConfirmacionCancelarOperacion(TpOperaClienDto tpOperaClienDtoCancelar) {
//    	
//    	operacionSeleccionada = tpOperaClienDtoCancelar;
//    	System.out.println("Se debe cancelar la operacion: "+tpOperaClienDtoCancelar.getCodOperClie());
//    	PrimeFaces.current().executeScript("mostrarPopupConfirmaCancelar();");
//    	
//    	   
//    }
//    
//    public void procesarFinalizarOperacion() {
//    	
//    	resultadoProcesoError = CadenasType.VACIO.getValor();
//    	
//    	if(operacionSeleccionada!=null) {
//    		System.out.println("Ejecutar"+operacionSeleccionada.getCodOperClie());
//    		
//    		ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
//
//    		operacionSeleccionada.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OOPERACION_FINALIZADA.getIdElemento());
//        	operacionSeleccionada.setUsuApliModi(operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai());
//        	operacionSeleccionada.setFecModiRegi(new Date());
//    		
//        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionSeleccionada);
//
//        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//        		
//        		getOperacionesCliente();
//        		PrimeFaces.current().executeScript("operacionFinalizadaExitosa();");
//        		
//        		LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
//
//    			CorreoEnvioHilo hiloEnvioOperacionesControlBean1 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_FINALIZO_OPERACION , "hiloEnvioOperacionesControlBean1", operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai() , operacionSeleccionada.getTpClien().getValPrimNombPers(), operacionSeleccionada.getCodUnicOperClie());
//    			 
//    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioOperacionesControlBean1);
//    			 
//    			nuevoHiloEnvioCorreo.start();
//
//    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
//    			
//        	}else {
//        		resultadoProcesoError = result;
//        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
//        		PrimeFaces.current().executeScript("procesoConError();");
//        	}
//    		
//    	}
//    }
//    
//    public void procesarCancelarOperacion() {
//    	
//    	resultadoProcesoError = CadenasType.VACIO.getValor();
//    	
//    	if(operacionSeleccionada !=null) {
//    		
//        	ServiceOperacionCliente serviceOperacionCliente = new ServiceOperacionClienteImpl();
//
//        	operacionSeleccionada.getTpEstadOpera().setCodEstaOper(ElementosTablasType.ESTADO_OOPERACION_CANCELADA_OPERACION.getIdElemento());
//        	operacionSeleccionada.setUsuApliModi(operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai());
//        	operacionSeleccionada.setFecModiRegi(new Date());
//
//        	String result = serviceOperacionCliente.actualizarEstadoOperacionCliente(operacionSeleccionada);
//
//        	if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//
//        		getOperacionesCliente();
//        		PrimeFaces.current().executeScript("operacionCanceladaExitosa();");
//        		
//        		LoggerUtil.getInstance().getLogger().info("Iniciando Hilo Principal");
//
//    			CorreoEnvioHilo hiloEnvioOperacionesControlBean2 = new CorreoEnvioHilo(PlantillasType.PLANTILLA_ENVIAR_CANCELA_OPERACION , "hiloEnvioOperacionesControlBean2", operacionSeleccionada.getTpClien().getTpUsuar().getIdeUsuaEmai() , operacionSeleccionada.getTpClien().getValPrimNombPers(), operacionSeleccionada.getCodUnicOperClie());
//    			 
//    			Thread nuevoHiloEnvioCorreo = new Thread(hiloEnvioOperacionesControlBean2);
//    			 
//    			nuevoHiloEnvioCorreo.start();
//
//    			LoggerUtil.getInstance().getLogger().info("Finalizando Hilo Principal");
//    			
//        	}else {
//        		resultadoProcesoError = result;
//        		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
//        		PrimeFaces.current().executeScript("procesoConError();");
//        	}
//    		
//    	}
//
//    }

    
    public void procesarReporteOperaciones()  throws IOException {
    	
    resultadoProcesoError	= CadenasType.VACIO.getValor();
    
    if (fechaDesde == null) {
    	resultadoProcesoError = "La fecha desde es obligatoria.";
    }else if (fechaHasta == null) {
    	resultadoProcesoError = "La fecha hasta es obligatoria.";
    }else if (fechaDesde.after(fechaHasta)) {
    	resultadoProcesoError = "La fecha hasta debe ser mayor o igual a la fecha desde.";
    }
    	
    if (ValidacionesString.esNuloOVacio(resultadoProcesoError))	{
    	
    	HSSFWorkbook wb = new HSSFWorkbook();       // crea libro
        HSSFSheet sheet = wb.createSheet("Operaciones");// crea hoja
        
        // Creamos un estilo para luego aplciarlo a las celdas
        CellStyle cellStyle = wb.createCellStyle();
        Font headersFont = wb.createFont();
        headersFont.setBold(true);
        cellStyle.setFont(headersFont);

        HSSFRow row1 = sheet.createRow(0);	// crea fila1
        HSSFCell a1 = row1.createCell(0);	// crea A1
        HSSFCell b1 = row1.createCell(1);	// crea B1
        HSSFCell c1 = row1.createCell(2);	// crea C1
        HSSFCell d1 = row1.createCell(3);	// crea D1
        HSSFCell e1 = row1.createCell(4);	// crea E1
        HSSFCell f1 = row1.createCell(5);	// crea F1
        HSSFCell g1 = row1.createCell(6);	// crea G1
        HSSFCell h1 = row1.createCell(7);	// crea H1
        HSSFCell i1 = row1.createCell(8);	// crea I1
        HSSFCell j1 = row1.createCell(9);	// crea J1
        HSSFCell k1 = row1.createCell(10);	// crea K1
        HSSFCell l1 = row1.createCell(11);	// crea L1
        HSSFCell m1 = row1.createCell(12);	// crea M1
        HSSFCell n1 = row1.createCell(13);	// crea M1
        HSSFCell o1 = row1.createCell(14);	// crea M1
        HSSFCell p1 = row1.createCell(13);	// crea M1
        HSSFCell q1 = row1.createCell(14);	// crea M1
        HSSFCell r1 = row1.createCell(15);
        
        // Aplicamos el estilo a cada celda
        a1.setCellStyle(cellStyle);
        b1.setCellStyle(cellStyle);
        c1.setCellStyle(cellStyle);
        d1.setCellStyle(cellStyle);
        e1.setCellStyle(cellStyle);
        f1.setCellStyle(cellStyle);
        g1.setCellStyle(cellStyle);
        h1.setCellStyle(cellStyle);
        i1.setCellStyle(cellStyle);
        j1.setCellStyle(cellStyle);
        k1.setCellStyle(cellStyle);
        l1.setCellStyle(cellStyle);
        m1.setCellStyle(cellStyle);  
        n1.setCellStyle(cellStyle);
        o1.setCellStyle(cellStyle);
        p1.setCellStyle(cellStyle);
        q1.setCellStyle(cellStyle);
        r1.setCellStyle(cellStyle);
        
        a1.setCellValue(new HSSFRichTextString("Codigo"));
        b1.setCellValue(new HSSFRichTextString("Fecha de Creaci贸n"));
        c1.setCellValue(new HSSFRichTextString("Correo"));
        d1.setCellValue(new HSSFRichTextString("Cliente"));
        e1.setCellValue(new HSSFRichTextString("Perfil"));
        f1.setCellValue(new HSSFRichTextString("Nombre de Perfil"));
        g1.setCellValue(new HSSFRichTextString("Raz贸n Social"));
        h1.setCellValue(new HSSFRichTextString("C贸digo de Operaci贸n Bancaria"));
        i1.setCellValue(new HSSFRichTextString("Cliente Envia"));
        j1.setCellValue(new HSSFRichTextString("Cliente Recibe"));
        k1.setCellValue(new HSSFRichTextString("Operaci贸n"));
        l1.setCellValue(new HSSFRichTextString("Tipo de Cambio"));
        m1.setCellValue(new HSSFRichTextString("Cup髇 Aplicado"));
        n1.setCellValue(new HSSFRichTextString("Plus de Cup髇"));
        o1.setCellValue(new HSSFRichTextString("Estado"));   
        p1.setCellValue(new HSSFRichTextString("Motivo Cancelaci贸n"));
        q1.setCellValue(new HSSFRichTextString("Fecha Cancelaci贸n"));
        r1.setCellValue(new HSSFRichTextString("Usuario Cancelaci贸n"));        
        
        
        Integer indicefila = 1;
        
        getOperacionesCliente();
        
        for(TpOperaClienDto temp : listaOperacionesControl) {
       	 
       	 HSSFRow row = sheet.createRow(indicefila);
       	 
       	 row.createCell(0).setCellValue(new HSSFRichTextString(temp.getCodUnicOperClie() != null ? temp.getCodUnicOperClie() : "No procesado"));
       	 row.createCell(1).setCellValue(new HSSFRichTextString(FechaUtil.formatoFechaHora(temp.getFecCreaRegi())));
       	 row.createCell(2).setCellValue(new HSSFRichTextString(temp.getTpClien().getTpUsuar().getIdeUsuaEmai()));
       	 row.createCell(3).setCellValue(new HSSFRichTextString(temp.getTpClien().getValNombreCompleto()));
       	 row.createCell(4).setCellValue(new HSSFRichTextString(temp.getTpClien().getTpUsuar().getCodUsuaPadr() != null ? "Empresa " : "Persona"));
       	 row.createCell(5).setCellValue(new HSSFRichTextString(temp.getTpClien().getValNombPerf() == null ? "-" : temp.getTpClien().getValNombPerf()));
       	 row.createCell(6).setCellValue(new HSSFRichTextString(temp.getTpClien().getValRazoSociPers() == null ? "-" : temp.getTpClien().getValRazoSociPers()));
       	 row.createCell(7).setCellValue(new HSSFRichTextString(temp.getCodTranBanc()));
       	 row.createCell(8).setCellValue(new HSSFRichTextString(temp.getMonEnvi()+" "+( temp.getIndCompVent() == 0 ? "D贸lares" : "Soles" )));
       	 row.createCell(9).setCellValue(new HSSFRichTextString(temp.getMonReci()+" "+( temp.getIndCompVent() == 1 ? "D贸lares" : "Soles" )));
       	 row.createCell(10).setCellValue(new HSSFRichTextString(temp.getIndCompVent() == 0 ? "COMPRA" : "VENTA" ));
       	 row.createCell(11).setCellValue(new HSSFRichTextString(temp.getValTipoCambUsad()+""));
       	 row.createCell(12).setCellValue(new HSSFRichTextString(temp.getNomCupoUsad().isEmpty() ? "-" :temp.getNomCupoUsad()));
       	 row.createCell(13).setCellValue(new HSSFRichTextString(temp.getMonDescCupoUsad() == null ? "-" :temp.getMonDescCupoUsad()+"" ));
       	 row.createCell(14).setCellValue(new HSSFRichTextString(temp.getTpEstadOpera().getDesEstaOper()));
       	 
       	 indicefila++;
       	 
        }
        
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);
        sheet.autoSizeColumn(11);
        sheet.autoSizeColumn(12);
        sheet.autoSizeColumn(13);
        sheet.autoSizeColumn(14);
        
        StringBuilder sb = new StringBuilder();
        
        final String ENTIDAD_REPORTE = "Operaciones";
        final String EXTENSION = ".xls";
        sb.append(ideUsuaEmai);
        sb.append(CadenasType.GUION_BAJO.getValor());
        sb.append(ENTIDAD_REPORTE);
        sb.append(CadenasType.GUION_BAJO.getValor());
        sb.append(FechaUtil.formatoFechaNombreArchivo(new Date()));
        sb.append(EXTENSION);
        
   	 FacesContext facesContext = FacesContext.getCurrentInstance();
   	 ExternalContext externalContext = facesContext.getExternalContext();
   	 externalContext.setResponseContentType("application/vnd.ms-excel");
   	 externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\""+sb.toString()+"\"");
   	 wb.write(externalContext.getResponseOutputStream());
   	 facesContext.responseComplete();
   	 wb.close();

    }else {
		LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
		PrimeFaces.current().executeScript("procesoConError();");
    }
    
   	    	 
   }
    
    
    
    public void procesarCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INDEX_PERSONAL.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
    }
    
    public void procesarRefrescarOperaciones() {
    	fechaDesde = new Date();
    	fechaHasta = null;
    }
    
//    public void procesarBuscarCodigo() {
//    	getOperacionesCliente();
//    }
    
	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public List<TpOperaClienDto> getListaOperacionesControl() {
		return listaOperacionesControl;
	}

	public void setListaOperacionesControl(List<TpOperaClienDto> listaOperacionesControl) {
		this.listaOperacionesControl = listaOperacionesControl;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public String getCodigoUnicoOperacionBuscar() {
		return codigoUnicoOperacionBuscar;
	}

	public void setCodigoUnicoOperacionBuscar(String codigoUnicoOperacionBuscar) {
		this.codigoUnicoOperacionBuscar = codigoUnicoOperacionBuscar;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	
    
}
