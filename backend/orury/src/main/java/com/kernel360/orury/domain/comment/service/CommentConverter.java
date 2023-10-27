package com.kernel360.orury.domain.comment.service;

import com.kernel360.orury.domain.comment.db.CommentEntity;
import com.kernel360.orury.domain.comment.model.CommentDto;
import com.kernel360.orury.domain.post.db.PostEntity;
import com.kernel360.orury.domain.post.db.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentConverter {

    private final PostRepository postRepository;
    public CommentDto toDto(CommentEntity commentEntity) {
        return CommentDto.builder()
                .id(commentEntity.getId())
                .postId(commentEntity.getPost().getId())
                .userId(commentEntity.getUserId())
                .commentContent(commentEntity.getCommentContent())
                .userNickname(commentEntity.getUserNickname())
                .likeCnt(commentEntity.getLikeCnt())
                .pId(commentEntity.getPId())
                .isDelete(commentEntity.isDelete())
                .createdBy(commentEntity.getCreatedBy())
                .createdAt(commentEntity.getCreatedAt())
                .updatedBy(commentEntity.getUpdatedBy())
                .updatedAt(commentEntity.getUpdatedAt())
                .build();
    }

    public CommentEntity toEntity(CommentDto commentdto) {
        PostEntity postEntity = postRepository.findById(commentdto.getPostId())
                .orElseThrow(
                        () -> new RuntimeException("해당 게시글이 없습니다: " + commentdto.getPostId())
                );
        return CommentEntity.builder()
                .id(commentdto.getId())
                .post(postEntity)
                .userId(commentdto.getUserId())
                .commentContent(commentdto.getCommentContent())
                .userNickname(commentdto.getUserNickname())
                .likeCnt(commentdto.getLikeCnt())
                .pId(commentdto.getPId())
                .isDelete(commentdto.isDelete())
                .createdBy(commentdto.getCreatedBy())
                .createdAt(commentdto.getCreatedAt())
                .updatedBy(commentdto.getUpdatedBy())
                .updatedAt(commentdto.getUpdatedAt())
                .build();
    }
}
