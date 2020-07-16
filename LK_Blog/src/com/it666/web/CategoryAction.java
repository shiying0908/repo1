package com.it666.web;

import com.it666.domain.Category;
import com.it666.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;


@SuppressWarnings({"ALL", "AlibabaLowerCamelCaseVariableNaming"})
public class CategoryAction extends ActionSupport implements ModelDriven<Category> {
    private Category category=new Category();
    @Override
    public Category getModel() {
        return category;
    }
    /*注入service*/
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String add(){
        System.out.println(category);
        categoryService.save(category);
        return "listAction";
    }

    public String list(){
        System.out.println("list----");
        //调用业务层，查询所有分类
        List<Category> list = categoryService.getAllCategory();
        System.out.println(list);
        //把数据存到值栈当中
        ActionContext.getContext().getValueStack().set("categorylist",list);//categorylist是自己起的名字
        return "list";
    }

    public String updateUI() throws IOException {
        System.out.println("updateUI------------------------------");
        System.out.println(category.getCid());//自动地把传过来的cid封装到了category中，所以使用.getCid()来调用传来的cid
       //调用业务层
        Category category2 = categoryService.getOneCategory(category.getCid());
        System.out.println(category2);
        //把数据传给页面
        //以json形式响应给页面，json是一个数据格式
        JSONArray jsonArray = JSONArray.fromObject(category2, new JsonConfig());
        System.out.println(jsonArray);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");//响应的类型和字符集编码
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());//响应给浏览器
        return null;
    }

    public String update(){
        categoryService.update(category);
        return "listAction";
    }

    public String delete(){
        categoryService.delete(category);
        return "listAction";
    }

    public String searchlist(){
        List<Category> searchlist = categoryService.searchlist(category.getCname());
        System.out.println(searchlist);
        ActionContext.getContext().getValueStack().set("categorylist",searchlist);
        return "list";
    }
}
