package de.peil.cryptography;

import java.math.BigInteger;

final class GCDStep {
	
	private BigInteger a;
	private BigInteger b;
	private BigInteger quot;
	private BigInteger rest;

	/**
	 * Konstruktor.
	 */
	public GCDStep(
			final BigInteger a, 
			final BigInteger b, 
			final BigInteger c, 
			final BigInteger rest) {
		this.a = a;
		this.b = b;
		this.quot = c;
		this.rest = rest;
	}

	public BigInteger getA() {
		return a;
	}

	public BigInteger getB() {
		return b;
	}

	public BigInteger getQuotient() {
		return quot;
	}

	public BigInteger getRest() {
		return rest;
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s * %s + %s", 
				this.a.toString(),
				this.b.toString(),
				this.quot.toString(),
				this.rest.toString());
	}
}