package com.gdsc.remine.post.controller;

import com.gdsc.remine.global.api_payload.ApiResponse;
import com.gdsc.remine.post.dto.response.CommunityPageResponse;
import com.gdsc.remine.post.dto.response.PostDetailResponse;
import com.gdsc.remine.post.dto.response.UpdatesPageResponse;
import com.gdsc.remine.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    ApiResponse<CommunityPageResponse> getCommunityPage() {
        return ApiResponse.onSuccess(postService.getCommunityPage());
    }

    @GetMapping("/updates")
    ApiResponse<UpdatesPageResponse> getUpdatesCommunityPage(
            @PageableDefault(size = 30) final Pageable pageable
    ) {
        return ApiResponse.onSuccess(postService.getUpdatesCommunityPage(pageable));
    }

    @GetMapping("/{postId}")
    ApiResponse<PostDetailResponse> getDetailPostInfo(
            @PathVariable("postId") final Long postId,
            @PageableDefault(size = 30) final Pageable pageable
    ) {
        return ApiResponse.onSuccess(postService.getDetailPostInfo(postId, pageable));
    }
}
