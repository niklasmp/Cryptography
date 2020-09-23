package de.peil.cryptography;

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
 * <li> 26 <-> 0
 * <li> 27 <-> 1
 * <li> ...
 * <li> 35 <-> 9
 * <li> 36 <-> .
 * <li> 37 <-> ,
 * <li> 38 <-> :
 * <li> 39 <-> ;
 * <li> 40 <-> -
 * <li> 41 <-> !
 * <li> 42 <-> ?
 * <li> 43 <-> "
 * <li> 44 <-> (
 * <li> 45 <-> )
 * <li> 46 <-> *
 * </ul>
 */
public final class CustomNotation extends PositionalNotation {
	
	private static final List<Character> CHARSET = new ArrayList<>();
	
	static {
		for (int i = 65; i < 91; i++) {
			CHARSET.add((char) i);
		}
		for (int i = 0; i < 10; i++) {
			CHARSET.add(Integer.toString(i).charAt(0));
		}
		CHARSET.add('.');
		CHARSET.add(',');
		CHARSET.add(':');
		CHARSET.add(';');
		CHARSET.add('-');
		CHARSET.add('!');
		CHARSET.add('?');
		CHARSET.add('"');
		CHARSET.add('(');
		CHARSET.add(')');
		CHARSET.add('*');
	}

	public CustomNotation() {
		super(CHARSET);
	}
	
}