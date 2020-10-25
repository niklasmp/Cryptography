package de.peil.cryptography.exercises;

import java.math.BigInteger;

import de.peil.cryptography.algorithm.RSAAlgorithm;
import de.peil.cryptography.notations.CustomNotation;
import de.peil.cryptography.notations.PositionalNotation;

public class H08 extends Exercise {

	public H08(final boolean debug) {
		super("H08", debug);
	}

	@Override
	public String computeResult() {		
		final BigInteger n = BigInteger.valueOf(477_310_661);
		final int e = 127;
		
		final BigInteger d = BigInteger.valueOf(263_059_903);
		
		final PositionalNotation charSet = new CustomNotation();
		final RSAAlgorithm algo = new RSAAlgorithm(n, e, d);
		final String mOrig = "ALLE*LIEBEN*KRYPTOGRAPHIE";
		
		final String c = algo.encrypt(mOrig, 5, 6, charSet);
		assert algo.decrypt(c, 5, 6, charSet).equals(mOrig);
		return c;
	}

}
