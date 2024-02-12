package com.gdsc.remine.declaration.dto.response;

import com.gdsc.remine.declaration.domain.Declaration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateDeclarationElement {
    private Long declarationId;
    private LocalDateTime createdDate;
    private String content;
    private String audioFileURL;

    @Builder(builderMethodName = "from")
    public PrivateDeclarationElement(Declaration declaration) {
        this.declarationId = declaration.getId();
        this.createdDate = declaration.getCreatedDate();
        this.content = declaration.getContent();
        this.audioFileURL = declaration.getAudioFileURL();
    }
}
