package com.gdsc.remine.declaration.service;

import com.gdsc.remine.declaration.domain.Declaration;
import com.gdsc.remine.declaration.domain.repository.DeclarationRepository;
import com.gdsc.remine.declaration.dto.response.PrivateDeclarationElement;
import com.gdsc.remine.declaration.dto.response.PrivateDeclarationElements;
import com.gdsc.remine.global.gcs.FilePath;
import com.gdsc.remine.global.gcs.service.GoogleCloudStorageService;
import com.gdsc.remine.login.jwt.util.AuthTokensGenerator;
import com.gdsc.remine.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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

    public PrivateDeclarationElements getDeclarationInWeek() {
        final Long loginMemberId = authTokensGenerator.getLoginMemberId();

        final LocalDateTime oneWeekAgo = getOneWeekAgoDate();
        final List<Declaration> declarationList = declarationRepository.findInRecent7Day(
                oneWeekAgo,
                loginMemberId
        );

        final LocalDateTime oneDayAgo = getOneDayAgoDate();
        final Long count = declarationRepository.countInRecent1Day(oneDayAgo);

        return PrivateDeclarationElements.from(
                count,
                declarationList
        );
    }

    private LocalDateTime getOneWeekAgoDate() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(6);
        return oneWeekAgo
                .minusHours(oneWeekAgo.getHour())
                .minusMinutes(oneWeekAgo.getMinute());
    }

    private LocalDateTime getOneDayAgoDate() {
        return LocalDateTime.now().minusDays(1);
    }
}
