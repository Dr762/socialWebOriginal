/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "ordemp_seq", sequenceName = "ordemp_seq", initialValue = 1, allocationSize=1)
public class OrderEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ordemp_seq")
    private Long id;
    @ManyToOne
    private CustomerOrder order;
    @ManyToOne
    private Employee emp;
    @ManyToOne
    private Role role;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.order != null ? this.order.hashCode() : 0);
        hash = 71 * hash + (this.emp != null ? this.emp.hashCode() : 0);
        hash = 71 * hash + (this.role != null ? this.role.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderEmployee other = (OrderEmployee) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.order != other.order && (this.order == null || !this.order.equals(other.order))) {
            return false;
        }
        if (this.emp != other.emp && (this.emp == null || !this.emp.equals(other.emp))) {
            return false;
        }
        if (this.role != other.role && (this.role == null || !this.role.equals(other.role))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  String.format("%s %s %s %s",id,order,emp,role) ;
    }

    
}
