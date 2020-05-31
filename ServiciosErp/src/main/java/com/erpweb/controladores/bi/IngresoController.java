package com.erpweb.controladores.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erpweb.entidades.bi.Ingreso;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.bi.IngresoService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.bi.IngresoValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/ingresos")
public class IngresoController {
	
	@Autowired
	private IngresoValidator ingresoValidator;
	
	@Autowired
	private IngresoService ingresoService;

	@GetMapping("/ingreso/{ingresoId}")
	public @ResponseBody AccionRespuesta getIngreso(@PathVariable Long ingresoId, Usuario user) {
		
		return this.ingresoService.getIngreso(ingresoId, user);
	}
	
	@GetMapping("/listado")
	public String getIngresos() {
		
		
		return "";
	}
	
	@GetMapping( { "/crearIngreso" , "/editarIngreso" })
	public @ResponseBody AccionRespuesta getCrearIngreso() {
		
		
		return new AccionRespuesta();
	}
	
	@PostMapping( { "/crearIngreso", "/editarIngreso" } )
	public @ResponseBody AccionRespuesta postCrearIngreso( Ingreso ingreso, BindingResult result ) {
		
		this.ingresoValidator.validate(ingreso, result);
		
		if( result.hasErrors() ) {
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	@PostMapping("/eliminarIngreso")
	public @ResponseBody AccionRespuesta postEliminarIngreso() {
		
		
		return new AccionRespuesta();
	}
}
