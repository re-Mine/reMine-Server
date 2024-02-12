package com.gdsc.remine.declaration.dto.response;

import com.gdsc.remine.declaration.domain.Declaration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateDeclarationElements {
    private String memberName;
    private Long todayParticipantsCount;
    private List<PrivateDeclarationElement> declarationList;

    public static PrivateDeclarationElements from(
            String memberName,
            Long todayParticipantsCount,
            List<Declaration> declarationList
    ) {
        final List<PrivateDeclarationElement> declarationElementList = declarationList.stream().map(
                PrivateDeclarationElement::new
        ).collect(Collectors.toList());

        return new PrivateDeclarationElements(
                memberName,
                todayParticipantsCount,
                declarationElementList
        );
    }
}
