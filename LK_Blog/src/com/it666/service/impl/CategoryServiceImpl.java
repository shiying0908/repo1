package com.it666.service.impl;

import com.it666.dao.CategoryDao;
import com.it666.domain.Category;
import com.it666.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//加入事务
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) {
        /*调用dao*/
        categoryDao.save(category);
        System.out.println("调用service");
    }

    @Override
    public List<Category> getAllCategory() {
        //调用dao层，查询所有的分类
        List<Category> list=categoryDao.getAllCategory();
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid) {
        Category category = categoryDao.getOneCategory(cid);
        return category;
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public List<Category> searchlist(String cname) {
        List<Category> searchlist=categoryDao.searchlist(cname);
        return searchlist;
    }


}
