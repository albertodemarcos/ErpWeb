package com.erpweb.controladores.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.dto.InformeDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.bi.InformeService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.bi.InformeValidator;


@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/informes")
public class InformeController {
	
	@Autowired 
	private InformeValidator informeValidator;
	
	@Autowired
	private InformeService informeService;

	@GetMapping("/informe/{informeId}")
	public @ResponseBody AccionRespuesta getInforme(@PathVariable Long informeId, Usuario user) throws Exception {
		
		return this.informeService.getInforme(informeId, user);
	}
	
	@GetMapping("/listado")
	public String getInformes() {
		return "";
	}
	
	@GetMapping( "/crearInforme" )
	public @ResponseBody AccionRespuesta getCrearInforme( Model model, Usuario user ) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarInforme/{informeId}" )
	public @ResponseBody AccionRespuesta getEditarInforme( @PathVariable Long informeId, Usuario user) throws Exception {
		
		return this.informeService.getInforme(informeId, user);
	}
	
	@PostMapping( { "/crearInforme/informe/{informeDto}.json" , "/editarInforme/informe/{informeDto}.json" } )
	public @ResponseBody AccionRespuesta postCrearInforme( InformeDto informeDto, Usuario user, BindingResult result) {
		
		this.informeValidator.validate(informeDto, result);
		
		if( result.hasErrors() ) {
			
			return this.informeService.getInforme(informeDto.getId(), user);
		}
		
		return this.informeService.getCrearEditarInforme(informeDto, user);
	}
	
	@PostMapping("/eliminarInforme/{informeId}")
	public @ResponseBody AccionRespuesta postEliminarInforme( @PathVariable Long informeId, Usuario user ) throws Exception {
		
		if(informeId == null || informeId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.informeService.eliminarInformePorId(informeId);
	}
	
	
}
