package com.erpweb.servicios.compras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.utiles.AccionRespuesta;



@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccionRespuesta crearCompraDesdeCompraDto(CompraDto compraDto) {
		
		logger.debug("Entramos en el metodo crearCompraDesdeCompraDto() con ID={}", compraDto.getId() );
		
		Compra compra = new Compra();
		
		compra.setCodigo(compraDto.getCodigo());
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setArticulo(compraDto.getArticulo());
		compra.setCantidad(compraDto.getCantidad());
		compra.setBaseImponibleTotal(compraDto.getBaseImponibleTotal());
		compra.setImpuesto(compraDto.getImpuesto());
		compra.setImporteTotal(compraDto.getImporteTotal());
		
		try {
			
			//Guardamos la compra en base de datos
			Compra compraSave = compraRepository.save(compra);
			
			return this.devolverDatosCompraDto(compraDto, compraSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearCompraDesdeCompraDto() con ID={}", compraDto.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarCompraDesdeCompraDto(CompraDto compraDto) {
		
		logger.debug("Entramos en el metodo actualizarCompraDesdeCompraDto() con ID={}", compraDto.getId() );
		
		Compra compra = new Compra();

		compra.setId(compraDto.getId());
		compra.setCodigo(compraDto.getCodigo());
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setArticulo(compraDto.getArticulo());
		compra.setCantidad(compraDto.getCantidad());
		compra.setBaseImponibleTotal(compraDto.getBaseImponibleTotal());
		compra.setImpuesto(compraDto.getImpuesto());
		compra.setImporteTotal(compraDto.getImporteTotal());
		
		try {
			
			//Actualizamos la compra en base de datos
			Compra compraSave = compraRepository.save(compra);
			
			return this.devolverDatosActualizadosCompraDto(compraDto, compraSave);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarCompraDesdeCompraDto() con ID={}", compraDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarCompra(Compra compra) {
		
		logger.debug("Entramos en el metodo eliminarCompra()");
		
		if(compra == null || compra.getId() == null) {
			
			logger.error("ERROR en el metodo eliminarCompra()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
		
		try {
			
			//Elimnamos la compra
			compraRepository.deleteById(compra.getId());
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCompra() con ID={} ", compra.getId() );
			
			e.printStackTrace();
						
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarCompraPorId(Long compraId) {
		
		logger.error("Entramos en el metodo eliminarCompraPorId()" );
		
		if( compraId == null) {
			
			logger.error("ERROR en el metodo eliminarCompra()");
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
				
		try {
			
			//Elimnamos el compra
			compraRepository.deleteById(compraId);
			
			return new AccionRespuesta(-2L, "OK", Boolean.TRUE);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarCompraPorId() con id={}", compraId );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
		
	public CompraDto obtenerCompraDtoDesdeCompra(Long id) {
		
		logger.debug("Entramos en el metodo obtenerCompraDtoDesdeCompra() con ID={}", id );
		
		Optional<Compra> compraOptional = compraRepository.findById(id);
		
		Compra compra = compraOptional.get();
		
		if(compra == null) {
			
			return new CompraDto();
		}
		
		CompraDto compraDto = new CompraDto();
		
		try {
			
			compraDto.setId(compra.getId());
			compraDto.setCodigo(compra.getCodigo());
			compraDto.setFechaCompra(compra.getFechaCompra());
			compraDto.setArticulo(compra.getArticulo());
			compraDto.setCantidad(compra.getCantidad());
			compraDto.setBaseImponibleTotal(compra.getBaseImponibleTotal());
			compraDto.setImpuesto(compra.getImpuesto());
			compraDto.setImporteTotal(compra.getImporteTotal());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerCompraDtoDesdeCompra() con ID={} ", id );
			
			e.printStackTrace();
		}
		
		return compraDto;
	}
	
	public List<CompraDto> getListadoCompras() {
		
		logger.debug("Entramos en el metodo getListado()" );
		
		try {
			
			List<Compra> compras = compraRepository.findAll();
			
			return this.obtieneListadoCompraDtoDelRepository(compras);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo getListado()" );
			
			e.printStackTrace();
		}
			
		return new ArrayList<CompraDto>();
	}
	
	public AccionRespuesta getCompra(Long compraId, Usuario user) {
		
		logger.debug("Entramos en el metodo getCompra()");
		
		if( compraId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe la compra", Boolean.FALSE);
		}
		
		CompraDto compraDto = this.obtenerCompraDtoDesdeCompra(compraId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( compraDto != null ) {
			
			AccionRespuesta.setId( compraDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("compraDto", compraDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar la compra");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarCompra(CompraDto compraDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarCompra() con usuario={}", user.getId() );
		
		if( compraDto.getId() != null && compraDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion de la Compra con usuario={}", user.getId() );
			
			return this.actualizarCompraDesdeCompraDto(compraDto);
			
		} else {
			
			logger.debug("Se va a crear una Compra con usuario={}", user.getId() );
			
			return this.crearCompraDesdeCompraDto(compraDto);
		}
	}

	private AccionRespuesta devolverDatosCompraDto(CompraDto compraDto, Compra compraSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(compraSave != null && compraSave.getId() != null) {
			
			compraDto.setId(compraSave.getId());
			
			respuesta.setId(compraSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("compraDto", compraDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("compraDto", compraDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosCompraDto(CompraDto compraDto, Compra compraSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(compraDto != null && compraSave != null) {
			
			respuesta.setId(compraSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("compraDto", compraDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("compraDto", compraDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}
	
	private List<CompraDto> obtieneListadoCompraDtoDelRepository(List<Compra> compras){
		
		List<CompraDto> comprasDto = new ArrayList<CompraDto>();
		
		if(CollectionUtils.isNotEmpty(compras) ) {
			
			for( Compra compra : compras) {
				
				CompraDto compraDto = new CompraDto();
				
				compraDto.setId(compra.getId());
				compraDto.setCodigo(compra.getCodigo());
				compraDto.setFechaCompra(compra.getFechaCompra());
				compraDto.setArticulo(compra.getArticulo());
				compraDto.setCantidad(compra.getCantidad());
				compraDto.setBaseImponibleTotal(compra.getBaseImponibleTotal());
				compraDto.setImpuesto(compra.getImpuesto());
				compraDto.setImporteTotal(compra.getImporteTotal());
				
				comprasDto.add(compraDto);			
			}
		}
		
		return comprasDto;
	}

}
