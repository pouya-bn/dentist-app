package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Board;
import com.pouya.dentist.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Board createBoard(@RequestBody Map<String, Object> boardRequest) {
        String name = (String) boardRequest.get("name");
        String description = (String) boardRequest.get("description");
        List<Integer> userIds = (List<Integer>) boardRequest.get("user_ids");
        List<Integer> postIds = (List<Integer>) boardRequest.get("post_ids");

        Board board = new Board();
        board.setName(name);
        board.setDescription(description);
        board.setUserIds(userIds);
        board.setPostIds(postIds);

        return boardService.createBoard(board);
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        Board existingBoard = boardService.getBoardById(id);
        existingBoard.setName(board.getName());
        existingBoard.setDescription(board.getDescription());
        existingBoard.setUsers(board.getUsers());
        existingBoard.setPosts(board.getPosts());
        existingBoard.setUserIds(board.getUserIds());
        existingBoard.setPostIds(board.getPostIds());
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Integer id) {
        try {
            boardService.deleteBoard(id);
            return "Board with id " + id + " deleted successfully";
        } catch (Exception e) {
            return "Error deleting board with id " + id;
        }
    }
}