package de.peil.cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import de.peil.cryptography.helper.GCDStep;
import de.peil.cryptography.notations.BinaryNotation;

/**
 * Ansammlung kryptografischer Hilfsmethoden.
 */
public class CryptoUtils {

	/**
	 * Konstruktor.
	 */
	private CryptoUtils() {
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
	
	public static final BigInteger gcd(final BigInteger a, final BigInteger b) {
		BigInteger aClone = a;
		BigInteger bClone = b;
		while (!bClone.equals(BigInteger.ZERO)) {
			final BigInteger rest = aClone.mod(bClone);
			aClone = bClone;
			bClone = rest;
		}
		return aClone;
	}
	
	/**
	 * TODO: Algorithmus vervollständigen.
	 */
	public static final BigInteger computeMultiplicativeInverse(
			final BigInteger a, 
			final BigInteger modul,
			final boolean debug) throws Exception {
		
		// Determine gcd
		final Stack<GCDStep> steps = new Stack<>();
		BigInteger aClone = a;
		BigInteger bClone = modul;
		while (!bClone.equals(BigInteger.ZERO)) {
			final BigInteger rest = aClone.mod(bClone);
			final BigInteger quot = aClone.divide(bClone);
			final GCDStep step = new GCDStep(aClone, bClone, quot, rest);
			steps.push(step);
			if (debug) {
				System.out.println(step);
			}
			aClone = bClone;
			bClone = rest;
		}
		
		if (!aClone.equals(BigInteger.ONE)) {
			throw new Exception("The gcd does not equals 1 - multiplicative inverse not found");
		}
		
		// Pop last calculation
		steps.pop();
		
		return aClone;
		
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
