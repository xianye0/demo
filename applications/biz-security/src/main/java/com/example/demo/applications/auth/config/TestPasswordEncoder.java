package com.example.demo.applications.auth.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: rock
 * @date: 2019/2/2
 * @Description:
 */
public class TestPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
