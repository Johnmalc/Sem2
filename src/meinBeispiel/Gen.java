package meinBeispiel;

public class Gen<T> {
	public T name;

	public Gen(T name) {
		this.name = name;
	}

	public T name() {
		return name;
	}
	public void name(T name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return (String) name;
	}
}
