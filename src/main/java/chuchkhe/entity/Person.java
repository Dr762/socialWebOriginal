/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "per_seq", sequenceName = "per_seq", initialValue = 1, allocationSize=1)
@XmlRootElement
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="per_seq")
    private Long id;
    private String fname;
    private String lname;
    private String mname;
    private String SocialID;
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getSocialID() {
        return SocialID;
    }

    public void setSocialID(String SocialID) {
        this.SocialID = SocialID;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.fname != null ? this.fname.hashCode() : 0);
        hash = 79 * hash + (this.lname != null ? this.lname.hashCode() : 0);
        hash = 79 * hash + (this.mname != null ? this.mname.hashCode() : 0);
        hash = 79 * hash + (this.SocialID != null ? this.SocialID.hashCode() : 0);
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
        final Person other = (Person) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.fname == null) ? (other.fname != null) : !this.fname.equals(other.fname)) {
            return false;
        }
        if ((this.lname == null) ? (other.lname != null) : !this.lname.equals(other.lname)) {
            return false;
        }
        if ((this.mname == null) ? (other.mname != null) : !this.mname.equals(other.mname)) {
            return false;
        }
        if ((this.SocialID == null) ? (other.SocialID != null) : !this.SocialID.equals(other.SocialID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  String.format( "%s %s %s %s", lname,fname,mname,SocialID) ;
    }

    public void setFIO(String fio) {
   String[] sp = fio.split(" +");
   lname = sp.length > 0? sp[0] : lname;
   fname = sp.length > 1? sp[1] : fname;
   mname = sp.length > 2? sp[2] : mname;
    }
    
    public String getFIO(){
     return  String.format( "%s %s %s", lname,fname,mname) ;
    
    }
   
}
