package com.erpweb.controladores.inventario;

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

import com.erpweb.dto.ArticuloDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.servicios.inventario.ArticuloService;
import com.erpweb.utiles.AccionRespuesta;
import com.erpweb.validadores.inventario.ArticuloValidator;

@CrossOrigin(origins = {"http://localhost:4200"}) //Conexion con angular 
@RestController
@RequestMapping("/articulos")
public class ArticuloController {
	
	@Autowired 
	private ArticuloValidator articuloValidator;

	@Autowired
	private ArticuloService articuloService;
	
	@GetMapping("/articulo/{articuloId}")
	public @ResponseBody AccionRespuesta getArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		return this.articuloService.getArticulo(articuloId, user);
	}
	
	@GetMapping("/articulos")
	public String getArticulos(  ) {
		
		
		return "";
	}
	
	@GetMapping( "/crearArticulo" )
	public @ResponseBody AccionRespuesta getCrearArticulo( Model model, Usuario user) throws Exception {
		
		return new AccionRespuesta();
	}
	
	@GetMapping( "/editarArticulo/{articuloId}" )
	public @ResponseBody AccionRespuesta getEditarArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		return this.articuloService.getArticulo(articuloId, user);
	}
	
	@PostMapping( {"/crearArticulo/articulo/{articuloDto}.json", "/editarArticulo/articulo/{articuloDto}.json"} )
	public @ResponseBody AccionRespuesta postCrearArticulo( ArticuloDto articuloDto, Usuario user, BindingResult result ) {
		
		this.articuloValidator.validate(articuloDto, result);
		
		if( result.hasErrors() ) {
			
			return this.articuloService.getArticulo(articuloDto.getId(), user);
		}
		
		return this.articuloService.getCrearEditarArticulo(articuloDto, user);
	}
	
	@PostMapping("/eliminarArticulo")
	public @ResponseBody AccionRespuesta postEliminarArticulo( @PathVariable Long articuloId, Usuario user) throws Exception {
		
		if(articuloId == null || articuloId.longValue() < 1L ) {
			
			return new AccionRespuesta();
		}
		
		return this.articuloService.eliminarArticuloPorId(articuloId);
	}
	
	
}
