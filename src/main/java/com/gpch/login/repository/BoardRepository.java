package com.gpch.login.repository;

import com.gpch.login.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("boardRepository")
public interface BoardRepository extends JpaRepository<Board, Integer> {
}

