/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.repository;
import java.util.List;

import org.signalement.entities.Photo;
import org.signalement.entities.Signalement;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Mamitiana
 */
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    public List<Photo>  findBySignalement(Signalement s);
}
