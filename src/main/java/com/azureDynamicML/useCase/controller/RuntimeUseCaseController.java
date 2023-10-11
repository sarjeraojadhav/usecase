package com.azureDynamicML.useCase.controller;

import com.azureDynamicML.useCase.services.UseCase;
import com.azureDynamicML.useCase.services.UseCaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/runtime-use-case")
public class RuntimeUseCaseController {
    @Autowired
    private UseCaseFactory useCaseFactory;

    @GetMapping("/{name}")
    public void executeUseCase(@PathVariable String name) {
        UseCase useCase = useCaseFactory.createUseCase(name);
        useCase.execute();
    }
}
