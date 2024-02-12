package com.gdsc.remine.declaration.controller;

import com.gdsc.remine.declaration.dto.response.PrivateDeclarationElement;
import com.gdsc.remine.declaration.dto.response.PrivateDeclarationElements;
import com.gdsc.remine.declaration.service.DeclarationService;
import com.gdsc.remine.global.api_payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/declarations")
public class DeclarationController {
    private final DeclarationService declarationService;

    @PostMapping("")
    public ApiResponse<PrivateDeclarationElement> uploadDeclaration(
            @RequestParam(value = "file") final MultipartFile audioFile,
            @RequestParam(value = "content") final String content
    ) {
        return ApiResponse.onSuccess(declarationService.uploadDeclaration(audioFile, content));
    }

    @GetMapping("")
    public ApiResponse<PrivateDeclarationElements> getDeclarationInWeek() {
        return ApiResponse.onSuccess(declarationService.getDeclarationInWeek());
    }
}
