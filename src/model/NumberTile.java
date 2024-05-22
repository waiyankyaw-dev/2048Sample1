package model;

public class NumberTile extends Tile {

	// MARK: Constructors
	
	public NumberTile() {
		super(NumberTile.getDefaultValue());
	}
	
	public NumberTile(int value) {
		super(value);
	}
	
	// MARK: Functionality
	
	public void merge(Tile other) {
		if(!this.equals(other)) {
			throw new IllegalArgumentException("Tiles do not match. This value: " 
						+ this.getValue() + ", Other value: " + other.getValue());
		}
		setValue(this.getValue() + other.getValue());
	}
	
	// MARK: Static Helper Methods
	
	public static final int base = 2;
	public static final int goalExponent = 11;
	
	public static int getDefaultValue() {
		double rand = Math.random();
		if(rand < 0.8) {
			return base;
		} else {
			return (int)Math.pow(base, 2);
		}
	}
	
}
