package com.springapp.mvc.document.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FormularioSecurity {

	private String usuario;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date creacionFecha;

	/**
	 * Proxy
	 */
	public FormularioSecurity() {
	}

	public FormularioSecurity(String usuario, Date creacionFecha) {
		this.usuario = usuario;
		this.creacionFecha = creacionFecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
}
