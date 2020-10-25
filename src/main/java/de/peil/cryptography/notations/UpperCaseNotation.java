package de.peil.cryptography.notations;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein spezifischer Zeichensatz, der die folgende bidirektionale Abbildung realisiert:
 * 
 * <ul>
 * <li> 0 <-> A
 * <li> 1 <-> B
 * <li> ...
 * <li> 25 <-> Z
 * </ul>
 */
public class UpperCaseNotation extends PositionalNotation {
	
	private static final List<Character> CHARSET = new ArrayList<>();
	
	static {
		for (int i = 65; i < 91; i++) {
			CHARSET.add((char) i);
		}
	}

	/**
	 * Erzeugt ein neues {@link UpperCaseNotation}-Objekt.
	 */
	public UpperCaseNotation() {
		super(CHARSET);
	}

}
