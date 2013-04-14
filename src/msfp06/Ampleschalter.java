package msfp06;

public class Ampleschalter {	
	
	public enum Schalter {	
		RED(1), GRUN(2);
		
		private int a;
		
		private Schalter(int A){
			this.a = A;
		}
		public int getC(){
			return a;
		}

	}
}