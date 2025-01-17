package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Client;
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

    @GetMapping("/{id}")
    public String lister(@PathVariable("id") final int id, Model model){
        model.addAttribute("exemplaires", exemplaireService.findById(id));
        model.addAttribute("dossier", "exemplaire");
        model.addAttribute("view", "lister");
        return "base";
    }

    @GetMapping("/ajouter/{id}")
    public String ajouterGet(@PathVariable("id") final int id, Model model){
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setJeu(jeuService.findOneById(id));
        model.addAttribute("exemplaire", exemplaire);
        model.addAttribute("dossier", "exemplaire");
        model.addAttribute("view", "ajouter");
        return "base";
    }

    @PostMapping("/ajouter/{id}")
    public String ajouterPost(@PathVariable("id") final int id, Exemplaire exemplaire){
        exemplaire.setJeu(jeuService.findOneById(id));
        exemplaireService.save(exemplaire);
        return "redirect:/jeu/"+id;
    }
//
//    @GetMapping("/{id}")
//    public String detail(@PathVariable("id") final int id, Model model){
//        Client client = clientService.getClient(id);
//        if (null != client){
//            model.addAttribute("client", client);
//            return "client/detail";
//        }else{
//            return "redirect:/client/lister";
//        }
//    }
//
//    @GetMapping("/supprimer/{id}")
//    public String detail(@PathVariable("id") final int id){
//        clientService.supprimerClient(clientService.getClient(id));
//        return "redirect:/client/lister";
//    }
}
