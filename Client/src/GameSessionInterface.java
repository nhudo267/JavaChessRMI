import java.rmi.*;

public interface GameSessionInterface extends Remote
{
	int getOccupancy() throws RemoteException;
	boolean playerJoin() throws RemoteException;
	void playerExit() throws RemoteException;
	String getPieceTypeAt(int x, int y) throws RemoteException;
	int getColorAt(int x, int y) throws RemoteException;
	boolean gameOver() throws RemoteException;
	int getWinningPlayer() throws RemoteException;
	int getPlayerTurn() throws RemoteException;
	void setPlayerTurn(int t) throws RemoteException;
	void nextPlayerTurn() throws RemoteException;
	void resetBoard() throws RemoteException;
	void setWinningPlayer(int p) throws RemoteException;
	boolean swapPiece(int x1, int y1, int x2, int y2, int c)throws RemoteException;
	
}