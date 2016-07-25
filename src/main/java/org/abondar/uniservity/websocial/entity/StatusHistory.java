/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abondar.uniservity.websocial.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "sthis_seq", sequenceName = "sthis_seq", initialValue = 1, allocationSize=1)
public class StatusHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    private String object_name;
    
    private Long objectID;
    
    @ManyToOne
    private Status status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public Long getObjectID() {
        return objectID;
    }

    public void setObjectID(Long objectID) {
        this.objectID = objectID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.startDate != null ? this.startDate.hashCode() : 0);
        hash = 47 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
        hash = 47 * hash + (this.object_name != null ? this.object_name.hashCode() : 0);
        hash = 47 * hash + (this.objectID != null ? this.objectID.hashCode() : 0);
        hash = 47 * hash + (this.status != null ? this.status.hashCode() : 0);
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
        final StatusHistory other = (StatusHistory) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.startDate != other.startDate && (this.startDate == null || !this.startDate.equals(other.startDate))) {
            return false;
        }
        if (this.endDate != other.endDate && (this.endDate == null || !this.endDate.equals(other.endDate))) {
            return false;
        }
        if ((this.object_name == null) ? (other.object_name != null) : !this.object_name.equals(other.object_name)) {
            return false;
        }
        if (this.objectID != other.objectID && (this.objectID == null || !this.objectID.equals(other.objectID))) {
            return false;
        }
        if (this.status != other.status && (this.status == null || !this.status.equals(other.status))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StatusHistory{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", object_name=" + object_name + ", objectID=" + objectID + ", status=" + status + '}';
    }

   
    
}
