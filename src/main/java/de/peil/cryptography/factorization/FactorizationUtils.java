package de.peil.cryptography.factorization;

import java.math.BigInteger;
import java.util.Arrays;

import de.peil.cryptography.CryptoUtils;

public class FactorizationUtils {

	/**
	 * Erzeugt ein neues {@link FactorizationUtils}-Objekt.
	 */
	private FactorizationUtils() {
	}
	
	/**
	 * Berechnet alle vier Quadratwurzel von {@code input} modulo {@code n}.
	 */
	public static final BigInteger[] computeSquareRoots(final BigInteger input, final int n) {
		final BigInteger[] result = new BigInteger[4];
		int resIndex = 0;
		for (int i = 0; i < n; i++) {
			final BigInteger tempRes = (BigInteger.valueOf(i).pow(2)).mod(BigInteger.valueOf(n));
			if (tempRes.equals(input)) {
				result[resIndex] = BigInteger.valueOf(i);
				resIndex++;
			}
		}
		return result;
		
	}
	
	/**
	 * Liefert die Primfaktorzerlegung der Eingabe {@code input}.
	 */
	public static final BigInteger[] computePrimeFactorization(
			final BigInteger input, 
			final boolean debug) {
		final BigInteger c = (BigInteger.valueOf(13013).pow(2)).mod(input);
		final BigInteger[] squareRoots = computeSquareRoots(c, input.intValue());
		final BigInteger[] gcd = new BigInteger[4];
		gcd[0] = CryptoUtils.gcd(BigInteger.ZERO, input);
		gcd[1] = CryptoUtils.gcd(squareRoots[1].subtract(squareRoots[0]), input);
		gcd[2] = CryptoUtils.gcd(squareRoots[2].subtract(squareRoots[0]), input);
		gcd[3] = CryptoUtils.gcd(squareRoots[3].subtract(squareRoots[0]), input);
		
		final BigInteger[] result = new BigInteger[2];
		result[0] = gcd[1];
		result[1] = gcd[2];
		
		if (debug) {
			System.out.println("Größte gemeinsame Teiler:");
			System.out.println(Arrays.toString(gcd));
		}
		
		return result;
		
	}
}
