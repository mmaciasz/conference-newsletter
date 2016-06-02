package pl.com.pollub.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Mateusz on 2016-06-02.
 */
@Entity
public class Conference {

    @Id
    private Integer conferenceid; // Beware of camel-case
    private String name;

    public Conference() {} //Always add default constructor

    public Integer getConferenceid() {
        return conferenceid;
    }

    public void setConferenceid(Integer conferenceid) {
        this.conferenceid = conferenceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
