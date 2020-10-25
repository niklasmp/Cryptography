package de.peil.cryptography.exercises;

import de.peil.cryptography.algorithm.MerkleMetaAlgorithm;
import de.peil.cryptography.compression.H13CompressFunction;

public class H13 extends Exercise {

	/**
	 * Erzeugt ein neues {@link H13}-Objekt.
	 */
	public H13(final boolean debug) {
		super("H13", debug);
	}

	@Override
	public String computeResult() {
		return new MerkleMetaAlgorithm(new H13CompressFunction(
				10, 
				6,
				2,
				69,
				83),
				this.debug).compute("1100010100101101010001010");
	}
	
}