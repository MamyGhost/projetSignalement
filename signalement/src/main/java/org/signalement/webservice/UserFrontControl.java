/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.webservice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.signalement.entities.Admin;
import org.signalement.entities.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.signalement.entities.Userfront;
import org.signalement.repository.RegionRepository;
import org.signalement.repository.UserfrontRepository;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HASINA
 */
@RestController
public class UserFrontControl {
     @Autowired
    private UserfrontRepository userRepository;
    private RegionRepository regionRepository;
    @PostMapping("/userfront/login")
    public ResponseEntity<String> authentification(@RequestBody Userfront user,@RequestBody Region reg)  {
        Region region= regionRepository.findRegionByNom(reg.getNom());
        Userfront userData= userRepository.findUserfrontlogin(user.getUsername(),user.getPassword(),region.getId());
        if(userData==null){
            throw new RuntimeException("diso le region ,na le username, na le password");
        }return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
//    
//    @GetMapping("region/{region}/userfront")
//    public List<Userfront> listUserByRegion(@PathVariable("region") Integer region) {
//    return userRepository.findUserfrontloginByRegion(region);
//}
     
    @GetMapping("/userfront")
    public List<Userfront> list() {
    return userRepository.findAll();
}

    @GetMapping("/userfront/{id}")
    public Userfront listUser(@PathVariable("id") Integer id){
    return userRepository.findById(id).get();
}
        
   
}
