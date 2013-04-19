package msfp05;

import java.util.Arrays;

/*
 * http://www.vogella.com/articles/JavaDatastructures/article.html#stack
 */
public class ArrayStack<E> implements Stack<E> {
	private E[] stack;
	int counter;

	public ArrayStack(int size) {
		stack = (E[]) new Object[size];
		counter = 0;

	}

	@Override
	public void push(E elem) {
		if (counter == stack.length) {
			//ensureCapa();
			throw new RuntimeException();
		}
		stack[counter++] = elem;
	}

	@Override
	public E pop() {
		if (stack[counter] == null) {
			throw new RuntimeException();
		}
		E e = (E) stack[--counter];
		stack[counter] = null;
		return e;
	}

	private void ensureCapa() {
		int newSize = stack.length * 2;
		stack = Arrays.copyOf(stack, newSize);
	}

}
