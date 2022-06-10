package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.CommentService;
import kr.ac.kumoh.backend.Service.LikeService;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.CommentDTO;
import kr.ac.kumoh.backend.dto.AddLikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("/addComment")
    public StatusOfUser addMovieComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @PostMapping("/reviseComment")
    public StatusOfUser reviseMovieComment(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.reviseComment(addLikeDTO);
    }

    @PostMapping("/deleteComment")
    public StatusOfUser deleteMovieComment(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.deleteComment(addLikeDTO);
    }

    @PostMapping("/addLike")
    public StatusOfUser addLike(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.addLike(addLikeDTO);
    }

    @PostMapping("/removeLike")
    public StatusOfUser removeLike(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.removeLike(addLikeDTO);
    }

    @GetMapping("/{userId}/likes")
    public List<String> usersLike(@PathVariable("userId") String userId) {
        return likeService.getCommentsThatUserHitLikeButton(userId);
    }
}
