package com.kernel360.orury.domain.post.controller;

import com.kernel360.orury.domain.post.PostViewRequest;
import com.kernel360.orury.domain.post.db.PostEntity;
import com.kernel360.orury.domain.post.dto.PostDto;
import com.kernel360.orury.domain.post.model.PostRequest;
import com.kernel360.orury.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@PostMapping("create/{boardId}/{userId}")      // 문찬욱 : 임시 url
	public PostDto create(
		@Valid
		@RequestBody
		PostRequest postRequest,
		@PathVariable
		Long userId,
		@PathVariable
		Long boardId
	) {
		return postService.createPost(postRequest, userId, boardId);
	}

	// 게시글 아이돌 게시글 조회. 주석 이따 지울게요

	@PostMapping("/view")
	public PostDto view(
		@Valid
		@RequestBody PostViewRequest postViewRequest
	) {
		return postService.getPost(postViewRequest);
	}

	@GetMapping("/all")             // 문찬욱 : baordId에 따른 결과가 나오게 리팩토링 필요
	public List<PostEntity> list(

	) {
		return postService.all();
	}

	@PatchMapping("")                // 문찬욱 : 임시 url, 리팩토링 필요
	public PostDto updatePost(
		@Valid
		@RequestBody
		PostRequest postRequest
	) {
		return postService.updatePost(postRequest);
	}

	@DeleteMapping("/{postId}")     // 문찬욱 : 임시 url
	public void delete(
		@PathVariable
		Long postId
	) {
		postService.deletePost(postId);
	}

    /*
    // 작업내용 주석처리
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{postId}")
    public String showPostDetail(@PathVariable Integer postId, Model model) {
        // postId를 이용하여 게시글 상세 정보 조회
        Post post = postService.getPostById(postId);

        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // 현재 사용자와 게시글 작성자 비교
        boolean currentUserIsAuthor = currentUsername.equals(post.getUser().getCreatedBy());

        // 댓글 목록 조회
        List<Comment> comments = postService.getCommentsByPostId(postId);

        // Thymeleaf 템플릿에 데이터 전달
        model.addAttribute("post", post);
        model.addAttribute("currentUserIsAuthor", currentUserIsAuthor);
        model.addAttribute("comments", comments);

        return "PostDetail";


     */
}
