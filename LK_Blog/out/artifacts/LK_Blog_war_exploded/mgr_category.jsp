<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="js/pageStyle.css">
    <script src="js/jquery.min.js"></script>
    <style>
        #modal_content2{
            padding: 30px 20px;
            width: 400px;
            height: 200px;
            background: white;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -100px;
            display: none;
        }
        #close2{
            position: absolute;
            right: 10px;
            top: 10px;
        }
    </style>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类</button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn" >搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>cid</li>
        <li>parentid</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>

    <%--迭代的取数据--%>
    <s:iterator value="categorylist" var="category"><%--"categorylist"是CategoryAction里定义的值栈集合名，"category"是遍历出来每个元素的名字--%>
        <ul class="list_goods_ul">
            <li><s:property value="#category.cid"/> </li>
            <li><s:property value="#category.parentid"/> </li><%--使用ogal方式来展示每个属性--%>
            <li><s:property value="#category.cname"/></li>
            <li><a href="#" class="updatebtn" data-id="<s:property value="#category.cid"/>"><img class="img_icon" src="images/edit_icon.png" alt=""></a></li>
            <li><a href="${pageContext.request.contextPath}/category_delete.action?cid=<s:property value="#category.cid"/>" class=""><img class="img_icon" src="images/delete_icon.png" alt=""></a></li>
        </ul>
    </s:iterator>


</div>

<div id="modal_view">


</div>

<div id="modal_content">
    <div id="close"><img src="images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>parentid:</span>
                <input type="text" class="am-form-field" id="parentid">&nbsp;&nbsp;<br/>
                <span>分类名称:</span>
                <input type="text" class="am-form-field" id="cname">&nbsp;&nbsp;<br/>&nbsp;&nbsp;
                <button class="am-btn am-btn-default" type="button" id="addcategory">添加</button>
             </div>
        </div>
    </div>
</div>
<div id="modal_content2" style="height: 250px; display: none">
    <div id="close2"><img src="images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span>修改分类：</span>
            </div>
        </div>
        <div class="item1" >
            <div>
                <span>parentid：</span>
                <input type="text" class="am-form-field" id="parentid2" >&nbsp;&nbsp;
                <br/>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" id="cname2" >&nbsp;&nbsp;
                <br/>
                <input type="hidden" id="cid2">
                <button class="am-btn am-btn-default" type="button" id="updatebtn">修改</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });
        /*监听添加按钮的点击事件*/
        $("#addcategory").click(function () {
            /*获取文本框的内容*/
            var parentid = $("#parentid").val();
            var cname = $("#cname").val();
            alert(parentid+cname);
            /*发送请求*/
            $(window).attr('location','${pageContext.request.contextPath}/category_add.action?parentid='+parentid+'&cname='+cname);
        });

        /*监听修改按钮的点击事件*/
        $(".updatebtn").click(function () {
            /*1.获取绑定的id*/
            var cid = $(this).data("id");
              /*取出对应的id的数据，到数据库中查询当前记录
              * 返回给页面
              * 展示到当前页面中
              * 在发送请求时，不需要做页面的跳转，使用到Ajax，可以取到当前返回的数据，展示到当前页面中
              * */
              /*2.发送异步请求*/
             $.post("${pageContext.request.contextPath}/category_updateUI.action",{"cid":cid},function (data) {
                 console.log(data);
                 /*把json数据展示到文本框中*/
                 $("#parentid2").val(data[0].parentid);
                 $("#cname2").val(data[0].cname);
                 $("#cid2").val(data[0].cid);
             },"json");
            /*2.弹出修改页面*/
            $("#modal_view").fadeIn();
            $("#modal_content2").fadeIn();
        })
        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content2").fadeOut();
        });

        $("#updatebtn").click(function () {
            /*获取文本框的值*/
            var parentid2 = $("#parentid2").val();
            var cname2 = $("#cname2").val();
            var cid2 = $("#cid2").val();
            $(window).attr('location','${pageContext.request.contextPath}/category_update.action?parentid='+parentid2+'&cname='+cname2+'&cid='+cid2);
        })

        $("#input_search_btn").click(function () {
            var cname = $("#input_search").val();
            $(window).attr('location','${pageContext.request.contextPath}/category_searchlist.action?cname='+cname);
            <%--$.post("${pageContext.request.contextPath}/category_searchlist.action",{"cname":cname},function (data) {--%>
            <%--  /*  console.log(data);*/--%>
            <%--},"json");--%>
        })
    });
</script>
</body>
</html>