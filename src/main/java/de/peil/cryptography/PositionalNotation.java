package de.peil.cryptography;

import java.math.BigInteger;
import java.util.List;

/**
 * Die Repräsentation eines Zeichensatzes, mit der Zahlen in die g-adischen Darstellung umgeformt werden können.
 */
public class PositionalNotation {
	
	private final List<Character> charSet;
	
	/**
	 * Konstruktor.
	 */
	public PositionalNotation(final List<Character> charSet) {
		this.charSet = charSet;
	}
	
	/**
	 * Liefert die minimale Zeichenkette, die die Zahl {@code input} mit dem Zeichensatz {@code charSet} darstellt.
	 * Minimal bedeutet dabei, das führende Nullen entfallen.
	 * 
	 * @param input
	 * 		Die Zahl, die mit dem Zeichensatz {@code charSet} dargestellt werden soll. 
	 * @return
	 * 		Die Darstellung der Zahl mit dem Zeichensatz {@code charSet}.
	 */
	public final String print(final BigInteger input) {
		String result = "";
		BigInteger remaining = input;
		final BigInteger charSetSize = BigInteger.valueOf(charSet.size());
		while (true) {
			result = charSet.get(remaining.mod(charSetSize).intValue()) + result;
			remaining = remaining.divide(charSetSize);
			if (remaining.compareTo(BigInteger.ZERO) <= 0) {
				break;
			}
		}
		return result;
	}
	
	/**
	 * Liefert die Zeichenkette, die die Zahl {@code input} mit dem Zeichensatz {@code charSet} darstellt.
	 * Die Zeichenkette wird dabei mit der Darstellung der Null von links aufgefüllt, so dass die Länge {@code minLength} entspricht.
	 * 
	 * @param input
	 * 		Die Zahl, die mit dem Zeichensatz {@code charSet} dargestellt werden soll. 
	 * @param minLength
	 * 		Die Mindest-Länge der gelieferten Zeichenkette.
	 * @return
	 * 		Die Darstellung der Zahl mit dem Zeichensatz {@code charSet}, die u.U. mit der Darstellung der Null aufgefüllt wird, 
	 * 		so dass die Länge {@code minLength} entspricht.
	 */
	public final String print(final BigInteger input, final int minLength) {
		String result = print(input);
		while (result.length() < minLength) {
			result = this.charSet.get(0) + result;
		}
		return result;
	}
	
	/**
	 * Wandelt die Zeichenkette {@code input} anhand des Zeichensatzes {@code charSet} in eine Zahl um und liefert diese zurück.
	 * 
	 * @param input
	 * 		Die Zeichenkette, die anhand des Zeichensatzes {@code charSet} umgewandelt werden soll.
	 * @return
	 * 		Die Zahl, die der Zeichenkette anhand des Zeichensatzes {@code charSet} entspricht.
	 */
	public BigInteger parse(final String input) {
		BigInteger result = BigInteger.ZERO;
		int exponent = input.length() - 1;
		for (char c : input.toCharArray()) {
			final BigInteger coefficient = BigInteger.valueOf(charSet.size()).pow(exponent);
			result = result.add(coefficient.multiply(BigInteger.valueOf(charSet.indexOf(c))));
			exponent--;
		}
		return result;
	}
}
