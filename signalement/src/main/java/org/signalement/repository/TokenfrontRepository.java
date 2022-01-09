/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.repository;

import org.signalement.entities.Tokenfront;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mamitiana
 */
@Repository
@Transactional
public interface TokenfrontRepository extends JpaRepository<Tokenfront, Integer> {
        
     public Tokenfront findByToken(String token);
}
