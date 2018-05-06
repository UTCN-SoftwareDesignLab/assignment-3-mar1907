package hospital.service.user;

import hospital.dto.UserDTO;
import hospital.entity.User;
import hospital.repository.role.RoleRepository;
import hospital.repository.user.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    ShaPasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User("nume","parola1!",1);
    }

    @Test
    public void getAll() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        Assert.assertEquals(userService.getAll().size(),0);
    }

    @Test
    public void create() {
        UserDTO userDTO = new UserDTO();
        userDTO.username = user.getUsername();
        userDTO.password = user.getPassword();
        Assert.assertEquals(userService.create(userDTO,"DOCTOR").getResult(),Boolean.TRUE);
    }

    @Test
    public void delete() {
        Assert.assertTrue(userService.delete(user.getUsername()).getResult());
    }

    @Test
    public void update() {
        when(userRepository.findOne("nume")).thenReturn(user);
        UserDTO userDTO = new UserDTO();
        userDTO.username = user.getUsername();
        userDTO.password = user.getPassword();
        Assert.assertEquals(userService.update(userDTO,"DOCTOR").getResult(),Boolean.TRUE);
    }
}