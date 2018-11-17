import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Square extends JPanel implements MouseListener{
	//~~~~ Private Member Variables ~~~~
	private static GameBoard board;
	private ChessPiece piece;
	private int row, col;
	private boolean highlighted = false;
	private Color highlightColor;
	//~~~~ Constructors ~~~~
	public Square(int r, int c, GameBoard gb, boolean b){
		board = gb;
		row = r;
		col = c;
		if(b)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		
	}
	
	public Square(int r, int c, GameBoard gb, ChessPiece myPiece, boolean b){
		board = gb;
		row = r;
		col = c;
		if(b)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		piece = myPiece;
	}
	
	public Square(int r, int c, GameBoard gb){
		board = gb;
		row = r;
		col = c;
		this.setBackground(Color.BLACK);		
		this.addMouseListener(this);
	}
	
	//~~~~ some functions to specify the color of this square ~~~~
	public void setColor( int c ){//1 means black, 0 means white
		if(c==1)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
	}

	public void setColor( boolean black ){//true means black, false means white
		if(black)
			this.setBackground(Color.BLACK);
		else
			this.setBackground(Color.WHITE);
	}
	public void setColor( Color c ){
		this.setBackground(c);
	}
	
	public void setHiglighted(boolean highlight, Color c){
		highlighted = highlight;
		highlightColor = c;
		this.repaint();
	}
	
	public void setHiglighted(boolean highlight){
		highlighted = highlight;
		this.repaint();
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(piece!=null)
			piece.draw(g);
		
		if(highlighted){
			g.setColor( highlightColor );
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
		}
	}
	
	//getBoard
	public GameBoard getBoard(){return board;} //gives you the board that you're on
	
	
	//getPiece
	public ChessPiece getPiece(){return piece;}
	
	public int getRow(){return row;}
	
	public int getCol(){return col;}
	
	//setPiece
	public void setPiece(ChessPiece nuw){
		piece = nuw;
		this.repaint();
	}
	
	
	//~~~~ MouseListener stuff ~~~~
	public void mousePressed(MouseEvent arg0) {
		//when I am clicked, I will tell the board that I was clicked!
		board.clicked(this);		
	}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}
	
	public String toString(){
		String str = "("+row+","+col+")";
		return str;
	}

}
