public class StrategieMoyennesMobiles extends Strategie {
    private static final int SHORT_PERIOD = 50;
    private static final int LONG_PERIOD = 200;

    public StrategieMoyennesMobiles(IndicateurTechnique indicateurs) {
        super("Stratégie Moyennes Mobiles", indicateurs);
    }

    @Override
    public String executer(List<Double> prices) {
        // Étape 1 : Calculer les SMA courtes et longues actuelles
        double shortSMA = (double) IndicateurTechnique.createSMA(prices, SHORT_PERIOD).getValeur();
        double longSMA = (double) IndicateurTechnique.createSMA(prices, LONG_PERIOD).getValeur();
        
        // Étape 2 : Calculer les SMA courtes et longues précédentes
        List<Double> previousPrices = prices.subList(0, prices.size() - 1);
        double previousShortSMA = (double) IndicateurTechnique.createSMA(previousPrices, SHORT_PERIOD).getValeur();
        double previousLongSMA = (double) IndicateurTechnique.createSMA(previousPrices, LONG_PERIOD).getValeur();

        // Étape 3 : Vérifier les conditions d'achat (croisement à la hausse)
        if (previousShortSMA <= previousLongSMA && shortSMA > longSMA) {
            return "ACHAT";
        } 
        // Étape 4 : Vérifier les conditions de vente (croisement à la baisse)
        else if (previousShortSMA >= previousLongSMA && shortSMA < longSMA) {
            return "VENTE";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE";
    }
}
