package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String rating;

    private Long store_id;

    private String category;

    // 기타 필요한 속성 및 관계 설정 등이 있을 수 있습니다.

    // 게터와 세터는 생략합니다.
}