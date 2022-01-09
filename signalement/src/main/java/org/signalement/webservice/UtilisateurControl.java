/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.webservice;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.signalement.entities.Signalement;
import org.signalement.entities.Tokenmobile;
import org.signalement.entities.Utilisateur;
import org.signalement.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import org.signalement.repository.TokenmobileRepository;

/**
 *
 * @author Mamitiana
 */
@RestController
public class UtilisateurControl {
     
     @Autowired
    private UtilisateurRepository utilisateurRepository;
     
      
     @Autowired
    private TokenmobileRepository tokenmobileRepository;
     
     @GetMapping("/wb/utilisateur/{id}/signalement")
        public ResponseEntity<List<Signalement>> getSignalementById(@PathVariable("id") int id) {
          Utilisateur r = utilisateurRepository.findById(id).get();
          List<Signalement> sData = r.getSignalementList();

          if (!sData.isEmpty()) {
            return new ResponseEntity<>(sData, HttpStatus.OK);
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
        }
        
     @PostMapping("/wb/utilisateur/login")
        public ResponseEntity<String> authentification(@RequestBody Utilisateur utilisateur)  {
          Utilisateur uData = utilisateurRepository.findByUsernameAndPassword(utilisateur.getUsername(),utilisateur.getPassword());

          if (uData==null) {
            throw new RuntimeException("Utilisateur inexistant: mop de passe ou utilisateur incorrect");
          }else{
               Tokenmobile token = new Tokenmobile();
               token.setUtilisateur(uData);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();
                LocalDateTime localDateTime =date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                localDateTime = localDateTime.plusDays(1);
                Date currentDatePlus = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
               token.setDateexp(currentDatePlus);
               String tok = date.toString()+uData.getUsername();
               
               String sha1 = "";
		
		// With the java libraries
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(tok.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
                       
               token.setToken(sha1);
               tokenmobileRepository.save(token);
               
               HttpHeaders headers = new HttpHeaders();
               headers.add("Authorization", "Bearer "+sha1);
               return new ResponseEntity<>(
                headers, HttpStatus.OK);
          }
        }
        
        
     
}
