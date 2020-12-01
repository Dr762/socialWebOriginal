/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.domain;

import org.abondar.uniservity.websocial.entity.Customer;
import org.abondar.uniservity.websocial.entity.CustomerOrder;
import org.abondar.uniservity.websocial.entity.Employee;
import org.abondar.uniservity.websocial.entity.Item;
import org.abondar.uniservity.websocial.entity.OrderEmployee;
import org.abondar.uniservity.websocial.entity.Person;
import org.abondar.uniservity.websocial.entity.Status;
import org.abondar.uniservity.websocial.entity.StatusHistory;
import org.abondar.uniservity.websocial.entity.StatusTree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author alex
 */
@Named
public class DAO {

    private static final Logger LOG = Logger.getLogger(DAO.class.getName());
    public static final String PERSISTENCE_UNIT = "chuchkhe.socialweb";
    @PersistenceUnit(name = PERSISTENCE_UNIT)
    private static EntityManagerFactory emf;
    private static boolean visited = false;

    public static void removeItem(Item item) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            item = em.find(Item.class,item.getId());
            LOG.severe("removing" + item);
            em.remove(item);
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.throwing("DAO", "removeItem", ex);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public DAO() {
    }

    public static EntityManager getEntityManager() {

        if (emf == null) {
//            em = JPAContainerFactory
//                    .createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);

            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        } else {
            if (!visited) {
                System.out.println("Injected!!! ");
            }
        }
//        for (Entry<String, Object> m : em.getProperties().entrySet()) {
//            System.out.println("em: " + m);
//        }
        visited = true;
        return emf.createEntityManager();
    }

    public static Customer findCustomer(Customer customer) {
        if (customer.getPerson() == null) {
            throw new IllegalArgumentException("customer.person must be set");
        }
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

        Root<Customer> cust = cq.from(Customer.class);
        if (customer.getPerson().getSocialID() != null && !customer.getPerson().getSocialID().equals("")) {
            cq = cq.where(cb.equal(cust.get("person").get("SocialID"), customer.getPerson().getSocialID()));
        } else {
            List<Predicate> predicates = new ArrayList<Predicate>();
            Person p = customer.getPerson();
            if (p.getFname() != null) {
                predicates.add(cb.equal(cust.get("person").get("fname"), customer.getPerson().getFname()));
            }
            if (p.getLname() != null) {
                predicates.add(cb.equal(cust.get("person").get("lname"), customer.getPerson().getLname()));
            }
            if (p.getMname() != null) {
                predicates.add(cb.equal(cust.get("person").get("mname"), customer.getPerson().getMname()));
            }

            if (p.getBirthdate() != null) {
                predicates.add(cb.equal(cust.get("person").get("birthdate"), customer.getPerson().getBirthdate()));
            }

            cq = cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        TypedQuery<Customer> query = em.createQuery(cq);
        Customer cr = null;
        try {
            cr = query.getSingleResult();
            em.detach(cr);
        } catch (NoResultException ex) {
        }
        if (cr != null) {
            em.detach(cr);
            em.close();
        }

        return cr;
    }

    public static void saveOrder(CustomerOrder order) {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (order.getId() == null) {
                Customer cust = em.find(Customer.class, order.getCustomer().getId());

                if (cust != null) {

                    order.setCustomer(cust);
//          cust.getOrders().add(order);
                    em.persist(order);
                }
            } else {
                em.merge(order);
            }

            changeOrderStat(order, Status.ST_ReadyToAssign);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public static boolean changeOrderStat(CustomerOrder order, long statusId) {
        EntityManager em = getEntityManager();
        Status st = em.find(Status.class, statusId);
        return changeOrderStat(order, st);
    }

    public static Boolean changeOrderStat(CustomerOrder order, Status status) {
        EntityManager em = getEntityManager();
        try {
            StatusHistory curStatus = getCurrentOrderStatus(order);
            if(curStatus != null && curStatus.getStatus().getId().equals(status.getId()))
                return true;
            if (curStatus == null || canChangeStatus(curStatus.getStatus(), status)) {

                StatusHistory newStatus = new StatusHistory();
                newStatus.setObject_name("customerorder");
                newStatus.setObjectID(order.getId());
                Date dt = new Date();
                newStatus.setStartDate(dt);
                newStatus.setStatus(status);

                em.getTransaction().begin();
                if (curStatus != null) {
                    curStatus.setEndDate(dt);
                    em.merge(curStatus);
                }
                em.persist(newStatus);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            LOG.throwing("DAO", "changeOrderStat", ex);
            return false;
        } finally {
            em.close();
        }
    }

    public static Boolean changeOrderStat(Long orderId, Long statusId) {
        EntityManager em = getEntityManager();
        try {
            CustomerOrder order = em.find(CustomerOrder.class, orderId);
            Status status = em.find(Status.class, statusId);
            return changeOrderStat(order, status);
        } finally {
            em.close();
        }
    }

    private static StatusHistory getCurrentOrderStatus(CustomerOrder order) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<StatusHistory> q = em.createQuery("SELECT sh from StatusHistory sh where sh.object_name = 'customerorder' AND sh.objectID = :custID  AND sh.endDate IS NULL", StatusHistory.class);
            q.setParameter("custID", order.getId());

            StatusHistory sh = null;
            try {
                sh = q.getSingleResult();

            } catch (PersistenceException ex) {
            }

            return sh;
        } finally {
            em.close();
        }
    }

    public static boolean canChangeStatus(Status prevStatus, Status nextStatus) {
        if (nextStatus.getId().equals(prevStatus.getId())) {
            return false;
        }
        EntityManager em = getEntityManager();
        try {
            TypedQuery<StatusTree> q = em.createQuery("SELECT st FROM StatusTree st WHERE st.prev_status = :prevStatus AND st.next_status = :nextStatus", StatusTree.class);
            q.setParameter("prevStatus", prevStatus);
            q.setParameter("nextStatus", nextStatus);
            List<StatusTree> res = q.getResultList();
            return !res.isEmpty();
        } finally {
            em.close();
        }
    }

    public static void saveOrderEmloyee(OrderEmployee oe) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(oe);
        if (changeOrderStat(oe.getOrder(), Status.ST_Assigned)) {

            em.getTransaction().commit();
        } else {

            em.getTransaction().rollback();
        }
    }

    public static CustomerOrder getAssignedCO(String username) {
        EntityManager em = getEntityManager();
        CustomerOrder co = null;
        try {
            TypedQuery<CustomerOrder> q = em.createQuery("Select oe.order from OrderEmployee oe,StatusHistory sth where oe.emp.username= :username AND sth.objectID=oe.order.id AND sth.object_name='customerorder' AND sth.status.id= :stID AND sth.endDate IS NULL ORDER BY sth.startDate ASC", CustomerOrder.class);
            q.setParameter("username", username);
            q.setParameter("stID", Status.ST_Assigned);
            List<CustomerOrder> col = q.getResultList();
            if (!col.isEmpty()) {
                CustomerOrder cco = col.get(0);
                em.detach(cco);
                return cco;
            }
        } finally {
            em.close();
        }
        return co;
    }

    public static Boolean changeEmployeeStat(String username, Long statusID) {
        EntityManager em = getEntityManager();
        try {
            Employee emp = getEmployeeByUsername(username);
            StatusHistory curStatus = getCurrentEmployeeStatus(emp);
            Status status = em.find(Status.class, statusID);
            if (curStatus == null || (canChangeStatus(curStatus.getStatus(), status))) {

                StatusHistory newStatus = new StatusHistory();
                newStatus.setObject_name(status.getObject_name());
                newStatus.setObjectID(emp.getId());
                Date dt = new Date();
                newStatus.setStartDate(dt);
                newStatus.setStatus(status);


                em.getTransaction().begin();
                if (curStatus != null) {
                    curStatus.setEndDate(dt);
                    em.merge(curStatus);
                }
                em.persist(newStatus);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } finally {
            em.close();
        }
    }

    private static StatusHistory getCurrentEmployeeStatus(Employee emp) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<StatusHistory> q = em.createQuery("SELECT sh from StatusHistory sh where sh.object_name = 'Employee' AND sh.objectID = :empID  AND sh.endDate IS NULL", StatusHistory.class);
            q.setParameter("empID", emp.getId());

            StatusHistory sh = null;
            try {
                sh = q.getSingleResult();

            } catch (PersistenceException ex) {
            }

            return sh;
        } finally {
            em.close();
        }

    }

    public static Employee getEmployeeByUsername(String username) {

        EntityManager em = getEntityManager();
        try {
            Employee emp = null;
            TypedQuery<Employee> q = em.createQuery("SELECT emp from Employee emp where emp.username= :username", Employee.class);
            q.setParameter("username", username);
            emp = q.getSingleResult();
            return emp;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public static StatusTree findMove(long prev, long next) {
        EntityManager em = getEntityManager();
        try {
            StatusTree stm = null;
            TypedQuery<StatusTree> q = em.createQuery("SELECT stm FROM StatusTree stm WHERE stm.prev_status.id = :prev AND stm.next_status.id = :next", StatusTree.class);
            q.setParameter("prev", prev)
                    .setParameter("next", next);
            stm = q.getSingleResult();
            return stm;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

    }

    public static boolean updateItems(List<Item> itemList) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            for (Item it : itemList) {
                em.merge(it);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            LOG.throwing("DAO", "updateItems", ex);
            return false;

        } finally {
            em.close();
        }
        return true;
    }
}
