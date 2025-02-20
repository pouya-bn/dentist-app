package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Board;
import com.pouya.dentist.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id " + id));
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Integer id, Board boardDetails) {
        Board board = getBoardById(id);
        board.setName(boardDetails.getName());
        board.setDescription(boardDetails.getDescription());
        board.setUsers(boardDetails.getUsers());
        return boardRepository.save(board);
    }

    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }
}
