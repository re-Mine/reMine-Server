package com.gdsc.remine.declaration.controller;

import com.gdsc.remine.declaration.service.DeclarationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeclarationController {
    private final DeclarationService declarationService;
}
