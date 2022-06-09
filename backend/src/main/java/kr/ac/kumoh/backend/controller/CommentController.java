package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.CommentService;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.CommentDTO;
import kr.ac.kumoh.backend.dto.AddLikeDTO;
import kr.ac.kumoh.backend.dto.MovieCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/addComment")
    public StatusOfUser addMovieComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @PostMapping("/addLike")
    public StatusOfUser addLike(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.addLike(addLikeDTO);
    }

    @GetMapping("/test")
    public List<MovieCommentDTO> test() {
        return commentService.getMovieComments("범죄도시 2");
    }
}
