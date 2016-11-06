package com.springapp.mvc.utils;

import com.springapp.mvc.document.bean.*;

import java.util.Comparator;

/**
 *
 */

public class CustomComparatorByFecha implements Comparator<Verificacion> {

	/**
	 * Compara las fechas de ambas verificaciones para odenarlas ascendentemente
	 *
	 * @param o1 the first object to be compared
	 * @param o2 the second object to be compared
	 * @return a negative integer, zero, or a positive integer as the
	 * first argument is less than, equal to, or greater than the
	 * second.
	 */
	@Override
	public int compare(Verificacion o1, Verificacion o2) {
		if (o1.getFecha() == null && o2.getFecha() == null) return 0; //Si ambas fechas son null, son iguales
		if (o1.getFecha() == null) return -1; //Si esta fecha es null, esta fecha sera menor
		if (o2.getFecha() == null) return 1; //Si la otra fecha es null, esta fecha sera mayor
		return o1.getFecha().compareTo(o2.getFecha()); //Si ninguna es null, comparar
	}
}
