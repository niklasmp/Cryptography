package de.peil.cryptography.exercises;

import java.math.BigInteger;

import de.peil.cryptography.algorithm.ShanksAlgorithm;

public class H10 extends Exercise {

	/**
	 * Erzeugt ein neues {@link H10}-Objekt.
	 */
	public H10(final boolean debug) {
		super("H10", debug);
	}

	@Override
	public String computeResult() {
		return new ShanksAlgorithm(
				BigInteger.valueOf(13), 
				BigInteger.valueOf(14), 
				BigInteger.valueOf(541)).execute(this.debug).toString();
	}

}
