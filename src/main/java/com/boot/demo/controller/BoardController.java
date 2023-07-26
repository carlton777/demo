package com.boot.demo.controller;

import com.boot.demo.model.Board;
import com.boot.demo.repository.BoardRepository;
import com.boot.demo.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);

        return "board/list";
    }
    @GetMapping("/form")
    public String form(Model model, @RequestParam Long id){
        if (id == null){
            model.addAttribute("board", new Board());
        }else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }
    @PostMapping("/form")
//    public String form(@ModelAttribute Board board, Model model){
    public String form(@Valid Board board, BindingResult bindingResult){
        log.info("@# form()");

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("@# hasErrors()");

            return "board/form";
        }

        boardRepository.save(board);

        return "redirect:/board/list";
    }
}
