package de.peil.cryptography.compression;

public abstract class CompressFunction {
	
	protected int m;
	protected int n;

	public CompressFunction(
			final int m, 
			final int n) {
		this.m = m;
		this.n = n;
	}

	public abstract String compute(String preparedInput);

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}
}