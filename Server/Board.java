import java.util.Objects;

public class Board 
{
    private int colorTurn = 1;
	private boolean gameOver = false;
	private int winningPlayer = 0;
	private Piece[][] board = new Piece[8][8];
	
	Board()
	{
		this.populateBoard();
	}
	
	public void populateBoard() //2 is black 1 is white
	{
		//set black back row
		gameOver = false;
		winningPlayer = 0;
		board[0][0] = new Piece(0,0,2, "Rook");
		board[1][0] = new Piece(1,0,2, "Knight");
		board[2][0] = new Piece(2,0,2, "Bishop");
		board[3][0] = new Piece(3,0,2, "Queen");
		board[4][0] = new Piece(4,0,2, "King");
		board[5][0] = new Piece(5,0,2, "Bishop");
		board[6][0] = new Piece(6,0,2, "Knight");
		board[7][0] = new Piece(7,0,2, "Rook");
		
		//set row of black pawns
		for(int i = 0; i < 8; i++)
		{
			board[i][1] = new Piece(i,1,2, "Pawn");
		}
		
		//set black space in middle
		for(int i = 0; i <= 7; i++)
		{
			for(int j = 2; j <= 5; j++)
			{
				board[i][j] = new Piece(i,j,0, "BlankPiece");
			}
		}
		
		//set row of white pawns
		for(int i = 0; i < 8; i++)
		{
			board[i][6] = new Piece(i,6,1, "Pawn");
		}
		
		//set black back row
		board[0][7] = new Piece(0,7,1, "Rook");
		board[1][7] = new Piece(1,7,1, "Knight");
		board[2][7] = new Piece(2,7,1, "Bishop");
		board[3][7] = new Piece(3,7,1, "Queen");
		board[4][7] = new Piece(4,7,1, "King");
		board[5][7] = new Piece(5,7,1, "Bishop");
		board[6][7] = new Piece(6,7,1, "Knight");
		board[7][7] = new Piece(7,7,1, "Rook");
	}
	
}
