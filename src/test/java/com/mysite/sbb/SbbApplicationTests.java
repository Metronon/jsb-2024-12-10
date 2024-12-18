package com.mysite.sbb;

import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        for (int i = 1; i <= 200; i++) {
            String subject = String.format("[%03d]번째 작성자 포함 테스트 데이터 입니다.", i);
            String content = "내용 없음";
            this.questionService.create(subject, content, null);
        }
    }


}
