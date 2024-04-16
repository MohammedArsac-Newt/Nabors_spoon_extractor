package com.example.demo.model.hibernate;

/**
 * Copyright (c) 2015 Yogie Kurniawan. All rights reserved.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yogie
 */
@Entity
@Table(schema = "PRUONE_EXP", name = "properties")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Property.findAll", query = "FROM Property WHERE status = 'active'"),
        @NamedNativeQuery(name = "Property.updateTokenHousekeepingStatus",
                query = "UPDATE Property SET value=:value where name=:name")
})
public class CustomerDetails5 {

    private static final long serialVersionUID = -3719163120377291537L;

    @Id
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "value", nullable = false, length = 250)
    private String value;

    @Column(name = "title", length = 250)
    private String title;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
