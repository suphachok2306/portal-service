//package pcc.portal.portalback.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import pcc.portal.portalback.entity.UserEntity;
//
//@Repository
//public interface UserRepository extends JpaRepository<UserEntity, Long> {
//
//    UserEntity findByEmail(String email);
//}



package pcc.portal.portalback.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pcc.portal.portalback.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
