package com.study.board.service;

import com.study.board.entity.Category;
import com.study.board.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // 가정: 각 직종에 대한 점포 목록을 하드코딩하여 반환하는 메서드
    public List<String> getStoresByCategory(String category) {
        switch (category) {
            case "커피전문점":
                return List.of("투썸플레이스", "스타벅스", "이디야", "메가커피");
            case "편의점":
                return List.of("CU", "세븐일레븐", "GS25", "이마트24");
            case "영화관":
                return List.of("CGV", "메가박스", "롯데시네마");
            case "디저트":
                return List.of("베스킨라빈스", "왕가탕후루", "설빙");
            case "뷰티헬스":
                return List.of("애슐리영", "이니스프리", "미쟝센");
            case "레스토랑":
                return List.of("아웃백", "빕스", "애슐리");
            case "주유세차":
                return List.of("주유세차1", "주유세차2", "주유세차3");
            case "놀이공원":
                return List.of("롯데월드", "경주월드", "에버랜드");
            default:
                // 해당하는 카테고리가 없을 경우 빈 목록 반환
                return Collections.emptyList();
        }
    }


    // 모든 직종 카테고리를 반환하는 메서드
    public List<String> getAllCategories() {
        return List.of("커피전문점", "편의점", "영화관", "디저트", "뷰티헬스", "레스토랑", "주유세차", "놀이공원");
    }

    // 각 점포별로 게시글을 조회하는 메서드
    public List<Category> findCategoriesByStore(String store) {
        return categoryRepository.findByStore(store);
    }

}