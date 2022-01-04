/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mamitiana
 */
@Entity
@Table(name = "userfront")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userfront.findAll", query = "SELECT u FROM Userfront u")
    , @NamedQuery(name = "Userfront.findById", query = "SELECT u FROM Userfront u WHERE u.id = :id")
    , @NamedQuery(name = "Userfront.findByUsername", query = "SELECT u FROM Userfront u WHERE u.username = :username")
    , @NamedQuery(name = "Userfront.findByPassword", query = "SELECT u FROM Userfront u WHERE u.password = :password")})
public class Userfront implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @JoinColumn(name = "Region", referencedColumnName = "Id")
    @ManyToOne
    private Region region;

    public Userfront() {
    }

    public Userfront(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
        if (!(object instanceof Userfront)) {
            return false;
        }
        Userfront other = (Userfront) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.signalement.entities.Userfront[ id=" + id + " ]";
    }
    
}
