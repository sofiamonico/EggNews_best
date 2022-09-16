package com.egg.EggNewsintel.domain.service;

import com.egg.EggNewsintel.domain.UserRequest;
import com.egg.EggNewsintel.domain.exception.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRequestServiceTest {

    @Autowired
    private UserService userService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void save() throws MyException {
        UserRequest userRequest = userService.save("sofia","12345678", "12345678");
        assertNotNull(userRequest);
    }

   // @Test
    //void modificar(){
        //assertNotNull(userService.modificar(9));
   // }


}