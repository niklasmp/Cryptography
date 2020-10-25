package de.peil.cryptography.algorithm;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.math.BigIntegerMath;

import de.peil.cryptography.CryptoUtils;
import de.peil.cryptography.helper.Pair;

public final class ShanksAlgorithm {
	
	private BigInteger g;
	private BigInteger b;
	private BigInteger p;

	public ShanksAlgorithm(
			final BigInteger g,
			final BigInteger b,
			final BigInteger p) {
		this.g = g;
		this.b = b;
		this.p = p;		
	}
	
	public BigInteger execute(final boolean debug) {
		final BigInteger decrementedP = p.subtract(BigInteger.ONE);
		final BigInteger m = BigIntegerMath.sqrt(decrementedP, RoundingMode.CEILING);
		final BigInteger s = CryptoUtils.fastExponentiation(this.g, m, this.p);
		
		final Map<Integer, BigInteger> giantSteps = new LinkedHashMap<>();
		final Map<Integer, BigInteger> babySteps = new LinkedHashMap<>();
		for (int j = 0; j < m.intValue(); j++) {
			final BigInteger giantStep = CryptoUtils.fastExponentiation(s, BigInteger.valueOf(j), this.p);

			final BigInteger exponent = decrementedP.subtract(BigInteger.valueOf(j));
			final BigInteger babyStep = CryptoUtils.fastExponentiation(this.g, exponent, this.p).multiply(this.b).mod(this.p);
			
			giantSteps.put(j, giantStep);
			babySteps.put(j, babyStep);
		}
		
		final Pair<Integer, Integer> match = findMatchingValue(giantSteps, babySteps);
		
		final BigInteger exponent = (m.multiply(BigInteger.valueOf(match.getFirst()))).add(BigInteger.valueOf(match.getSecond()));
		
		if (debug) {
			print("Giantsteps", giantSteps);
			System.out.println();
			print("Babysteps", babySteps);
		}
		
		return exponent;
	}

	private static Pair<Integer, Integer> findMatchingValue(
			final Map<Integer, BigInteger> giantSteps, 
			final Map<Integer, BigInteger> babySteps) {
		assert giantSteps.size() == babySteps.size();
		final List<Map.Entry<Integer, BigInteger>> giantStepsSorted = sortByKey(giantSteps);
		final List<Map.Entry<Integer, BigInteger>> babyStepsSorted = sortByKey(babySteps);
		
		int gIndex = 0;
		int bIndex = 0;
		while (true) {
			final Map.Entry<Integer, BigInteger> giantStep = giantStepsSorted.get(gIndex);
			final Map.Entry<Integer, BigInteger> babyStep = babyStepsSorted.get(bIndex);

			final int compare = giantStep.getValue().compareTo(babyStep.getValue());
			if (compare == 0) {
				break;
			}
			if (compare > 0) {
				bIndex++;
			} else {
				gIndex++;
			}
		}
		return new Pair<Integer, Integer>(
				giantStepsSorted.get(gIndex).getKey(),
				babyStepsSorted.get(bIndex).getKey());
	}
	
	private static List<Map.Entry<Integer, BigInteger>> sortByKey(final Map<Integer, BigInteger> input) {
		return input.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue())
			       .collect(Collectors.toList());
	}

	private static void print(String title, Map<Integer, BigInteger> steps) {
		System.out.println(String.format("%s:", title));
		for (final Entry<Integer, BigInteger> entry : steps.entrySet()) {
			System.out.println(String.format("(%d, %s)", entry.getKey(), entry.getValue().toString()));
		}
	}

}
