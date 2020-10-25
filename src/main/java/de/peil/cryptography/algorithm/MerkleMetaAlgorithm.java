package de.peil.cryptography.algorithm;

import java.math.BigInteger;

import de.peil.cryptography.compression.CompressFunction;
import de.peil.cryptography.notations.BinaryNotation;

public final class MerkleMetaAlgorithm {
	
	private final CompressFunction compressFunction;
	private final boolean debug;

	/**
	 * Erzeugt ein neues {@link MerkleMetaAlgorithm}-Objekt.
	 */
	public MerkleMetaAlgorithm(
			final CompressFunction compressFunction,
			final boolean debug) {
		this.compressFunction = compressFunction;
		this.debug = debug;
	}
	
	public String compute(final String input) {
		final String preparedInput = this.prepare(input);
		
		final int r = this.compressFunction.getM() - this.compressFunction.getN();
		final String zeroString = getZeroString(this.compressFunction.getN());
		final String totalInput = zeroString + preparedInput;
		final String firstInput = totalInput.substring(0, this.compressFunction.getN() + r);
		String result = this.compressFunction.compute(firstInput);
		
		int startIndex = r;
		for (int i = 1; i < (int) (preparedInput.length() / r); i++) {
			final String newInput = result + preparedInput.substring(startIndex, startIndex + r);
			result = this.compressFunction.compute(newInput);
			startIndex += r;
		}
		
		assert startIndex == preparedInput.length();
		assert result.length() == this.compressFunction.getN();
		return result;
	}
	
	private String prepare(final String inputAsBinary) {
		final int r = this.compressFunction.getM() - this.compressFunction.getN();
		String x = inputAsBinary;
		
		// Schritt 1:
		while (x.length() % r != 0) {
			x = "0" + x;
		}
		print(1, x, r);
		
		// Schritt 2:
		for (int i = 0; i < r; i++) {
			x += "0";
		}
		print(2, x, r);
		
		// Schritt 3:
		String lengthAsBinary= new BinaryNotation().print(BigInteger.valueOf(inputAsBinary.length()));
		while (lengthAsBinary.length() % (r-1) != 0) {
			lengthAsBinary += "0";
		}
		print(3, lengthAsBinary, r-1);
		
		// Schritt 4:
		final StringBuilder b = new StringBuilder("1");
		int i = 0;
		for (final Character c : lengthAsBinary.toCharArray()) {
			b.append(c);
			i++;
			if (i % (r-1) == 0 && i < lengthAsBinary.length()) {
				b.append("1");
			}
		}
		lengthAsBinary = b.toString();
		print(4, lengthAsBinary, r);
		
		// Schritt 5:
		final String result = x + lengthAsBinary;
		print(5, result, r);
		return result;
		
	}
	
	private static final String getZeroString(final int zeroCount) {
		final StringBuilder b = new StringBuilder(zeroCount);
		for (int i = 0; i < zeroCount; i++) {
			b.append("0");
		}
		return b.toString();
	}
	
	private final void print(
			final int step, 
			final String x, 
			final int r) {
		if (!this.debug) {
			return;
		}
		final StringBuilder b = new StringBuilder();
		int i = 0;
		for (final Character c : x.toCharArray()) {
			i++;
			b.append(c);
			if (i % r == 0) {
				b.append(" ");
			}
		}
		System.out.println(String.format("(%d)   %s", step, b.toString()));
	}
}
