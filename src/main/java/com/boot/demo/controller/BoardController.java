package com.boot.demo.controller;

import com.boot.demo.model.Board;
import com.boot.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);

        return "board/list";
    }
    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("board", new Board());
        return "board/form";
    }
    @PostMapping("/form")
    public String form(@ModelAttribute Board board, Model model){
        boardRepository.save(board);

        return "redirect:/board/list";
    }
}
