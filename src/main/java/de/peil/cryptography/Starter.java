package de.peil.cryptography;

import java.math.BigInteger;

public class Starter {

	public static void main(final String[] args) throws Exception {
		
		// H13
		System.out.println(new MerkleMetaAlgorithm(new H13CompressFunction(
				10, 
				6,
				2,
				69,
				83),
				true).compute("1100010100101101010001010"));
		
		System.out.println(new UpperCaseNotation().parse("FHDW"));
		
		System.out.println(CryptoUtil.fastExponentiation(
				BigInteger.valueOf(2), 
				BigInteger.valueOf(457042), 
				BigInteger.valueOf(457043)));
		
		new ShanksAlgorithm(
				BigInteger.valueOf(13), 
				BigInteger.valueOf(14), 
				BigInteger.valueOf(541)).execute(true);
		
		new ShanksAlgorithm(
				BigInteger.valueOf(8), 
				BigInteger.valueOf(555), 
				BigInteger.valueOf(677)).execute(false);
		
		final BigInteger result4 = CryptoUtil.fastExponentiation(
				BigInteger.valueOf(13), 
				BigInteger.valueOf(24), 
				BigInteger.valueOf(541));
		System.out.println(result4);
		
		final BigInteger result = CryptoUtil.fastExponentiation(
				BigInteger.valueOf(17), 
				BigInteger.valueOf(999), 
				BigInteger.valueOf(301));
		System.out.println(result);
		
		final BigInteger result2 = CryptoUtil.fastExponentiation(
				BigInteger.valueOf(1234), 
				BigInteger.valueOf(9899), 
				BigInteger.valueOf(10117));
		System.out.println(result2);
		
		final BigInteger result3 = CryptoUtil.fastExponentiation(
				BigInteger.valueOf(12), 
				BigInteger.valueOf(123), 
				BigInteger.valueOf(247));
		System.out.println(result3);
		
		final BigInteger gcd = CryptoUtil.computeMultiplicativeInverse(
				BigInteger.valueOf(127), 
				BigInteger.valueOf(477265824));
		System.out.println(gcd);
		
		final BigInteger n = BigInteger.valueOf(477_310_661);
		final int e = 127;
		final BigInteger d = BigInteger.valueOf(263_059_903);
		
		final PositionalNotation charSet = new CustomNotation();
		final RSAAlgorithm algo = new RSAAlgorithm(n, e, d);
		final String mOrig = "ALLE*LIEBEN*KRYPTOGRAPHIE";
		
		final String c = algo.encrypt(mOrig, 5, 6, charSet);
		System.out.println(c);
		
		final String m = algo.decrypt("A4L,7SAD)F!3AF)U)9A3HK\";A1R-5X", 5, 6, charSet);
		System.out.println(m);
	}

}
