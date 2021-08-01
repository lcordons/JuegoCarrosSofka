/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aplicacion.persistencia;

import co.com.aplicacion.juego.Puestos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GanadoresBDJpaController implements Serializable {

    public GanadoresBDJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public GanadoresBDJpaController() {
        emf = Persistence.createEntityManagerFactory("mx.com.gm1.0-PU");

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puestos ganadoresBD) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ganadoresBD);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGanadoresBD(ganadoresBD.getId()) != null) {
                throw new PreexistingEntityException("GanadoresBD " + ganadoresBD + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puestos ganadoresBD) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ganadoresBD = em.merge(ganadoresBD);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ganadoresBD.getId();
                if (findGanadoresBD(id) == null) {
                    throw new NonexistentEntityException("The ganadoresBD with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puestos ganadoresBD;
            try {
                ganadoresBD = em.getReference(Puestos.class, id);
                ganadoresBD.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ganadoresBD with id " + id + " no longer exists.", enfe);
            }
            em.remove(ganadoresBD);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puestos> findGanadoresBDEntities() {
        return findGanadoresBDEntities(true, -1, -1);
    }

    public List<Puestos> findGanadoresBDEntities(int maxResults, int firstResult) {
        return findGanadoresBDEntities(false, maxResults, firstResult);
    }

    private List<Puestos> findGanadoresBDEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puestos.class));
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

    public Puestos findGanadoresBD(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puestos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGanadoresBDCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puestos> rt = cq.from(Puestos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
