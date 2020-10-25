package de.peil.cryptography.compression;

import java.math.BigInteger;

import de.peil.cryptography.CryptoUtils;
import de.peil.cryptography.notations.BinaryNotation;
import de.peil.cryptography.notations.PositionalNotation;

public class H13CompressFunction extends CompressFunction {
	
	private final PositionalNotation binaryNotation = new BinaryNotation();

	private int a;
	private int b;
	private int p;

	/**
	 * Konstruktor.
	 */
	public H13CompressFunction(
			final int m, 
			final int n,
			final int a,
			final int b,
			final int p) {
		super(m, n);
		this.a = a;
		this.b = b;
		this.p = p;
	}

	@Override
	public String compute(final String input) {
		assert input.length() == this.m;
		final int mid = input.length() / 2;
		final String[] parts = { input.substring(0, mid), input.substring(mid)};
		
		int x1 = this.binaryNotation.parse(parts[0]).intValue();
		int x2 = this.binaryNotation.parse(parts[1]).intValue();
		final BigInteger result = f(
				x1, 
				x2);
		
		String rAsBinary = this.binaryNotation.print(result);
		if (rAsBinary.length() > this.n) {
			rAsBinary = rAsBinary.substring(0, this.n);
		} else if (rAsBinary.length() < this.n) {
			while (rAsBinary.length() % this.n != 0) {
				rAsBinary = rAsBinary + "1";
			}
		}
		
		assert rAsBinary.length() == this.n;
		
		return rAsBinary;
	}
	
	private final BigInteger f(int x1, int x2) {
		final BigInteger pAsBigInteger = BigInteger.valueOf(this.p);
		final BigInteger r1 = CryptoUtils.fastExponentiation(
				BigInteger.valueOf(this.a), 
				BigInteger.valueOf(x1),
				pAsBigInteger);
		final BigInteger r2 = CryptoUtils.fastExponentiation(
				BigInteger.valueOf(this.b), 
				BigInteger.valueOf(x2),
				pAsBigInteger);
		return (r1.multiply(r2)).mod(pAsBigInteger);
	}

}
