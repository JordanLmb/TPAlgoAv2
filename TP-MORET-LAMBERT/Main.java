import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
// clear; javac *.java; java Main.java
// clear; javac *.java; java TestsAutomatiques.java
class Main {
  public static void main(String[] args) {
    boolean[][] p0 = {
                {true, true},
                {false, false},
                {true, false},

        };
        Coord sp0 = new Coord(0, 0);
        int k0 = 3;
        Instance in0 = new Instance(p0, sp0, k0);
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(0);
    list.add(1);
    list.add(2);
    ElemPermutHC permutHC = new ElemPermutHC(in0, list);
    int getVal = permutHC.getVal();
    System.out.println("getVal : " + getVal);
    }
}