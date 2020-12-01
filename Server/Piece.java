public class Piece {
  private int positionX;
	private int positionY;
	private int color;
	private String pieceType;

  //constructors and getters and setters
	Piece(int c)
	{
		this.setPositionX(-1);
		this.positionY =-1;
		this.setColor(c);
	}
  Piece(int x, int y, int c, String pT)
	{
		this.setPositionX(x);
		this.positionY = y;
		this.setColor(c);
		pieceType = pT;
	}
}