package pl.com.pollub.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "UserSettings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private String email;
    private int newsletterLevel;
    private int active;

    public UserSettings() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
	public boolean compere (User user){
		return (this.getEmail().equals(user.getEmail()) 
				&& this.userId == user.getUserId());
	}
}
