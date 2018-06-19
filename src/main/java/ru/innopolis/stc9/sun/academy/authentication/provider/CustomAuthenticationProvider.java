package ru.innopolis.stc9.sun.academy.authentication.provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.controllers.IndexController;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.UserService;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDTO userDTO = userService.getUserByEmail(email);

        if (userDTO==null || !userDTO.getEmail().equalsIgnoreCase(email)) {
            LOGGER.info("Trying to enter with wrong login");
            throw new BadCredentialsException("Неверный логин или пароль.");
        }

        if (!password.equals(userDTO.getPassword())) {
            LOGGER.info("Trying to enter with wrong password");
            throw new BadCredentialsException("Неверный логин или пароль.");
        }

        Collection<? extends GrantedAuthority> roles = userDTO.getRoles();

        return new UsernamePasswordAuthenticationToken(userDTO, password, roles);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}
