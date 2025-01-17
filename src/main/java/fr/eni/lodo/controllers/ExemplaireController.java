package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Client;
import fr.eni.lodo.services.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exemplaire")
public class ExemplaireController {

    @Autowired
    private final ExemplaireService exemplaireService;

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

    @GetMapping("/ajouter")
    public String ajouterGet(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        return "client/ajouter";
    }
//
//    @PostMapping("/ajouter")
//    public String ajouterPost(Client client){
//        clientService.ajouterClient(client);
//        return "redirect:/client/lister";
//    }
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
