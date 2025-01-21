package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Exemplaire;
import fr.eni.lodo.services.ExemplaireService;
import fr.eni.lodo.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exemplaire")
public class ExemplaireController {

    @Autowired
    private final ExemplaireService exemplaireService;

    @Autowired
    private JeuService jeuService;

    public ExemplaireController(
            ExemplaireService exemplaireService
    ) {
        this.exemplaireService = exemplaireService;
    }

//    @GetMapping("/{id}")
//    public String lister(@PathVariable("id") final int id, Model model){
//        model.addAttribute("exemplaires", exemplaireService.findById(id));
//        model.addAttribute("dossier", "exemplaire");
//        model.addAttribute("view", "lister");
//        return "base";
//    }

    @GetMapping("/ajouter/{id}")
    public String ajouterGet(@PathVariable("id") final int id, Model model){
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setNo_jeu(jeuService.findOneById(id));
        model.addAttribute("exemplaire", exemplaire);
        model.addAttribute("dossier", "exemplaire");
        model.addAttribute("view", "ajouter");
        return "base";
    }

    @PostMapping("/ajouter/{id}")
    public String ajouterPost(@PathVariable("id") final int id, Exemplaire exemplaire){
        exemplaire.setNo_jeu(jeuService.findOneById(id));
        exemplaireService.save(exemplaire);
        return "redirect:/jeu/"+id;
    }

    @GetMapping("/modifier/{id}")
    public String modifierGet(@PathVariable("id") final int id, Model model){
        Exemplaire exemplaire = exemplaireService.findById(id);
        model.addAttribute("exemplaire", exemplaire);
        model.addAttribute("dossier", "exemplaire");
        model.addAttribute("view", "modifier");
        return "base";
    }

    @PostMapping("/modifier/{id}")
    public String modifierPost(@PathVariable("id") final int id, Exemplaire exemplaire){
        System.out.println(exemplaire.getNo_exemplaire());
        exemplaireService.save(exemplaire);
        return "redirect:/jeu/lister";
    }

    @GetMapping("/supprimer/{id}")
    public String detail(@PathVariable("id") final int id){
//        Exemplaire exemplaire = exemplaireService.findById(id);
//        int idJeu = exemplaire.getJeu().getNo_jeu();
        exemplaireService.supprimer(id);
        return "redirect:/jeu/lister";
    }
}
