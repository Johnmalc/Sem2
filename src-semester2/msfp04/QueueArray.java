package msfp04;

public class QueueArray<E> {

	private E[] queue;
	private int next;
	private int last;

	@SuppressWarnings("unchecked")
	public QueueArray(int capacity) {
		// super(capacity);
		queue = (E[]) new Object[capacity];
		next = last = 0;
	}

	public void enter(E elem) throws Exception {
		if (last >= queue.length) {
			throw new java.lang.Exception();
		}
		if (next > queue.length / 2) {
			rearange();
		}
		queue[last] = elem;
		last++;
	}

	public E leave() {
		if (last == 0) {
			throw new RuntimeException();
		}
		E elem = queue[next];
		next++;
		return elem;
	}

	private void rearange() {
		@SuppressWarnings("unchecked")
		E[] nqueue = (E[]) new Object[queue.length];
		for (int i = next; i < last; i++) {
			nqueue[i - next] = queue[i];
		}
		last = last - next;
		next = 0;
		queue = nqueue;
	}

	public static void main(String[] args) throws Exception {
		QueueArray<Integer> d = new QueueArray<Integer>(10);
		QueueArray<String> s = new QueueArray<String>(10);
		d.enter(3);
		d.enter(new Integer(2));
		d.enter(1);
		s.enter("fsd");
		s.enter(new Integer(5).toString());
		System.out.println(d.leave());
		String p = s.leave();
		
		System.out.println(p);
		String r = s.leave();
		System.out.println(r);
		String e = s.leave();
		System.out.println(e);
		
		
	}

}
