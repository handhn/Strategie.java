import java.util.List;

public abstract class Strategie {
    protected String nom;
    protected IndicateurTechnique indicateurs;

    public Strategie(String nom, IndicateurTechnique indicateurs) {
        this.nom = nom;
        this.indicateurs = indicateurs;
    }

    // Méthode pour les stratégies utilisant tous les types de prix
    public abstract String executer(List<Double> prices);
    
    // Méthode pour les stratégies utilisant uniquement les prix de clôture
    public abstract String executer(List<Double> closingPrices);
    
    public String getNom() {
        return nom;
    }
}
