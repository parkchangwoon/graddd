package com.study.board.controller;


import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.SortedMap;

import org.springframework.data.domain.PageRequest ;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model) {

        if (board.getContent().isEmpty()) { // 내용이 비어 있는지 확인
            model.addAttribute("message", "내용을 입력해주세요.");
            model.addAttribute("searchUrl", "/board/write");
            return "message"; // 메시지 화면으로 리다이렉트
        } else {
            boardService.write(board);
            model.addAttribute("message", "리뷰 작성이 완료되었습니다.");
            model.addAttribute("searchUrl", "/board/list");
            return "message";
        }
    }



    @GetMapping("/board/list")
    public String boardlist(Model model,
                            @PageableDefault(page=0, size=10, sort="id", direction= Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {

        Page<Board> list = null;

        if (searchKeyword == null || searchKeyword.isEmpty()) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSeachList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() +1;
        int totalPages = list.getTotalPages();
        int startPage = Math.max(nowPage -4,1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);


        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {

        Board board = boardService.boardview(id);
        model.addAttribute("board", board);
        model.addAttribute("title", board.getTitle());
        model.addAttribute("content", board.getContent());
        model.addAttribute("rating", board.getRating());
        return "boardview";
    }



    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {

        model.addAttribute("board", boardService.boardview(id));

        return "boardmodify";
    }


    @PostMapping("/board/rate/{id}")
    public String rateBoard(@PathVariable("id") Integer id, @RequestParam("rate") int rating) {
        Board board = boardService.boardview(id);
        boardService.rateBoard(id, rating);
        return "redirect:/board/view/" + id;
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, @RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("rating") Integer rating) {
        boardService.boardUpdate(id, title, content, rating);
        return "redirect:/board/list";

    }
}