package com.erpweb.entidades.crm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.erpweb.entidades.abstractas.Persona;
import com.erpweb.entidades.comun.DireccionPostal;
import com.erpweb.utiles.enumerados.TipoCliente;

@Entity
@Table(name="cliente")
public class Cliente extends Persona implements Serializable{

	private static final long serialVersionUID = -3351686938578336993L;
	
	private Long id;
    private String codigo;          
    private String nombre;					  //Nombre del cliente
    private DireccionPostal direccionPostal;  //Datos para la facturacion
    private TipoCliente TipoCliente;          //Tipo de cliente

    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	//@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	@Enumerated
	public TipoCliente getTipoCliente() {
		return TipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		TipoCliente = tipoCliente;
	}
    
   
    
    
    
    
}