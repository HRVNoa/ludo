package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Genre;
import fr.eni.lodo.models.Jeu;
import fr.eni.lodo.services.ClientService;
import fr.eni.lodo.services.ExemplaireService;
import fr.eni.lodo.services.JeuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jeu")
public class JeuController {

    @Autowired
    private final JeuService jeuService;

    @Autowired
    private ExemplaireService exemplaireService;

    List<Genre> genres = new ArrayList<>();

    public JeuController(
            JeuService jeuService
    ) {
        this.jeuService = jeuService;

        Genre genre = new Genre();
        genre.setNo_genre(1);
        genre.setLibelle("Action");
        Genre genre2 = new Genre();
        genre2.setNo_genre(2);
        genre2.setLibelle("Carte");

        genres.add(genre);
        genres.add(genre2);
    }

    @GetMapping("/lister")
    public String lister(Model model){
        model.addAttribute("jeux", jeuService.findAll());
        model.addAttribute("dossier", "jeu");
        model.addAttribute("view", "lister");
        return "base";
    }

    @GetMapping("/ajouter")
    public String ajouterGet(Model model){
        Jeu jeu = new Jeu();

        model.addAttribute("jeu", jeu);
        model.addAttribute("genres", genres);
        model.addAttribute("dossier", "jeu");
        model.addAttribute("view", "ajouter");
        return "base";
    }

    @PostMapping("/ajouter")
    public String ajouterPost(@Valid Jeu jeu, BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute( "org.springframework.validation.BindingResult.jeu", result);
            redirectAttributes.addFlashAttribute("jeu", jeu);
            return "redirect:/jeu/ajouter";
        }

//        System.out.println(jeu.getGenres());
        jeuService.save(jeu);

        return "redirect:/jeu/lister";
    }

    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable("id") final int id, Model model){
        Jeu jeu = jeuService.findOneById(id);
        if (null == jeu){
            return "redirect:/jeu/lister";
        }
        String genreNames;
        List<String> temp = new ArrayList<>();
        for (Genre genre : jeu.getGenres()){
            temp.add(genre.getLibelle().toLowerCase());
        }
        genreNames = String.join(",", temp);
        model.addAttribute("genreNames", genreNames);
        model.addAttribute("jeu", jeu);
        model.addAttribute("genres", genres);
        model.addAttribute("dossier", "jeu");
        model.addAttribute("view", "modifier");
        return "base";
    }

    @PostMapping("/modifier/{id}")
    public String modifierPost(@Valid Jeu jeu){
//        System.out.println(jeu.getGenres());
        jeuService.save(jeu);
        return "redirect:/jeu/lister";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model){
        Jeu jeu = jeuService.findOneById(id);
        if (null != jeu){
            model.addAttribute("jeu", jeu);
            model.addAttribute("exemplaires", exemplaireService.findById(id));
            model.addAttribute("dossier", "jeu");
            model.addAttribute("view", "detail");
            return "base";
        }else{
            return "redirect:/jeu/lister";
        }
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable("id") final int id){
        jeuService.supprimerJeu(jeuService.findOneById(id));
        return "redirect:/jeu/lister";
    }
}
