package com.erpweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.erpweb.utiles.enumerados.TipoImpuesto;

public class PedidoDto implements Serializable {

	private static final long serialVersionUID = -733488310357756721L;
	
	
	private Long id;
	private String codigo;
	private Date fechaPedido;
	private String articulo; 								//Articulo
	private BigDecimal cantidad;							//Cantidad de articulos del mismo tipo y precio
	private BigDecimal baseImponibleTotal;					//Importe de la linea correspondiente al importe de los articulos sin impuestos
	private TipoImpuesto impuesto; 							//Impuesto 
	private BigDecimal importeTotal;	    				//Importe de la linea correspondiente al importe de los articuloscon impuestos
	private HashMap<Long, BigDecimal> articulosCantidad; 	//Mapa articuloId, Cantidad de articulos
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

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getBaseImponibleTotal() {
		return baseImponibleTotal;
	}

	public void setBaseImponibleTotal(BigDecimal baseImponibleTotal) {
		this.baseImponibleTotal = baseImponibleTotal;
	}

	public TipoImpuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(TipoImpuesto impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public HashMap<Long, BigDecimal> getArticulosCantidad() {
		return articulosCantidad;
	}

	public void setArticulosCantidad(HashMap<Long, BigDecimal> articulosCantidad) {
		this.articulosCantidad = articulosCantidad;
	}
	
	
	
	
}
