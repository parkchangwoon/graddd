package com.study.board.repository;

import com.study.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 카테고리별 점포 목록을 조회하는 메서드
    List<Category> findByCategory(String category);

    List<Category> findByStore(String store);
}