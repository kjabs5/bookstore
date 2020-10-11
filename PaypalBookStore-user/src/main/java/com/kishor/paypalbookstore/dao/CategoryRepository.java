package com.kishor.paypalbookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.paypalbookstore.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
