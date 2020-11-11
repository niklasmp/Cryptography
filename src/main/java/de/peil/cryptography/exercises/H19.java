package de.peil.cryptography.exercises;

import java.math.BigInteger;

import de.peil.cryptography.factorization.FactorizationUtils;

public class H19 extends Exercise {

	/**
	 * Erzeugt ein neues {@link H19}-Objekt.
	 */
	public H19(final boolean debug) {
		super("H19", debug);
	}

	@Override
	public String computeResult() {
		final BigInteger[] factors = FactorizationUtils.computePrimeFactorization(
				BigInteger.valueOf(25_777), 
				this.debug);
		return String.format("%s, %s", 
				factors[0].toString(),
				factors[1].toString());
	}

}
