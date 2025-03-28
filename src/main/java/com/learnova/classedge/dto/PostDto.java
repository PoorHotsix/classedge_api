package com.learnova.classedge.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;

    private String title;

    private String contents;

    private String nickname;

    private LocalDateTime regDate;

    private LocalDateTime lmiDate;

    private String boardName;


    private List<FileItemDto> fileItems = new ArrayList<>();

    private int commentCount;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean hasFile;
}
