package com.it666.dao;

import com.it666.domain.Category;

import java.util.List;

public interface CategoryDao {
    public void save(Category category);

    List<Category> getAllCategory();

    Category getOneCategory(Integer cid);

    void update(Category category);

    void delete(Category category);


    List<Category> searchlist(String cname);
}
