package com.erpweb.servicios.compras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.CompraDto;
import com.erpweb.entidades.compras.Compra;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.compras.CompraRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;



@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void crearCompraDesdeCompraDto(CompraDto compraDto) {
		
		Compra compra = new Compra();
		
		if(compraDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(compraDto.getEmpresaId()).orElse(new Empresa());
		
		compra.setCodigo(compraDto.getCodigo());
		compra.setEmpresa(empresa);
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setLineaCompra(compraDto.getLineaCompra());
		compra.setProveedor(compraDto.getProveedor());
		
		try {
			//Guardamos la compra en base de datos
			compraRepository.save(compra);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void actualizarCompraDesdeCompraDto(CompraDto compraDto) {
		
		Compra compra = new Compra();
		
		if(compraDto.getEmpresaId() == null) {
			//return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(compraDto.getEmpresaId()).orElse(new Empresa());
		
		compra.setId(compraDto.getId());
		compra.setCodigo(compraDto.getCodigo());
		compra.setEmpresa(empresa);
		compra.setFechaCompra(compraDto.getFechaCompra());
		compra.setLineaCompra(compraDto.getLineaCompra());
		compra.setProveedor(compraDto.getProveedor());
		
		try {
			//Actualizamos la compra en base de datos
			compraRepository.save(compra);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
	
	public void eliminarCompra(Compra compra) {
		
		if(compra == null || compra.getId() == null) {
			//return Boolean.FALSE;
		}
		
		try {
			//Elimnamos la compra
			compraRepository.deleteById(compra.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			//return Boolean.FALSE;
		}
		
		//return Boolean.TRUE;
	}
		
	public CompraDto obtenerCompraDtoDesdeCompra(Long id, Long empresaId) {
		
		Compra compra = compraRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(compra == null) {
			return new CompraDto();
		}
		
		CompraDto compraDto = new CompraDto();
		
		compraDto.setCodigo(compra.getCodigo());
		compraDto.setEmpresaId(empresaId);
		compraDto.setFechaCompra(compra.getFechaCompra());
		compraDto.setLineaCompra(compra.getLineaCompra());
		compraDto.setProveedor(compra.getProveedor());
		
		return compraDto;
	}

}
