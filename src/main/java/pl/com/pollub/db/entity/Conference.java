package pl.com.pollub.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by Mateusz on 2016-06-02.
 */
@Entity
@Table(name = "conference")
public class Conference {

    @Id
    @Column(name = "Conferenceid")
    private Integer conferenceId;

    @Column(name = "Name")
    private String name;

    @Column(name = "datefrom")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateFrom;

    @Column(name = "dateto")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTo;

    @Column(name = "applicationdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime applicationDate;

    @Column(name = "fullpaperdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime FullPaperDate;

    @Column(name = "creationdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime CreationDate;

    @Column(name = "paymentdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime PaymentDate;

    @Column(name = "www")
    private String www;

    public Conference() {
    } //Always add default constructor

    public Conference(Integer conferenceId, String name, LocalDateTime dateFrom, LocalDateTime dateTo, LocalDateTime applicationDate) {
        this.conferenceId = conferenceId;
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.applicationDate = applicationDate;
    }

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

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDateTime getFullPaperDate() {
        return FullPaperDate;
    }

    public void setFullPaperDate(LocalDateTime fullPaperDate) {
        FullPaperDate = fullPaperDate;
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        CreationDate = creationDate;
    }

    public LocalDateTime getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }
}
