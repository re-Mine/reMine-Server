package com.gdsc.remine.like.service;

import com.gdsc.remine.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
}
