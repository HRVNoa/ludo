package fr.eni.lodo.models;

import java.util.List;

public class ExemplaireJeu {

    private int no_exemplaire;
    private String codebarre;
    private boolean louable;
    private List<Jeu> jeux;

    public int getNbExemplaire(){
        return jeux.size();
    }

    public int getNo_exemplaire() {
        return no_exemplaire;
    }

    public void setNo_exemplaire(int no_exemplaire) {
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

    public List<Jeu> getJeux() {
        return jeux;
    }

    public void setJeux(List<Jeu> jeux) {
        this.jeux = jeux;
    }

    public void addJeu(Jeu jeu){
        jeux.add(jeu);
    }
}
