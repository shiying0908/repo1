package com.it666.dao.impl;

import com.it666.dao.CategoryDao;
import com.it666.domain.Category;
import com.it666.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
    @Override
    public void save(Category category) {
        System.out.println("ca来了++++++++");
        this.getHibernateTemplate().save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        //qbc查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        List<Category> list = (List<Category>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        //设置条件
        detachedCriteria.add(Restrictions.eq("cid",cid));

        List<Category> list = (List<Category>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }

    @Override
    public void delete(Category category) {
        this.getHibernateTemplate().delete(category);
    }

    @Override
    public List<Category> searchlist(String cname) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        //设置条件
        detachedCriteria.add(Restrictions.like("cname","%"+cname+"%"));
        return (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

}
