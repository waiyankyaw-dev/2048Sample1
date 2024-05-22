package view;

import java.awt.Font;
import java.io.File;

import model.NumberTile;

public class FontHelper {
	public static Font getDefaultTileFont(int value) {
		File fontFile = new File("ClearSans-Bold.ttf");
		Font font;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile)
					   .deriveFont(Font.BOLD, getFontSize(value));
		} catch(Exception e) {
			font = new Font("Helvetica", Font.BOLD, getFontSize(value));
		}
		
		return font;
	}
	
	public static Font getDefaultFontOfSize(int size) {
		File fontFile = new File("ClearSans-Bold.ttf");
		Font font;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile)
					   .deriveFont(Font.BOLD, size);
		} catch(Exception e) {
			font = new Font("Helvetica", Font.BOLD, size);
		}
		
		return font;
	}
	
	private static int getFontSize(int value) {
		int exponent = (int)(Math.log(value) / Math.log(NumberTile.base));
		return exponent > 8 ? 35 : 45;
	}
}
