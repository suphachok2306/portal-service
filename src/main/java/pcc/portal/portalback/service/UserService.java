//package pcc.portal.portalback.service;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import pcc.portal.portalback.entity.UserEntity;
//import pcc.portal.portalback.model.UserModel;
//import pcc.portal.portalback.repository.UserRepository;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    public Object checkEmailPassword(String email, String password) {
//        if (email != null && password != null) {
//            UserEntity userEntity = userRepository.findByEmail(email);
//            if (userEntity != null) {
//                if (userEntity.getPassword().equals(password)) {
//                    return userEntity;
//                } else {
//                    return "รหัสผ่านไม่ถูกต้อง";
//                }
//            } else {
//                return "ไม่พบผู้ใช้งานด้วยอีเมลที่ระบุ";
//            }
//        } else {
//            return "กรุณาระบุอีเมลและรหัสผ่าน";
//        }
//    }
//
//    public String createUser(UserModel userModel){
//        UserEntity userEntity = modelMapper.map(userModel, UserEntity.class);
//        userRepository.save(userEntity);
//        return "SUCCESS";
//    }
//}
//

package pcc.portal.portalback.service;
import pcc.portal.portalback.entity.Role;
import pcc.portal.portalback.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUser();
}
