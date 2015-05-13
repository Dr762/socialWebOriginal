/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "cust_seq", sequenceName = "cust_seq", initialValue = 1, allocationSize = 1)
@XmlRootElement
//@XmlType(propOrder = {"customerId", "name", "Address"})
@Access(AccessType.FIELD)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cust_seq")
    @XmlTransient
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @XmlTransient
    private Person person;
    @OneToOne(cascade = {CascadeType.ALL})
    @XmlTransient
    private Address address;
    @OneToMany( mappedBy = "customer")
    List<CustomerOrder> orders;
    
    @ManyToOne
    @XmlTransient
    private Employee employee;

    @XmlTransient
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @XmlTransient
    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    @XmlTransient
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @XmlTransient
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElement(name = "customerId")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 31 * hash + (this.person != null ? this.person.hashCode() : 0);
        hash = 31 * hash + (this.address != null ? this.address.hashCode() : 0);
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
        final Customer other = (Customer) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.person != other.person && (this.person == null || !this.person.equals(other.person))) {
            return false;
        }
        if (this.address != other.address && (this.address == null || !this.address.equals(other.address))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", person, address);
    }

    @XmlElement(name = "name")
    public String getName() {
        return person == null ? "" : person.getFIO();
    }

    @XmlElement(name = "address")
    public String getAddressStr() {
        return address == null ? "" : address.getAddress();
    }
}
