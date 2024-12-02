import java.util.List;

public class StrategieRSI extends Strategie {
    private static final double OVERSOLD = 30;
    private static final double OVERBOUGHT = 70;
    private static final int PERIOD = 14;

    public StrategieRSI(IndicateurTechnique indicateurs) {
        super("Stratégie RSI", indicateurs);
    }

    //@Override
    //public String executerAvecPrix(List<Double> prices) {
        //throw new UnsupportedOperationException("Cette stratégie nécessite des prix de clôture.");
    //}

    @Override
    public String executerAvecPrixCloture(List<Double> closingPrices) {
        // Étape 1 : Calculer le RSI actuel
        double rsi = (double) indicateurs.createRSI(closingPrices, PERIOD).getValeur();
        
        // Étape 2 : Calculer le RSI précédent
        double previousRSI = (double) indicateurs.createRSI(closingPrices.subList(0, closingPrices.size() - 1), PERIOD).getValeur();

        // Étape 3 : Vérifier les conditions d'achat
        if (previousRSI <= OVERSOLD && rsi > OVERSOLD) {
            return "ACHAT : RSI < 30 → Actif survendu, possible remontée.\n" + "Commentaire : Cela indique que l'actif pourrait bientôt augmenter.\n" + "Décision : Il est préférable d'acheter lorsque le prix est bas et qu'une hausse est probable.\n";
        } 
        // Étape 4 : Vérifier les conditions de vente
        else if (previousRSI >= OVERBOUGHT && rsi < OVERBOUGHT) {
            return "VENTE : RSI > 70 → L’actif est suracheté, correction probable.\n" + "Commentaire : Cela montre que le prix pourrait baisser bientôt.\n" + "Décision : Il est préférable de vendre avant que le prix chute.\n";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE : RSI entre 30 et 70 → Marché neutre, pas d’action immédiate.\n" + "Commentaire : Le marché est stable, il vaut mieux attendre.\n" + "Décision : Il est préférable d'attendre que le marché donne un signal plus clair.\n";
    }
}

