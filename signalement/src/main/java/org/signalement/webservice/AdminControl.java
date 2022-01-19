/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.webservice;

import java.util.List;
import java.util.Optional;
import org.signalement.entities.Admin;
import org.signalement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mamitiana
 */
@RestController
public class AdminControl {
    
    @Autowired
    private AdminRepository AdminRepository;
    
    @GetMapping("/admin")
    public List<Admin> listAdmin(){
    // select * from admin
    return AdminRepository.findAll();
}
    
    @GetMapping("/admin/{id}")
    public Admin listAdmin(@PathVariable Integer id){
    // select * from admin where id =
    return AdminRepository.findById(id).get();
}
    
    @PostMapping("/admin")
    public Admin save(@RequestBody Admin admin){
        // inserte admin
    return AdminRepository.save(admin);
    }
    
    
    @PutMapping("/admin/{id}")
     public ResponseEntity<Admin> update(@PathVariable("id") Integer id, @RequestBody Admin admin) {
    Optional<Admin> tutorialData = AdminRepository.findById(id);

    if (tutorialData.isPresent()) {
      Admin adminv = tutorialData.get();
      adminv.setPassword(admin.getPassword());
      adminv.setUsername(admin.getUsername());
      return new ResponseEntity<>(AdminRepository.save(adminv), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    
}
