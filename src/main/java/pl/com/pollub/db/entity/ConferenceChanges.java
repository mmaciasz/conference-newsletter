package pl.com.pollub.db.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "conferencechanges")
public class ConferenceChanges {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Conferenceid")
    private Conference conference;

    @Column(name = "type")
    private String type;

    @Column(name = "changedate")
    private Date changedate;

    @Column(name = "changedfields")
    private String changedFields;

    @Column(name = "changetype")
    private String changeType;

    public ConferenceChanges() {
    } //Always add default constructor

    public ConferenceChanges(Conference conference, String type, Date changedate, String changedFields, String changeType) {
        this.conference = conference;
        this.type = type;
        this.changedate = changedate;
        this.changedFields = changedFields;
        this.changeType = changeType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public String getChangedFields() {
        return changedFields;
    }

    public void setChangedFields(String changedFields) {
        this.changedFields = changedFields;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }


    public Conference getConference() {
        return conference;
    }


    public void setConference(Conference conference) {
        this.conference = conference;
    }

}
