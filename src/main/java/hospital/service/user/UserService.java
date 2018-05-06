package hospital.service.user;

import hospital.dto.UserDTO;
import hospital.entity.User;
import hospital.entity.validation.Notification;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    Notification<Boolean> create(UserDTO userDTO, String role);
    Notification<Boolean> delete(String username);
    Notification<Boolean> update(UserDTO userDTO, String role);

    List<User> getDoctors();
    User findByName(String name);
}
