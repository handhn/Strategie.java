public class StrategieBollinger extends Strategie {
    private static final int PERIOD = 20;
    private static final double K = 2.0;

    public StrategieBollinger(IndicateurTechnique indicateurs) {
        super("Stratégie Bandes de Bollinger", indicateurs);
    }

    @Override
    public String executer(List<Double> prices) {
        // Étape 1 : Calculer les bandes de Bollinger actuelles
        BollingerBDCalculator.calculateBollingerBD current = (BollingerBDCalculator.calculateBollingerBD) IndicateurTechnique.createBollingerBands(prices, PERIOD, K).getValeur();
        
        // Étape 2 : Calculer les bandes de Bollinger précédentes
        BollingerBDCalculator.calculateBollingerBD previous = (BollingerBDCalculator.calculateBollingerBD) IndicateurTechnique.createBollingerBands(prices.subList(0, prices.size() - 1), PERIOD, K).getValeur();

        // Étape 3 : Obtenir les prix actuels et précédents
        double currentPrice = prices.get(prices.size() - 1);
        double previousPrice = prices.get(prices.size() - 2);

        // Étape 4 : Vérifier les conditions d'achat (rebond sur la bande inférieure)
        if (previousPrice <= previous.lowerBand && currentPrice > current.lowerBand) {
            return "ACHAT";
        } 
        // Étape 5 : Vérifier les conditions de vente (rebond sur la bande supérieure)
        else if (previousPrice >= previous.upperBand && currentPrice < current.upperBand) {
            return "VENTE";
        }
        // Étape 6 : Si aucune condition n'est remplie, attendre
        return "ATTENTE";
    }
}
