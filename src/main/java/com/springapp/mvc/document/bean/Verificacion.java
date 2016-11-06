package com.springapp.mvc.document.bean;

import com.springapp.mvc.document.enums.*;

import java.util.Date;
import java.util.List;

public class Verificacion {

	private VerificacionPayload codigo;

	private String observacionDocumental;

	private String observacionFisica;

	private List<Actividad> actividades;

	private String usuario;

	private Date fecha;

	public Verificacion() {
	}

	public Verificacion(
			VerificacionPayload codigo,
			String observacionDocumental,
			String observacionFisica,
			List<Actividad> actividades,
			String usuario,
			Date fecha
	) {
		this.codigo = codigo;
		this.observacionDocumental = observacionDocumental;
		this.observacionFisica = observacionFisica;
		this.actividades = actividades;
		this.usuario = usuario;
		this.fecha = fecha;
	}

	public VerificacionPayload getCodigo() {
		return codigo;
	}

	public void setCodigo(VerificacionPayload codigo) {
		this.codigo = codigo;
	}

	public String getObservacionDocumental() {
		return observacionDocumental;
	}

	public void setObservacionDocumental(String observacionDocumental) {
		this.observacionDocumental = observacionDocumental;
	}

	public String getObservacionFisica() {
		return observacionFisica;
	}

	public void setObservacionFisica(String observacionFisica) {
		this.observacionFisica = observacionFisica;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Verificacion{" +
				"codigo=" + codigo +
				", observacionDocumental='" + observacionDocumental + '\'' +
				", observacionFisica='" + observacionFisica + '\'' +
				", actividades=" + actividades +
				", usuario='" + usuario + '\'' +
				", fecha=" + fecha +
				'}';
	}
}
