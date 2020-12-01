/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "ord_seq", sequenceName = "ord_seq", initialValue = 1, allocationSize=1)
@XmlRootElement
@XmlSeeAlso({Item.class,Customer.class})
//@XmlType(propOrder={"listId","customer","ShopList"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomerOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ord_seq")
    @XmlTransient
    private Long id;
    @ManyToOne
    private Customer customer;
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="order", targetEntity=Item.class)
    private List<Item> items = new ArrayList<Item>();
    
    @OneToMany(mappedBy="order")
    @XmlTransient
    private List<OrderEmployee> assignments ;

    @XmlTransient
    public List<OrderEmployee> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<OrderEmployee> assignments) {
        this.assignments = assignments;
    }
    
    @XmlElementWrapper(name="ShopList")
    @XmlElement(name="item")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.customer != null ? this.customer.hashCode() : 0);
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
        final CustomerOrder other = (CustomerOrder) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" + "id=" + id + ", customer=" + customer + '}';
    }
    
    @XmlElement(name="listId")
    public Long getListId(){
        return getId();
    }
    
    public void setListId(Long listId){
        setId(listId);
    }
    
}
