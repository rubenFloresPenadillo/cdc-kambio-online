package service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

import dto.TpFactuDocumCabecDto;
import dto.TpFactuDocumDetalDto;
import hibernate.entidades.TpFactuDocumCabec;
import hibernate.entidades.TpFactuDocumDetal;
import hibernate.util.HibernateUtil;
import model.dao.DaoFacturacionElectronica;
import model.dao.impl.DaoFacturacionElectronicaImpl;
import service.ServiceFacturacionElectronica;
import util.constantes.Constants;

public class ServiceFacturacionElectronicaImpl  implements ServiceFacturacionElectronica{

    private DaoFacturacionElectronica daoFacturacionElectronica;
    private Session session;

    public ServiceFacturacionElectronicaImpl() {
        session =  HibernateUtil.getSessionFactory().openSession();
        daoFacturacionElectronica = new DaoFacturacionElectronicaImpl(session);
    }

	@Override
	public String generarComprobanteElectronico(TpFactuDocumCabecDto tpFactuDocumCabecDto,
			List<TpFactuDocumDetalDto> listTpFactuDocumDetalDto) {
		
		TpFactuDocumCabec tpFactuDocumCabec = new TpFactuDocumCabec();
		
//		tpFactuDocumCabec.setCodFactDocuCabec(tpFactuDocumCabecDto.getCodFactDocuCabec());
		tpFactuDocumCabec.setCodOperClie(tpFactuDocumCabecDto.getCodOperClie());
		tpFactuDocumCabec.setTipoDeComprobante(tpFactuDocumCabecDto.getTipoDeComprobante());
//		tpFactuDocumCabec.setSerie(tpFactuDocumCabecDto.getSerie());
//		tpFactuDocumCabec.setNumero(tpFactuDocumCabecDto.getNumero());
		tpFactuDocumCabec.setSunatTransaction(Constants.FACTURACION_ELECTRONICA_SUNAT_TRANSACTION);
		tpFactuDocumCabec.setClienteTipoDeDocumento(tpFactuDocumCabecDto.getClienteTipoDeDocumento());
		tpFactuDocumCabec.setClienteNumeroDeDocumento(tpFactuDocumCabecDto.getClienteNumeroDeDocumento());
		tpFactuDocumCabec.setClienteDenominacion(tpFactuDocumCabecDto.getClienteDenominacion());
		tpFactuDocumCabec.setClienteDireccion(tpFactuDocumCabecDto.getClienteDireccion());
		tpFactuDocumCabec.setClienteEmail(tpFactuDocumCabecDto.getClienteEmail());
		tpFactuDocumCabec.setClienteEmail1(tpFactuDocumCabecDto.getClienteEmail1());
		tpFactuDocumCabec.setClienteEmail2(tpFactuDocumCabecDto.getClienteEmail2());
		tpFactuDocumCabec.setFechaDeEmision(tpFactuDocumCabecDto.getFechaDeEmision());
		tpFactuDocumCabec.setFechaDeVencimiento(tpFactuDocumCabecDto.getFechaDeVencimiento());
		tpFactuDocumCabec.setMoneda(Constants.FACTURACION_ELECTRONICA_MONEDA);
		tpFactuDocumCabec.setTipoDeCambio(tpFactuDocumCabecDto.getTipoDeCambio());
		tpFactuDocumCabec.setPorcentajeDeIgv(Constants.FACTURACION_ELECTRONICA_PORCENTAJE_DE_IGV);
		tpFactuDocumCabec.setDescuentoGlobal(tpFactuDocumCabecDto.getDescuentoGlobal());
		tpFactuDocumCabec.setTotalDescuento(tpFactuDocumCabecDto.getTotalDescuento());
		tpFactuDocumCabec.setTotalAnticipo(tpFactuDocumCabecDto.getTotalAnticipo());
		tpFactuDocumCabec.setTotalGravada(tpFactuDocumCabecDto.getTotalGravada());
		tpFactuDocumCabec.setTotalInafecta(tpFactuDocumCabecDto.getTotalInafecta());
		tpFactuDocumCabec.setTotalExonerada(tpFactuDocumCabecDto.getTotalExonerada());
		tpFactuDocumCabec.setTotalIgv(tpFactuDocumCabecDto.getTotalIgv());
		tpFactuDocumCabec.setTotalGratuita(tpFactuDocumCabecDto.getTotalGratuita());
		tpFactuDocumCabec.setTotalOtrosCargos(tpFactuDocumCabecDto.getTotalOtrosCargos());
		tpFactuDocumCabec.setTotalIsc(tpFactuDocumCabecDto.getTotalIsc());
		tpFactuDocumCabec.setTotal(tpFactuDocumCabecDto.getTotal());
		tpFactuDocumCabec.setPercepcionTipo(tpFactuDocumCabecDto.getPercepcionTipo());
		tpFactuDocumCabec.setPercepcionBaseImponible(tpFactuDocumCabecDto.getPercepcionBaseImponible());
		tpFactuDocumCabec.setTotalPercepcion(tpFactuDocumCabecDto.getTotalPercepcion());
		tpFactuDocumCabec.setTotalIncluidoPercepcion(tpFactuDocumCabecDto.getTotalIncluidoPercepcion());
		tpFactuDocumCabec.setDetraccion(Constants.FACTURACION_ELECTRONICA_FLAG_DETRACCION);
		tpFactuDocumCabec.setObservaciones(tpFactuDocumCabecDto.getObservaciones());
		tpFactuDocumCabec.setDocumentoQueSeModificaTipo(tpFactuDocumCabecDto.getDocumentoQueSeModificaTipo());
		tpFactuDocumCabec.setDocumentoQueSeModificaSerie(tpFactuDocumCabecDto.getDocumentoQueSeModificaSerie());
		tpFactuDocumCabec.setDocumentoQueSeModificaNumero(tpFactuDocumCabecDto.getDocumentoQueSeModificaNumero());
		tpFactuDocumCabec.setTipoDeNotaDeCredito(tpFactuDocumCabecDto.getTipoDeNotaDeCredito());
		tpFactuDocumCabec.setTipoDeNotaDeDebito(tpFactuDocumCabecDto.getTipoDeNotaDeDebito());
		tpFactuDocumCabec.setEnviarAutomaticamenteALaSunat(Constants.FACTURACION_ELECTRONICA_FLAG_ENVIO_SUNAT);
		tpFactuDocumCabec.setEnviarAutomaticamenteAlCliente(Constants.FACTURACION_ELECTRONICA_FLAG_ENVIO_CLIENTE);
		tpFactuDocumCabec.setCodigoUnico(tpFactuDocumCabecDto.getCodigoUnico());
		tpFactuDocumCabec.setCondicionesDePago(tpFactuDocumCabecDto.getCondicionesDePago());
		tpFactuDocumCabec.setMedioDePago(tpFactuDocumCabecDto.getMedioDePago());
		tpFactuDocumCabec.setPlacaVehiculo(tpFactuDocumCabecDto.getPlacaVehiculo());
		tpFactuDocumCabec.setOrdenCompraServicio(tpFactuDocumCabecDto.getOrdenCompraServicio());
		tpFactuDocumCabec.setFormatoDePdf(Constants.FACTURACION_ELECTRONICA_FORMATO_DE_PDF);
		tpFactuDocumCabec.setCodEstaDocu(tpFactuDocumCabecDto.getCodEstaDocu());
		tpFactuDocumCabec.setDesCortDocuGene(tpFactuDocumCabecDto.getDesCortDocuGene());
		tpFactuDocumCabec.setFecCreaRegi(tpFactuDocumCabecDto.getFecCreaRegi());
		tpFactuDocumCabec.setIndEsta(tpFactuDocumCabecDto.getIndEsta());
		tpFactuDocumCabec.setUsuApliCrea(String.valueOf(tpFactuDocumCabecDto.getUsuApliCrea()));
		
		List<TpFactuDocumDetal> listTpFactuDocumDetal = new ArrayList<TpFactuDocumDetal>();
		
		for(TpFactuDocumDetalDto temp : listTpFactuDocumDetalDto) {
			
			TpFactuDocumDetal tpFactuDocumDetal = new TpFactuDocumDetal();
			
			tpFactuDocumDetal.setCodFactDocuDetal(temp.getCodFactDocuDetal());
			tpFactuDocumDetal.setTpFactuDocumCabec(tpFactuDocumCabec);
			tpFactuDocumDetal.setUnidadDeMedida(Constants.FACTURACION_ELECTRONICA_UNIDAD_MEDIDA);
			tpFactuDocumDetal.setCodigo(temp.getCodigo());
			tpFactuDocumDetal.setDescripcion(temp.getDescripcion());
			tpFactuDocumDetal.setCantidad(temp.getCantidad());
			tpFactuDocumDetal.setValorUnitario(temp.getValorUnitario());
			tpFactuDocumDetal.setPrecioUnitario(temp.getPrecioUnitario());
			tpFactuDocumDetal.setDescuento(temp.getDescuento());
			tpFactuDocumDetal.setSubtotal(temp.getSubtotal());
			tpFactuDocumDetal.setTipoDeIgv(Constants.FACTURACION_ELECTRONICA_TIPO_DE_IGV);
			tpFactuDocumDetal.setIgv(Constants.FACTURACION_ELECTRONICA_IGV_DE_LA_LINEA);
			tpFactuDocumDetal.setTotal(temp.getTotal());
			tpFactuDocumDetal.setAnticipoRegularizacion(Constants.FACTURACION_ELECTRONICA_FLAG_ANTICIPO_REGULACION);
			tpFactuDocumDetal.setAnticipoDocumentoSerie(temp.getAnticipoDocumentoSerie());
			tpFactuDocumDetal.setAnticipoDocumentoNumero(temp.getAnticipoDocumentoNumero());
			tpFactuDocumDetal.setCodigoProductoSunat(temp.getCodigoProductoSunat());
			tpFactuDocumDetal.setFecCreaRegi(temp.getFecCreaRegi());
			tpFactuDocumDetal.setIndEsta(temp.getIndEsta());
			tpFactuDocumDetal.setUsuApliCrea(String.valueOf(temp.getUsuApliCrea()));
			
			listTpFactuDocumDetal.add(tpFactuDocumDetal);
		}
		
		String result = daoFacturacionElectronica.generarComprobanteElectronico(tpFactuDocumCabec, listTpFactuDocumDetal);
		
		return result;
	}

	@Override
	public List<TpFactuDocumCabecDto> getDocumentosPorEstado(TpFactuDocumCabecDto tpFactuDocumCabecDto) {

		List<TpFactuDocumCabecDto> resultado = new LinkedList<TpFactuDocumCabecDto>() ;
		
		TpFactuDocumCabec entidad = new TpFactuDocumCabec();
		entidad.setCodEstaDocu(tpFactuDocumCabecDto.getCodEstaDocu());
		
		List<TpFactuDocumCabec> listaTemporal = daoFacturacionElectronica.getDocumentosPorEstado(entidad);
		
		for(TpFactuDocumCabec temp : listaTemporal) {
			TpFactuDocumCabecDto dto = new TpFactuDocumCabecDto();

			dto.setCodFactDocuCabec(temp.getCodFactDocuCabec());
			dto.setCodOperClie(temp.getCodOperClie());
			dto.setTipoDeComprobante(temp.getTipoDeComprobante());
			dto.setSerie(temp.getSerie());
			dto.setNumero(temp.getNumero());
			dto.setSunatTransaction(temp.getSunatTransaction());
			dto.setClienteTipoDeDocumento(temp.getClienteTipoDeDocumento());
			dto.setClienteNumeroDeDocumento(temp.getClienteNumeroDeDocumento());
			dto.setClienteDenominacion(temp.getClienteDenominacion());
			dto.setClienteDireccion(temp.getClienteDireccion());
			dto.setClienteEmail(temp.getClienteEmail());
			dto.setClienteEmail1(temp.getClienteEmail1());
			dto.setClienteEmail2(temp.getClienteEmail2());
			dto.setFechaDeEmision(temp.getFechaDeEmision());
			dto.setFechaDeVencimiento(temp.getFechaDeVencimiento());
			dto.setMoneda(temp.getMoneda());
			dto.setTipoDeCambio(temp.getTipoDeCambio());
			dto.setPorcentajeDeIgv(temp.getPorcentajeDeIgv());
			dto.setDescuentoGlobal(temp.getDescuentoGlobal());
			dto.setTotalDescuento(temp.getTotalDescuento());
			dto.setTotalAnticipo(temp.getTotalAnticipo());
			dto.setTotalGravada(temp.getTotalGravada());
			dto.setTotalInafecta(temp.getTotalInafecta());
			dto.setTotalExonerada(temp.getTotalExonerada());
			dto.setTotalIgv(temp.getTotalIgv());
			dto.setTotalGratuita(temp.getTotalGratuita());
			dto.setTotalOtrosCargos(temp.getTotalOtrosCargos());
			dto.setTotalIsc(temp.getTotalIsc());
			dto.setTotal(temp.getTotal());
			dto.setPercepcionTipo(temp.getPercepcionTipo());
			dto.setPercepcionBaseImponible(temp.getPercepcionBaseImponible());
			dto.setTotalPercepcion(temp.getTotalPercepcion());
			dto.setTotalIncluidoPercepcion(temp.getTotalIncluidoPercepcion());
			dto.setDetraccion(temp.getDetraccion());
			dto.setObservaciones(temp.getObservaciones());
			dto.setDocumentoQueSeModificaTipo(temp.getDocumentoQueSeModificaTipo());
			dto.setDocumentoQueSeModificaSerie(temp.getDocumentoQueSeModificaSerie());
			dto.setDocumentoQueSeModificaNumero(temp.getDocumentoQueSeModificaNumero());
			dto.setTipoDeNotaDeCredito(temp.getTipoDeNotaDeCredito());
			dto.setTipoDeNotaDeDebito(temp.getTipoDeNotaDeDebito());
			dto.setEnviarAutomaticamenteALaSunat(temp.getEnviarAutomaticamenteALaSunat());
			dto.setEnviarAutomaticamenteAlCliente(temp.getEnviarAutomaticamenteAlCliente());
			dto.setCodigoUnico(temp.getCodigoUnico());
			dto.setCondicionesDePago(temp.getCondicionesDePago());
			dto.setMedioDePago(temp.getMedioDePago());
			dto.setPlacaVehiculo(temp.getPlacaVehiculo());
			dto.setOrdenCompraServicio(temp.getOrdenCompraServicio());
			dto.setFormatoDePdf(temp.getFormatoDePdf());
			dto.setCodEstaDocu(temp.getCodEstaDocu());
			dto.setDesCortDocuGene(temp.getDesCortDocuGene());
			dto.setFecCreaRegi(temp.getFecCreaRegi());
			dto.setIndEsta(temp.getIndEsta());
			dto.setUsuApliCrea(String.valueOf(temp.getUsuApliCrea()));			
			
			resultado.add(dto);
		}
		return resultado;
        
	}

	@Override
	public List<TpFactuDocumDetalDto> getDetallePorCodCabecera(Integer codFactDocuCabec) {
		
		List<TpFactuDocumDetalDto> resultado = new LinkedList<TpFactuDocumDetalDto>() ;
		
		
		List<TpFactuDocumDetal> listaTemporal = daoFacturacionElectronica.getDetallePorCodCabecera(codFactDocuCabec);
		
		for(TpFactuDocumDetal temp : listaTemporal) {
			
			TpFactuDocumDetalDto dto = new TpFactuDocumDetalDto();

		
			dto.setCodFactDocuDetal(temp.getCodFactDocuDetal());
			
			
//			dto.setTpFactuDocumCabec(temp.getTpFactuDocumCabec());
			dto.setUnidadDeMedida(temp.getUnidadDeMedida());
			dto.setCodigo(temp.getCodigo());
			dto.setDescripcion(temp.getDescripcion());
			dto.setCantidad(temp.getCantidad());
			dto.setValorUnitario(temp.getValorUnitario());
			dto.setPrecioUnitario(temp.getPrecioUnitario());
			dto.setDescuento(temp.getDescuento());
			dto.setSubtotal(temp.getSubtotal());
			dto.setTipoDeIgv(temp.getTipoDeIgv());
			dto.setIgv(temp.getIgv());
			dto.setTotal(temp.getTotal());
			dto.setAnticipoRegularizacion(temp.getAnticipoRegularizacion());
			dto.setAnticipoDocumentoSerie(temp.getAnticipoDocumentoSerie());
			dto.setAnticipoDocumentoNumero(temp.getAnticipoDocumentoNumero());
			dto.setCodigoProductoSunat(temp.getCodigoProductoSunat());
			dto.setFecCreaRegi(temp.getFecCreaRegi());
			dto.setIndEsta(temp.getIndEsta());
			dto.setUsuApliCrea(String.valueOf(temp.getUsuApliCrea()));
			
			resultado.add(dto);
		}
		
		
		return resultado;
	}

	@Override
	public String updateComprobante(TpFactuDocumCabecDto tpFactuDocumCabecDto) {
		
		TpFactuDocumCabec  entidadCabecera = new TpFactuDocumCabec();
		
		entidadCabecera.setCodFactDocuCabec(tpFactuDocumCabecDto.getCodFactDocuCabec());
		entidadCabecera.setCodOperClie(tpFactuDocumCabecDto.getCodOperClie());
		entidadCabecera.setTipoDeComprobante(tpFactuDocumCabecDto.getTipoDeComprobante());
		entidadCabecera.setSerie(tpFactuDocumCabecDto.getSerie());
		entidadCabecera.setNumero(tpFactuDocumCabecDto.getNumero());
		entidadCabecera.setSunatTransaction(tpFactuDocumCabecDto.getSunatTransaction());
		entidadCabecera.setClienteTipoDeDocumento(tpFactuDocumCabecDto.getClienteTipoDeDocumento());
		entidadCabecera.setClienteNumeroDeDocumento(tpFactuDocumCabecDto.getClienteNumeroDeDocumento());
		entidadCabecera.setClienteDenominacion(tpFactuDocumCabecDto.getClienteDenominacion());
		entidadCabecera.setClienteDireccion(tpFactuDocumCabecDto.getClienteDireccion());
		entidadCabecera.setClienteEmail(tpFactuDocumCabecDto.getClienteEmail());
		entidadCabecera.setClienteEmail1(tpFactuDocumCabecDto.getClienteEmail1());
		entidadCabecera.setClienteEmail2(tpFactuDocumCabecDto.getClienteEmail2());
		entidadCabecera.setFechaDeEmision(tpFactuDocumCabecDto.getFechaDeEmision());
		entidadCabecera.setFechaDeVencimiento(tpFactuDocumCabecDto.getFechaDeVencimiento());
		entidadCabecera.setMoneda(tpFactuDocumCabecDto.getMoneda());
		entidadCabecera.setTipoDeCambio(tpFactuDocumCabecDto.getTipoDeCambio());
		entidadCabecera.setPorcentajeDeIgv(tpFactuDocumCabecDto.getPorcentajeDeIgv());
		entidadCabecera.setDescuentoGlobal(tpFactuDocumCabecDto.getDescuentoGlobal());
		entidadCabecera.setTotalDescuento(tpFactuDocumCabecDto.getTotalDescuento());
		entidadCabecera.setTotalAnticipo(tpFactuDocumCabecDto.getTotalAnticipo());
		entidadCabecera.setTotalGravada(tpFactuDocumCabecDto.getTotalGravada());
		entidadCabecera.setTotalInafecta(tpFactuDocumCabecDto.getTotalInafecta());
		entidadCabecera.setTotalExonerada(tpFactuDocumCabecDto.getTotalExonerada());
		entidadCabecera.setTotalIgv(tpFactuDocumCabecDto.getTotalIgv());
		entidadCabecera.setTotalGratuita(tpFactuDocumCabecDto.getTotalGratuita());
		entidadCabecera.setTotalOtrosCargos(tpFactuDocumCabecDto.getTotalOtrosCargos());
		entidadCabecera.setTotalIsc(tpFactuDocumCabecDto.getTotalIsc());
		entidadCabecera.setTotal(tpFactuDocumCabecDto.getTotal());
		entidadCabecera.setPercepcionTipo(tpFactuDocumCabecDto.getPercepcionTipo());
		entidadCabecera.setPercepcionBaseImponible(tpFactuDocumCabecDto.getPercepcionBaseImponible());
		entidadCabecera.setTotalPercepcion(tpFactuDocumCabecDto.getTotalPercepcion());
		entidadCabecera.setTotalIncluidoPercepcion(tpFactuDocumCabecDto.getTotalIncluidoPercepcion());
		entidadCabecera.setDetraccion(tpFactuDocumCabecDto.getDetraccion());
		entidadCabecera.setObservaciones(tpFactuDocumCabecDto.getObservaciones());
		entidadCabecera.setDocumentoQueSeModificaTipo(tpFactuDocumCabecDto.getDocumentoQueSeModificaTipo());
		entidadCabecera.setDocumentoQueSeModificaSerie(tpFactuDocumCabecDto.getDocumentoQueSeModificaSerie());
		entidadCabecera.setDocumentoQueSeModificaNumero(tpFactuDocumCabecDto.getDocumentoQueSeModificaNumero());
		entidadCabecera.setTipoDeNotaDeCredito(tpFactuDocumCabecDto.getTipoDeNotaDeCredito());
		entidadCabecera.setTipoDeNotaDeDebito(tpFactuDocumCabecDto.getTipoDeNotaDeDebito());
		entidadCabecera.setEnviarAutomaticamenteALaSunat(tpFactuDocumCabecDto.getEnviarAutomaticamenteALaSunat());
		entidadCabecera.setEnviarAutomaticamenteAlCliente(tpFactuDocumCabecDto.getEnviarAutomaticamenteAlCliente());
		entidadCabecera.setCodigoUnico(tpFactuDocumCabecDto.getCodigoUnico());
		entidadCabecera.setCondicionesDePago(tpFactuDocumCabecDto.getCondicionesDePago());
		entidadCabecera.setMedioDePago(tpFactuDocumCabecDto.getMedioDePago());
		entidadCabecera.setPlacaVehiculo(tpFactuDocumCabecDto.getPlacaVehiculo());
		entidadCabecera.setOrdenCompraServicio(tpFactuDocumCabecDto.getOrdenCompraServicio());
		entidadCabecera.setFormatoDePdf(tpFactuDocumCabecDto.getFormatoDePdf());
		entidadCabecera.setCodEstaDocu(tpFactuDocumCabecDto.getCodEstaDocu());
		entidadCabecera.setDesCortDocuGene(tpFactuDocumCabecDto.getDesCortDocuGene());
		entidadCabecera.setFecCreaRegi(tpFactuDocumCabecDto.getFecCreaRegi());
		entidadCabecera.setIndEsta(tpFactuDocumCabecDto.getIndEsta());
		entidadCabecera.setUsuApliCrea(String.valueOf(tpFactuDocumCabecDto.getUsuApliCrea()));
		
		// Respuesta de Servicio
		entidadCabecera.setRepuestaEnlace(tpFactuDocumCabecDto.getRepuestaEnlace()); 
		entidadCabecera.setRepuestaAceptadaPorSunat(tpFactuDocumCabecDto.getRepuestaAceptadaPorSunat());  
		entidadCabecera.setRepuestaSunatDescription(tpFactuDocumCabecDto.getRepuestaSunatDescription());  
		entidadCabecera.setRepuestaSunatNote(tpFactuDocumCabecDto.getRepuestaSunatNote());  
		entidadCabecera.setRepuestaSunatResponsecode(tpFactuDocumCabecDto.getRepuestaSunatResponsecode());  
		entidadCabecera.setRepuestaSunatSoapError(tpFactuDocumCabecDto.getRepuestaSunatSoapError());  
		entidadCabecera.setRepuestaAnulado(tpFactuDocumCabecDto.getRepuestaAnulado()); 
		entidadCabecera.setRepuestaPdfZipBase64(tpFactuDocumCabecDto.getRepuestaPdfZipBase64());  
		entidadCabecera.setRepuestaXmlZipBase64(tpFactuDocumCabecDto.getRepuestaXmlZipBase64());  
		entidadCabecera.setRepuestaCdrZipBase64(tpFactuDocumCabecDto.getRepuestaCdrZipBase64());  
		entidadCabecera.setRepuestaCadenaParaCodigoQr(tpFactuDocumCabecDto.getRepuestaCadenaParaCodigoQr());  
		entidadCabecera.setRepuestaCodigoHash(tpFactuDocumCabecDto.getRepuestaCodigoHash());  
		entidadCabecera.setRepuestaEnlaceDelPdf(tpFactuDocumCabecDto.getRepuestaEnlaceDelPdf());  
		entidadCabecera.setRepuestaEnlaceDelXml(tpFactuDocumCabecDto.getRepuestaEnlaceDelXml());  
		entidadCabecera.setRepuestaEnlaceDelCdr(tpFactuDocumCabecDto.getRepuestaEnlaceDelCdr());  
		
		// Respuesta Solo si hay error
		entidadCabecera.setRepuestaErrors(tpFactuDocumCabecDto.getRepuestaErrors() );
		entidadCabecera.setRepuestaCodigoError(tpFactuDocumCabecDto.getRepuestaCodigoError() );
		
		// Se actualiza el estado para el manejo del comercio
		entidadCabecera.setCodEstaDocu(tpFactuDocumCabecDto.getCodEstaDocu());
		entidadCabecera.setFecModiRegi(tpFactuDocumCabecDto.getFecModiRegi());
		entidadCabecera.setUsuApliModi(tpFactuDocumCabecDto.getUsuApliModi());
		
        String result = daoFacturacionElectronica.updateComprobante(entidadCabecera);

        return result;
	}

	
	
	
	
}

