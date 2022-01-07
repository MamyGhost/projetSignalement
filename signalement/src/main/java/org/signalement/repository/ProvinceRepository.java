/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.repository;
import org.signalement.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Mamitiana
 */
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    
}
