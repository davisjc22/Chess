import java.awt.Color;

public class Rook extends ChessPiece{
	
	public Rook(String im, boolean tm, Square lc, Color co) {
		super(im, tm, lc, co);
		
	}
	
	public boolean isMoveLegal(Square dest) {
		if(dest == getLoc())
			return false;
		if((Math.abs(dest.getRow()-this.getLoc().getRow())==0)||((Math.abs(dest.getCol()-this.getLoc().getCol())==0)))
			return true; //if there's no change in row or no change in col, it can move there
		else
			return false;
	}
	
}
