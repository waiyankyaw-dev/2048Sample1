package model;

public class Tile {

	private int value;
	// private Color color;
	
	public Tile(int value) {
		setValue(value);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean equals(Tile other) {
		return (this.value == other.value) && this.value != DeadTile.value;
	}
	
}
