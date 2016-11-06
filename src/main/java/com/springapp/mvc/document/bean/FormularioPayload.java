package com.springapp.mvc.document.bean;

import com.springapp.mvc.document.enums.*;
import com.springapp.mvc.utils.*;
import java.util.*;

public class FormularioPayload {

	private List<Verificacion> verificaciones;

	/**
	 * Proxy
	 */
	public FormularioPayload() {
	}

	/**
	 * Constructor
	 *
	 * @param verificaciones Lista de verificaciones o actividades realizadas sobre el documento
	 */
	public FormularioPayload(List<Verificacion> verificaciones) {
		this.verificaciones = verificaciones;
	}

	public List<Verificacion> getVerificaciones() {
		return verificaciones;
	}

	public void setVerificaciones(List<Verificacion> verificaciones) {
		this.verificaciones = verificaciones;
	}

	/**
	 * Saca la verificacion del payload del documento que tiene la fecha mas reciente
	 *
	 * @return Verificacion mas reciente del payload
	 */
	public Verificacion latestVerification() {
		if (this.verificaciones != null && this.verificaciones.size() > 0) {
			List<Verificacion> verificaciones = this.verificaciones;
			Collections.sort(verificaciones, new CustomComparatorByFecha());
			return verificaciones.get(verificaciones.size() - 1);
		} else {
			return null;
		}
	}

	/**
	 * Agrega una verificacion a la lista de verificaciones del payload
	 * si ultima verificacion existente en el payload es borrador, sobre-escribe dicha verificacion con la nueva
	 *
	 * @param verificacion Nueva verificacion
	 */
	public void addVerification(Verificacion verificacion) {

		Boolean addNew = true;

		//Si existen elementos en el list
		if (this.verificaciones != null && this.verificaciones.size() > 0) {

			//Recuperamos el ultimo elemento del list
			Verificacion verificacion1 = this.latestVerification();

			//Si el ultimo elemento del list es borrador, lo sobre-escribimos
			if (verificacion1.getCodigo().equals(VerificacionPayload.AFORO_BORRADOR)) {

				//Sobre escribir el ultimo elemento con la nueva verificacion dada
				Collections.sort(this.verificaciones, new CustomComparatorByFecha());
				this.verificaciones.set(this.verificaciones.size() - 1, verificacion);
				addNew = false;
			}
		}

		//Si tengo que aumentar un nuevo elemento, lo agrego aqui
		if (addNew) {
			this.verificaciones = this.verificaciones != null ? this.verificaciones : new ArrayList<Verificacion>();
			this.verificaciones.add(verificacion);
		}
	}

	@Override
	public String toString() {
		return "FormularioPayload{" +
				"verificaciones=" + verificaciones +
				'}';
	}
}
