package com.gpch.login.service;

import com.gpch.login.model.Board;
import com.gpch.login.model.Comment;
import com.gpch.login.repository.BoardRepository;
import com.gpch.login.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RepositoryServiceImpl implements RepositoryService {
    @Qualifier("boardRepository")
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Board addBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
