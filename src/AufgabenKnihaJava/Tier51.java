package AufgabenKnihaJava;

class TierKa<E> {
	private E is;

	public void s(E x) {
		is = x;
	}
	public E get() {
		return is;
	}
}

class Hund extends Tier51 {

}

class Katze extends Tier51 {

}

class Tier51 {

}

class Tierasjd {
	public static void main(String[] args) {
		TierKa<Katze> sf = new TierKa<Katze>();
		sf.s(new Katze());

	}
}
