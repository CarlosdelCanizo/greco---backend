package com.greco.repository;

import com.greco.model.Comment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
}
