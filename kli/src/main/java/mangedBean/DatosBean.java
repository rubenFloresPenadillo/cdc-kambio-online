package mangedBean;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpClienDto;
import dto.TpCuentBancoDto;
import dto.TpDeparDto;
import dto.TpDistrDto;
import dto.TpPaisDto;
import dto.TpProviDto;
import dto.TpTipoDocumPersoDto;
import dto.TpUsuarDto;
import loggerUtil.LoggerUtil;
import numeros.util.ValidacionesNumeros;
import service.ServiceCliente;
import service.ServiceUbigeo;
import service.ServiceUsuario;
import service.impl.ServiceClienteImpl;
import service.impl.ServiceUbigeoImpl;
import service.impl.ServiceUsuarioImpl;
import util.sesion.ConeccionSesion;
import util.types.CadenasType;
import util.types.ElementosTablasType;
import util.types.MesType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.RegistroActivoType;

@ManagedBean(name="datosBean")
@ViewScoped
public class DatosBean {

	private List<SelectItem> listaComboTipoDocumentosPerso;
	private List<SelectItem> listaComboPais;
	private List<SelectItem> listaComboNacionalidad;
	private List<SelectItem> listaComboDepartamentos;
	private List<SelectItem> listaComboProvincias;
	private List<SelectItem> listaComboDistritos;
	private List<SelectItem> listaComboFechaAnio;
	private List<SelectItem> listaComboFechaMes;
	private List<SelectItem> listaComboFechaDia;
	private Integer valorCodigoDepartamento;
	private Integer valorCodigoProvincia;
	private Integer valorCodigoDistrito;
	private Integer valorFechaNaciDia;
	private Integer valorFechaNaciMes;
	private Integer valorFechaNaciAnio;
	private Integer codigoUsuario;
	private Integer codigoCliente;
	
	private String resultadoProcesoExito;
	private String resultadoProcesoError;
	private TpClienDto clienteFormulario;
	private String valorNombre;
	private Integer indCompleDatos;
	private String ideUsuaEmai;
	private final Integer CODIGO_PAIS_PERU = 117;
	private Boolean mostrarSelecionUbigeo;
	private Boolean indOperacionEnCurso;
	private Boolean mostrarDatosSeleccionPep;
	private Integer indSeleccionPep;
	
	public DatosBean() {
		System.out.println("Entro al constructor datosBean");
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		clienteFormulario = new TpClienDto();
		clienteFormulario.setIndPepo(NumerosType.NUMERO_MINIMO_CERO.getValor());
		mostrarSelecionUbigeo = Boolean.TRUE;
		indSeleccionPep = NumerosType.NUMERO_MINIMO_CERO.getValor(); 
	}
	
    /**
     * Realiza su ejecución despues del Constructor de la clase DatosBean.
     */
    @PostConstruct
    public void init() {
    	HttpSession sesion = ConeccionSesion.getSession();
    	valorNombre = (String) sesion.getAttribute("valorNombre");
    	indCompleDatos = (Integer) sesion.getAttribute("indCompleDatos");
    	ideUsuaEmai = (String) sesion.getAttribute("ideUsuaEmai");
    	if(ValidacionesString.esNuloOVacio(valorNombre)) {
    		valorNombre = CadenasType.CADENA_USUARIO.getValor();
    	}
    	codigoUsuario = (Integer) sesion.getAttribute("codigoUsuario");
    	codigoCliente = (Integer) sesion.getAttribute("codigoCliente");
    	getListaTipoDocumentos();
    	getPaisesGentilicios();
    	getDepartamentos();
    	getCombosFechaNacimiento();
    	indOperacionEnCurso = Boolean.FALSE;
    	
    	// El codigo de cliente se crea despues de completar los datos, al darle click en el metodo validar() de la clase DatosBean
    	if(!ValidacionesNumeros.esCeroONuloEntero(codigoCliente)) {
    		getDatosCliente();
    		getCargaIndicadorOperacionEnCurso();
    	}
    	
    }
    
    private void getCargaIndicadorOperacionEnCurso() {
    	ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		TpUsuarDto  resultadoTemporal = serviceUsuario.getOperacionEnCurso(codigoUsuario);
		if(ValidacionesNumeros.esCeroONuloEntero(resultadoTemporal.getCodOperClie())) {
			indOperacionEnCurso = Boolean.FALSE;
		}else {
			indOperacionEnCurso = Boolean.TRUE;
		}
    }
    
    private void getDatosCliente() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl(); 
    	clienteFormulario = serviceCliente.get(codigoCliente);
    	
    	if(clienteFormulario != null) {
    		valorCodigoDepartamento = clienteFormulario.getTpDepar().getCodDepa();
        	valorCodigoProvincia = clienteFormulario.getTpProvi().getCodProv();
        	valorCodigoDistrito = clienteFormulario.getTpDistr().getCodDist();

        	LocalDate fechaNacimiento = clienteFormulario.getFecNaci().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	
        	valorFechaNaciAnio = fechaNacimiento.getYear();
        	valorFechaNaciMes =  fechaNacimiento.getMonthValue();
        	valorFechaNaciDia =  fechaNacimiento.getDayOfMonth();
        	
        	ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
        	TpProviDto tpProviDto = serviceUbigeo.getProvincia(valorCodigoProvincia);
        	TpDistrDto tpDistrDto = serviceUbigeo.getDistrito(valorCodigoDistrito); 
        	
        	listaComboProvincias = new ArrayList<SelectItem>();
    		listaComboProvincias.add(new SelectItem(tpProviDto.getCodProv(), tpProviDto.getDesProv()));
    		
        	listaComboDistritos = new ArrayList<SelectItem>();
        	listaComboDistritos.add(new SelectItem(tpDistrDto.getCodDist(), tpDistrDto.getDesDist()));
        	
        	if (NumerosType.INDICADOR_POSITIVO_UNO.getValor().equals(clienteFormulario.getIndPepo())) {
        		mostrarDatosSeleccionPep = Boolean.TRUE;
        		indSeleccionPep = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
        	}else {
        		mostrarDatosSeleccionPep = Boolean.FALSE;
        		indSeleccionPep = NumerosType.NUMERO_MINIMO_CERO.getValor();
        	}
        	
    	}
    	
	}

	public void getCombosFechaNacimiento() {
    	LocalDate fechaActual = LocalDate.now();
    	
    	System.out.println("hoy "+fechaActual.getYear());
    	System.out.println("a "+fechaActual.minusYears(18).getYear());
    	System.out.println("b "+fechaActual.minusYears(80).getYear());
    	
    	listaComboFechaAnio = new ArrayList<SelectItem>();
    	listaComboFechaMes = new ArrayList<SelectItem>();
    	listaComboFechaDia = new ArrayList<SelectItem>();
    	
    	IntStream it= IntStream.rangeClosed(fechaActual.minusYears(80).getYear(), fechaActual.minusYears(18).getYear()).map(i -> fechaActual.minusYears(18).getYear() - i + fechaActual.minusYears(80).getYear());
    	it.forEach(i-> listaComboFechaAnio.add(new SelectItem(i, String.valueOf(i))));

    	listaComboFechaMes.add(new SelectItem(MesType.ENERO.getValor(), MesType.ENERO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.FEBRERO.getValor(), MesType.FEBRERO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.MARZO.getValor(), MesType.MARZO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.ABRIL.getValor(), MesType.ABRIL.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.MAYO.getValor(), MesType.MAYO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.JUNIO.getValor(), MesType.JUNIO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.JULIO.getValor(), MesType.JULIO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.AGOSTO.getValor(), MesType.AGOSTO.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.SETIEMBRE.getValor(), MesType.SETIEMBRE.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.OCTUBRE.getValor(), MesType.OCTUBRE.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.NOVIEMBRE.getValor(), MesType.NOVIEMBRE.getNombre()));
    	listaComboFechaMes.add(new SelectItem(MesType.DICIEMBRE.getValor(), MesType.DICIEMBRE.getNombre()));
    	 
    	IntStream iS= IntStream.rangeClosed(NumerosType.INDICADOR_POSITIVO_UNO.getValor(),31);
    	iS.forEach(i-> listaComboFechaDia.add(new SelectItem(i, String.valueOf(i))));
    	
    	
    }
    
	public void asignarDiasPorMes() {
		
		if(valorFechaNaciMes > 0) {
			listaComboFechaDia.clear();
			LocalDate date = LocalDate.of(valorFechaNaciAnio == null ? 1938 : valorFechaNaciAnio , valorFechaNaciMes, 01);
	    	IntStream iS= IntStream.rangeClosed(NumerosType.INDICADOR_POSITIVO_UNO.getValor(), date.lengthOfMonth());
	    	iS.forEach(i-> listaComboFechaDia.add(new SelectItem(i, String.valueOf(i))));
		}
		
	}
	
	public void asignarDiasPorAnio() {
		
		if(MesType.FEBRERO.getValor().equals(valorFechaNaciMes)) {
			listaComboFechaDia.clear();
			YearMonth anioEnMes = YearMonth.of(valorFechaNaciAnio, valorFechaNaciMes);
			IntStream iS= IntStream.rangeClosed(NumerosType.INDICADOR_POSITIVO_UNO.getValor(), anioEnMes.lengthOfMonth());
	    	iS.forEach(i-> listaComboFechaDia.add(new SelectItem(i, String.valueOf(i))));
		}
		
	}
	
	public void asignarNacionalidadPeruana() {
		
		if(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_DNI.getIdElemento().equals(clienteFormulario.getTpTipoDocumPerso().getCodTipoDocuPers())) {
			clienteFormulario.getTpPaisByCodPaisNaci().setCodPais(CODIGO_PAIS_PERU);
		}else {
			clienteFormulario.getTpPaisByCodPaisNaci().setCodPais(null);
		}
		
	}
	
	
	public void getListaTipoDocumentos() {
		listaComboTipoDocumentosPerso = new ArrayList<SelectItem>();
		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
		TpTipoDocumPersoDto tpTipoDocumPersoDto = new TpTipoDocumPersoDto();
		tpTipoDocumPersoDto.getTpTipoPerso().setCodTipoPers(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento());
		
		
		for(TpTipoDocumPersoDto temp : serviceUsuario.getTipoDocumentoPersona(tpTipoDocumPersoDto)) {
			listaComboTipoDocumentosPerso.add(new SelectItem(temp.getCodTipoDocuPers(), temp.getNomTipoDocuPerso()));
		}
	
	}
	
	public void getPaisesGentilicios() {
		listaComboPais = new ArrayList<SelectItem>();
		listaComboNacionalidad = new ArrayList<SelectItem>();
		
		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
		
		for(TpPaisDto temp : serviceUbigeo.getPaises(null)) {
			listaComboPais.add(new SelectItem(temp.getCodPais(), temp.getNomPais()));
			listaComboNacionalidad.add(new SelectItem(temp.getCodPais(), temp.getDesGent()));
		}
		
		clienteFormulario.getTpPaisByCodPaisResi().setCodPais(CODIGO_PAIS_PERU);
	}

	public void asignarMostrarSelecionUbigeo() {
		if(!CODIGO_PAIS_PERU.equals(clienteFormulario.getTpPaisByCodPaisResi().getCodPais())) {
			mostrarSelecionUbigeo = Boolean.FALSE;
			valorCodigoDepartamento = null;
	    	valorCodigoProvincia = null;
	    	valorCodigoDistrito = null;
			
		}else {
			mostrarSelecionUbigeo = Boolean.TRUE;
		}
	}
	
	
	public void getDepartamentos() {
		listaComboDepartamentos = new ArrayList<SelectItem>();
		
		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
		
		for(TpDeparDto temp : serviceUbigeo.getDepartamentos()) {
			listaComboDepartamentos.add(new SelectItem(temp.getCodDepa(), temp.getDesDepa()));
		}

	}
	
	public void asignarProvincias() {
		listaComboProvincias = new ArrayList<SelectItem>();
		
		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
		
		for(TpProviDto temp : serviceUbigeo.getProvincias(valorCodigoDepartamento)) {
			listaComboProvincias.add(new SelectItem(temp.getCodProv(), temp.getDesProv()));
		}
		
		listaComboDistritos = new ArrayList<SelectItem>();

	}
	
	public void asignarDistritos() {
		listaComboDistritos = new ArrayList<SelectItem>();
		
		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
		
		for(TpDistrDto temp : serviceUbigeo.getDistritos(valorCodigoProvincia)) {
			listaComboDistritos.add(new SelectItem(temp.getCodDist(), temp.getDesDist()));
		}
		
	}
	
	public void validar() {
		
    	System.out.println("Entro a validar");
    	String result = null;
        
    	LocalDate fechaNacimiento = LocalDate.of(valorFechaNaciAnio, valorFechaNaciMes, valorFechaNaciDia);
    	Instant instant = fechaNacimiento.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    	Date fechaNacimientoUtil = Date.from(instant);
    	
    	System.out.println("fechaNacimientoUtil "+fechaNacimientoUtil);
    	
    	clienteFormulario.setFecNaci(fechaNacimientoUtil);
    	clienteFormulario.getTpUsuar().setCodUsua(codigoUsuario);
    	
    	if(!ValidacionesNumeros.esCeroONuloEntero(valorCodigoDepartamento)) {
    		TpDeparDto tpDeparDto = new TpDeparDto();
    		tpDeparDto.setCodDepa(valorCodigoDepartamento);
    		clienteFormulario.setTpDepar(tpDeparDto);
    	}
    	
    	if(!ValidacionesNumeros.esCeroONuloEntero(valorCodigoProvincia)) {
    		TpProviDto tpProviDto = new TpProviDto();
    		tpProviDto.setCodProv(valorCodigoProvincia);
    		clienteFormulario.setTpProvi(tpProviDto);
    	}
    	
    	if(!ValidacionesNumeros.esCeroONuloEntero(valorCodigoDistrito)) {
    		TpDistrDto tpDistrDto = new TpDistrDto();
    		tpDistrDto.setCodDist(valorCodigoDistrito);
    		clienteFormulario.setTpDistr(tpDistrDto);
    	}
    	
    	clienteFormulario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
    	clienteFormulario.setUsuApliCrea(String.valueOf(ideUsuaEmai));
    	clienteFormulario.setFecCreaRegi(new Date());
    	clienteFormulario.getTpActivEcono().setCodActiEcon(NumerosType.NUMERO_MINIMO_CERO.getValor());
    	
    	
    	
    	ServiceCliente serviceCliente = new ServiceClienteImpl(); 
    	result = serviceCliente.insertUpdate(clienteFormulario);
    	
        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
        	if(ValidacionesNumeros.esCeroONuloEntero(codigoCliente)) {
        		String[] respuesta = result.split(CadenasType.GUION.getValor());
        		codigoCliente = Integer.parseInt(respuesta[1]);
        		valorNombre = String.valueOf(respuesta[2]);
        		indCompleDatos = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
        		HttpSession sesion = ConeccionSesion.getSession();
				sesion.setAttribute("codigoCliente", codigoCliente);
				sesion.setAttribute("valorNombre", valorNombre);
				sesion.setAttribute("indCompleDatos", indCompleDatos);
        	}
        	
//        	resultadoProcesoExito = "Datos guardados con éxito.";
        	PrimeFaces.current().executeScript("operacionDatosGuardadosExito();");
        } else if (result.startsWith(CadenasType.INDICADOR_PROCESO_ACTUALIZA_OK.getValor())) {
        	PrimeFaces.current().executeScript("operacionDatosActualizaExito();");
        } else {
        	resultadoProcesoError = result;
        	LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
        	PrimeFaces.current().ajax().update("idFormDatosPersonales:idMensajeResultadoProceso");
        	PrimeFaces.current().executeScript("procesoConError();");
	        
        }
        
        
//        if(result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {
//
//        	cuentaBancariaAdminItem = new TpCuentBancoDto();
//        	PrimeFaces.current().executeScript("operacionGuardarCuentaBancariaAdminExitosa();");
//        }else {
//        	resultadoProcesoError = result;
//        	PrimeFaces.current().executeScript("procesoConError();");
//        }

    }
	
	public void habilitarDatosPep() {
		if(NumerosType.INDICADOR_POSITIVO_UNO.getValor().equals(indSeleccionPep)) {
			mostrarDatosSeleccionPep  = Boolean.TRUE;
			clienteFormulario.setIndPepo(NumerosType.INDICADOR_POSITIVO_UNO.getValor());
		}else {
			mostrarDatosSeleccionPep  = Boolean.FALSE;
			clienteFormulario.setIndPepo(NumerosType.NUMERO_MINIMO_CERO.getValor());
		}
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
    
	public void redireccionarInicioOperacion() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_INICIO.getValor());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.getInstance().getLogger().error(e.getMessage());
	        LoggerUtil.getInstance().getLogger().error(e);
		}
	}
	
	public List<SelectItem> getListaComboTipoDocumentosPerso() {
		return listaComboTipoDocumentosPerso;
	}

	public void setListaComboTipoDocumentosPerso(List<SelectItem> listaComboTipoDocumentosPerso) {
		this.listaComboTipoDocumentosPerso = listaComboTipoDocumentosPerso;
	}

	public List<SelectItem> getListaComboPais() {
		return listaComboPais;
	}

	public void setListaComboPais(List<SelectItem> listaComboPais) {
		this.listaComboPais = listaComboPais;
	}

	public List<SelectItem> getListaComboNacionalidad() {
		return listaComboNacionalidad;
	}

	public void setListaComboNacionalidad(List<SelectItem> listaComboNacionalidad) {
		this.listaComboNacionalidad = listaComboNacionalidad;
	}

	public List<SelectItem> getListaComboDepartamentos() {
		return listaComboDepartamentos;
	}

	public void setListaComboDepartamentos(List<SelectItem> listaComboDepartamentos) {
		this.listaComboDepartamentos = listaComboDepartamentos;
	}

	public List<SelectItem> getListaComboProvincias() {
		return listaComboProvincias;
	}

	public void setListaComboProvincias(List<SelectItem> listaComboProvincias) {
		this.listaComboProvincias = listaComboProvincias;
	}

	public List<SelectItem> getListaComboDistritos() {
		return listaComboDistritos;
	}

	public void setListaComboDistritos(List<SelectItem> listaComboDistritos) {
		this.listaComboDistritos = listaComboDistritos;
	}

	public Integer getValorCodigoDepartamento() {
		return valorCodigoDepartamento;
	}

	public void setValorCodigoDepartamento(Integer valorCodigoDepartamento) {
		this.valorCodigoDepartamento = valorCodigoDepartamento;
	}

	public Integer getValorCodigoProvincia() {
		return valorCodigoProvincia;
	}

	public void setValorCodigoProvincia(Integer valorCodigoProvincia) {
		this.valorCodigoProvincia = valorCodigoProvincia;
	}

	public Integer getValorCodigoDistrito() {
		return valorCodigoDistrito;
	}

	public void setValorCodigoDistrito(Integer valorCodigoDistrito) {
		this.valorCodigoDistrito = valorCodigoDistrito;
	}

	public String getResultadoProcesoExito() {
		return resultadoProcesoExito;
	}

	public void setResultadoProcesoExito(String resultadoProcesoExito) {
		this.resultadoProcesoExito = resultadoProcesoExito;
	}

	public String getResultadoProcesoError() {
		return resultadoProcesoError;
	}

	public void setResultadoProcesoError(String resultadoProcesoError) {
		this.resultadoProcesoError = resultadoProcesoError;
	}

	public List<SelectItem> getListaComboFechaAnio() {
		return listaComboFechaAnio;
	}

	public void setListaComboFechaAnio(List<SelectItem> listaComboFechaAnio) {
		this.listaComboFechaAnio = listaComboFechaAnio;
	}

	public List<SelectItem> getListaComboFechaMes() {
		return listaComboFechaMes;
	}

	public void setListaComboFechaMes(List<SelectItem> listaComboFechaMes) {
		this.listaComboFechaMes = listaComboFechaMes;
	}

	public List<SelectItem> getListaComboFechaDia() {
		return listaComboFechaDia;
	}

	public void setListaComboFechaDia(List<SelectItem> listaComboFechaDia) {
		this.listaComboFechaDia = listaComboFechaDia;
	}

	public TpClienDto getClienteFormulario() {
		return clienteFormulario;
	}

	public void setClienteFormulario(TpClienDto clienteFormulario) {
		this.clienteFormulario = clienteFormulario;
	}

	public Integer getValorFechaNaciDia() {
		return valorFechaNaciDia;
	}

	public void setValorFechaNaciDia(Integer valorFechaNaciDia) {
		this.valorFechaNaciDia = valorFechaNaciDia;
	}

	public Integer getValorFechaNaciMes() {
		return valorFechaNaciMes;
	}

	public void setValorFechaNaciMes(Integer valorFechaNaciMes) {
		this.valorFechaNaciMes = valorFechaNaciMes;
	}

	public Integer getValorFechaNaciAnio() {
		return valorFechaNaciAnio;
	}

	public void setValorFechaNaciAnio(Integer valorFechaNaciAnio) {
		this.valorFechaNaciAnio = valorFechaNaciAnio;
	}

	public String getValorNombre() {
		return valorNombre;
	}

	public void setValorNombre(String valorNombre) {
		this.valorNombre = valorNombre;
	}

	public Integer getIndCompleDatos() {
		return indCompleDatos;
	}

	public void setIndCompleDatos(Integer indCompleDatos) {
		this.indCompleDatos = indCompleDatos;
	}

	public Boolean getMostrarSelecionUbigeo() {
		return mostrarSelecionUbigeo;
	}

	public void setMostrarSelecionUbigeo(Boolean mostrarSelecionUbigeo) {
		this.mostrarSelecionUbigeo = mostrarSelecionUbigeo;
	}

	public Boolean getIndOperacionEnCurso() {
		return indOperacionEnCurso;
	}

	public void setIndOperacionEnCurso(Boolean indOperacionEnCurso) {
		this.indOperacionEnCurso = indOperacionEnCurso;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getIndSeleccionPep() {
		return indSeleccionPep;
	}

	public void setIndSeleccionPep(Integer indSeleccionPep) {
		this.indSeleccionPep = indSeleccionPep;
	}

	public Boolean getMostrarDatosSeleccionPep() {
		return mostrarDatosSeleccionPep;
	}

	public void setMostrarDatosSeleccionPep(Boolean mostrarDatosSeleccionPep) {
		this.mostrarDatosSeleccionPep = mostrarDatosSeleccionPep;
	}

//	public Boolean getIndSeleccionPep() {
//		return indSeleccionPep;
//	}
//
//	public void setIndSeleccionPep(Boolean indSeleccionPep) {
//		this.indSeleccionPep = indSeleccionPep;
//	}

	
}
