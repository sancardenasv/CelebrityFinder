package com.electroeing.finder.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Person {

    @Id
    private Long id;
    private boolean celebrity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCelebrity() {
        return celebrity;
    }

    public void setCelebrity(boolean celebrity) {
        this.celebrity = celebrity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", celebrity=").append(celebrity);
        sb.append('}');
        return sb.toString();
    }
}
