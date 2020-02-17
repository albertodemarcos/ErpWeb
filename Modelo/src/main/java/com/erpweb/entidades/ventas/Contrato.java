package com.erpweb.entidades.ventas;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contrato")
public class Contrato implements Serializable {

	private static final long serialVersionUID = -6237983148813797015L;
	
	
	private Long id;
	private String codigo;
	private Set<LineaContrato> lineaContrato;
	private Factura factura;
		

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

	@OneToMany
	public Set<LineaContrato> getLineaContrato() {
		return lineaContrato;
	}

	public void setLineaContrato(Set<LineaContrato> lineaContrato) {
		this.lineaContrato = lineaContrato;
	}

	@OneToMany
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
	
	
	
	
}