package fr.eni.lodo.services;

import fr.eni.lodo.interfaces.ClientInterface;
import fr.eni.lodo.interfaces.JeuInterface;
import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Genre;
import fr.eni.lodo.models.Jeu;
import fr.eni.lodo.repository.JeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JeuService implements JeuInterface {

    private List<Jeu> jeux;

    private GenreService genreService;

    @Autowired
    private JeuRepository jeuRepository;

    public JeuService(GenreService genreService) {
        this.jeux = new ArrayList<>();
        this.genreService = genreService;

//        Jeu jeu = new Jeu();
//        jeu.setNo_jeu(jeux.size()+1);
//        jeu.setTitre("Trio");
//        jeu.setReference("6841562");
//        jeu.setDescription("TRIO est un jeu de déduction malin et passionnant qui peut se jouer en solo ou en équipe !");
//        jeu.setTarif_journee(2);
//        jeu.setAge_min(6);
//        jeu.setDuree(15);
//        jeux.add(jeu);
//
//        Jeu jeu2 = new Jeu();
//        jeu2.setNo_jeu(jeux.size()+1);
//        jeu2.setTitre("Dekal");
//        jeu2.setReference("5648861");
//        jeu2.setDescription("Jeu d'ambiance - Jeu de société à la mécanique du taquin revisitée - Un Jeu addictif et pour Tous Niveaux");
//        jeu2.setTarif_journee(1);
//        jeu2.setAge_min(7);
//        jeu2.setDuree(15);
//        jeu2.addGenres(genreService.getGenre(1));
//        jeux.add(jeu2);
//
//        Jeu jeu3 = new Jeu();
//        jeu3.setNo_jeu(jeux.size()+1);
//        jeu3.setTitre("Traitres à Bord");
//        jeu3.setReference("1258467");
//        jeu3.setDescription("Traitres à Bord est un jeu d'ambiance à identité secrète qui allie bluff, stratégie, alliances et trahisons. Parfait pour passer vos meilleurs soirées entre amis ou en famille !");
//        jeu3.setTarif_journee(3);
//        jeu3.setAge_min(10);
//        jeu3.setDuree(20);
//        jeu3.addGenres(genreService.getGenre(1));
//        jeu3.addGenres(genreService.getGenre(2));
//        jeux.add(jeu3);
//
//        Jeu jeu4 = new Jeu();
//        jeu4.setNo_jeu(jeux.size()+1);
//        jeu4.setTitre("Sea salt & paper");
//        jeu4.setReference("1548247");
//        jeu4.setDescription("Constituez votre main, posez des cartes pour leur effet et décidez si vous mettez fin à la manche. Mais vous devez choisir : stopper immédiatement la manche ou laisser aux autres un tour supplémentaire pour tenter de creuser l’écart.");
//        jeu4.setTarif_journee(3);
//        jeu4.setAge_min(10);
//        jeu4.setDuree(20);
//        jeu4.addGenres(genreService.getGenre(2));
//        jeux.add(jeu4);

    }

    @Override
    public void save(Jeu jeu) {
        jeuRepository.save(jeu);
//        jeu.setNo_jeu(jeux.size()+1);
//        this.jeux.add(jeu);
    }

    @Override
    public List<Jeu> findAll() {
        return jeuRepository.findAll();
//        return this.jeux;
    }

    @Override
    public Jeu findOneById(int id) {
        Optional<Jeu> jeu = jeuRepository.findById(id);
        return jeu.orElse(null);
//        for (Jeu jeu : jeux) {
//            if (jeu.getNo_jeu() == id) {
//                return jeu;
//            }
//        }
//        return null;
    }

    @Override
    public void supprimerJeu(Jeu jeu) {
        jeuRepository.supprimer(jeu);
    }
}
