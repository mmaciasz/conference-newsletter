package pl.com.pollub.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import pl.com.pollub.db.converter.DateTimeConverter;

import javax.persistence.*;

import java.time.LocalDateTime;


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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime changedate;

    @Column(name = "changedfields")
    private String changedFields;

    @Column(name = "changetype")
    private String changeType;

    public ConferenceChanges() {
    } //Always add default constructor

    public ConferenceChanges(Conference conference, String type, LocalDateTime changedate, String changedFields, String changeType) {
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

    public LocalDateTime getChangedate() {
        return changedate;
    }

    public void setChangedate(LocalDateTime changedate) {
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
