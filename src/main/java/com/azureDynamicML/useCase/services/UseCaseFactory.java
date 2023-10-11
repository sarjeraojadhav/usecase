package com.azureDynamicML.useCase.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UseCaseFactory {
    private final Map<String, Class<? extends UseCase>> useCases = new HashMap<>();

    public void registerUseCase(String name, Class<? extends UseCase> useCaseClass) {
        useCases.put(name, useCaseClass);
    }

    public UseCase createUseCase(String name) {
        Class<? extends UseCase> useCaseClass = useCases.get(name);
        if (useCaseClass != null) {
            try {
                return useCaseClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creating use case: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Use case not found: " + name);
        }
    }
}
