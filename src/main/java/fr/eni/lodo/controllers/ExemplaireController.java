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
        exemplaire.setNo_jeu(jeuService.findOneById(id).getNo_jeu());
        model.addAttribute("exemplaire", exemplaire);
        model.addAttribute("dossier", "exemplaire");
        model.addAttribute("view", "ajouter");
        return "base";
    }

    @PostMapping("/ajouter/{id}")
    public String ajouterPost(@PathVariable("id") final int id, Exemplaire exemplaire, Model model){
        exemplaire.setNo_jeu(jeuService.findOneById(id).getNo_jeu());
        if (exemplaireService.codebarreExiste(exemplaire.getNo_exemplaire(), exemplaire.getCodebarre())){
            model.addAttribute("errorCodebarre", true);
            model.addAttribute("exemplaire", exemplaire);
            model.addAttribute("dossier", "exemplaire");
            model.addAttribute("view", "ajouter");
            return "base";
        }
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
    public String modifierPost(@PathVariable("id") final int id, Exemplaire exemplaire, Model model){
        System.out.println(exemplaire.getNo_exemplaire());
        if (exemplaireService.codebarreExiste(exemplaire.getNo_exemplaire(), exemplaire.getCodebarre())){
            model.addAttribute("errorCodebarre", true);
            model.addAttribute("exemplaire", exemplaire);
            model.addAttribute("dossier", "exemplaire");
            model.addAttribute("view", "modifier");
            return "base";
        }
        exemplaireService.save(exemplaire);
        return "redirect:/jeu/"+exemplaire.getNo_jeu();
    }

    @GetMapping("/supprimer/{id}")
    public String detail(@PathVariable("id") final int id){
        Exemplaire exemplaire = exemplaireService.findById(id);
        int idJeu = exemplaire.getNo_jeu();
        exemplaireService.supprimer(id);
        return "redirect:/jeu/"+idJeu;
    }
}
