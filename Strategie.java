import java.util.List;

public abstract class Strategie {
    protected String nom;
    protected IndicateurTechnique indicateurs;

    public Strategie(String nom, IndicateurTechnique indicateurs) {
        this.nom = nom;
        this.indicateurs = indicateurs;
    }

    //public abstract String executerAvecPrix(List<Double> prices);
    public abstract String executerAvecPrixCloture(List<Double> closingPrices);
    
    public String getNom() {
        return nom;
    }
}
