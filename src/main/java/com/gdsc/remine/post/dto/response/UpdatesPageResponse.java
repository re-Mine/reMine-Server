package com.gdsc.remine.post.dto.response;

import com.gdsc.remine.global.PageInfo;
import com.gdsc.remine.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatesPageResponse {
    private List<PostElement> famousPostList;
    private PageInfo recentPostPageInfo;
    private List<PostElement> recentPostList;

    public static UpdatesPageResponse of(List<Post> famousPostList, Page<PostElement> recentPostPage) {
        final List<PostElement> famousPostElementList = famousPostList.stream().map(
                PostElement::new
        ).collect(Collectors.toList());

        final PageInfo pageInfo = new PageInfo(recentPostPage);
        final List<PostElement> postElementList = recentPostPage.getContent();

        return new UpdatesPageResponse(
                famousPostElementList,
                pageInfo,
                postElementList
        );
    }
}
