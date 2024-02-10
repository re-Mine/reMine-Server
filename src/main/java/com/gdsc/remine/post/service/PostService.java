package com.gdsc.remine.post.service;

import com.gdsc.remine.declaration.domain.Declaration;
import com.gdsc.remine.declaration.domain.repository.DeclarationRepository;
import com.gdsc.remine.login.jwt.util.AuthTokensGenerator;
import com.gdsc.remine.post.domain.Post;
import com.gdsc.remine.post.domain.repository.PostRepository;
import com.gdsc.remine.post.dto.response.CommunityPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final DeclarationRepository declarationRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public CommunityPageResponse getCommunityPage() {
        final List<Post> postList = postRepository.findFamousPost();
        final List<Declaration> declarationList = declarationRepository.findRecentDeclarations();
        return CommunityPageResponse.of(
                postList,
                declarationList
        );
    }
}
