import java.util.List;

public class StrategieMACD extends Strategie {
    private static final int SHORT_PERIOD = 12;
    private static final int LONG_PERIOD = 26;
    private static final int SIGNAL_PERIOD = 9;

    public StrategieMACD(IndicateurTechnique indicateurs) {
        super("Stratégie MACD", indicateurs);
    }

    @Override
    public String executerAvecPrixCloture(List<Double> closingPrices) {
        // Étape 1 : Calculer le MACD actuel
        List<Double> macd = (List<Double>) indicateurs.createMACD(closingPrices, SHORT_PERIOD, LONG_PERIOD, SIGNAL_PERIOD).getValeur();
        
        // Étape 2 : Calculer le MACD précédent
        List<Double> previousMACD = (List<Double>) indicateurs.createMACD(closingPrices.subList(0, closingPrices.size() - 1), SHORT_PERIOD, LONG_PERIOD, SIGNAL_PERIOD).getValeur();

        // Étape 3 : Vérifier les conditions d'achat (croisement à la hausse)
        if (previousMACD.get(previousMACD.size() - 1) <= macd.get(macd.size() - 2) && macd.get(macd.size() - 1) > previousMACD.get(previousMACD.size() - 1)) {
            return "ACHAT : La ligne MACD croise la ligne de signal vers le haut → Pression acheteuse.\n" + "Commentaire : Cela indique que l'actif pourrait bientôt augmenter.\n" + "Décision : Il est préférable d'acheter lorsque le prix est bas et qu'une hausse est probable.\n";
        } 
        // Étape 4 : Vérifier les conditions de vente (croisement à la baisse)
        else if (previousMACD.get(previousMACD.size() - 1) >= macd.get(macd.size() - 2) && macd.get(macd.size() - 1) < previousMACD.get(previousMACD.size() - 1)) {
            return "VENTE : La ligne MACD croise la ligne de signal vers le bas → Pression vendeuse.\n" + "Commentaire : Cela montre que le prix pourrait baisser bientôt.\n" + "Décision : Il est préférable de vendre avant que le prix chute.\n";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE : La ligne MACD est proche de la ligne de signal → Aucun signal clair, attendre.\n" + "Commentaire : Le marché est stable, il vaut mieux attendre.\n" + "Décision : Il est préférable d'attendre que le marché donne un signal plus clair.\n";
    }

    //@Override
    //public String executerAvecPrixCloture(List<Double> closingPrices) {
        //throw new UnsupportedOperationException("Cette stratégie ne nécessite pas des prix de clôture.");
    //}
}
