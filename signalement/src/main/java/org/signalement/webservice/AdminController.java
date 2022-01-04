/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.webservice;

import java.util.List;
import org.signalement.entities.Admin;
import org.signalement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mamitiana
 */
@RestController
public class AdminController {
    
    @Autowired
    private AdminRepository AdminRepository;
    
    @GetMapping("/admin")
    public List<Admin> listAdmin(){
    // select * from admin
    return AdminRepository.findAll();
}
    
    @PostMapping("/admin")
    public Admin save(@RequestBody Admin admin){
        // inserte admin
    return AdminRepository.save(admin);
    }
    
}
