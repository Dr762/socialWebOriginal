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

/**
 *
 * @author alex
 */
@Entity
@SequenceGenerator(name = "pos_seq", sequenceName = "pos_seq", initialValue = 1, allocationSize=1)
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="pos_seq")
    private Long id;
    @ManyToOne
    private Position boss;
    private String pos_name;

    public Position getBoss() {
        return boss;
    }

    public void setBoss(Position boss) {
        this.boss = boss;
    }

    public String getPos_name() {
        return pos_name;
    }

    public void setPos_name(String pos_name) {
        this.pos_name = pos_name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.boss != null ? this.boss.hashCode() : 0);
        hash = 19 * hash + (this.pos_name != null ? this.pos_name.hashCode() : 0);
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
        final Position other = (Position) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.boss != other.boss && (this.boss == null || !this.boss.equals(other.boss))) {
            return false;
        }
        if ((this.pos_name == null) ? (other.pos_name != null) : !this.pos_name.equals(other.pos_name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  String.format("%s %s %s", id, pos_name, boss == null ? "" : "(boss: " + boss + " )");
    }

   
    
}
