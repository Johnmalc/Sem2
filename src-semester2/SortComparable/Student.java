package SortComparable;

public class Student implements Comparable<Student> {

	private Integer matNr;
	private String last;

	public Student(int matNr, String last) {
		this.matNr = matNr;
		this.last = last;
	}
	@Override
	public String toString() {
		return matNr + ":" + last;
	}
	public int getMatNr() {
		return matNr;
	}
	public void setMatNr(int matNr) {
		this.matNr = matNr;
	}

	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	@Override
	public int compareTo(Student arg0) {
		return this.matNr.compareTo(arg0.matNr) ;
	}
	@Override
	public boolean equals(Object obj) {
		Student s = (Student) obj;
		return this.compareTo(s) == 0 ? true : false;
	}
}
