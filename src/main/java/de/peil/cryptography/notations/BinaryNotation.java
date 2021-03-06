package de.peil.cryptography.notations;

import java.util.Arrays;

/**
 * Ein spezifischer Zeichensatz, der die folgende bidirektionale Abbildung realisiert:
 * 
 * <ul>
 * <li> 0 <-> 0
 * <li> 1 <-> 1
 * </ul>
 */
public class BinaryNotation extends PositionalNotation {

	/**
	 * Erzeugt ein neues {@link BinaryNotation}-Objekt.
	 */
	public BinaryNotation() {
		super(Arrays.asList('0', '1'));
	}
	
}