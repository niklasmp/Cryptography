package de.peil.cryptography.exercises;

public abstract class Exercise {

	private final String title;
	
	protected final boolean debug;

	protected Exercise(
			final String title,
			final boolean debug) {
		this.title = title;
		this.debug = debug;
	}
	
	public final void compute() {
		final String result = computeResult();
		if (this.debug) {
			System.out.println();
		}
		System.out.println(String.format("(%s) %s", 
				this.title, 
				result));
		System.out.println();
		System.out.println("*********************");
		System.out.println();
	}
	
	public abstract String computeResult();

}
