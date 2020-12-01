/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
@SequenceGenerator(name = "item_seq", sequenceName = "item_seq", initialValue = 1, allocationSize=1)
@XmlRootElement
@XmlType(propOrder={"listId","itemId","itemName","price","quantity","uom","shop_name","manufacturer","bought"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="item_seq")
//    @XmlElement(name="listId")
    private Long id;
    @XmlTransient
    private String item_name = "";
    private Integer price = 0;
    private Double quantity = 0.0;
    private String uom = "";
    private String shop_name = "";
    private String manufacturer = "";
    private boolean bought = false;
    
    @ManyToOne(targetEntity=CustomerOrder.class)
    @XmlTransient
    private CustomerOrder order;

    @XmlTransient
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @XmlTransient
    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
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
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.item_name != null ? this.item_name.hashCode() : 0);
//        hash = 67 * hash + this.price;
//        hash = 67 * hash + this.quantity;
        hash = 67 * hash + (this.uom != null ? this.uom.hashCode() : 0);
        hash = 67 * hash + (this.shop_name != null ? this.shop_name.hashCode() : 0);
        hash = 67 * hash + (this.manufacturer != null ? this.manufacturer.hashCode() : 0);
        hash = 67 * hash + (this.bought ? 1 : 0);
        hash = 67 * hash + (this.order != null ? this.order.hashCode() : 0);
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
        final Item other = (Item) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.item_name == null) ? (other.item_name != null) : !this.item_name.equals(other.item_name)) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if ((this.uom == null) ? (other.uom != null) : !this.uom.equals(other.uom)) {
            return false;
        }
        if ((this.shop_name == null) ? (other.shop_name != null) : !this.shop_name.equals(other.shop_name)) {
            return false;
        }
        if ((this.manufacturer == null) ? (other.manufacturer != null) : !this.manufacturer.equals(other.manufacturer)) {
            return false;
        }
        if (this.bought != other.bought) {
            return false;
        }
        if (this.order != other.order && (this.order == null || !this.order.equals(other.order))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", item_name=" + item_name + ", price=" + price + ", quantity=" + quantity + ", uom=" + uom + ", shop_name=" + shop_name + ", manufacturer=" + manufacturer + ", bought=" + bought + ", order=" + order + '}';
    }

    public Item withOrder(CustomerOrder order) {
        setOrder(order);
        return this;
    }

    @XmlElement(name="listId")
    public Long getListId(){
        return order.getId();
    }
    
    @XmlElement(name="itemId")
    public Long getItemId(){
        return getId();
    }
    
    public void setItemId(Long id){
        setId(id);
    }
    
    public String getItemName(){
        return getItem_name();
    }
    
    public void setItemName(String iName){
        setItem_name(iName);
    }
    
}    
    
    
