package com.gpch.login.controller;

import com.gpch.login.model.Board;
import com.gpch.login.model.Comment;
import com.gpch.login.model.User;
import com.gpch.login.repository.BoardRepository;
import com.gpch.login.repository.CommentRepository;
import com.gpch.login.repository.UserRepository;
import com.gpch.login.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserRepository userRepository;

//    첫 화면
    @GetMapping("/index")
    public ModelAndView index(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "It's Liit");
        List<Board> boardList = boardRepository.findAll();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
//        List<User> userList = userRepository.findAll();
        modelAndView.addObject("boardList", boardList);
//        modelAndView.addObject("userList", userList);
        modelAndView.addObject("user", userDetails.getUsername());
        return modelAndView;
    }

//    게시글 보기
    @GetMapping("/content/{board_id}")
    public ModelAndView content(@PathVariable Integer board_id, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("content");
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Board board = boardRepository.findById(board_id).get();
        List<Comment> commentList = commentRepository.findAllByboardId(board_id);
        modelAndView.addObject("headTitle", "게시글 보기");
        modelAndView.addObject("user", userDetails.getUsername());
        modelAndView.addObject("content", board);
//        modelAndView.addObject("title", board.getTitle());
//        modelAndView.addObject("content", board.getContent());
//        modelAndView.addObject("writeTime", board.getWriteTime());
//        modelAndView.addObject("writer", board.getUser_id());
        modelAndView.addObject("list", commentList);
        return modelAndView;
    }

//    게시글 쓰기
    @GetMapping("/write")
    public ModelAndView write(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("write");
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        modelAndView.addObject("headTitle", "게시글 쓰기");
        modelAndView.addObject("user", userDetails.getUsername());
        return modelAndView;
    }

//    게시글 날리기
    @PostMapping("/insertContent")
    public String insertContent(Authentication authentication,
                                String title, String content) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Board board = Board.builder()
                .title(title)
                .content(content)
                .user_id(userDetails.getUsername())
                .build();
        repositoryService.addBoard(board);
//        Board entity = repositoryService.addBoard(board);
//        model.addAttribute(entity);
        return "redirect:/index";
    }

//    댓글 날리기
    @PostMapping("/insertComment")
    public String insertComment(@RequestParam Integer board_id, String wcontent, Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Comment comment = Comment.builder()
                .content(wcontent)
                .user_id(userDetails.getUsername())
                .board(boardRepository.findById(board_id).get())
                .build();
        repositoryService.addComment(comment);
        return "redirect:/content/" + board_id;
    }

}
