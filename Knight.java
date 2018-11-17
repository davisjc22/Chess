import java.awt.Color;

public class Knight extends ChessPiece{
	
	public Knight(String im, boolean tm, Square lc, Color co) {
		super(im, tm, lc, co);
		canjump = true;
		
	}
	
	public boolean isMoveLegal(Square dest) {
		if(((Math.abs(dest.getRow()-this.getLoc().getRow())==1)&&((Math.abs(dest.getCol()-this.getLoc().getCol())==2)))||(Math.abs(dest.getRow()-this.getLoc().getRow())==2)&&((Math.abs(dest.getCol()-this.getLoc().getCol())==1)))
			return true; // if the change in row is 1 and col is 2, it can move. if the change in row is 2 and col is 1, it can move there
		else
			return false;
	}
	
}
