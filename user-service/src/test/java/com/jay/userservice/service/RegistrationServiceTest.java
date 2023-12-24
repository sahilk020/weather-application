package com.jay.userservice.service;

import com.jay.userservice.entity.User;
import com.jay.userservice.entity.UserCredentials;
import com.jay.userservice.entity.UserDto;
import com.jay.userservice.exception.UserAlreadyRegisteredException;
import com.jay.userservice.repository.RegisterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    private RegisterRepository repository;
    @Mock
    KafkaMessagePublisher kafkaMessagePublisher;
    @InjectMocks
    private RegisterServiceImpl registerService;

    private User user;
    private UserDto userDto;
    private UserCredentials userCredentials;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setContactNumber("1234567890");
        user.setEmail("abcd@fg.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("Abcd@1234");
        user.setUsername("username");
        userDto = new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getContactNumber(), user.getPassword());
        userCredentials = new UserCredentials(user.getUsername(), user.getPassword());
    }

    @AfterEach
    void tearDown() {
        user = null;
        userDto = null;
        userCredentials = null;
    }

    @Test
    void testRegister() throws UserAlreadyRegisteredException {
        when(repository.findById(userDto.getUsername())).thenReturn(Optional.empty());
        when(repository.save(user)).thenReturn(user);
        doNothing().when(kafkaMessagePublisher).sendEventsToTopic(userCredentials);
        assertEquals(user, registerService.register(userDto));
        verify(repository).findById(userDto.getUsername());
        verify(repository).save(user);
    }

    @Test
    void testRegisterThrowsUserAlreadyRegisteredException() throws UserAlreadyRegisteredException {
        when(repository.findById(userDto.getUsername())).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyRegisteredException.class,()->registerService.register(userDto));
        verify(repository).findById(userDto.getUsername());

    }
}
