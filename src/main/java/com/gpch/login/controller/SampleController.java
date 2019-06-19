package com.gpch.login.controller;

import com.gpch.login.model.Board;
import com.gpch.login.model.Comment;
import com.gpch.login.repository.BoardRepository;
import com.gpch.login.repository.CommentRepository;
import com.gpch.login.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SampleController {

    private final RepositoryService repositoryService;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

//    첫 화면
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "첫 화면");
        List<Board> boardList = boardRepository.findAll();
        modelAndView.addObject("boardList", boardList);
        return modelAndView;
    }

//    게시글 보기
    @GetMapping("/content/{board_id}")
    public ModelAndView content(@PathVariable Integer board_id) {
        ModelAndView modelAndView = new ModelAndView("content");
        Board board = boardRepository.findById(board_id).get();
        List<Comment> commentList = commentRepository.findAllByboardId(board_id);
        modelAndView.addObject("headTitle", "게시글 보기");
        modelAndView.addObject("title", board.getTitle());
        modelAndView.addObject("content", board.getContent());
        modelAndView.addObject("writeTime", board.getWriteTime());
        modelAndView.addObject("list", commentList);
        return modelAndView;
    }

//    게시글 쓰기
    @GetMapping("/write")
    public ModelAndView write() {
        ModelAndView modelAndView = new ModelAndView("write");
        modelAndView.addObject("headTitle", "게시글 쓰기");
        return modelAndView;
    }

//    게시글 날리기
    @PostMapping("/insertContent")
    public String insertContent(@ModelAttribute Board board, Model model) {
        Board entity = repositoryService.addBoard(board);
        model.addAttribute(entity);
        return "redirect:/index";
    }

//    댓글 날리기
    @PostMapping("/insertComment")
    public String insertComment(@RequestParam Integer board_id, String wcontent, Model model) {
        Comment comment = Comment.builder()
                .content(wcontent)
                .board(boardRepository.findById(board_id).get())
                .build();
        repositoryService.addComment(comment);
        return "redirect:/content/" + board_id;
    }

}
