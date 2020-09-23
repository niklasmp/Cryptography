package de.peil.cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Ansammlung kryptografischer Hilfsmethoden.
 */
public class CryptoUtil {

	/**
	 * Konstruktor.
	 */
	private CryptoUtil() {
	}
	
	/**
	 * Liefert die Restklasse von {@code (base ^ exponent) mod modul}.
	 * 
	 * @param base
	 * 		Die Basis.
	 * @param exponent
	 * 		Der Exponent.
	 * @param modul
	 * 		Das Modul.
	 * @return
	 * 		Die Restklasse.
	 */
	public static final BigInteger fastExponentiation(
			final BigInteger base, 
			final BigInteger exponent, 
			final BigInteger modul) {
		final char[] binaryExponent = new BinaryNotation().print(exponent).toCharArray();
		
		BigInteger current = base;
		final List<BigInteger> tempResults = new ArrayList<>();
		tempResults.add(current);
		for (int i = 0; i < binaryExponent.length - 1; i++) {
			final BigInteger tempResult = (current.pow(2)).mod(modul);
			current = tempResult;
			tempResults.add(tempResult);
		}
		
		final Iterator<BigInteger> iterator = tempResults.iterator();
		int index = binaryExponent.length - 1;
		while(iterator.hasNext()) {
			iterator.next();
			if (binaryExponent[index] == '0') {
				iterator.remove();
			}
			index--;
		}
		
		return fastExponentiation(tempResults, modul);
	}
	
	private static final BigInteger fastExponentiation(
			final List<BigInteger> tempResults, 
			final BigInteger modul) {
		if (tempResults.size() <= 2) {
			return multiplyWithFurtherMod(tempResults, modul);
		} else {
			final List<BigInteger> first = tempResults.subList(0, tempResults.size() / 2);
			final List<BigInteger> second = tempResults.subList(tempResults.size() / 2, tempResults.size());
			final List<BigInteger> r = Arrays.asList(
					fastExponentiation(first, modul), 
					fastExponentiation(second, modul));
			return fastExponentiation(r, modul);
		}
	}
	
	private static final BigInteger multiplyWithFurtherMod(
			final List<BigInteger> tempResults,
			final BigInteger modul) {
		BigInteger result = BigInteger.ONE;
		for (final BigInteger b : tempResults) {
			result = result.multiply(b);
		}
		return result.mod(modul);
	}
}
