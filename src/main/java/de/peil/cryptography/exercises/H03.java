package de.peil.cryptography.exercises;

import java.math.BigInteger;

import de.peil.cryptography.CryptoUtils;

public class H03 extends Exercise {

	/**
	 * Erzeugt ein neues {@link H03}-Objekt.
	 */
	public H03(final boolean debug) {
		super("H03", debug);
	}

	@Override
	public String computeResult() {
		try {
			return CryptoUtils.computeMultiplicativeInverse(
					BigInteger.valueOf(4671), 
					BigInteger.valueOf(8731),
					this.debug).toString();
		} catch (Exception e) {
			return e.getCause().toString();
		}
	}

}
