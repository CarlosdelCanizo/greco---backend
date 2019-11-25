package com.greco.service.impl;

import com.greco.exception.NotFoundException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Comment;
import com.greco.repository.CommentRepository;
import com.greco.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment insert(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if(!optional.isPresent())
            throw new NotFoundException(GenericCheckingMessage.ENTITY_NOT_FOUND.toString());
        return optional.get();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> findBySolarPanelId(Long solarPanelId, Pageable pageable) {
        return commentRepository.findBySolarPanelId(solarPanelId, pageable);
    }
}
