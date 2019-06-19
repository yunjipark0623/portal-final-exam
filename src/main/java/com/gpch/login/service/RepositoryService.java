package com.gpch.login.service;

import com.gpch.login.model.Board;
import com.gpch.login.model.Comment;

public interface RepositoryService {
    Board addBoard(Board board);
    Comment addComment(Comment comment);
}
