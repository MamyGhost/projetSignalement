/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mamitiana
 */
@Entity
@Table(name = "statut")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statut.findAll", query = "SELECT s FROM Statut s")
    , @NamedQuery(name = "Statut.findById", query = "SELECT s FROM Statut s WHERE s.id = :id")
    , @NamedQuery(name = "Statut.findByEtat", query = "SELECT s FROM Statut s WHERE s.etat = :etat")})
public class Statut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Etat")
    private Boolean etat;
    @OneToMany(mappedBy = "statut")
    private List<Signalement> signalementList;

    public Statut() {
    }

    public Statut(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Signalement> getSignalementList() {
        return signalementList;
    }

    public void setSignalementList(List<Signalement> signalementList) {
        this.signalementList = signalementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statut)) {
            return false;
        }
        Statut other = (Statut) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.signalement.entities.Statut[ id=" + id + " ]";
    }
    
}
