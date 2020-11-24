import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameSession extends UnicastRemoteObject implements GameSessionInterface
{
    private static final long serialVersionUID = 1L;
	private int playersInGame = 0;
	private Board board = new Board();
	
	public GameSession() throws RemoteException 
	{

    }
    @Override
	public int getOccupancy() throws RemoteException
	{
		return playersInGame;
	}
	
	@Override
	public String getPieceTypeAt(int x, int y) throws RemoteException
	{
		return board.getTypeAt(x, y);
	}
	
	@Override
	public boolean playerJoin() throws RemoteException
	{
		if(playersInGame == 0)
		{
			playersInGame++;
			return true;
		}
		else if(playersInGame == 1)
		{
			playersInGame++;
			board.setWinningPlayer(0);
			return true;
		}
		else{return false;}
	}
}