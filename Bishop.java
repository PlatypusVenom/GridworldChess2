import java.util.List;
import java.util.ArrayList;

public class Bishop extends ChessPiece
{
  public Bishop(char c)
  {
    super(c, 3);
  }
  
  public List<Location> analyzeNextPos(List<Location> legalMoves, int dir, Location cPos, Location nPos)
  {
    while(cPos != null && cPos.isOnBoard())
    {
    	System.out.println(cPos);
      nPos = cPos.getAdjacentLocation(dir);
      if(isLegal(nPos))
        legalMoves.add(nPos);
      cPos = nPos;
    }
    return legalMoves;
  }
  public Location[] getLegalMoves()
  {
        //List<Location> legalMoves = new ArrayList<Location>(0);
        Location[] legalMovesArray = new Location[14];
        int i = 0;
        for(int d = 45; d < 360; d += 90)
        {
            Location sPos = getLocation();
            while(isLegal(sPos) || sPos.equals(getLocation()))
            {
                if(isLegal(sPos))
                {
                    legalMovesArray[i] = sPos;
                    i++;
                    if(getGrid().get(sPos) != null)
                				break;
                }
                sPos = sPos.getAdjacentLocation(d);
            }
        }
    
        /*
         * This converts ArrayLists into Arrays so that
         * we can have arrays that will always be
         * the right size.
         */
        //Location[] legalMovesArray = new Location[legalMoves.size()];
        //legalMovesArray = legalMoves.toArray(legalMovesArray);
        return legalMovesArray;
    }
  
  public void copyTo(Location loc)
  {
    ChessRunner.add(loc, new Bishop(getColorType()));
  }
}