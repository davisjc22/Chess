import java.awt.Color;

public class Bishop extends ChessPiece{
	
	public Bishop(String im, boolean tm, Square lc, Color co) {
		super(im, tm, lc, co);
		
	}	
	
	public boolean isMoveLegal(Square dest) {
		if(dest == getLoc())
			return false;
		if(Math.abs(dest.getRow()-this.getLoc().getRow())==Math.abs(dest.getCol()-this.getLoc().getCol()))
			return true; //if the new row and col are the same, it can move there
		else
			return false;
			
	}
	
}
