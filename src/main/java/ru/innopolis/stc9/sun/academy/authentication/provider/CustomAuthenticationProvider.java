package ru.innopolis.stc9.sun.academy.authentication.provider;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        LOGGER.info(String.format("Trying to login by %s.", email));
        UserDTO userDTO = userService.getUserByEmail(email);
        LOGGER.info(String.format("Find user %s.", userDTO.getFirstName()));

        if (userDTO==null || !userDTO.getEmail().equalsIgnoreCase(email)) {
            LOGGER.info("Попытка входа с неверным логином");
            throw new BadCredentialsException("Неверный логин или пароль.");
        }

        if (!password.equals(userDTO.getPassword())) {
            LOGGER.info("Попытка входа с неверным паролем");
            throw new BadCredentialsException("Неверный логин или пароль.");
        }

        Collection<? extends GrantedAuthority> roles = userDTO.getRoles();

        return new UsernamePasswordAuthenticationToken(email, password, roles);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}
