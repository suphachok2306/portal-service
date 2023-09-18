////package pcc.portal.portalback.entity;
////
////import lombok.Data;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.User;
////
////import java.util.Collection;
////
////public class CustomUserDetails extends User {
////    private String firstname;
////    private String lastname;
////    private String email;
////    private String telephone;
////
////    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String telephone) {
////        super(username, password, authorities);
////        this.telephone = telephone;
////    }
////
////    public String getFirstname() {return firstname;}
////    public void setFirstname(String firstname) {this.firstname = firstname;}
////
////    public String getLastname() {return lastname;}
////    public void setLastname(String lastname) {this.lastname = lastname;}
////
////    public String getEmail() {return email;}
////    public void setEmail(String email) {this.email = email;}
////
////    public String getTelephone() {return telephone;}
////    public void setTelephone(String telephone) {this.telephone = telephone;}
////}
////
//
//package pcc.portal.portalback.entity;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//@Data
//public class CustomUserDetails{
//    private String username;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//    private String firstname;
//    private String lastname;
//    private String email;
//    private String telephone;
//
//    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstname, String lastname, String email, String telephone) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.email = email;
//        this.telephone = telephone;
//    }
//}
//
//
