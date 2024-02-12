package com.gdsc.remine.declaration.service;

import com.gdsc.remine.declaration.domain.Declaration;
import com.gdsc.remine.declaration.domain.repository.DeclarationRepository;
import com.gdsc.remine.declaration.dto.response.PrivateDeclarationElement;
import com.gdsc.remine.global.gcs.FilePath;
import com.gdsc.remine.global.gcs.service.GoogleCloudStorageService;
import com.gdsc.remine.login.jwt.util.AuthTokensGenerator;
import com.gdsc.remine.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DeclarationService {
    private final DeclarationRepository declarationRepository;
    private final GoogleCloudStorageService storageService;
    private final AuthTokensGenerator authTokensGenerator;

    @Transactional
    public PrivateDeclarationElement uploadDeclaration(
            final MultipartFile audioFile,
            final String content
    ) {
        final String fileURL = storageService.saveFile(FilePath.DECLARATION, audioFile);
        final Member loginMember = authTokensGenerator.getLoginMember();
        final Declaration declaration = Declaration.of()
                .audioFileURL(fileURL)
                .member(loginMember)
                .content(content)
                .build();
        final Declaration saved = declarationRepository.save(declaration);
        return PrivateDeclarationElement.from()
                .declaration(saved)
                .build();
    }
}
