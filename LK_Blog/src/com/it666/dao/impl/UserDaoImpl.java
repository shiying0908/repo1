package com.it666.dao.impl;

import com.it666.dao.UserDao;
import com.it666.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public User getUser(String username, String password) {
        System.out.println("dao"+username+password);
        //到数据库中查询,使用qbc形式,看不到sql语句，全是以面向对象形式来操作的
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);//设置从哪个表开始查，括号中写入User类的字节码
        detachedCriteria.add(Restrictions.eq("username",username));//添加条件
        detachedCriteria.add(Restrictions.eq("password",password));//添加条件

        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);//  此处应该传入一个条件：查询哪个表，并且在查表时，是否有什么条件;返回值集合会自动封装成一个User对象
        System.out.println(list);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
