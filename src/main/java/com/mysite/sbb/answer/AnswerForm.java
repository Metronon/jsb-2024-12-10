package com.mysite.sbb.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
    @NotEmpty(message="내용은 빈 칸이 될 수 없습니다.")
    private String content;
}
