import java.awt.Color;

public class Pawn extends ChessPiece{
	
	private int direction;
	private int startingrow;
	private int startingcol;
	public Pawn(String im, boolean tm, Square lc, int dr, int r, int c, Color co) {
		super(im, tm, lc, co);
		direction = dr;
		startingrow = r;
		startingcol = c;
		
	}

	
	public boolean isMoveLegal(Square dest) {
		int destrow = dest.getRow();
		int destcol = dest.getCol();
		
		int dr = destrow - mysquare.getRow();
		int dc = Math.abs(destcol - mysquare.getCol());
	
		if(dest.getPiece()!=null){//i'm landing on a piece
			if(dest.getPiece().getTeam()!= this.getTeam()){ //if it's attacking an enemy
				
				if((dc==1)&&(dr==1*direction))
					return true;     //if the change in col is 1 and the change in row is 1 forward, it can attack
				else
					return false;
			
			}
			else//trying to kill a teammate
				return false;
				
		}
		//if i survived to this point in the code --> it's NOT landing on anyone
		if((mysquare.getRow() == startingrow)&&(mysquare.getCol() == startingcol)){//if he's on his starting square, then it'll be his first move
				if((dest.getCol()-startingcol==0) && (dr == 2*direction))// he can move 2 spaces
						return true;
				if((dest.getCol()-startingcol)==0 && (dr == 1*direction))// he can move 1 space
						return true;
				else
					return false;
		}
		
		
		
		if ((dr == 1*direction) && (dest.getCol() - mysquare.getCol() == 0))//if he stays in his column and only moves forward, he can move
				return true;
		else		
			return false;
	}
	
}
