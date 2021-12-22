package org.campus.webshop.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.campus.webshop.entity.User;

import java.util.*;

public class SecurityService {
    private Map<String, User> userTokens = Collections.synchronizedMap(new HashMap<>());
    private UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public boolean isAuth(String token) {
        return userTokens.containsKey(token);
    }

    public boolean isUserExist(String email) {
        User user = this.userService.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    public String signIn(String email, String password) {//login
        User user = this.userService.findByEmail(email);
        if (user != null) {
            String hashedPassword = DigestUtils.md5Hex(user.getSole() + password);
            boolean coincided = Objects.equals(hashedPassword, user.getPassword());
            if (coincided) {
                return createToken(user);
            } else {
                throw new RuntimeException();//
            }
        } else {
            throw new RuntimeException();//
        }
    }

    public static void signOut(User user) {//logout

    }

    public String signUp(User newUser) {//register
        if (this.isUserExist(newUser.getEmail())){
            throw new RuntimeException();
        }
        newUser.setSole(UUID.randomUUID().toString());
        User user = this.userService.add(newUser);
        if (user != null) {
            return createToken(user);
        } else {
            throw new RuntimeException();//
        }
    }

    private String createToken(User user) {
        String token = UUID.randomUUID().toString();
        userTokens.put(token, user);
        return token;
    }

    private User removeToken(String token) {
        return userTokens.remove(token);
    }

}
