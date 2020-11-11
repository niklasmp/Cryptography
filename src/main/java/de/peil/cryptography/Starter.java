package de.peil.cryptography;

import de.peil.cryptography.exercises.H01;
import de.peil.cryptography.exercises.H03;
import de.peil.cryptography.exercises.H08;
import de.peil.cryptography.exercises.H10;
import de.peil.cryptography.exercises.H13;
import de.peil.cryptography.exercises.H19;

public class Starter {

	public static void main(final String[] args) throws Exception {
		
		final boolean debug = false;
		
		new H01(debug).compute();
		
		new H03(debug).compute();
		
		new H08(debug).compute(); 
		
		new H10(debug).compute();
		
		new H13(debug).compute();
		
		new H19(debug).compute();
	}

}
