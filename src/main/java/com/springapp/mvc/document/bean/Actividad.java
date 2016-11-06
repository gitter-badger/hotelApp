package com.springapp.mvc.document.bean;

public class Actividad {

	private CodigoValor actividad;

	private Boolean estado;

	private String observacion;

	/**
	 * Proxy
	 */
	public Actividad() {
	}

	public Actividad(CodigoValor actividad, Boolean estado, String observacion) {
		this.actividad = actividad;
		this.estado = estado;
		this.observacion = observacion;
	}

	public CodigoValor getActividad() {
		return actividad;
	}

	public void setActividad(CodigoValor actividad) {
		this.actividad = actividad;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public String toString() {
		return "Actividad{" +
				"actividad=" + actividad +
				", estado=" + estado +
				", observacion='" + observacion + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Actividad actividad1 = (Actividad) o;

		return actividad != null ? actividad.equals(actividad1.actividad) : actividad1.actividad == null;
	}

	@Override
	public int hashCode() {
		return actividad != null ? actividad.hashCode() : 0;
	}
}
