

import java.util.*;

public class Algos {

    public static boolean egalEnsembliste(ArrayList<?> a1, ArrayList<?> a2){
        //retourn vrai ssi les a1 à les même éléments que a2 (peut importe l'ordre)
        return a1.containsAll(a2) && a2.containsAll(a1);
    }


    public static Solution greedySolver(Instance i) {

        //calcule la solution obtenue en allant chercher à chaque étape la pièce restante la plus proche
        //(si plusieurs pièces sont à la même distance, on fait un choix arbitraire)

        return i.calculerSol(i.greedyPermut());
    }

    //int sol = 0;
    public static Solution algoFPT1(InstanceDec id) {
        //algorithme qui décide id (c'est à dire si opt(id.i) >= id.c) en branchant (en 4^k) dans les 4 directions pour chacun des k pas
        //retourne une solution de valeur >= c si une telle solution existe, et null sinon
        //Ne doit pas modifier le paramètre
        //Rappel : si c==0, on peut retourner la solution égale au point de départ puisque l'on est pas obligé d'utiliser les k pas
        // (on peut aussi retourner une solution plus longue si on veut)
        //Remarque : quand vous aurez codé la borneSup, pensez à l'utiliser dans cet algorithme pour ajouter un cas de base

        //à compléter
        //id.i = instance
        //id.c = np
        //id.i.startingP = case départ
        //id.i.k = k
        //id.i.plateau = grille
        /*Instance newInst = Instance(id.i);
        Solution sol = new Solution(id.i.startingP);
      if(id.c == 0 && id.i.k == 0) {
        return sol;
      }*/
     /* 
      if(sol == id.c) {
          return true;
        }
        if(id.i.k == 0) {
          return false;
        }
        if((sol+id.i.k)< id.c){
          return false;
        }
        if(id.i.plateau(id.i.startingP)==true) {
          sol++;
          id.i.plateau(id.i.startingP)==false;
        }
        return algo(gauche || droite || haut || bas)
      */
        InstanceDec i = new InstanceDec(id.i,id.c);
        return algoFPT1rec(i, new Solution());
    }

    private static Solution algoFPT1rec(InstanceDec id, Solution sol) {
      // previous : 0 debut, 1 gauche, 2 droite, 3 haut, 4 bas
      // ajout du chemin à la solution
        sol.add(id.i.getStartingP());
      // cas de base
      if(sol.piecesRamassees(id.i.getListeCoordPieces()) == id.c) {
        //System.out.println("Solution trouvée : " + sol);
        return sol;
      }
      if(id.i.getK() == 0) {
        //System.out.println("Plus de déplacements... ");
        return null;
      }
      // copie de l'instance
      InstanceDec idc = new InstanceDec(id.i,id.c);
      idc.i.setK(id.i.getK()-1);
      // Tester les côtés possibles
      // Point de départ : pas à gauche => tester à gauche
      if(id.i.getStartingP().getL() != 0){
        idc.i.setStartingP(new Coord(id.i.getStartingP().getL() - 1, id.i.getStartingP().getC()));
        return algoFPT1rec(idc, sol);
      }
      // Point de départ : pas à droite => tester à droite
      if(id.i.getStartingP().getL() != id.i.getNbL() - 1){
        idc.i.setStartingP(new Coord(id.i.getStartingP().getL() + 1, id.i.getStartingP().getC()));
        return algoFPT1rec(idc, sol);
      }
      // Point de départ : pas en haut => tester en haut
      if(id.i.getStartingP().getC() != 0){
        idc.i.setStartingP(new Coord(id.i.getStartingP().getL(), id.i.getStartingP().getC() - 1));
        return algoFPT1rec(idc, sol);
      }
      // Point de départ : pas en bas => tester en bas
      if(id.i.getStartingP().getC() != id.i.getNbC() - 1){
        idc.i.setStartingP(new Coord(id.i.getStartingP().getL(), id.i.getStartingP().getC() + 1));
        return algoFPT1rec(idc, sol);
      }
    return null;
    }





    public static Solution algoFPT1DP(InstanceDec id,  HashMap<InstanceDec,Solution> table) {
        //même spécification que algoFPT1, si ce n'est que
        // - si table.containsKey(id), alors id a déjà été calculée, et on se contente de retourner table.get(id)
        // - sinon, alors on doit calculer la solution s pour id, la ranger dans la table (table.put(id,res)), et la retourner

        //Remarques
        // - ne doit pas modifier l'instance id en param (mais va modifier la table bien sûr)
        // - même si le branchement est le même que dans algoFPT1, ne faites PAS appel à algoFPT1 (et donc il y aura de la duplication de code)


        //à compléter
        return null;
    }


    public static Solution algoFPT1DPClient(InstanceDec id){
        //si il est possible de collecter >= id.c pièces dans id.i, alors retourne une Solution de valeur >= c, sinon retourne null
        //doit faire appel à algoFPT1DP

        //à completer
        return null;

    }



}
