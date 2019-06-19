package com.gpch.login.repository;

import com.gpch.login.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByboardId (Integer board_id);
}
