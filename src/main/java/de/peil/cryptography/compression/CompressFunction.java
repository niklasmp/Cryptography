package de.peil.cryptography.compression;

public abstract class CompressFunction {
	
	protected int m;
	protected int n;

	/**
	 * Erzeugt ein neues {@link CompressFunction}-Objekt.
	 */
	public CompressFunction(final int m, final int n) {
		this.m = m;
		this.n = n;
	}

	public String compute(final String input) {
		assert input.length() == this.m;
		final String result = compress(input);
		assert result.length() == this.n;
		return result;
	}
	
	protected abstract String compress(final String input);

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}
}