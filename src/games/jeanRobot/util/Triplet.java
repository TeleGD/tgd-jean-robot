package games.jeanRobot.util;

public class Triplet {
	private double a,b,c;

	public Triplet(double a,double b,double c){
		this.a=a;
		this.b=b;
		this.c=c;
	}

	public double getValue(int i){
		if(i==0){
			return a;
		}else if (i==1){
			return b;
		} else{
			return c;
		}
	}
}
