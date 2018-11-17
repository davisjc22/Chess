import java.awt.Color;

public class King extends ChessPiece{
	
	public King(String im, boolean tm, Square lc, Color co) {
		super(im, tm, lc, co);
		
	}
	
	public boolean isMoveLegal(Square dest) {
		if((Math.abs(dest.getRow()-this.getLoc().getRow())==1)&&((Math.abs(dest.getCol()-this.getLoc().getCol())==1)))
			return true;    //if the change in row is 1 or the change in col is 1 (can't be both), it can move there
		
		else if((Math.abs(dest.getRow()-this.getLoc().getRow())==0)&&((Math.abs(dest.getCol()-this.getLoc().getCol())==1)))
			return true;   //it can move up or down 1
		
		else if((Math.abs(dest.getRow()-this.getLoc().getRow())==1)&&((Math.abs(dest.getCol()-this.getLoc().getCol())==0)))
			return true;    //it can move sideways 1
		
		else
			return false;
	}
	

	
}
