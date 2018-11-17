import java.awt.*;

import javax.swing.*;

public class GameBoard extends JFrame{
	private static final int ROWS = 8, COLS = 8;
	//you'll need a 2d array
	final Square[][] chessboard;
	//you'll need variables to keep track of the 1st and 2nd squares that were clicked
	Square start;
	Square finish;
	boolean goodturn = true;
	
	Color oracle = new Color( 255, 247, 0, 100); //Yellow
	Color nw = new Color( 0, 106, 255, 100); //Blue'
	Color def = new Color(240, 10, 125, 100);//Grey
	Color bman = new Color( 46, 168, 255, 100);//Lighter Blue
	Color rr = new Color( 242, 73, 27, 100); //Robin Red
	Color riddler = new Color( 24, 196, 47, 100);//Green
	Color scarecrow = new Color( 232, 148, 12, 100);//Brown
	Color freeze = new Color( 21, 232, 204, 100);//Ice Blue
	Color hred = new Color( 255, 0, 0, 100);//Harley Red
	Color jker = new Color( 199, 12, 232, 100);//Purple
	
	int turncount = 0;
	
	public GameBoard(){
		super("CHESS");
		
		this.setLayout(new GridLayout(ROWS,COLS));
		boolean black = false;
		//be sure to instantiate the array
		chessboard = new Square[8][8];

		//now you'll need to birth each element of the array AND add it to the Frame 
		for (int r=0; r<ROWS; r++){
			for (int c=0; c< COLS; c++){
				chessboard[r][c] = new Square(r,c,this, black);
				this.add(chessboard[r][c]);
				if(black)
					black = false;
				else
					black = true;
			}
			if(black)
				black = false;
			else
				black = true;
		}	
				
		//Chesspieces
		
		//Make Pawns
		Pawn[] robin = new Pawn[8];
		robin[0] = new Pawn("Dick Grayson Robin.png", true, chessboard[1][0], 1, 1, 0, rr);
		robin[1] = new Pawn("Jason Todd Robin.png", true, chessboard[1][1], 1, 1, 1, rr);
		robin[2] = new Pawn("Tim Drake Robin.png", true, chessboard[1][2], 1, 1, 2, rr);
		robin[3] = new Pawn("Damian Wayne Robin.png", true, chessboard[1][3], 1, 1, 3, rr);
		
		
		robin[3] = new Pawn("Damian Wayne Robin.png", true, chessboard[1][4], 1, 1, 4, rr);
		robin[2] = new Pawn("Tim Drake Robin.png", true, chessboard[1][5], 1, 1, 5, rr);
		robin[6] = new Pawn("Jason Todd Robin.png", true, chessboard[1][6], 1, 1, 6, rr);
		robin[7] = new Pawn("Dick Grayson Robin.png", true, chessboard[1][7], 1, 1, 7, rr);
		
		
		Pawn[] pawnbad = new Pawn[8];
		for(int c=0; c<COLS; c++)
			pawnbad[c]= new Pawn("Riddler.png", false, chessboard[6][c], -1, 6, c, riddler);
		
		//Make Rooks
		Rook oraclegood1 = new Rook("Oracle.png", true, chessboard[0][0], oracle);
		Rook oraclegood2 = new Rook("Oracle.png", true, chessboard[0][7], oracle);
		
		Rook penbad1 = new Rook("The Penguin.png", false, chessboard[7][0], def);
		Rook penkbad2 = new Rook("The Penguin.png", false, chessboard[7][7], def);
		
		//Make Bishops
		Bishop luciusbishop1 = new Bishop("Lucius Fox.png", true, chessboard[0][2], def);
		Bishop luciusbishop2 = new Bishop("Lucius Fox.png", true, chessboard[0][5], def);
		
		Bishop freezebishop1 = new Bishop("Mr Freeze.png", false, chessboard[7][2], freeze);
		Bishop freezebishop2 = new Bishop("Mr Freeze.png", false, chessboard[7][5], freeze);
		
		
		//Make Knights
		Knight goodknight1 = new Knight("Nightwing.png", true, chessboard[0][1], nw);
		Knight goodknight2 = new Knight("Nightwing.png", true, chessboard[0][6], nw);
		
		Knight badknight1 = new Knight("Scarecrow.png", false, chessboard[7][1], scarecrow);
		Knight badknight2 = new Knight("Scarecrow.png", false, chessboard[7][6], scarecrow);
		
		//Make Kings
		King batman = new King("Batman.png", true, chessboard[0][4], bman);
		King joker = new King("Joker.png", false, chessboard[7][4], jker);
		
		//Make Queens
		
		Queen alfred = new Queen("Alfred.png", true, chessboard[0][3], def);
		Queen harley = new Queen("Harley Quinn.png", false, chessboard[7][3], hred);
		
		
		
		
		
			
		
		
		
		//some finishing touches
		this.setSize(750,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//one of the squares will call this function to tell the board it was clicked
	public void clicked(Square whoGotClicked){

		if(start==null){//it's the FIRST click (you are trying to select a piece)
			if(whoGotClicked.getPiece()!=null && goodturn == whoGotClicked.getPiece().getTeam()){
				start = whoGotClicked;
			}
			else{
				JOptionPane.showMessageDialog(this, "It's not your turn!");//let's them know it's the other teams turn
				return;
			}
			for(int r = 0; r<ROWS; r++)
				for(int c=0; c<COLS; c++)
					if(whoGotClicked.getPiece().isMoveLegal(chessboard[r][c]) && blocked(start, chessboard[r][c])==false)
						chessboard[r][c].setHiglighted(true, whoGotClicked.getPiece().getColor());//Highlights all possible moves
		}
		else{//it is the SECOND click (you're telling it where to move)
			finish=whoGotClicked;
			
			if(finish == start || blocked(start, finish)){//if the piece doesn't move, it'll stay the same team's turn
				for(int r = 0; r<ROWS; r++)//goes through and unhighlights all pieces
					for(int c=0; c<COLS; c++)
						chessboard[r][c].setHiglighted(false);
				start = null;//restes start
				finish = null;//resets finish
				return;
			}
			
			
			start.getPiece().move(finish);
			for(int r=0; r<ROWS; r++)
				for(int c=0; c<COLS; c++)
					if(chessboard[r][c]!=null)
						if(chessboard[r][c].getPiece() instanceof King)
							if(check(chessboard[r][c]))
								JOptionPane.showMessageDialog(this, "Check!");//lets them know if they're in check
				
			start = null;//resets start
			finish = null;//resets finish
			for(int r = 0; r<ROWS; r++)//goes through and unhighlights all pieces
				for(int c=0; c<COLS; c++)
					chessboard[r][c].setHiglighted(false);
			
			

		}




	}
		
	public void changeturn(){
		if(goodturn)
			goodturn = false;
		else
			goodturn = true;
	}
	
	public boolean check( Square king){
		for(int r = 0; r<ROWS; r++)
			for(int c=0; c<COLS; c++)
				if(chessboard[r][c].getPiece()!= null && chessboard[r][c].getPiece().isMoveLegal(king)&& chessboard[r][c].getPiece().getTeam()!=king.getPiece().getTeam())
					return true;
		return false;
	}
	
	

	
	
	
	
	
	public boolean blocked(Square orig, Square dest){
		int addrow;
		int addcol;
		int currow = orig.getRow();//starts at the origin
		int curcol = orig.getCol();
		
		if(orig.getRow()==dest.getRow())// makes sure you don't divide by zero
			addrow = 0;
		else
			addrow = (dest.getRow()-orig.getRow())/(Math.abs(dest.getRow()-orig.getRow()));
			
		if(orig.getCol() == dest.getCol())//makes sure you don't divide by zero
			addcol = 0;
		else
			addcol = (dest.getCol() - orig.getCol())/(Math.abs((dest.getCol() - orig.getCol())));
		
		//don't check the origin
		currow += addrow;
		curcol += addcol;
		if(dest.getPiece() != null)//checks if the destination is occupied
			if(dest.getPiece().getTeam() == orig.getPiece().getTeam())//doesn't let pieces go through others
				return true;
		
		if( orig.getPiece() instanceof Knight)//lets knights jump other pieces
			return false;
		
		
		//if(orig.getPiece() instanceof King)
			//if()
				
				
		while( !(currow==dest.getRow() && curcol==dest.getCol())){ //while the piece isn't at it's destination
			System.out.println("Checking " + currow+ "," + curcol);
			if( isOutOfBounds(currow, curcol) || chessboard[currow][curcol].getPiece() != null ){
				System.out.println("Blocked at " + currow+ "," + curcol);//let's them know if the move is blocked
				return true;
			}
			
			currow += addrow;
			curcol += addcol;
				
		}
		
		return false;
	}
		
	public boolean isOutOfBounds( int r, int c){
		if(r>=ROWS || c>=COLS || r<0 || c<0)//returns false if the square isn't on the board
			return true;
		return false;
	}

	//lame main
	public static void main(String[] args) {
		new GameBoard();
	}

}
