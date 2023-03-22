
import java.util.ArrayList;


public class Solution extends ArrayList<Coord> {
    public Solution(Coord c){
        this.add(c);
    }
    public Solution(){

    }

    public int piecesRamassees(ArrayList<Coord> pieces) {
      int nbPieces = 0;
      for(Coord c: this) {
        if(pieces.contains(c)) { nbPieces++; }
      }
      return nbPieces;
    }


}
