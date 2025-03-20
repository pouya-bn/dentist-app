package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Board;
import com.pouya.dentist.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing boards.
 */
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * Handles GET requests for all boards.
     *
     * @return A list of all boards.
     */
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    /**
     * Handles GET requests for a board with a given ID.
     *
     * @param id The ID of the board to get.
     * @return The board with the given ID if it exists, null otherwise.
     */
    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Integer id) {
        return boardService.getBoardById(id);
    }

    /**
     * Handles POST requests to create a new board.
     *
     * @param boardRequest A map containing the name and description of the board.
     * @return The created board.
     */
    @PostMapping
    public Board createBoard(@RequestBody Map<String, Object> boardRequest) {
        return boardService.createBoard(boardRequest);
    }

    /**
     * Handles PUT requests to update an existing board.
     *
     * @param id The ID of the board to update.
     * @param board The updated board object.
     * @return The updated board.
     */
    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        return boardService.updateBoard(id, board);
    }

    /**
     * Handles DELETE requests to delete a board.
     *
     * @param id The ID of the board to delete.
     * @return A string indicating whether the board was deleted successfully or not.
     */
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Integer id) {
        return boardService.deleteBoard(id);
    }
}