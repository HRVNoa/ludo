package fr.eni.lodo.controllers;

import fr.eni.lodo.models.Client;
import fr.eni.lodo.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private final ClientService clientService;

    public ClientController(
            ClientService clientService
    ) {
        this.clientService = clientService;
    }

    @GetMapping("/lister")
    public String lister(Model model){
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("dossier", "client");
        model.addAttribute("view", "lister");
        return "base";
    }

    @GetMapping("/ajouter")
    public String ajouterGet(Model model, Client client){
        model.addAttribute("client", client);
        model.addAttribute("dossier", "client");
        model.addAttribute("view", "ajouter");
        return "base";
    }

    @PostMapping("/ajouter")
    public String ajouterPost(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("client", client);
            model.addAttribute("dossier", "client");
            model.addAttribute("view", "ajouter");
            return "base";
        }

        clientService.save(client);
        return "redirect:/client/lister";
    }

    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable("id") final int id, Model model){
        Client client = clientService.findOneById(id);
        model.addAttribute("client", client);
        model.addAttribute("dossier", "client");
        model.addAttribute("view", "modifier");
        return "base";
    }

    @PostMapping("/modifier/{id}")
    public String modifierPost(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("client", client);
            model.addAttribute("error", result);
            model.addAttribute("dossier", "client");
            model.addAttribute("view", "modifier");
            return "base";
        }
        clientService.save(client);
//        Client clientModif = clientService.findOneById(client.getNo_client());
//        clientModif.setNo_client(client.getNo_client());
//        clientModif.setNom(client.getNom());
//        clientModif.setPrenom(client.getPrenom());
//        clientModif.setEmail(client.getEmail());
//        clientModif.setNo_tel(client.getNo_tel());
//        clientModif.setRue(client.getRue());
//        clientModif.setCode_postal(client.getCode_postal());
//        clientModif.setVille(client.getVille());
        return "redirect:/client/lister";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model){
        Client client = clientService.findOneById(id);
        if (null != client){
            model.addAttribute("client", client);
            model.addAttribute("dossier", "client");
            model.addAttribute("view", "detail");
            return "base";
        }else{
            return "redirect:/client/lister";
        }
    }

    @GetMapping("/supprimer/{id}")
    public String detail(@PathVariable("id") final int id){
        clientService.supprimerClient(clientService.findOneById(id));
        return "redirect:/client/lister";
    }
}
