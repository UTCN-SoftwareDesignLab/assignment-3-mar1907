package hospital.service.user;

import hospital.dto.UserDTO;
import hospital.entity.Role;
import hospital.entity.User;
import hospital.entity.validation.Notification;
import hospital.entity.validation.UserValidator;
import hospital.repository.role.RoleRepository;
import hospital.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ShaPasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ShaPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Notification<Boolean> create(UserDTO userDTO, String role) {
        User user = new User(userDTO.username,userDTO.password,1);

        UserValidator validator = new UserValidator(user);
        boolean userValid = validator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!userValid){
            validator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            user.setPassword(passwordEncoder.encodePassword(userDTO.password,null));
            userRepository.save(user);
            roleRepository.save(new Role(userDTO.username,role));
            notification.setResult(Boolean.TRUE);
            return notification;
        }
    }

    @Override
    public Notification<Boolean> delete(String username) {
        userRepository.delete(username);
        roleRepository.deleteByUsername(username);
        Notification<Boolean> notification = new Notification<>();
        notification.setResult(Boolean.TRUE);
        return notification;
    }

    @Override
    public Notification<Boolean> update(UserDTO userDTO, String role) {
        User user = userRepository.findOne(userDTO.username);
        user.setPassword(userDTO.password);

        UserValidator validator = new UserValidator(user);
        boolean userValid = validator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!userValid){
            validator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            user.setPassword(passwordEncoder.encodePassword(userDTO.password,null));
            userRepository.save(user);
            roleRepository.deleteByUsername(userDTO.username);
            roleRepository.save(new Role(userDTO.username,role));
            notification.setResult(Boolean.TRUE);
            return notification;
        }
    }
}
