package com.erpweb.servicios.usuarios;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpweb.dto.UsuarioDto;
import com.erpweb.entidades.usuarios.Usuario;
import com.erpweb.repositorios.usuarios.UsuarioRepository;
import com.erpweb.utiles.AccionRespuesta;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public AccionRespuesta crearUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		logger.debug("Entramos en el metodo crearUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
		
		Usuario usuario = new Usuario();
		
		usuario.setCodigo(usuarioDto.getCodigo());
		usuario.setName(usuarioDto.getName());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setIdentidad(usuarioDto.getIdentidad());
		usuario.setIdioma(usuarioDto.getIdioma());
		
		try {
			//Guardamos el usuario en base de datos
			usuarioRepository.save(usuario);
			
			return this.devolverDatosUsuarioDto(usuarioDto, usuario);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo crearUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta actualizarUsuarioDesdeUsuarioDto(UsuarioDto usuarioDto) {
		
		logger.debug("Entramos en el metodo actualizarUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
		
		Usuario usuario = new Usuario();
		
		usuario.setId(usuarioDto.getId());
		usuario.setCodigo(usuarioDto.getCodigo());
		usuario.setName(usuarioDto.getName());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setIdentidad(usuarioDto.getIdentidad());
		usuario.setIdioma(usuarioDto.getIdioma());
		
		try {
			//Guardamos el usuario en base de datos
			usuarioRepository.save(usuario);
			
			return this.devolverDatosActualizadosUsuarioDto(usuarioDto, usuario);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo actualizarUsuarioDesdeUsuarioDto() con ID={}", usuarioDto.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta(-1L, "NOK", Boolean.FALSE);
		}
	}
	
	public AccionRespuesta eliminarUsuario(Usuario usuario) {
		
		logger.debug("Entramos en el metodo eliminarUsuario() con ID={}", usuario.getId() );
		
		if(usuario == null || usuario.getId() == null) {
			
			return new AccionRespuesta();
		}
		
		try {
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuario.getId());
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarUsuario() con ID={}", usuario.getId() );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public AccionRespuesta eliminarUsuarioPorId(Long usuarioId) {
		
		logger.error("Entramos en el metodo eliminarUsuarioPorId() con ID={}", usuarioId );
				
		try {
			
			//Elimnamos el usuario
			usuarioRepository.deleteById(usuarioId);
			
		}catch(Exception e) {
			
			logger.error("Error en el metodo eliminarUsuarioPorId() con ID={}", usuarioId );
			
			e.printStackTrace();
			
			return new AccionRespuesta();
		}
		
		return new AccionRespuesta();
	}
	
	public UsuarioDto obtenerUsuarioDtoDesdeUsuario(Long id) {
		
		logger.debug("Entramos en el metodo obtenerUsuarioDtoDesdeUsuario() con ID={}", id );
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		Usuario usuario = usuarioOptional.get();
		
		if(usuario == null) {
			return new UsuarioDto();
		}
		
		UsuarioDto usuarioDto = new UsuarioDto();
		
		try {
			
			usuarioDto.setCodigo(usuario.getCodigo());
			usuarioDto.setName(usuario.getName());
			usuarioDto.setPassword(usuario.getPassword());
			usuarioDto.setIdentidad(usuario.getIdentidad());
			usuarioDto.setIdioma(usuario.getIdioma());
			
		} catch(Exception e) {
			
			logger.error("Error en el metodo obtenerUsuarioDtoDesdeUsuario() con ID={}", id );
			
			e.printStackTrace();
		}
		
		return usuarioDto;
	}
	
	public AccionRespuesta getUsuario(Long usuarioId, Usuario user) {
		
		logger.debug("Entramos en el metodo getUsuario()");
		
		if( usuarioId == null) {
			
			return new AccionRespuesta(-1L, "Error, existe el usuario", Boolean.FALSE);
		}
		
		UsuarioDto usuarioDto = this.obtenerUsuarioDtoDesdeUsuario(usuarioId);
		
		AccionRespuesta AccionRespuesta = new AccionRespuesta();
		
		if( usuarioDto != null ) {
			
			AccionRespuesta.setId( usuarioDto.getId() );
			
			AccionRespuesta.setRespuesta("");
			
			AccionRespuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("usuarioDto", usuarioDto);
			
			AccionRespuesta.setData(new HashMap<String, Object>(mapa));
			
		}else {
			
			AccionRespuesta.setId( -1L );
			
			AccionRespuesta.setRespuesta("Error, no se ha podido recuperar el usuario");
			
			AccionRespuesta.setResultado(Boolean.FALSE);
		}
		
		return AccionRespuesta;
	}
	
	public AccionRespuesta getCrearEditarUsuario(UsuarioDto usuarioDto, Usuario user) {
		
		logger.debug("Entramos en el metodo getCrearEditarUsuario() con usuario={}", user.getId() );
		
		if( usuarioDto.getId() != null && usuarioDto.getId().longValue() > 0) {
			
			logger.debug("Se va a realizar una actualizacion del Usuario con usuario={}", user.getId() );
			
			return this.actualizarUsuarioDesdeUsuarioDto(usuarioDto);
			
		} else {
			
			logger.debug("Se va a crear un Usuario con usuario={}", user.getId() );
			
			return this.crearUsuarioDesdeUsuarioDto(usuarioDto);
		}
	}
	
	private AccionRespuesta devolverDatosUsuarioDto(UsuarioDto usuarioDto, Usuario usuarioSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		//Guardado el cliente se devuelve en su dto
		if(usuarioSave != null && usuarioSave.getId() != null) {
			
			usuarioDto.setId(usuarioSave.getId());
			
			respuesta.setId(usuarioSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setCodigo("NOK");
						
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);
		}
		
		return respuesta;
	}
	
	private AccionRespuesta devolverDatosActualizadosUsuarioDto(UsuarioDto usuarioDto, Usuario usuarioSave) {
		
		AccionRespuesta respuesta = new AccionRespuesta();
		
		if(usuarioSave != null && usuarioDto != null) {
			
			respuesta.setId(usuarioSave.getId());
			
			respuesta.setCodigo("OK");
						
			respuesta.setResultado(Boolean.TRUE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);
			
		}else {
			
			respuesta.setId(-1L);
			
			respuesta.setCodigo("NOK");
			
			respuesta.setResultado(Boolean.FALSE);
			
			HashMap<String, Object> data= new HashMap<String, Object> ();
			
			data.put("usuarioDto", usuarioDto);
			
			respuesta.setData(data);			
		}
		
		return respuesta;		
	}

}
