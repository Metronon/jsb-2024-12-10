package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.CompositeUriComponentsContributor;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final CompositeUriComponentsContributor compositeUriComponentsContributor;
    private final UserService userService;

    // 질문 목록 주소 매핑
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value="page", defaultValue="0") int page
                       ) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    // 특정 게시글 페이지 매핑 (id로 구분)
    @GetMapping(value = "/detail/{id}")
    public String detail(
            Model model,
            @PathVariable("id") Integer id,
            AnswerForm answerForm
            ) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // 게시글 생성 페이지 매핑 (생성폼 연결)
    @PreAuthorize("isAuthenticated()") // 로그인하지 않으면 작성 권한 X
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    // 게시글 작성 버튼에 대한 반응 (포스트로 생성폼 연결)
    @PreAuthorize("isAuthenticated()") // 로그인하지 않으면 작성 권한 X
    @PostMapping("/create")
    public String QuestionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(
                questionForm.getSubject(),
                questionForm.getContent(),
                siteUser
        );
        return "redirect:/question/list";
    }
}
