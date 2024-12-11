package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message="제목은 빈칸으로 제출할 수 없습니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 빈칸으로 제출할 수 없습니다.")
    private String content;
}
