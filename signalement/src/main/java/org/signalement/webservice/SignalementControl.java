/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.webservice;

import org.signalement.repository.SignalementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mamitiana
 */
@RestController
public class SignalementControl {
    
     @Autowired
    private SignalementRepository signalementRepository;
    
}
