package de.peil.cryptography.helper;

public class Pair<T, U> {
	
	private T first;
	private U second;

	public Pair(final T first, final U second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public U getSecond() {
		return second;
	}

}
