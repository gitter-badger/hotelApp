package com.springapp.mvc.document.bean;

public class CodigoValor {

	private String codigo;

	private String valor;

	private String tipo;

	/**
	 * Proxy
	 */
	public CodigoValor() {
	}

	public CodigoValor(String codigo, String valor, String tipo) {
		this.codigo = codigo;
		this.valor = valor;
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "CodigoValor{" +
				"codigo='" + codigo + '\'' +
				", valor='" + valor + '\'' +
				", tipo='" + tipo + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CodigoValor that = (CodigoValor) o;

		return codigo != null ? codigo.equals(that.codigo) : that.codigo == null;
	}

	@Override
	public int hashCode() {
		return codigo != null ? codigo.hashCode() : 0;
	}
}
