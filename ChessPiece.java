import java.util.ArrayList;
import java.lang.Comparable;
import java.awt.Color;

public abstract class ChessPiece extends Actor implements Comparable
{
    private char colorType;
    protected static int value;

    public ChessPiece(char c)
    {
        colorType = c;
        value = 0;
    }

    public ChessPiece(char c, int v)
    {
        colorType = c;
        value = v;
        //if(c == 'b')
        	//setColor(Color.black);
        //else
        	//setColor(Color.white);
    }
	
    public char getColorType()
    {
        return colorType;
    }
    
    public void swapTo(Location loc)
    {
    	if(loc == getLocation())
    		return;
    	
    	if(getGrid().get(loc) != null)
    	{
    		ChessPiece C = (ChessPiece)(getGrid().get(loc));
    		//if(C.compareTo(this) == 0)
    		//	return;
    		
    		Location loc0 = getLocation();
    		super.moveTo(C.getLocation());
    		C.putSelfInGrid(getGrid(), loc0);
    	}
    	else
    		super.moveTo(loc);
    }
    
    public King getKing()
    {
    	for (Location loc : getLocations(getColorType()))
    	{
    		ChessPiece C = (ChessPiece)(getGrid().get(loc));
    		if (C instanceof King)
    			return (King)C;
    	}
    	return null;
    }
    
    public boolean isLegal(boolean check, Location loc)
    {
    	
    	if(loc == null || !getGrid().isValid(loc))
    		return false;
    		
    	if(!loc.isOnBoard())
    		return false;
    		
    	if(loc.equals(getLocation()))
    		return false;

    	ChessPiece C = (ChessPiece)getGrid().get(loc);
    	King king = getKing();
    	Location prevLoc = getLocation();
    	
    		
    	if(C != null && C.getColorType() == colorType)
    		return false;
    	
    	if(check)
    	{
	    	ChessPiece other = tryMove(loc);
	    	if(king.isInCheck())
	    	{
	    		super.moveTo(prevLoc);
	    		if(other != null)
	    			ChessBoard.add(loc, other);
	    		return false;
	    	}
	    	super.moveTo(prevLoc);
	    	if(other != null)
	    		ChessBoard.add(loc, other);
    	}
    		
    	return true;
    }
    
    public ChessPiece tryMove(Location loc)
    {
    	ChessPiece other = (ChessPiece)(getGrid().get(loc));
    	super.moveTo(loc);
    	return other;
    }
    
    public ArrayList<Location> getLocations(char c)
    {
    	ArrayList<Location> Locs = getGrid().getOccupiedLocations();
    	for(int i = 0; i < Locs.size(); i++)
    	{
      	  	ChessPiece C = (ChessPiece)(getGrid().get(Locs.get(i)));
      	  	if(C.getColorType() != c)
      	  	{
	    		Locs.remove(i);
	    		i--;
      	  	}
		}
		return Locs;
    }
    
    public int compareTo(Object obj)
    {
    	ChessPiece C = (ChessPiece)obj;
    	if(value != C.value && !getClass().equals(C.getClass()))
    		return value - C.value;
    	else
    		return -1;
    }
    
    public String toString()
    {
        return getClass().getName() + "[Location: " + getLocation() + ", colorType: " + getColorType() + "]";
    }

    public abstract Location[] getLegalMoves(boolean check);
    public abstract void copyTo(Location loc);
}