package com.neomodeon.gridenauth;


import com.neomodeon.gridenauth.config.JwtProperties;
import com.neomodeon.gridenauth.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

class JwtServiceImplTest {
    private JwtServiceImpl jwtService;

    @BeforeEach
    void setUp() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("Spasi_HospadiSpasi_HospadiSpasi_HospadiSpasi_Hospadi");
        jwtService = new JwtServiceImpl(jwtProperties);
    }

    @Test
    void generateAndValidateToken() {
        UserDetails user = User.withUsername("testUser")
                .password("testPwd")
                .roles("USER")
                .build();

        String token = jwtService.generateToken(user);
        assertNotNull(token);

        String username = jwtService.extractUsername(token);
        assertEquals("testUser", username);
        assertTrue(jwtService.validateToken(token, user));
    }
}
