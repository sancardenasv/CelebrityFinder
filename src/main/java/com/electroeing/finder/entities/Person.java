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
    private String known;

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

    public String getKnown() {
        return known;
    }

    public void setKnown(String known) {
        this.known = known;
    }

    @Override
    public String toString() {
        return new StringBuilder("Person{")
                .append("id=").append(id)
                .append(", celebrity=").append(celebrity)
                .append(", known='").append(known).append('\'')
                .append('}').toString();

    }
}
