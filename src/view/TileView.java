package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

import model.*;

public class TileView {

	private Tile tile;
	private int row;
	private int col;
	private int size;
	
	public static final int padding = 12;
	
	public TileView(Tile tile, int row, int col, int size) {
		this.tile = tile;
		this.row = row;
		this.col = col;
		this.size = size;
	}
	
	public void draw(Graphics2D g2d) {
		
		int x = padding + padding * (1 + col) + col * size;
		int y = 3 * padding + padding * (1 + row) + row * size;
		
		int value = tile.getValue();
		
		g2d.setColor(getColorForTileBackground(value));
		g2d.fillRoundRect(x, y, size, size, 10, 10);
		g2d.setColor(getColorForTileText(value)); 

		// Displaying Value
		g2d.setFont(FontHelper.getDefaultTileFont(value));
		
		FontMetrics fm = g2d.getFontMetrics();
		int textOffset = 10;
	    Rectangle2D rect = fm.getStringBounds(Integer.toString(value), g2d);
	    int xc = (int)(x + size / 2 - rect.getWidth() / 2);
	    int yc = (int)(y + size / 2 + rect.getHeight() / 2 - textOffset);

		if(value > 0) {
			g2d.drawString(Integer.toString(value), xc, yc);
		}
	}
	
	private Color getColorForTileBackground(int value) {
		int exponent = (int)(Math.log(value) / Math.log(NumberTile.base));
		
		if(value == 0) {
			return new Color(238, 228, 218, 35);
		}
		
		switch(exponent) {
		case 1:
			return new Color(238, 228, 208);
		case 2:
			return new Color(237, 224, 200);
		case 3:
			return new Color(242, 177, 121);
		case 4:
			return new Color(245, 149, 99);
		case 5:
			return new Color(246, 124, 95);
		case 6:
			return new Color(246, 94, 59);
		case 7:
			return new Color(117, 167, 241);
		case 8:
			return new Color(69, 133, 242);
		case 9:
			return new Color(237, 207, 114);
		case 10:
			return new Color(237, 197, 63);
		case 11:
			return new Color(247, 202, 24);
		default:
			return new Color(14, 17, 17);
		}
	}
	
	private Color getColorForTileText(int value) {
		int exponent = (int)(Math.log(value) / Math.log(NumberTile.base));
		
		switch(exponent) {
		case 1:
		case 2:
			return new Color(119, 110, 101);
		default:
			return new Color(249, 246, 242);
		}
	}
	
}
