package de.peil.cryptography.algorithm;

import java.math.BigInteger;

import de.peil.cryptography.CryptoUtils;
import de.peil.cryptography.notations.PositionalNotation;

/**
 * Die Implementierung des RSA-Algorithmus.
 */
public class RSAAlgorithm {
	
	/**
	 * Das Modul (Bestandteil des öffentlichen Schlüssels).
	 */
	private final BigInteger n;
	
	/**
	 * Der Verschlüsselungsexponent (Bestandteil des öffentlichen Schlüssels).
	 */
	private final int e;
	
	/**
	 * Der Entschlüsselungsexponent (privater Schlüssel).
	 */
	private final BigInteger d;
	
	/**
	 * Erzeugt ein neues {@link RSAAlgorithm}-Objekt.
	 */
	public RSAAlgorithm(
			final BigInteger n,
			final int e,
			final BigInteger d) {
		this.n = n;
		this.e = e;
		this.d = d;
	}
	
	/**
	 * Verschlüsselt den Klartext {@code m} und liefert das berechnete Chiffrat zurück. Dafür wird der Zeichensatz {@code charSet} verwendet.
	 * Der Klartext {@code m} wird dafür in Blöcke der Länge {@code k} zerlegt. Das Chiffrat wird durch Blöcke der Länge {@code l} 
	 * zusammengesetzt. 
	 * 
	 * @param m
	 * 		Der Klartext, der verschlüsselt werden soll.
	 * @param k
	 * 		Die Länge der Blöcke (Character) des Klartexts.
	 * @param l
	 * 		Die Länge der Blöcke (Character) des Chiffrats.
	 * @param charSet
	 * 		Der verwendete Zeichensatz.
	 * @return
	 * 		Das Chiffrat aus dem Klartext {@code m}.
	 */
	public final String encrypt(
			final String m, 
			final int k, 
			final int l,
			final PositionalNotation charSet, 
			final boolean debug) {
		final String[] blocks = m.split(String.format("(?<=\\G.{%d})", k));
		String result = "";
		for (String block : blocks) {
			final BigInteger mBlock = charSet.parse(block);	
			if (debug) {
				System.out.println(mBlock.toString());
			}
			final BigInteger cBlock = CryptoUtils.fastExponentiation(
					mBlock, 
					BigInteger.valueOf(this.e), 
					this.n);
			result += charSet.print(cBlock, l);
		}
		return result;
		
	}
	
	/**
	 * Entschlüsselt das Chiffrat {@code c} und liefert den berechneten Klartext zurück. Dafür wird der Zeichensatz {@code charSet} verwendet.
	 * Das Chiffrat {@code c} wird dafür in Blöcke der Länge {@code l} zerlegt. Der Klartext wird durch Blöcke der Länge {@code k} 
	 * zusammengesetzt. 
	 * 
	 * @param c
	 * 		Das Chiffrat, das entschlüsselt werden soll.
	 * @param k
	 * 		Die Länge der Blöcke (Character) des Klartexts.
	 * @param l
	 * 		Die Länge der Blöcke (Character) des Chiffrats.
	 * @param charSet
	 * 		Der verwendete Zeichensatz.
	 * @return
	 * 		Der Klartext aus dem Chiffrat {@code c}.
	 */
	public final String decrypt(
			final String c, 
			final int k, 
			final int l,
			final PositionalNotation charSet) {
		final String[] blocks = c.split(String.format("(?<=\\G.{%d})", l));
		String result = "";
		for (String block : blocks) {
			final BigInteger cBlock = charSet.parse(block);			
			final BigInteger mBlock = CryptoUtils.fastExponentiation(
					cBlock, 
					this.d, 
					this.n);
			result += charSet.print(mBlock, k);
		}
		return result;
		
	}
}
