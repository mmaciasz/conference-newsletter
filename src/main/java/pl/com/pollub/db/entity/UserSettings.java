package pl.com.pollub.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usersettings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private String lastName;
    private int newsletterLevel;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;
    private int allowedNewsletterLevel;
    private int active;

    public UserSettings() {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
