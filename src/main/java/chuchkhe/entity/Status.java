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

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "st_seq", sequenceName = "st_seq", initialValue = 1, allocationSize=1)
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final long ST_Draft = 1;
    public static final long ST_ReadyToAssign = 2;
    public static final long ST_Assigned = 3;
    public static final long ST_Active = 4;
    public static final long ST_Closed = 5;
    public static final long ST_Offline = 6;
    public static final long ST_Online = 7;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status_name;
    private String object_name;

    public Status() {
    }

    public Status(Long id, String status_name, String object_name) {
        this.id = id;
        this.status_name = status_name;
        this.object_name = object_name;
    }

    
    
    
    
    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
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
        hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 13 * hash + (this.status_name != null ? this.status_name.hashCode() : 0);
        hash = 13 * hash + (this.object_name != null ? this.object_name.hashCode() : 0);
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
        final Status other = (Status) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.status_name == null) ? (other.status_name != null) : !this.status_name.equals(other.status_name)) {
            return false;
        }
        if ((this.object_name == null) ? (other.object_name != null) : !this.object_name.equals(other.object_name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Status{" + "id=" + id + ", status_name=" + status_name + ", object_name=" + object_name + '}';
    }

    
    
}
