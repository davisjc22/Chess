import java.awt.Color;

public class Queen extends ChessPiece{

	public Queen(String im, boolean tm, Square lc, Color co) {
		super(im, tm, lc, co);
		
	}
	
	public boolean isMoveLegal(Square dest) {
		if(dest == getLoc())
			return false;
		if((Math.abs(dest.getRow()-this.getLoc().getRow())==0)||((Math.abs(dest.getCol()-this.getLoc().getCol())==0)))
			return true;    //Can move like a Rook
		if(Math.abs(dest.getRow()-this.getLoc().getRow())==Math.abs(dest.getCol()-this.getLoc().getCol()))
			return true;   //Can move like a Bishop
		else
			return false; // If it doesn't move like a rook or bishop, returns false(doesn't let it move)
		
	}
	
}
