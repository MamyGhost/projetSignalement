/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.web;

import java.util.List;

import org.signalement.entities.Photo;
import org.signalement.entities.Region;
import org.signalement.entities.Signalement;
import org.signalement.entities.Userfront;
import org.signalement.repository.RegionRepository;
import org.signalement.repository.SignalementRepository;
import org.signalement.repository.UserfrontRepository;
import org.signalement.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mamitiana
 */
@Controller
public class ResourceManagement {
    
    @Autowired
    private SignalementRepository signalementRepository;
    
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserfrontRepository userFrontRepository;

     @Autowired
    private PhotoRepository photoRep;
    
    
    
        
    
    @GetMapping("/manageResource/userFrontRsrc")
    public String manageUserFront(Model model){
        List<Userfront> list=userFrontRepository.findAll();
        model.addAttribute("fontUserList", list);
        return "userFrontList";
    }

    @GetMapping("/manageResource/signalementRsrc")
    public String manageSignalement(Model model){
        List<Signalement> lista=signalementRepository.findAll();
        model.addAttribute("signalement", lista);
        return "signalementList";
    }

    @GetMapping("/manageResource/modifUF/{id}")
    public String manageSignalement(Model model,@PathVariable(name="id") Integer id){
        Userfront s=userFrontRepository.findById(id).get();
        List<Region> listRegion=regionRepository.findAll();
        model.addAttribute("user",s);
        model.addAttribute("region",listRegion);
        return "modifUserFront";
    }

    @GetMapping("/manageResource/updateUF")
    public String updateregion(@RequestParam(name="region") Integer regionid,@RequestParam(name="id") Integer id,@RequestParam(name="username")  String usrName,@RequestParam(name="mdp") String mdp){
        Userfront usr= userFrontRepository.findById(id).get();
        Region r = regionRepository.findById(regionid).get();
        usr.setRegion(r);
        usr.setUsername(usrName);
        usr.setPassword(mdp);
        userFrontRepository.save(usr);
        return "redirect:/manageResource/userFrontRsrc";
   
    } 

    @GetMapping("/manageResource/formulaireUF")
    public String create(Model model){
        List<Region> listRegion = regionRepository.findAll();
        Userfront empty=new Userfront();
        model.addAttribute("region",listRegion);
        model.addAttribute("userfront",empty);
        return "formulaireUF";
   
    }

    @PostMapping("/manageResource/saveUF")
    public String insertUF(@ModelAttribute("userfront") Userfront user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){

        }
        userFrontRepository.save(user);
        return "redirect:/manageResource/userFrontRsrc";
   
    }  
    @GetMapping("/manageResource/deleteUF/{id}")
    public String deleteUF(@PathVariable(name="id") Integer id){
        Userfront user=userFrontRepository.findById(id).get();
        userFrontRepository.delete(user);
        return "redirect:/manageResource/userFrontRsrc";
   
    } 
    @GetMapping("/manageResource/deleteSIGN/{id}")
    public String deleteSIGN(@PathVariable(name="id") Integer id){
        Signalement sign=signalementRepository.findById(id).get();
        signalementRepository.delete(sign);
        return "redirect:/manageResource/signalementRsrc";
   
    } 

    @GetMapping("/manageResource/viewSIGN/{id}")
    public String detailSIGN(@PathVariable(name="id") Integer id,Model model){
        Signalement sign=signalementRepository.findById(id).get();
        List<Photo> photos=photoRep.findBySignalement(sign);
        model.addAttribute("sign",sign);
        model.addAttribute("photos",photos);
        return "detailSignalement";
   
    } 

}
