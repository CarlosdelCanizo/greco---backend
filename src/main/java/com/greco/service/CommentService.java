package com.greco.service;

import com.greco.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Comment insert(Comment comment);
    Comment findById(Long id);
    void deleteById(Long id);
    Page<Comment> findBySolarPanelId(Long solarPanelId, Pageable pageable);
}
