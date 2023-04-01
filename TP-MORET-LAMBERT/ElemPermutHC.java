import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

public class ElemPermutHC implements IElemHC {

    private Instance i;
    private ArrayList<Integer> permut; //permutation de {0,..,i.getListePieces().size()-1} représentant l'ordre dans lequel on souhaite ramasser les pièces
    private static int dist = 1; //distance à laquelle on génère voisinage

    public ElemPermutHC(Instance i, ArrayList<Integer> p){
        this.i = i;
        permut = p;
    }

    public ElemPermutHC(ElemPermutHC s){
        this.i = new Instance(s.i);
        this.permut = new ArrayList<Integer>();
        permut.addAll(s.permut);
    }

    public static void setDist(int d){
        dist = d;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElemPermutHC)) return false;
        ElemPermutHC that = (ElemPermutHC) o;
        return i.equals(that.i) && permut.equals(that.permut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, permut);
    }

    public int getVal(){


        //retourne nbCases * valSol - nbStepsTotal, où :
        //- nbCases est le nombre de cases du plateau
        //- valSol est la valeur de la solution associée à this
        //- nbStepsTotal est le nombre de pas total qu'il faudrait pour ramasser toutes les pièces dans l'ordre de permut

        // à compléter
      int nbCases = i.getNbL()*i.getNbC();
      System.out.println("nbCases : " + nbCases);
      int valSol = this.getSol().piecesRamassees(i.getListeCoordPieces());
      System.out.println("valSol : " + valSol);
      int nbStepsTotal = 0;
      nbStepsTotal += Math.abs(i.getStartingP().getC()-i.getListeCoordPieces().get(permut.get(0)).getC()) +    
                      Math.abs(i.getStartingP().getL()-i.getListeCoordPieces().get(permut.get(0)).getL());
      System.out.println("nbStepsTotal : " + nbStepsTotal);
      for(int j=0;j<permut.size()-1;j++){
        nbStepsTotal += Math.abs(i.getListeCoordPieces().get(permut.get(j)).getC()-i.getListeCoordPieces().get(permut.get(j+1)).getC()) +    
                        Math.abs(i.getListeCoordPieces().get(permut.get(j)).getL()-i.getListeCoordPieces().get(permut.get(j+1)).getL());
      System.out.println("nbStepsTotal : " + nbStepsTotal);
      }
      System.out.println("nbStepsTotal : " + nbStepsTotal);
      return nbCases * valSol - nbStepsTotal;
    }

    public Solution getSol(){
        return i.calculerSol(permut);
    }


    public ArrayList<ElemPermutHC> getVoisinsImmediats() {


        //retourne l'ensemble des voisins à dist <= 1 (et donc this fait partie du résultat car à distance 0)
        //voisins = toutes les permutations que l'on peut atteindre en repoussant un élément de permut à la fin
        //ex pour permut = (0,1,2), doit retourner {(1,2,0),(0,2,1),(0,1,2)}
        //les objets retournés doivent être indépendant de this, et cette méthode ne doit pas modifier this

        //ne dois pas modifier this

        ArrayList<ElemPermutHC> result = new ArrayList<ElemPermutHC>();
        //result.add(this);
        for(int i: this.permut) {
            ElemPermutHC newPermut = new ElemPermutHC(this);
            newPermut.permut.remove(i);
            newPermut.permut.add(i);
            result.add(newPermut);
        }
        return result;
    }



    public ArrayList<ElemPermutHC> getVoisins(){

        //retourne voisins (sans doublons) à une distance <= dist
        //pour dist = 1, doit retourner getVoisinsImmediats();
        if(dist == 1){
            return getVoisinsImmediats();
        }
        ArrayList<ElemPermutHC> result = new ArrayList<ElemPermutHC>();
        result.add(this);

        for (int d = 1; d <= dist; d++) {
            ArrayList<ElemPermutHC> newPermutations = new ArrayList<ElemPermutHC>();
            for (ElemPermutHC e : result) {
                for (int i = 0; i < e.permut.size() - 1; i++) {
                    for (int j = i + 1; j < e.permut.size(); j++) {
                        ElemPermutHC newPermut = new ElemPermutHC(e);
                        Collections.swap(newPermut.permut, i, j);
                        newPermutations.add(newPermut);
                    }
                }
            }
            result.addAll(newPermutations);
        }

        // Remove duplicates
        HashSet<ElemPermutHC> set = new HashSet<ElemPermutHC>(result);
        result.clear();
        result.addAll(set);

        return result;
    }

}