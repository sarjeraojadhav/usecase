package com.azureDynamicML.useCase.Config;

import com.azureDynamicML.useCase.services.CarbonEmissionUseCase;
import com.azureDynamicML.useCase.services.PowerConsumptionUseCase;
import com.azureDynamicML.useCase.services.UseCaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Autowired
    public UseCaseConfiguration(UseCaseFactory useCaseFactory) {
        useCaseFactory.registerUseCase("powerConsumption", PowerConsumptionUseCase.class);
        useCaseFactory.registerUseCase("carbonEmission", CarbonEmissionUseCase.class);

    }
}

