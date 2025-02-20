package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Board;
import com.pouya.dentist.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Integer id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
    }
}
