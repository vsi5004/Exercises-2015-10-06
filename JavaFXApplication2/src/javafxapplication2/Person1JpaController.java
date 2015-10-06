/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.Serializable;
import java.util.List;
import javafxapplication2.exceptions.NonexistentEntityException;
import javafxapplication2.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author vsi5004
 */
public class Person1JpaController implements Serializable {

    public Person1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Person1 person1) throws PreexistingEntityException, Exception {
        if (person1.getPerson1PK() == null) {
            person1.setPerson1PK(new Person1PK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(person1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerson1(person1.getPerson1PK()) != null) {
                throw new PreexistingEntityException("Person1 " + person1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Person1 person1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            person1 = em.merge(person1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Person1PK id = person1.getPerson1PK();
                if (findPerson1(id) == null) {
                    throw new NonexistentEntityException("The person1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Person1PK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Person1 person1;
            try {
                person1 = em.getReference(Person1.class, id);
                person1.getPerson1PK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The person1 with id " + id + " no longer exists.", enfe);
            }
            em.remove(person1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Person1> findPerson1Entities() {
        return findPerson1Entities(true, -1, -1);
    }

    public List<Person1> findPerson1Entities(int maxResults, int firstResult) {
        return findPerson1Entities(false, maxResults, firstResult);
    }

    private List<Person1> findPerson1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Person1.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Person1 findPerson1(Person1PK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person1.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerson1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Person1> rt = cq.from(Person1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
