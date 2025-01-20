package fr.eni.lodo.models;

public class Exemplaire {

    private Integer no_exemplaire;
    private String codebarre;
    private boolean louable;
    private Jeu no_jeu;

    public Integer getNo_exemplaire() {
        return no_exemplaire;
    }

    public void setNo_exemplaire(Integer no_exemplaire) {
        this.no_exemplaire = no_exemplaire;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public boolean isLouable() {
        return louable;
    }

    public void setLouable(boolean louable) {
        this.louable = louable;
    }

    public Jeu getNo_jeu() {
        return no_jeu;
    }

    public void setNo_jeu(Jeu jeu) {
        this.no_jeu = jeu;
    }
}
