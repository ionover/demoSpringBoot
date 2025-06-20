package com.example.demospringboot.config.profiles;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty
public interface SystemProfile {

    String getProfile();
}
