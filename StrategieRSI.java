public class StrategieRSI extends Strategie {
    private static final double OVERSOLD = 30;
    private static final double OVERBOUGHT = 70;
    private static final int PERIOD = 14;

    public StrategieRSI(IndicateurTechnique indicateurs) {
        super("Stratégie RSI", indicateurs);
    }

    @Override
    public String executer(List<Double> prices) {
        // Étape 1 : Calculer le RSI actuel
        double rsi = (double) IndicateurTechnique.createRSI(prices, PERIOD).getValeur();
        
        // Étape 2 : Calculer le RSI précédent
        double previousRSI = (double) IndicateurTechnique.createRSI(prices.subList(0, prices.size() - 1), PERIOD).getValeur();

        // Étape 3 : Vérifier les conditions d'achat
        if (previousRSI <= OVERSOLD && rsi > OVERSOLD) {
            return "ACHAT";
        } 
        // Étape 4 : Vérifier les conditions de vente
        else if (previousRSI >= OVERBOUGHT && rsi < OVERBOUGHT) {
            return "VENTE";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE";
    }
}
