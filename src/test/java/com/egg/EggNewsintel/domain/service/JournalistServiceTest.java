package com.egg.EggNewsintel.domain.service;

import com.egg.EggNewsintel.domain.Journalist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JournalistServiceTest {
    @Autowired
    private JournalistService journalistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void findPeriodistaByNombreUsuario() {
        Optional<Journalist> respuesta = journalistService.findPeriodistaByNombreUsuario("periodista");
        assertNotNull(respuesta.get().getNews());

    }
}