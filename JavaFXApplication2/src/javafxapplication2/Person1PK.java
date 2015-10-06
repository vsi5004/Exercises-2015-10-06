/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vsi5004
 */
@Embeddable
public class Person1PK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Id")
    private long id;
    @Basic(optional = false)
    @Column(name = "First_Name")
    private String firstName;

    public Person1PK() {
    }

    public Person1PK(long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (firstName != null ? firstName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person1PK)) {
            return false;
        }
        Person1PK other = (Person1PK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.firstName == null && other.firstName != null) || (this.firstName != null && !this.firstName.equals(other.firstName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxapplication2.Person1PK[ id=" + id + ", firstName=" + firstName + " ]";
    }
    
}
