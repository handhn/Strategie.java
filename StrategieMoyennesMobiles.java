import java.util.List;

public class StrategieMoyennesMobiles extends Strategie {
    private static final int SHORT_PERIOD = 20;
    private static final int LONG_PERIOD = 50;

    public StrategieMoyennesMobiles(IndicateurTechnique indicateurs) {
        super("Stratégie Moyennes Mobiles", indicateurs);
    }

    @Override
    public String executerAvecPrixCloture(List<Double> closingPrices) {
        // Étape 1 : Calculer les SMA courtes et longues actuelles
        double shortSMA = (double) indicateurs.createSMA(closingPrices, SHORT_PERIOD).getValeur();
        double longSMA = (double) indicateurs.createSMA(closingPrices, LONG_PERIOD).getValeur();
        
        // Étape 2 : Calculer les SMA courtes et longues précédentes
        List<Double> previousPrices = closingPrices.subList(0, closingPrices.size() - 1);
        double previousShortSMA = (double) indicateurs.createSMA(previousPrices, SHORT_PERIOD).getValeur();
        double previousLongSMA = (double) indicateurs.createSMA(previousPrices, LONG_PERIOD).getValeur();

        // Étape 3 : Vérifier les conditions d'achat (croisement à la hausse)
        if (previousShortSMA <= previousLongSMA && shortSMA > longSMA) {
            return "ACHAT : La moyenne courte croise la longue par le haut → Tendance haussière.\n" + "Commentaire : Cela indique que l'actif pourrait bientôt augmenter.\n" + "Décision : Il est préférable d'acheter lorsque le prix est bas et qu'une hausse est probable.\n";
        } 
        // Étape 4 : Vérifier les conditions de vente (croisement à la baisse)
        else if (previousShortSMA >= previousLongSMA && shortSMA < longSMA) {
            return "VENTE : La moyenne courte croise la longue par le bas → Tendance baissière.\n" + "Commentaire : Cela montre que le prix pourrait baisser bientôt.\n" + "Décision : Il est préférable de vendre avant que le prix chute.\n";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE : Pas de croisement ou croisement incertain → Attendre la confirmation.\n" + "Commentaire : Le marché est stable, il vaut mieux attendre.\n" + "Décision : Il est préférable d'attendre que le marché donne un signal plus clair.\n";
    }

    //@Override
    //public String executerAvecPrixCloture(List<Double> closingPrices) {
        //throw new UnsupportedOperationException("Cette stratégie ne nécessite pas des prix de clôture.");
    //}
}
