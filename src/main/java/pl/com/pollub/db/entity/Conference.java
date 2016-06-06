package pl.com.pollub.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Mateusz on 2016-06-02.
 */
@Entity
@Table(name="conference")
public class Conference {

    @Id
    @Column(name="Conferenceid")
    private Integer conferenceId; // Beware of camel-case
    
    @Column(name="Name")
    private String name;
    
    @Column(name="datefrom")
    private Date dateFrom;
    
    @Column(name="dateto")
    private Date dateTo;
    
    @Column(name="applicationdate")
    private Date applicationDate;
    
    @Column(name="fullpaperdate")
    private Date FullPaperDate;
    
    @Column(name="creationdate")
    private Date CreationDate;
    
    @Column(name="paymentdate")
    private Date PaymentDate;
    
    @Column(name="www")
    private String www;
    
    public Conference() {} //Always add default constructor

    public Integer getConferenceid() {
        return conferenceId;
    }

    public void setConferenceid(Integer conferenceid) {
        this.conferenceId = conferenceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getFullPaperDate() {
		return FullPaperDate;
	}

	public void setFullPaperDate(Date fullPaperDate) {
		FullPaperDate = fullPaperDate;
	}

	public Date getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}

	public Date getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}
}
