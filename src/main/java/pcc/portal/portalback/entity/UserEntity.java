package pcc.portal.portalback.entity;

import javax.persistence.*;

@Entity
@Table(name="users", catalog="sd-portal-back")
public class UserEntity {
    private long id;
    private String username;
    private String password;
    private String email;

    public UserEntity(){super();}

    public UserEntity(long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Column(name = "username", length = 20)
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    @Column(name = "password", length = 20)
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Column(name = "email", length = 20)
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
