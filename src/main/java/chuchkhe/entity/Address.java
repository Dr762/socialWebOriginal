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
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "add_seq", sequenceName = "add_seq", initialValue = 1, allocationSize=1)
@XmlRootElement
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="add_seq")
    private Long id;
    private String city;
    private String district;
    private String street;
    private String house_no;
    private String apartment_no;
    private String phone;

  
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getApartment_no() {
        return apartment_no;
    }

    public void setApartment_no(String apartment_no) {
        this.apartment_no = apartment_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 71 * hash + (this.district != null ? this.district.hashCode() : 0);
        hash = 71 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 71 * hash + (this.house_no != null ? this.house_no.hashCode() : 0);
        hash = 71 * hash + (this.apartment_no != null ? this.apartment_no.hashCode() : 0);
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
        final Address other = (Address) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if ((this.district == null) ? (other.district != null) : !this.district.equals(other.district)) {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if ((this.house_no == null) ? (other.house_no != null) : !this.house_no.equals(other.house_no)) {
            return false;
        }
        if ((this.apartment_no == null) ? (other.apartment_no != null) : !this.apartment_no.equals(other.apartment_no)) {
            return false;
        }
        if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,tel %s",city,district,street,house_no,apartment_no,phone );
    }

    public String getAddress(){
    
    return String.format("%s,%s,%s,%s,%s,tel %s",city,district,street,house_no,apartment_no,phone );
    }
}
