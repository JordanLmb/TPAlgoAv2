

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
        InstanceDec i = new InstanceDec(id.i, id.c);
        return algoFPT1rec(i, new Solution());
    }

    private static Solution algoFPT1rec(InstanceDec id, Solution sol) {
        sol.add(id.i.getStartingP());

        if (sol.piecesRamassees(id.i.getListeCoordPieces()) == id.c) {
            return sol;
        }

        if (id.i.getK() == 0 || sol.getNbPieces() + id.i.borneSup() < id.c) {
            return null;
        }

        InstanceDec idc = new InstanceDec(id.i, id.c);
        idc.i.setK(id.i.getK() - 1);

        if (id.i.getStartingP().getL() != 0) {
            idc.i.setStartingP(new Coord(id.i.getStartingP().getL() - 1, id.i.getStartingP().getC()));
            return algoFPT1rec(idc, sol);
        }

        if (id.i.getStartingP().getL() != id.i.getNbL() - 1) {
            idc.i.setStartingP(new Coord(id.i.getStartingP().getL() + 1, id.i.getStartingP().getC()));
            return algoFPT1rec(idc, sol);
        }

        if (id.i.getStartingP().getC() != 0) {
            idc.i.setStartingP(new Coord(id.i.getStartingP().getL(), id.i.getStartingP().getC() - 1));
            return algoFPT1rec(idc, sol);
        }

        if (id.i.getStartingP().getC() != id.i.getNbC() - 1) {
            idc.i.setStartingP(new Coord(id.i.getStartingP().getL(), id.i.getStartingP().getC() + 1));
            return algoFPT1rec(idc, sol);
        }

        return null;
    }





    public static Solution algoFPT1DP(InstanceDec id, HashMap<InstanceDec, Solution> table) {
      // Si id est déjà dans la table, on renvoie la solution correspondante
      if (table.containsKey(id)) {
          return table.get(id);
      }
  
      Solution sol = new Solution();
      sol.add(id.i.getStartingP());
  
      if (sol.piecesRamassees(id.i.getListeCoordPieces()) == id.c) {
          table.put(id, sol);
          return sol;
      }
  
      if (id.i.getK() == 0 || sol.getNbPieces() + id.i.borneSup() < id.c) {
          return null;
      }
  
      InstanceDec idc = new InstanceDec(id.i, id.c);
      idc.i.setK(id.i.getK() - 1);
  
      Solution sol1 = null;
      if (id.i.getStartingP().getL() != 0) {
          idc.i.setStartingP(new Coord(id.i.getStartingP().getL() - 1, id.i.getStartingP().getC()));
          sol1 = algoFPT1DP(idc, table);
      }
  
      Solution sol2 = null;
      if (id.i.getStartingP().getL() != id.i.getNbL() - 1) {
          idc.i.setStartingP(new Coord(id.i.getStartingP().getL() + 1, id.i.getStartingP().getC()));
          sol2 = algoFPT1DP(idc, table);
      }
  
      Solution sol3 = null;
      if (id.i.getStartingP().getC() != 0) {
          idc.i.setStartingP(new Coord(id.i.getStartingP().getL(), id.i.getStartingP().getC() - 1));
          sol3 = algoFPT1DP(idc, table);
      }
  
      Solution sol4 = null;
      if (id.i.getStartingP().getC() != id.i.getNbC() - 1) {
          idc.i.setStartingP(new Coord(id.i.getStartingP().getL(), id.i.getStartingP().getC() + 1));
          sol4 = algoFPT1DP(idc, table);
      }
  
      Solution bestSol = null;
      int bestNbPieces = 0;
  
      if (sol1 != null && sol1.getNbPieces() > bestNbPieces) {
          bestSol = sol1;
          bestNbPieces = sol1.getNbPieces();
      }
      if (sol2 != null && sol2.getNbPieces() > bestNbPieces) {
          bestSol = sol2;
          bestNbPieces = sol2.getNbPieces();
      }
      if (sol3 != null && sol3.getNbPieces() > bestNbPieces) {
          bestSol = sol3;
          bestNbPieces = sol3.getNbPieces();
      }
      if (sol4 != null && sol4.getNbPieces() > bestNbPieces) {
          bestSol = sol4;
      }
  
      if (bestSol != null) {
          sol.addAll(bestSol);
          table.put(id, sol);
          return sol;
      } else {
          return null;
      }
  }


  public static Solution algoFPT1DPClient(InstanceDec id){
    //si il est possible de collecter >= id.c pièces dans id.i, alors retourne une Solution de valeur >= c, sinon retourne null
    //doit faire appel à algoFPT1DP

    // Vérification rapide si la solution greedy permet de ramasser suffisamment de pièces
    Solution greedy = greedySolver(id.i);
    if (greedy.getNbPieces() >= id.c) {
        return greedy;
    }

    // Initialisation de la table pour la programmation dynamique
    HashMap<InstanceDec, Solution> table = new HashMap<>();
    table.put(new InstanceDec(id.i, 0), new Solution());

    // Appel récursif à algoFPT1DP
    Solution solution = algoFPT1DP(id, table);

    if (solution != null && solution.getNbPieces() >= id.c) {
        return solution;
    }

    return null;
  }
}
