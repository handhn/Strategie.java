import java.util.List;

public class StrategieTendance extends Strategie {
    private static final int PERIOD = 50;

    public StrategieTendance(IndicateurTechnique indicateurs) {
        super("Stratégie de Tendance", indicateurs);
    }

    @Override
    public String executerAvecPrixCloture(List<Double> closingPrices) {
        // Étape 1 : Calculer la SMA
        double sma = (double) indicateurs.createSMA(closingPrices, PERIOD).getValeur();
        
        // Étape 2 : Obtenir les prix actuels et précédents
        double currentPrice = closingPrices.get(closingPrices.size() - 1);
        double previousPrice = closingPrices.get(closingPrices.size() - 2);

        // Étape 3 : Vérifier les conditions d'achat (prix passe au-dessus de la SMA)
        if (previousPrice <= sma && currentPrice > sma) {
            return "ACHAT : Le prix dépasse la moyenne mobile → Début de tendance haussière.\n" + "Commentaire : Cela indique que l'actif pourrait bientôt augmenter.\n" + "Décision : Il est préférable d'acheter lorsque le prix est bas et qu'une hausse est probable.\n";
        } 
        // Étape 4 : Vérifier les conditions de vente (prix passe en-dessous de la SMA)
        else if (previousPrice >= sma && currentPrice < sma) {
            return "VENTE : Le prix passe sous la moyenne mobile → Début de tendance baissière.\n" + "Commentaire : Cela montre que le prix pourrait baisser bientôt.\n" + "Décision : Il est préférable de vendre avant que le prix chute.\n";
        }
        
         // Étape 5 : Si aucune condition n'est remplie, attendre
         return "ATTENTE : Le prix fluctue autour de la moyenne mobile → Pas de tendance claire, attendre.\n" + "Commentaire : Le marché est stable, il vaut mieux attendre.\n" + "Décision : Il est préférable d'attendre que le marché donne un signal plus clair.\n";
     }

     //@Override
     //public String executerAvecPrixCloture(List<Double> closingPrices) {
         //throw new UnsupportedOperationException("Cette stratégie ne nécessite pas des prix de clôture.");
     //}
}
