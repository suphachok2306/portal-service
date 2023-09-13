package pcc.portal.portalback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcc.portal.portalback.entity.UserEntity;
import pcc.portal.portalback.model.UserModel;
import pcc.portal.portalback.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object checkEmailPassword(String email, String password) {
        if (email != null && password != null) {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity != null) {
                if (userEntity.getPassword().equals(password)) {
                    return userEntity;
                } else {
                    return "รหัสผ่านไม่ถูกต้อง";
                }
            } else {
                return "ไม่พบผู้ใช้งานด้วยอีเมลที่ระบุ";
            }
        } else {
            return "กรุณาระบุอีเมลและรหัสผ่าน";
        }
    }

    //จะมาแก้อีกที แต่ใช้ได้แล้ว
    public UserModel createUser(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userModel.getUsername());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setEmail(userModel.getEmail());
        userEntity = userRepository.save(userEntity);
        return convertToModel(userEntity);
    }

    private UserModel convertToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        userModel.setEmail(userEntity.getEmail());
        return userModel;
    }
}

