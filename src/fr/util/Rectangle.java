package fr.util;

public interface Rectangle {
	
	public double getWidth();
	public double getHeight();
	public double getY();
	public double getX();
	boolean containsPoint(int x, int y);
}
