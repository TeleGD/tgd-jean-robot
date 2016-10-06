package fr.bonus;
import fr.util.*;

public abstract class Bonus extends Entity{
	
	private int duration;
	
	public int getDuration(){
		return duration;
	}
	
	public void setDuration(int duration){
		this.duration=duration;
	}
	
	abstract public void comportment();

}
