package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Board;
import com.pouya.dentist.models.Post;
import com.pouya.dentist.models.User;
import com.pouya.dentist.repositories.BoardRepository;
import com.pouya.dentist.repositories.PostRepository;
import com.pouya.dentist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    @Autowired
    private PostRepository postRepository; // Inject PostRepository

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id " + id));
    }

    @Transactional
    public Board createBoard(Board board) {
        List<User> users = new ArrayList<>();
        if (board.getInputUserIds() != null) {
            for (Integer userId : board.getInputUserIds()) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
                users.add(user);
            }
        }
        board.setUsers(users);

        List<Post> posts = new ArrayList<>();
        if (board.getInputPostIds() != null) {
            for (Integer postId : board.getInputPostIds()) {
                Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
                posts.add(post);
            }
        }
        board.setPosts(posts);

        return boardRepository.save(board);
    }


    public Board updateBoard(Integer id, Board boardDetails) {
        Board board = getBoardById(id);
        board.setName(boardDetails.getName());
        board.setDescription(boardDetails.getDescription());
        // Keep or adjust user/post updates based on your requirements. Consider updating via IDs as well.
        board.setUsers(boardDetails.getUsers());
        return boardRepository.save(board);
    }

    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }
}