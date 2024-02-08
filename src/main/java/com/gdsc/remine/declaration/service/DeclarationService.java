package com.gdsc.remine.declaration.service;

import com.gdsc.remine.declaration.domain.repository.DeclarationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeclarationService {
    private final DeclarationRepository declarationRepository;
}
