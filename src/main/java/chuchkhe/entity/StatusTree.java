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
@SequenceGenerator(name = "stt_seq", sequenceName = "stt_seq", initialValue = 1, allocationSize=1)
public class StatusTree implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Status prev_status;
    
    @ManyToOne
    private Status next_status;
    
    private String st_action;

    public StatusTree() {
    }

    public StatusTree(Status prev_status, Status next_status) {
        this.prev_status = prev_status;
        this.next_status = next_status;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getPrev_status() {
        return prev_status;
    }

    public void setPrev_status(Status prev_status) {
        this.prev_status = prev_status;
    }

    public Status getNext_status() {
        return next_status;
    }

    public void setNext_status(Status next_status) {
        this.next_status = next_status;
    }

    public String getSt_action() {
        return st_action;
    }

    public void setSt_action(String st_action) {
        this.st_action = st_action;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.prev_status != null ? this.prev_status.hashCode() : 0);
        hash = 67 * hash + (this.next_status != null ? this.next_status.hashCode() : 0);
        hash = 67 * hash + (this.st_action != null ? this.st_action.hashCode() : 0);
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
        final StatusTree other = (StatusTree) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.prev_status != other.prev_status && (this.prev_status == null || !this.prev_status.equals(other.prev_status))) {
            return false;
        }
        if (this.next_status != other.next_status && (this.next_status == null || !this.next_status.equals(other.next_status))) {
            return false;
        }
        if ((this.st_action == null) ? (other.st_action != null) : !this.st_action.equals(other.st_action)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StatusTree{" + "id=" + id + ", prev_status=" + prev_status + ", next_status=" + next_status + ", st_action=" + st_action + '}';
    }

    
}
