import java.util.List;

public abstract class Strategie {
    protected String nom;
    protected List<String> indicateurs;

    public Strategie(String nom, List<String> indicateurs) {
        this.nom = nom;
        this.indicateurs = indicateurs;
    }

    public abstract String executer(List<Double> prices);

    public String getNom() {
        return nom;
    }

    public List<String> getIndicateurs() {
        return indicateurs;
    }
}
