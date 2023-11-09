package com.kernel360.orury.domain.comment.service;

import com.kernel360.orury.domain.comment.db.CommentEntity;
import com.kernel360.orury.domain.comment.model.CommentDto;
import com.kernel360.orury.domain.post.db.PostEntity;
import com.kernel360.orury.domain.post.db.PostRepository;
import com.kernel360.orury.domain.user.db.UserEntity;
import com.kernel360.orury.domain.user.db.UserRepository;
import com.kernel360.orury.global.message.errors.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentConverter {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentDto toDto(CommentEntity commentEntity) {

        try {
            // 유저 닉네임 할당
            UserEntity userEntity = userRepository.findById(commentEntity.getUserId())
                    .orElseThrow(() -> new RuntimeException(ErrorMessages.THERE_IS_NO_USER.getMessage() + commentEntity.getUserId()));

            return CommentDto.builder()
                    .id(commentEntity.getId())
                    .postId(commentEntity.getPost().getId())
                    .userId(commentEntity.getUserId())
                    .commentContent(commentEntity.getCommentContent())
                    .userNickname(userEntity.getNickname())
                    .likeCnt(commentEntity.getLikeCnt())
                    .pId(commentEntity.getPId())
                    .createdBy(commentEntity.getCreatedBy())
                    .createdAt(commentEntity.getCreatedAt())
                    .updatedBy(commentEntity.getUpdatedBy())
                    .updatedAt(commentEntity.getUpdatedAt())
                    .build();
        } catch (Exception e) {
            // 예외가 발생한 경우 JSON 형식의 응답을 생성
            String errorMessage = ErrorMessages.THERE_IS_NO_USER.getMessage();
            throw new RuntimeException(errorMessage);
        }

    }

    public CommentEntity toEntity(CommentDto commentdto) {
        PostEntity postEntity = postRepository.findById(commentdto.getPostId())
                .orElseThrow(
                        () -> new RuntimeException(ErrorMessages.THERE_IS_NO_POST.getMessage() + commentdto.getPostId())
                );
        return CommentEntity.builder()
                .id(commentdto.getId())
                .post(postEntity)
                .userId(commentdto.getUserId())
                .commentContent(commentdto.getCommentContent())
                .likeCnt(commentdto.getLikeCnt())
                .pId(commentdto.getPId())
                .createdBy(commentdto.getCreatedBy())
                .createdAt(commentdto.getCreatedAt())
                .updatedBy(commentdto.getUpdatedBy())
                .updatedAt(commentdto.getUpdatedAt())
                .build();
    }
}
