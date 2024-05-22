package view;

import model.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.swing.JPanel;

public class ScoreView {
	private Board board;
	private int x;
	private int y;
	private boolean altKeyBinding;
	
	public ScoreView(Board board, int x, int y, boolean altKeyBinding) {
		this.board = board;
        this.x = x + TileView.padding;
        this.altKeyBinding = altKeyBinding;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void draw(Graphics g) {	
		String scoreText = "SCORE " + board.getScoreTotal();
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16));
		FontMetrics fm = g.getFontMetrics();
	    Rectangle2D rect = fm.getStringBounds(scoreText, g);
		
		g.setColor(BoardView.boardColor);
		int scoreBackgroundWidth = (int)(2 * TileView.padding + rect.getWidth());
		g.fillRoundRect(this.x, y, scoreBackgroundWidth, 
				(int)(TileView.padding + rect.getHeight()), 10, 10);
		
		g.setColor(BoardView.backgroundColor);
		
		int scoreX = x + TileView.padding;
		int scoreY = y + TileView.padding * 2;
		g.drawString(scoreText, scoreX, scoreY);
		g.setColor(new Color(119, 110, 101));
		
		String instructionText = altKeyBinding ? "HOW TO PLAY: WASD to join tiles." 
				: "HOW TO PLAY: Arrow keys to join tiles.";
		g.drawString(instructionText, this.x, (int)(scoreY + rect.getHeight() + TileView.padding));
	}
}
