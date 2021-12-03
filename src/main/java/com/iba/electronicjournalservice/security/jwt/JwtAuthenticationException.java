package com.iba.electronicjournalservice.security.jwt;

import net.bytebuddy.implementation.bytecode.Throw;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
