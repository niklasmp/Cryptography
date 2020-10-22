package de.peil.cryptography;

public abstract class CompressFunction {
	
	protected int m;
	protected int n;

	public CompressFunction(
			final int m, 
			final int n) {
		this.m = m;
		this.n = n;
	}

	protected abstract String compute(String preparedInput);
}