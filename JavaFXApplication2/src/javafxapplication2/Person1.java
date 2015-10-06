/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsi5004
 */
@Entity
@Table(name = "Person_1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person1.findAll", query = "SELECT p FROM Person1 p"),
    @NamedQuery(name = "Person1.findById", query = "SELECT p FROM Person1 p WHERE p.person1PK.id = :id"),
    @NamedQuery(name = "Person1.findByFirstName", query = "SELECT p FROM Person1 p WHERE p.person1PK.firstName = :firstName")})
public class Person1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Person1PK person1PK;

    public Person1() {
    }

    public Person1(Person1PK person1PK) {
        this.person1PK = person1PK;
    }

    public Person1(long id, String firstName) {
        this.person1PK = new Person1PK(id, firstName);
    }

    public Person1PK getPerson1PK() {
        return person1PK;
    }

    public void setPerson1PK(Person1PK person1PK) {
        this.person1PK = person1PK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (person1PK != null ? person1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person1)) {
            return false;
        }
        Person1 other = (Person1) object;
        if ((this.person1PK == null && other.person1PK != null) || (this.person1PK != null && !this.person1PK.equals(other.person1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxapplication2.Person1[ person1PK=" + person1PK + " ]";
    }
    
}
