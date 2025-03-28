package com.learnova.classedge.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CommentDto {
 

    private Long id;

    private String content;

    @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    private Long parent;

    private String nickname;

    private Long postId;

    private Integer level = 0;

    private List<CommentDto> subComments = new ArrayList<>();

    private List<FileItemDto> fileItems = new ArrayList<>();

    public void addFileItems (FileItemDto fileItemDto){
        fileItems.add(fileItemDto);
    }

    
}
