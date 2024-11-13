public class StrategieTendance extends Strategie {
    private static final int PERIOD = 50;

    public StrategieTendance(IndicateurTechnique indicateurs) {
        super("Stratégie de Tendance", indicateurs);
    }

    @Override
    public String executer(List<Double> prices) {
        // Étape 1 : Calculer la SMA
        double sma = (double) IndicateurTechnique.createSMA(prices, PERIOD).getValeur();
        
        // Étape 2 : Obtenir les prix actuels et précédents
        double currentPrice = prices.get(prices.size() - 1);
        double previousPrice = prices.get(prices.size() - 2);

        // Étape 3 : Vérifier les conditions d'achat (prix passe au-dessus de la SMA)
        if (previousPrice <= sma && currentPrice > sma) {
            return "ACHAT";
        } 
        // Étape 4 : Vérifier les conditions de vente (prix passe en-dessous de la SMA)
        else if (previousPrice >= sma && currentPrice < sma) {
            return "VENTE";
        }
        // Étape 5 : Si aucune condition n'est remplie, attendre
        return "ATTENTE";
    }

    @Override
    public String executer(List<Double> closingPrices) {
        // Cette méthode pourrait lever une exception si elle n'est pas supportée
        throw new UnsupportedOperationException("Cette stratégie ne nécessite pas des prix de clôture.");
    }

}
