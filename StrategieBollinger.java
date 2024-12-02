import java.util.List;

public class StrategieBollinger extends Strategie {
    private static final int PERIOD = 20;
    private static final double K = 2.0;

    public StrategieBollinger(IndicateurTechnique indicateurs) {
        super("Stratégie Bandes de Bollinger", indicateurs);
    }

    @Override
    public String executerAvecPrixCloture(List<Double> closingPrices) {
        // Étape 1 : Calculer les bandes de Bollinger actuelles
        BollingerBDCalculator.calculateBollingerBD current = 
            (BollingerBDCalculator.calculateBollingerBD) indicateurs.createBollingerBands(closingPrices, PERIOD, K).getValeur();
        
        // Étape 2 : Calculer les bandes de Bollinger précédentes
        BollingerBDCalculator.calculateBollingerBD previous = 
            (BollingerBDCalculator.calculateBollingerBD) indicateurs.createBollingerBands(closingPrices.subList(0, closingPrices.size() - 1), PERIOD, K).getValeur();

        // Étape 3 : Obtenir les prix actuels et précédents
        double currentPrice = closingPrices.get(closingPrices.size() - 1);
        double previousPrice = closingPrices.get(closingPrices.size() - 2);

        // Étape 4 : Vérifier les conditions d'achat (rebond sur la bande inférieure)
        if (previousPrice <= previous.lowerBand && currentPrice > current.lowerBand) {
            return "ACHAT : Le prix touche la bande inférieure → Actif survendu, rebond attendu.\n" + "Commentaire : Cela indique que l'actif pourrait bientôt augmenter.\n" + "Décision : Il est préférable d'acheter lorsque le prix est bas et qu'une hausse est probable.\n";
        } 
        // Étape 5 : Vérifier les conditions de vente (rebond sur la bande supérieure)
        else if (previousPrice >= previous.upperBand && currentPrice < current.upperBand) {
            return "VENTE : Le prix touche la bande supérieure → Actif suracheté, baisse attendue.\n" + "Commentaire : Cela montre que le prix pourrait baisser bientôt.\n" + "Décision : Il est préférable de vendre avant que le prix chute.\n";
        }
        // Étape 6 : Si aucune condition n'est remplie, attendre
        return "ATTENTE : Le prix reste entre les bandes → Marché stable, attendre.\n" + "Commentaire : Le marché est stable, il vaut mieux attendre.\n" + "Décision : Il est préférable d'attendre que le marché donne un signal plus clair.\n";
    }

    //@Override
    //public String executerAvecPrixCloture(List<Double> closingPrices) {
        // Cette méthode pourrait lever une exception si elle n'est pas supportée
        //throw new UnsupportedOperationException("Cette stratégie ne nécessite pas des prix de clôture.");
    //}
}
