package groom.backend.study.service;

import groom.backend.study.dto.UserRegisterDto;
import groom.backend.study.dto.UserLoginDto;
import groom.backend.study.domain.User;
import groom.backend.study.repository.UserRepository;
import groom.backend.study.util.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        // Mockito 초기화
    }

    @Test
    public void testRegisterUser_Success() {
        // given
        UserRegisterDto userDto = new UserRegisterDto("test@example.com", "password", "STUDENT");
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");

        // when
        userService.registerUser(userDto);

        // then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLoginUser_Success() {
        // given
        User user = new User("test@example.com", "encodedPassword", "STUDENT");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", user.getPassword())).thenReturn(true);
        when(jwtTokenProvider.createToken(anyString(), anyString())).thenReturn("token");

        // when
        String token = userService.loginUser(new UserLoginDto("test@example.com", "password"));

        // then
        Assertions.assertEquals("token", token);
    }
}
