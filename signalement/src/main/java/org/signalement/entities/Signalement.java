/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.signalement.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author Mamitiana
 */
@Entity
@Table(name = "signalement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signalement.findAll", query = "SELECT s FROM Signalement s")})
@JsonIdentityInfo(scope = Signalement.class,
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Signalement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Description")
    private String description;
    @Column(name = "daty")
    @Temporal(TemporalType.DATE)
    private Date daty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Latitude")
    private BigDecimal latitude;
    @Column(name = "Longitude")
    private BigDecimal longitude;
    @JoinColumn(name = "Utilisateur", referencedColumnName = "Id") 
    @ManyToOne
    private Utilisateur utilisateur;
    @JoinColumn(name = "Type", referencedColumnName = "Id")
    @ManyToOne
    private Type type;
    @JoinColumn(name = "Region", referencedColumnName = "Id")
    @ManyToOne
    private Region region;
    @JoinColumn(name = "Statut", referencedColumnName = "Id") 
    @ManyToOne
    private Statut statut;
    @JoinColumn(name = "Signalnew", referencedColumnName = "Id")
    @ManyToOne
    private Signalnew signalnew;
    @OneToMany(mappedBy = "signalement")
    private List<Photo> photoList;

    public Signalement() {
    }

    public Signalement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Signalnew getSignalnew() {
        return signalnew;
    }

    public void setSignalnew(Signalnew signalnew) {
        this.signalnew = signalnew;
    }

    @XmlTransient
    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Signalement)) {
            return false;
        }
        Signalement other = (Signalement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.signalement.entities.Signalement[ id=" + id + " ]";
    }

    public Signalement(Integer id, String description, Date daty, BigDecimal latitude, BigDecimal longitude, Type type, Statut statut, Signalnew signalnew) {
        this.id = id;
        this.description = description;
        this.daty = daty;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.statut = statut;
        this.signalnew = signalnew;
    }
    
    
    
}
