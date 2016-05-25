public class Queen extends ChessPiece
{
  public Queen(char c)
  {
  	super(c, 9);
  }
  
  public Location[] getLegalMoves(boolean check)
  {
        Location[] legalMovesArray = new Location[27];
        
        if(!ChessBoard.isTurn(getColorType()) && check)
        	return legalMovesArray;
        	
        int i = 0;
        for(int d = 0; d < 360; d += 45)
        {
            Location sPos = getLocation();
            while((loc != null && loc.isOnBoard()) || sPos.equals(getLocation()))
            {
                if(loc != null && loc.isOnBoard())
                {
                    ChessPiece C = (ChessPiece)(getGrid().get(sPos));
                    if(C != null)
                    {
                      if(isLegal(check, sPos))
                        legalMoves[i] = sPos;
                      break;
                    }
                    else if(isLegal(check, sPos))
                      legalMoves[i] = sPos;
                    i++;
                }
                sPos = sPos.getAdjacentLocation(d);
            }
        }
        return legalMovesArray;
  }
  
  public void copyTo(Location loc)
  {
    ChessBoard.add(loc, new Bishop(getColorType()));
  }
}
