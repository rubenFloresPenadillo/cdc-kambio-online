package mangedBean;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import cadenas.util.ValidacionesString;
import dto.TpActivEconoDto;
import dto.TpClienDto;
import dto.TpDeparDto;
import dto.TpDistrDto;
import dto.TpPaisDto;
import dto.TpProviDto;
import dto.TpSectoEconoDto;
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
import util.types.EstadosCuentaUsuarioType;
import util.types.NumerosType;
import util.types.PaginasPrivadasType;
import util.types.PerfilesType;
import util.types.RegistroActivoType;

@ManagedBean(name="datosBeanEmpresa")
@ViewScoped
public class DatosEmpresaBean {

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
	private final Integer CODIGO_DEPARTAMENTO_LIMA = 14;
	private final Integer CODIGO_PROVINCIA_LIMA = 135;
	private final Integer CODIGO_DISTRITO_LIMA = 1352;
	private Boolean mostrarSelecionUbigeo;
	private Boolean indOperacionEnCurso;
	private Boolean mostrarDatosSeleccionPep;
	private Integer indSeleccionPep;
	private TpSectoEconoDto tpSectorEconoSeleccionado;
	private TpActivEconoDto tpActivEconoSeleccionado;
	private TpClienDto datosClientePadre;
	
	private List<SelectItem> listaComboSectorEconomico;
	private List<SelectItem> listaComboActividadEconomica;
	
	
	public DatosEmpresaBean() {
		HttpSession sesion = ConeccionSesion.getSession();
		System.out.println("Entro al constructor datosBean");
		resultadoProcesoExito = CadenasType.VACIO.getValor();
		resultadoProcesoError = CadenasType.VACIO.getValor();
		clienteFormulario = new TpClienDto();
		tpSectorEconoSeleccionado = new TpSectoEconoDto();
		tpActivEconoSeleccionado = new TpActivEconoDto();
//		clienteFormulario.setIndPepo(NumerosType.NUMERO_MINIMO_CERO.getValor());
		mostrarSelecionUbigeo = Boolean.TRUE;
		indSeleccionPep = NumerosType.NUMERO_MINIMO_CERO.getValor(); 
		datosClientePadre = new TpClienDto();
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
    	
//    	getListaTipoDocumentos();
//    	getPaisesGentilicios();
//    	getDepartamentos();
//    	getCombosFechaNacimiento();
    	indOperacionEnCurso = Boolean.FALSE;
    	getListaSectorEconomico() ;
    	getObtenerDatosClientePadre();
    	
    	// El codigo de cliente se crea despues de completar los datos, al darle click en el metodo validar() de la clase DatosBean
    	if(!ValidacionesNumeros.esCeroONuloEntero(codigoCliente)) {
    		getDatosCliente();
    		//getCargaIndicadorOperacionEnCurso();
    	}
    	
    }
    
//    private void getCargaIndicadorOperacionEnCurso() {
//    	ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
//		TpUsuarDto  resultadoTemporal = serviceUsuario.getOperacionEnCurso(codigoUsuario);
//		if(ValidacionesNumeros.esCeroONuloEntero(resultadoTemporal.getCodOperClie())) {
//			indOperacionEnCurso = Boolean.FALSE;
//		}else {
//			indOperacionEnCurso = Boolean.TRUE;
//		}
//    }
    
    
	public void getListaSectorEconomico() {
		
		listaComboSectorEconomico = new ArrayList<SelectItem>();
		ServiceCliente serviceCliente = new ServiceClienteImpl();
		
		for(TpSectoEconoDto temp : serviceCliente.getListaSectorEconomico(null)) {
			listaComboSectorEconomico.add(new SelectItem(temp.getCodSectEcon(), temp.getDesSectEcon()));
		}
	
	}    
    
	public void getListaActividadEconomica() {
		
		listaComboActividadEconomica = new ArrayList<SelectItem>();
		ServiceCliente serviceCliente = new ServiceClienteImpl();
		TpSectoEconoDto tpSectoEconoDto = new TpSectoEconoDto();
		tpSectoEconoDto.setCodSectEcon(tpSectorEconoSeleccionado.getCodSectEcon());
//		TpTipoDocumPersoDto tpTipoDocumPersoDto = new TpTipoDocumPersoDto();
//		tpTipoDocumPersoDto.getTpTipoPerso().setCodTipoPers(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento());
		
		
		for(TpActivEconoDto temp : serviceCliente.getListaActividadEconomica(tpSectoEconoDto)) {
			listaComboActividadEconomica.add(new SelectItem(temp.getCodActiEcon(), temp.getDesActiEcon()));
		}
	
	}  
	
	public void cargarActividadEconomica() {
		getListaActividadEconomica();
	}
	
	public void getObtenerDatosClientePadre() {
    	ServiceCliente serviceCliente = new ServiceClienteImpl(); 
    	datosClientePadre = serviceCliente.get(codigoCliente);
		
	}
	
	
	
    private void getDatosCliente() {
    	
    	ServiceCliente serviceCliente = new ServiceClienteImpl(); 
    	clienteFormulario = serviceCliente.get(codigoCliente);
    	
    	if(clienteFormulario != null) {
    		
    		tpSectorEconoSeleccionado.setCodSectEcon(clienteFormulario.getTpActivEcono().getCodActiEcon());
    		
    		
//    		valorCodigoDepartamento = clienteFormulario.getTpDepar().getCodDepa();
//        	valorCodigoProvincia = clienteFormulario.getTpProvi().getCodProv();
//        	valorCodigoDistrito = clienteFormulario.getTpDistr().getCodDist();
//
//        	LocalDate fechaNacimiento = clienteFormulario.getFecNaci().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        	
//        	valorFechaNaciAnio = fechaNacimiento.getYear();
//        	valorFechaNaciMes =  fechaNacimiento.getMonthValue();
//        	valorFechaNaciDia =  fechaNacimiento.getDayOfMonth();
//        	
//        	ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
//        	TpProviDto tpProviDto = serviceUbigeo.getProvincia(valorCodigoProvincia);
//        	TpDistrDto tpDistrDto = serviceUbigeo.getDistrito(valorCodigoDistrito); 
//        	
//        	listaComboProvincias = new ArrayList<SelectItem>();
//    		listaComboProvincias.add(new SelectItem(tpProviDto.getCodProv(), tpProviDto.getDesProv()));
//    		
//        	listaComboDistritos = new ArrayList<SelectItem>();
//        	listaComboDistritos.add(new SelectItem(tpDistrDto.getCodDist(), tpDistrDto.getDesDist()));
//        	
//        	if (NumerosType.INDICADOR_POSITIVO_UNO.getValor().equals(clienteFormulario.getIndPepo())) {
//        		mostrarDatosSeleccionPep = Boolean.TRUE;
//        		indSeleccionPep = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
//        	}else {
//        		mostrarDatosSeleccionPep = Boolean.FALSE;
//        		indSeleccionPep = NumerosType.NUMERO_MINIMO_CERO.getValor();
//        	}
        	
    	}
    	
	}

	
//	public void getListaTipoDocumentos() {
//		listaComboTipoDocumentosPerso = new ArrayList<SelectItem>();
//		ServiceUsuario serviceUsuario = new ServiceUsuarioImpl();
//		TpTipoDocumPersoDto tpTipoDocumPersoDto = new TpTipoDocumPersoDto();
//		tpTipoDocumPersoDto.getTpTipoPerso().setCodTipoPers(ElementosTablasType.TIPO_PERSONERIA_NATURAL.getIdElemento());
//		
//		
//		for(TpTipoDocumPersoDto temp : serviceUsuario.getTipoDocumentoPersona(tpTipoDocumPersoDto)) {
//			listaComboTipoDocumentosPerso.add(new SelectItem(temp.getCodTipoDocuPers(), temp.getNomTipoDocuPerso()));
//		}
//	
//	}
	
//	public void getPaisesGentilicios() {
//		listaComboPais = new ArrayList<SelectItem>();
//		listaComboNacionalidad = new ArrayList<SelectItem>();
//		
//		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
//		
//		for(TpPaisDto temp : serviceUbigeo.getPaises(null)) {
//			listaComboPais.add(new SelectItem(temp.getCodPais(), temp.getNomPais()));
//			listaComboNacionalidad.add(new SelectItem(temp.getCodPais(), temp.getDesGent()));
//		}
//		
//		clienteFormulario.getTpPaisByCodPaisResi().setCodPais(CODIGO_PAIS_PERU);
//	}

//	public void asignarMostrarSelecionUbigeo() {
//		if(!CODIGO_PAIS_PERU.equals(clienteFormulario.getTpPaisByCodPaisResi().getCodPais())) {
//			mostrarSelecionUbigeo = Boolean.FALSE;
//			valorCodigoDepartamento = null;
//	    	valorCodigoProvincia = null;
//	    	valorCodigoDistrito = null;
//			
//		}else {
//			mostrarSelecionUbigeo = Boolean.TRUE;
//		}
//	}
//	
//	
//	public void getDepartamentos() {
//		listaComboDepartamentos = new ArrayList<SelectItem>();
//		
//		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
//		
//		for(TpDeparDto temp : serviceUbigeo.getDepartamentos()) {
//			listaComboDepartamentos.add(new SelectItem(temp.getCodDepa(), temp.getDesDepa()));
//		}
//
//	}
//	
//	public void asignarProvincias() {
//		listaComboProvincias = new ArrayList<SelectItem>();
//		
//		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
//		
//		for(TpProviDto temp : serviceUbigeo.getProvincias(valorCodigoDepartamento)) {
//			listaComboProvincias.add(new SelectItem(temp.getCodProv(), temp.getDesProv()));
//		}
//		
//		listaComboDistritos = new ArrayList<SelectItem>();
//
//	}
//	
//	public void asignarDistritos() {
//		listaComboDistritos = new ArrayList<SelectItem>();
//		
//		ServiceUbigeo serviceUbigeo = new ServiceUbigeoImpl();
//		
//		for(TpDistrDto temp : serviceUbigeo.getDistritos(valorCodigoProvincia)) {
//			listaComboDistritos.add(new SelectItem(temp.getCodDist(), temp.getDesDist()));
//		}
//		
//	}
	
	public void procesarGuardarDatoEmpresa() {

		System.out.println("Entro a validar");
		String result = null;

		TpUsuarDto usuario = new TpUsuarDto();

		// usuario.setCodClav();
		usuario.setCodEstaCuenUsua(EstadosCuentaUsuarioType.ESTADO_CUENTA_USUARIO_ACTIVADA.getIdElemento());
		usuario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		// usuario.setValTokeCuen(codigoVerificacion.toString());
		// usuarioFormulario.setValTokeCuen(uuid.toString());
		usuario.setFecCreaToke(new Date());
		usuario.setCodPerfUsua(PerfilesType.CLIENTE.getIdElemento());
		usuario.setUsuApliCrea(ideUsuaEmai);
		usuario.setFecCreaRegi(new Date());
		usuario.getTpTipoPerso().setCodTipoPers(ElementosTablasType.TIPO_PERSONERIA_JURIDICA.getIdElemento());
		usuario.setIndCompDato(RegistroActivoType.ACTIVO.getLlave());
		usuario.setCodUsuaPadr(codigoUsuario);

		// ServiceUsuario serviceUsuarioRegistro = new ServiceUsuarioImpl();
		// result = serviceUsuarioRegistro.insertUpdate(usuario);

		TpDeparDto tpDeparDto = new TpDeparDto();
		tpDeparDto.setCodDepa(CODIGO_DEPARTAMENTO_LIMA);
		clienteFormulario.setTpDepar(tpDeparDto);

		TpProviDto tpProviDto = new TpProviDto();
		tpProviDto.setCodProv(CODIGO_PROVINCIA_LIMA);
		clienteFormulario.setTpProvi(tpProviDto);

		TpDistrDto tpDistrDto = new TpDistrDto();
		tpDistrDto.setCodDist(CODIGO_DISTRITO_LIMA);
		clienteFormulario.setTpDistr(tpDistrDto);
		
		TpPaisDto tpPaisByCodPaisNaciDto = new TpPaisDto();
		clienteFormulario.setTpPaisByCodPaisNaci(tpPaisByCodPaisNaciDto);
		
		TpPaisDto tpPaisByCodPaisResiDto = new TpPaisDto();
		clienteFormulario.setTpPaisByCodPaisResi(tpPaisByCodPaisResiDto);

		TpTipoDocumPersoDto tpTipoDocumPersoDto = new TpTipoDocumPersoDto();
		tpTipoDocumPersoDto.setCodTipoDocuPers(ElementosTablasType.TIPO_DOCUMENTO_PERSONA_RUC.getIdElemento());
		tpTipoDocumPersoDto.getTpTipoPerso().setCodTipoPers(ElementosTablasType.TIPO_PERSONERIA_JURIDICA.getIdElemento());
		clienteFormulario.setTpTipoDocumPerso(tpTipoDocumPersoDto);
		
		clienteFormulario.getTpPaisByCodPaisResi().setCodPais(CODIGO_PAIS_PERU);
		clienteFormulario.getTpPaisByCodPaisNaci().setCodPais(CODIGO_PAIS_PERU);
		
		clienteFormulario.setIndEsta(RegistroActivoType.ACTIVO.getLlave());
		clienteFormulario.setUsuApliCrea(String.valueOf(ideUsuaEmai));
		clienteFormulario.setFecCreaRegi(new Date());
		
		clienteFormulario.getTpActivEcono().setCodActiEcon(tpActivEconoSeleccionado.getCodActiEcon());
		
		clienteFormulario.setValPrimNombPers(datosClientePadre.getValPrimNombPers());
		clienteFormulario.setValSeguNombPers(datosClientePadre.getValSeguNombPers());
		clienteFormulario.setValPrimApelPers(datosClientePadre.getValPrimApelPers());
		clienteFormulario.setValSeguApelPers(datosClientePadre.getValSeguApelPers());

		ServiceCliente serviceCliente = new ServiceClienteImpl();
		result = serviceCliente.insertUpdateEnterprise(usuario, clienteFormulario);

		if (result.startsWith(CadenasType.INDICADOR_PROCESO_OK.getValor())) {

			if (ValidacionesNumeros.esCeroONuloEntero(codigoCliente)) {
//				String[] respuesta = result.split(CadenasType.GUION.getValor());
//				codigoCliente = Integer.parseInt(respuesta[1]);
//				valorNombre = String.valueOf(respuesta[2]);
//				indCompleDatos = NumerosType.INDICADOR_POSITIVO_UNO.getValor();
//				HttpSession sesion = ConeccionSesion.getSession();
//				sesion.setAttribute("codigoCliente", codigoCliente);
//				sesion.setAttribute("valorNombre", valorNombre);
//				sesion.setAttribute("indCompleDatos", indCompleDatos);
			}
			PrimeFaces.current().executeScript("operacionDatosGuardadosExito();");

		} else if (result.startsWith(CadenasType.INDICADOR_PROCESO_ACTUALIZA_OK.getValor())) {
			PrimeFaces.current().executeScript("operacionDatosActualizaExito();");

		} else {
			resultadoProcesoError = result;
			LoggerUtil.getInstance().getLogger().error(resultadoProcesoError);
			 PrimeFaces.current().executeScript("procesoConError();");
		}

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
    
	public void redireccionarElegirPerfil() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+PaginasPrivadasType.PAGINA_ELEGIR_PERFIL.getValor());
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

	public TpSectoEconoDto getTpSectorEconoSeleccionado() {
		return tpSectorEconoSeleccionado;
	}

	public void setTpSectorEconoSeleccionado(TpSectoEconoDto tpSectorEconoSeleccionado) {
		this.tpSectorEconoSeleccionado = tpSectorEconoSeleccionado;
	}

	public TpActivEconoDto getTpActivEconoSeleccionado() {
		return tpActivEconoSeleccionado;
	}

	public void setTpActivEconoSeleccionado(TpActivEconoDto tpActivEconoSeleccionado) {
		this.tpActivEconoSeleccionado = tpActivEconoSeleccionado;
	}

	public List<SelectItem> getListaComboSectorEconomico() {
		return listaComboSectorEconomico;
	}

	public void setListaComboSectorEconomico(List<SelectItem> listaComboSectorEconomico) {
		this.listaComboSectorEconomico = listaComboSectorEconomico;
	}

	public List<SelectItem> getListaComboActividadEconomica() {
		return listaComboActividadEconomica;
	}

	public void setListaComboActividadEconomica(List<SelectItem> listaComboActividadEconomica) {
		this.listaComboActividadEconomica = listaComboActividadEconomica;
	}

	

}
