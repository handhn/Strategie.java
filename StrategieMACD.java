public class StrategieMACD extends Strategie {
    private static final int SHORT_PERIOD = 12;
    private static final int LONG_PERIOD = 26;
    private static final int SIGNAL_PERIOD = 9;

    public StrategieMACD(IndicateurTechnique indicateurs) {
        super("Stratégie MACD", indicateurs);
    }

    @Override
    public String executer(List<Double> prices) {
        // Étape 1 : Calculer le MACD actuel
        List<Double> macd = (List<Double>) IndicateurTechnique.createMACD(prices, SHORT_PERIOD, LONG_PERIOD, SIGNAL_PERIOD).getValeur();
        
        // Étape 2 : Calculer le MACD précédent
        List<Double> previousMACD = (List<Double>) IndicateurTechnique.createMACD(prices.subList(0, prices.size() - 1), SHORT_PERIOD, LONG_PERIOD, SIGNAL_PERIOD).getValeur();

        // Étape 3 : Vérifier les conditions d'achat (croisement à la hausse)
        if (previousMACD.get(0) <= previousMACD.get(1) && macd.get(0) > macd.get(1)) {
            return "ACHAT";
        } 
        // Étape 4 : Vérifier les conditions de vente (croisement à la baisse)
        else if (previousMACD.get(0) >= previousMACD.get(1) && macd.get(0) < macd.get(1)) {
            return "VENTE";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE";
    }
}
