package com.springapp.mvc.utils;

/**
 *
 */
public class ModelStringUtils {

	/**
	 * Retornar true si una cadena solo contiene numeros, false de lo contrario
	 *
	 * @param string Cadena
	 * @return True si la cadena solo contiene numeros, false de lo contrario
	 */
	public static boolean containsOnlyNumbers(String string) {

		boolean letterFound = false;

		for (char ch : string.toCharArray()) {
			if (Character.isLetter(ch)) {
				letterFound = true;
			}
			if (letterFound) {
				//Si tenemos letras retornar false
				return false;
			}
		}

		//Si no se encuentran letras, retornar true
		return true;
	}
}
