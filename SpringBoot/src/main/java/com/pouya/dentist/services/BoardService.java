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
import java.util.Map;

/**
 * Service class for managing boards.
 */
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * Retrieves all boards.
     *
     * @return a list of all boards
     */
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    /**
     * Retrieves a board by its ID.
     *
     * @param id the ID of the board
     * @return the board with the specified ID
     * @throws ResourceNotFoundException if the board is not found
     */
    public Board getBoardById(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id " + id));
    }

    /**
     * Creates a new board with the given details.
     *
     * @param boardRequest a map containing the name, description, user IDs, and post IDs of the board
     * @return the created board
     * @throws ResourceNotFoundException if any user or post ID is not found
     */
    @Transactional
    public Board createBoard(Map<String, Object> boardRequest) {
        String name = (String) boardRequest.get("name");
        String description = (String) boardRequest.get("description");
        List<Integer> userIds = (List<Integer>) boardRequest.get("user_ids");
        List<Integer> postIds = (List<Integer>) boardRequest.get("post_ids");

        Board board = new Board();
        board.setName(name);
        board.setDescription(description);

        List<User> users = new ArrayList<>();
        if (userIds != null) {
            for (Integer userId : userIds) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
                users.add(user);
            }
        }
        board.setUsers(users);

        List<Post> posts = new ArrayList<>();
        if (postIds != null) {
            for (Integer postId : postIds) {
                Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
                posts.add(post);
            }
        }
        board.setPosts(posts);

        return boardRepository.save(board);
    }

    /**
     * Updates the details of an existing board.
     *
     * @param id the ID of the board to update
     * @param boardDetails the new details of the board
     * @return the updated board
     * @throws ResourceNotFoundException if the board is not found
     */
    @Transactional
    public Board updateBoard(Integer id, Board boardDetails) {
        Board existingBoard = getBoardById(id);
        existingBoard.setName(boardDetails.getName());
        existingBoard.setDescription(boardDetails.getDescription());
        existingBoard.setUsers(boardDetails.getUsers());
        existingBoard.setPosts(boardDetails.getPosts());
        existingBoard.setUserIds(boardDetails.getUserIds());
        existingBoard.setPostIds(boardDetails.getPostIds());
        return boardRepository.save(existingBoard);
    }

    /**
     * Deletes a board by its ID.
     *
     * @param id the ID of the board to delete
     * @return a message indicating the result of the deletion
     */
    public String deleteBoard(Integer id) {
        try {
            getBoardById(id);
            boardRepository.deleteById(id);
            return "Board with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Board not found with id " + id;
            }
            return "Error deleting board with id " + id;
        }
    }
}