import java.util.ArrayList;
import java.util.Collections;

public class StorageArea
{
    private static ArrayList<ChessPiece> whitePieces, blackPieces;
    private static Location[] whitePlaces, blackPlaces;
    
    static
    {
        whitePieces = new ArrayList<ChessPiece>();
        blackPieces = new ArrayList<ChessPiece>();
        whitePlaces = new Location[16];
        blackPlaces = new Location[16];
        for(int i = 0; i < 16; i++)
        {
            whitePlaces[i] = new Location(i % 8, 8 + i/8);
            blackPlaces[i] = new Location(i % 8, 8 + i/8);
        }
    }
    
    public static void takePiece(ChessPiece C)
    {
    	if(C.getColorType() == 'w')
	        whitePieces.add(C);
	    else
	        blackPieces.add(C);
        //sort();
        organize();
    }
    
    public static void sort()
    {
			ArrayList<ChessPiece> sorted = new ArrayList();
			
			int max = 0, index = 0;
			for(int i = 0; i < whitePieces.size(); i++)
			{
				if(whitePieces.get(i).value > max)
				{
					max = whitePieces.get(i).value;
					index = i;
				}
			}
			sorted.add(whitePieces.get(index));
    }
	    
	public static void organize()
	{
		for(int i = 0; i < whitePieces.size(); i++)
		{
			
	        whitePieces.get(i).swapTo(whitePlaces[i]);
		}
	                
	    for(int i = 0; i < blackPieces.size(); i++)
	        blackPieces.get(i).swapTo(blackPlaces[i]);
    }
}
