import java.util.ArrayList;

class HillClimbing {

    public static Solution hillClimbingWithRestart(IFactory f, int nbRestart) {
        //prérequis : nbRestart >= 1
        //effectue nbRestart fois l'algorithme de hillClimbing, en partant à chaque fois d'un élément donné par f
        Solution bestSolution = null;
    
        for (int i = 0; i < nbRestart; i++) {
            IElemHC start = f.getRandomSol();
            Solution solution = (Solution) hillClimbing(start).getSol();
            if (bestSolution == null || solution.getNbPieces() > bestSolution.getNbPieces()) {
                bestSolution = solution;
            }
        }
    
        return bestSolution;
    }
    
    public static IElemHC hillClimbing(IElemHC s) {
        //effectue une recherche locale en partant de s :
        // - en prenant à chaque étape le meilleur des voisins de la solution courante (ou un des meilleurs si il y a plusieurs ex aequo)
        // - en s'arrêtant dès que la solution courante n'a pas de voisin strictement meilleur qu'elle
        // (meilleur au sens de getVal strictement plus grand)
    
        IElemHC current = s;
    
        while (true) {
            ArrayList<? extends IElemHC> neighbors = current.getVoisins();
            IElemHC bestNeighbor = null;
            for (IElemHC neighbor : neighbors) {
                if (bestNeighbor == null || neighbor.getVal() > bestNeighbor.getVal()) {
                    bestNeighbor = neighbor;
                }
            }
            if (bestNeighbor.getVal() <= current.getVal()) {
                break;
            }
            current = bestNeighbor;
        }
    
        return current;
    }
}