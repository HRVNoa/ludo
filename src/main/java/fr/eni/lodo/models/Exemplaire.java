package fr.eni.lodo.models;

public class Exemplaire {

    private Integer no_exemplaire;
    private String codebarre;
    private boolean louable;
    private Integer no_jeu;

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

    public Integer getNo_jeu() {
        return no_jeu;
    }

    public void setNo_jeu(Integer no_jeu) {
        this.no_jeu = no_jeu;
    }
}
