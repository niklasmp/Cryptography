package de.peil.cryptography;

import java.math.BigInteger;

public class Starter {

	public static void main(String[] args) {
		
		final BigInteger n = new BigInteger("791569306435939");
		final int e = 15_485_863;
		final BigInteger d = new BigInteger("577322589362687");
		
		final PositionalNotation charSet = new CustomNotation();
		final RSAAlgorithm algo = new RSAAlgorithm(n, e, d);
		final String mOrig = "MATHEMATIK*IST*SPANNEND!";
		
		final String c = algo.encrypt(mOrig, 8, 9, charSet);
		System.out.println(c);
		
		final String m = algo.decrypt("R8F9BX-YOI,FQC2LZGO9OIZLNC5", 8, 9, charSet);
		System.out.println(m);
	}

}
