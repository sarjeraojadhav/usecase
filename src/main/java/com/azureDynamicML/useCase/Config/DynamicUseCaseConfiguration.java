package com.azureDynamicML.useCase.Config;

import com.azureDynamicML.useCase.services.UseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicUseCaseConfiguration {

    private final Map<String, Class<? extends UseCase>> useCases = new HashMap<>();

    /*@Bean
    public DynamicUseCaseConfiguration dynamicUseCaseConfiguration() {
        return new DynamicUseCaseConfiguration();
    }
*/
    public void registerUseCase(String name, Class<? extends UseCase> useCaseClass) {
        useCases.put(name, useCaseClass);
    }

    public void removeUseCase(String name) {
        useCases.remove(name);
    }

    @Bean
    public UseCaseRegistrar useCaseRegistrar() {
        return new UseCaseRegistrar();
    }

    public class UseCaseRegistrar {
        @Bean
        public Map<String, UseCase> useCases() {
            Map<String, UseCase> registeredUseCases = new HashMap<>();
            useCases.forEach((name, useCaseClass) -> {
                try {
                    registeredUseCases.put(name, useCaseClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    throw new RuntimeException("Error creating use case: " + e.getMessage(), e);
                }
            });
            return registeredUseCases;
        }
    }
}
