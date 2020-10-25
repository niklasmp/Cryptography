package de.peil.cryptography.exercises;

import java.math.BigInteger;

import de.peil.cryptography.CryptoUtils;

public class H01 extends Exercise {

	/**
	 * Erzeugt ein neues {@link H01}-Objekt.
	 */
	public H01(final boolean debug) {
		super("H01", debug);
	}

	@Override
	public String computeResult() {
		return CryptoUtils.fastExponentiation(
				BigInteger.valueOf(17), 
				BigInteger.valueOf(999),
				BigInteger.valueOf(301)).toString();
	}

}
