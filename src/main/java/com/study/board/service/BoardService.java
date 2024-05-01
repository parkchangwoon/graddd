package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성
    public void write(Board board) {

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSeachList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 불러오기
    public Board boardview(Integer id) {

        return boardRepository.findById(id).orElse(null);
    }

    // 별점 업데이트
    public void rateBoard(Integer id, int rating) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            board.setRating(rating);
            boardRepository.save(board);
        }
    }

    // 게시글 수정
    public void boardUpdate(Integer id, String title, String content, Integer rating) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            board.setTitle(title);
            board.setContent(content);
            board.setRating(rating);
            boardRepository.save(board);
        }
    }
}
