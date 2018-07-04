package com.cosmink.services.security;

import com.cosmink.models.user.User;
import com.cosmink.models.user.UserDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UsernamePasswordValidator {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserDao userDao;

    public User validateCredentials(String email, String password) {

        User user = userDao.findByUsernameOrEmail(email);

        if (user == null) {
            // user cannot be found with the given username/email
            throw new AuthenticationException("Bad credentials.");
        }

        /*if (!user.isActive()) {
            // user is not active
            throw new AuthenticationException("The user is inactive.");
        }*/

        if (!passwordEncoder.checkPassword(password, user.getUserCredentials().getPassword())) {
            // Invalid password
            throw new AuthenticationException("Bad credentials.");
        }

        return user;
    }

}
