package com.greco.service;

import com.greco.model.Comment;

public interface CommentService {
    Comment insert(Comment comment);
    Comment findById(Long id);
    void deleteById(Long id);
}
