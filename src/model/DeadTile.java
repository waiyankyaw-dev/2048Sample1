package model;

public class DeadTile extends NumberTile {

	public static final int exponent = 9;
	public static final int threshold = (int)Math.pow(NumberTile.base, exponent);
	public static final int value = -1;
	
	public DeadTile() {
		super(value);
	}

}
