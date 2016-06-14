package pl.com.pollub.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "UserSettings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String email;
    private int newsletterLevel;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNewsletterLevel() {
        return newsletterLevel;
    }

    public void setNewsletterLevel(int newsletterLevel) {
        this.newsletterLevel = newsletterLevel;
    }

}
