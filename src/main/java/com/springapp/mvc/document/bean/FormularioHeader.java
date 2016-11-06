package com.springapp.mvc.document.bean;

import com.springapp.mvc.document.enums.*;

public class FormularioHeader {

	private FormularioCodigo codigo;

	private FormularioTipo tipo;

	private Integer version;

	private boolean vigencia;

	/**
	 * Proxy
	 */
	public FormularioHeader() {
	}

	public FormularioHeader(FormularioCodigo codigo, FormularioTipo tipo, Integer version) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.version = version;
	}

	public FormularioHeader(FormularioCodigo codigo, FormularioTipo tipo, Integer version, boolean vigencia) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.version = version;
		this.vigencia = vigencia;
	}

	public FormularioCodigo getCodigo() {
		return codigo;
	}

	public void setCodigo(FormularioCodigo codigo) {
		this.codigo = codigo;
	}

	public FormularioTipo getTipo() {
		return tipo;
	}

	public void setTipo(FormularioTipo tipo) {
		this.tipo = tipo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
}
