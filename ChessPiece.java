import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JOptionPane;

public abstract class ChessPiece {	
	private Image img;
	//variable for what team/color i am
	private boolean myteam;
	//variable for what Square I'm sitting on 
	Square mysquare;
	
	protected Color mycolor;
	protected boolean canjump = false;
	
	//Constructor :  you will need some more parameters!
	public ChessPiece(String im, boolean me, Square mine, Color hl){
		loadImage(im);

	    
		//set up your other variables
		
		myteam = me;
		mysquare = mine;
		mine.setPiece(this);
		mycolor = hl;
		
	}//end constructor
	
	
	public Square getLoc(){return mysquare;}
	
	public boolean getTeam(){return myteam;}
	
	public boolean getJump(){return canjump;}//returns whether the piece can jump other pieces or not (only knights can jump other pieces)
	
	public void move( Square dest){
		
		if(isMoveLegal(dest)){
			Square old = mysquare; //save the old square
			mysquare = dest;//moves the piece
			dest.setPiece(this);//tells the new square that it has a piece
			old.setPiece(null);//
			mysquare.getBoard().changeturn();
		}
	}
	
	public Color getColor(){return mycolor;}//gets the color to highlight squares with
	
	
	
	//helper function for loading up your image
	private void loadImage( String im ){
		img = Toolkit.getDefaultToolkit().getImage( getClass().getResource(im) );		
	    
		MediaTracker tracker = new MediaTracker (new Component () {});
		tracker.addImage(img, 0);
		//block while reading image
		try { tracker.waitForID (0); }
	        catch (InterruptedException e) {
	        	JOptionPane.showMessageDialog(null, "Error reading file");
	        }
	}//end loadImage

	public void draw(Graphics g){
		g.drawImage(img,0,0,90,90,null,null);
	}
	
	public abstract boolean isMoveLegal(Square dest);
	
}
