package com.erpweb.servicios.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.GastoDto;
import com.erpweb.entidades.bi.Gasto;
import com.erpweb.entidades.empresa.Empresa;
import com.erpweb.repositorios.bi.GastoRepository;
import com.erpweb.repositorios.empresa.EmpresaRepository;
import com.erpweb.servicios.bi.interfaces.GastoServiceInterfaz;

@Service
public class GastoService implements GastoServiceInterfaz {
	
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Boolean creaGastoDesdeGastoDto(GastoDto gastoDto) {
		
		Gasto gasto = new Gasto();

		if(gastoDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(gastoDto.getEmpresaId()).orElse(new Empresa());
		
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setEmpresa(empresa);
		gasto.setProcedencia(gastoDto.getProcedencia());
		gasto.setBaseImponible(gastoDto.getBaseImponible());
		gasto.setCuotaTributaria(gastoDto.getCuotaTributaria());
		gasto.setImporteTotal(gastoDto.getImporteTotal());
		gasto.setDescripcion(gastoDto.getDescripcion());
		gasto.setObservaciones(gastoDto.getObservaciones());
		
		try {
			//Guardamos el gasto en base de datos
			gastoRepository.save(gasto);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public GastoDto obtieneGastoDto(Long id, Long empresaId) {
		
		Gasto gasto = gastoRepository.findByIdAndEmpresaId(id, empresaId);
		
		if(gasto == null) {
			return new GastoDto();
		}
		
		GastoDto gastoDto = new GastoDto();
		
		gastoDto.setCodigo(gasto.getCodigo());
		gastoDto.setEmpresaId(gasto.getEmpresa().getId());
		gastoDto.setProcedencia(gasto.getProcedencia());
		gastoDto.setBaseImponible(gasto.getBaseImponible());
		gastoDto.setCuotaTributaria(gasto.getCuotaTributaria());
		gastoDto.setImporteTotal(gasto.getImporteTotal());
		gastoDto.setDescripcion(gasto.getDescripcion());
		gastoDto.setObservaciones(gasto.getObservaciones());
		
		return gastoDto;
	}

	@Override
	public Boolean actualizaGasto(GastoDto gastoDto) {
		
		Gasto gasto = new Gasto();

		if(gastoDto.getEmpresaId() == null) {
			return Boolean.FALSE;
		}
		
		Empresa empresa = empresaRepository.findById(gastoDto.getEmpresaId()).orElse(new Empresa());
		
		gasto.setId(gastoDto.getId());
		gasto.setCodigo(gastoDto.getCodigo());
		gasto.setEmpresa(empresa);
		gasto.setProcedencia(gastoDto.getProcedencia());
		gasto.setBaseImponible(gastoDto.getBaseImponible());
		gasto.setCuotaTributaria(gastoDto.getCuotaTributaria());
		gasto.setImporteTotal(gastoDto.getImporteTotal());
		gasto.setDescripcion(gastoDto.getDescripcion());
		gasto.setObservaciones(gastoDto.getObservaciones());
		
		try {
			//Actualizamos el gasto en base de datos
			gastoRepository.save(gasto);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage() );
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminaGasto(Gasto gasto) {
		
		if(gasto == null || gasto.getId() == null) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el gasto
			gastoRepository.deleteById(gasto.getId());
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	

	@Override
	public Boolean eliminarGastoPorId(Long gastoId, Long empresaId) {
		// TODO Auto-generated method stub
		
		if(gastoId == null || gastoId == 0L) {
			return Boolean.FALSE;
		}
		
		try {
			//Elimnamos el gasto
			gastoRepository.deleteByIdAndEmpresaId(gastoId,empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Gasto obtieneGasto(Long id, Long empresaId) {
		
		Gasto gasto;
		
		try {
			//Recuperamos el gasto
			gasto = gastoRepository.findByIdAndEmpresaId(id, empresaId);
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getLocalizedMessage());
			
			return new Gasto();
		}
		
		return gasto;
	}
	
}
