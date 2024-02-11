package com.gdsc.remine.post.dto.response;

import com.gdsc.remine.declaration.domain.Declaration;
import com.gdsc.remine.declaration.dto.response.DeclarationElement;
import com.gdsc.remine.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPageResponse {
    private List<PostElement> famousPostList;
    private List<DeclarationElement> declarationList;

    public static CommunityPageResponse of(
            final List<Post> postList,
            final List<Declaration> declarationList
    ) {
        final List<PostElement> postElementList = postList.stream().map(
                PostElement::new
        ).collect(Collectors.toList());

        final List<DeclarationElement> declarationElementList = declarationList.stream().map(
                DeclarationElement::new
        ).collect(Collectors.toList());

        return new CommunityPageResponse(postElementList, declarationElementList);
    }
}
