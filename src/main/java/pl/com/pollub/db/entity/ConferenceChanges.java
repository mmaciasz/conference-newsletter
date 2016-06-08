package pl.com.pollub.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="conferencechanges")
public class ConferenceChanges {

    @Id
    @Column(name="id")
    private Integer id; 
    
    @Column(name="Conferenceid")
    private Integer conferenceId; 
    
    @Column(name="type")
    private String type;
    
    @Column(name="changedate")
    private Date changedate;
    
    @Column(name="changedfields")
    private String changedFields;
    
    @Column(name="changetype")
    private String changeType;

    public ConferenceChanges() {} //Always add default constructor

    public Integer getConferenceid() {
        return conferenceId;
    }

    public void setConferenceid(Integer conferenceid) {
        this.conferenceId = conferenceid;
    }

	public Integer getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
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

}