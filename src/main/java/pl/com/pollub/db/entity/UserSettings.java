package pl.com.pollub.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usersettings")
public class UserSettings {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "userid")
	private Long userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "newsletterLevel")
	private int newsletterLevel;
	
	@Column(name = "time")
	private Date time;
	
	@Column(name = "allowednewsletterlevel")
	private int allowedNewsletterLevel;
	
	@Column(name = "active")
	private int active;
	
	public UserSettings(){
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getNewsletterLevel() {
		return newsletterLevel;
	}
	public void setNewsletterLevel(int newsletterLevel) {
		this.newsletterLevel = newsletterLevel;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getAllowedNewsletterLevel() {
		return allowedNewsletterLevel;
	}
	public void setAllowedNewsletterLevel(int allowedNewsletterLevel) {
		this.allowedNewsletterLevel = allowedNewsletterLevel;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
}
