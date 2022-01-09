/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mamitiana
 */
@Entity
@Table(name = "tokenfront")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tokenfront.findAll", query = "SELECT t FROM Tokenfront t")})
 @JsonIdentityInfo(scope = Tokenfront.class,
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")

public class Tokenfront implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Token")
    private String token;
    @Column(name = "Dateexp")
    @Temporal(TemporalType.DATE)
    private Date dateexp;
    @JoinColumn(name = "Userfront", referencedColumnName = "Id")
    @ManyToOne
    private Userfront userfront;

    public Tokenfront() {
    }

    public Tokenfront(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateexp() {
        return dateexp;
    }

    public void setDateexp(Date dateexp) {
        this.dateexp = dateexp;
    }

    public Userfront getUserfront() {
        return userfront;
    }

    public void setUserfront(Userfront userfront) {
        this.userfront = userfront;
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
        if (!(object instanceof Tokenfront)) {
            return false;
        }
        Tokenfront other = (Tokenfront) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.signalement.entities.Tokenfront[ id=" + id + " ]";
    }
    
}
