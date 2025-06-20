package com.example.demospringboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty
public interface SystemProfile {

    String getProfile();
}
