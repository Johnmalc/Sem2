package iteratorSchmolli;

import java.util.Iterator;

public class Collection<E> {

	private E[] inner = (E[]) new Object[3];

	Iterator<E> iterator() {

		return new IteratorImpl<E>();

	}

	class IteratorImpl<E> implements Iterator<E> {

		int aktPos = 0;

		@Override
		public boolean hasNext() {
			// TODO Test
			return aktPos < inner.length;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			aktPos++;
			return (E) inner[aktPos - 1];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}