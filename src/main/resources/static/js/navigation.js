layui.use('element', function() {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    // element.on('nav(demo)', function(elem) {
    //     layer.msg(elem.text());
    // });
});

// $("#page_pcheader").load("pcheader.html");
$("#foot").load("/foot.html");
var m1 = getUrlParam("m1");
if(m1==""){
    m1="1";
}
var m2 = getUrlParam("m2");

var mid = getUrlParam("mid");

$(function() {
    $('#dl-menu').dlmenu();
});
initMenu();
function initMenu(){
    $.ajax({
        url:"/permissions/current",
        type:"get",
        async:false,
        success:function(data){
            if(!$.isArray(data)){
                location.href='/login.html';
                return;
            }
            var menutop = $("#menutop");
            $.each(data, function(i,item){
                var href = item.href;
                if(href.indexOf("?")>0){
                    href += "&m1=" + item.id;
                }else{
                    href += "?m1=" + item.id;
                }
                var a = $("<a href='"+href +"'></a>");

                var css = item.css;
                if(css!=null && css!=""){
                    a.append("<i aria-hidden='true' class='fa " + css +"'></i>");
                }
                a.append("<cite> "+item.name+"</cite>");

                var li = $("<li class='layui-nav-item'></li>");
                if (item.id == m1) {
                    li.addClass("layui-this");
                }
                li.append(a);
                menutop.append(li);

                //多级菜单
                if (item.id == m1) {
                    setMenuLeft(li, item.child)
                }
            });

            m_initMenu(data);
        }
    });
}

function setMenuLeft(parentElement, child){
    var menuleft = $("#menuleft");

    if(child != null && child.length > 0){
        $.each(child, function(j,item2){
            var href = item2.href;
            if(href.indexOf("?")>0){
                href += "&m1=" + m1 + "&m2=" + item2.id;
            }else{
                href += "?m1=" + m1 + "&m2=" + item2.id;
            }

            if(item2.href == ""){
                var a = $("<a href='javascript:;'></a>");
            }else{
                var a = $("<a href='"+href +"'></a>");
            }

            var css = item2.css;
            if(css!=null && css!=""){
                a.append("<i aria-hidden='true' class='fa " + css +"'></i>");
            }
            a.append("<cite> "+item2.name+"</cite>");

            var li = $("<li class='layui-nav-item'></li>");
            if (item2.id == m2) {
                li.addClass("layui-nav-itemed");
            }
            li.append(a);
            menuleft.append(li);

            // 递归
            setChild(li, item2.child);
        });
    }
}

function setChild(parentElement, child){
    if(child != null && child.length > 0){
        $.each(child, function(j,item2){
            var href = item2.href;
            if(href.indexOf("?")>0){
                href += "&m1=" + m1 + "&m2=" + item2.parentId + "&mid=" + item2.id;
            }else{
                href += "?m1=" + m1 + "&m2=" + item2.parentId + "&mid=" + item2.id;
            }
            var ca = $("<a href='"+href+"'></a>");

            var css2 = item2.css;
            if(css2!=null && css2!=""){
                ca.append("<i aria-hidden='true' class='fa " + css2 +"'></i>");
            }

            ca.append("<cite>"+item2.name+"</cite>");

            var dd = $("<dd></dd>");
            dd.append(ca);

            var dl = $("<dl class='layui-nav-child'></dl>");

            if (item2.id == mid) {
                dd.addClass("layui-this");
            }
            dl.append(dd);

            parentElement.append(dl);

            // 递归
            setChild(dd, item2.child);
        });
    }
}


// 登陆用户头像昵称
showLoginInfo();
function showLoginInfo(){
    $.ajax({
        type : 'get',
        url : '/users/current',
        async : false,
        success : function(data) {
            $("#nickname").text(data.nickname);
            $("#m_nickname").text(data.nickname);
        }
    });
}

showUnreadNotice();
function showUnreadNotice(){
    $.ajax({
        type : 'get',
        url : '/notices/count-unread',
        success : function(data) {
            $("[unreadNotice]").each(function(){
                if(data>0){
                    $(this).html("<span class='layui-badge'>"+data+"</span>");
                }else{
                    $(this).html("");
                }
            });

        }
    });
}

function logout(){
    $.ajax({
        type : 'get',
        url : '/logout',
        success : function(data) {
            localStorage.removeItem("token");
            location.href='/login.html';
        }
    });
}


function m_initMenu(data){
    var m_menu = $("#m_menu");
    $.each(data, function(i,item){
        var a = $("<a href='"+item.href+"'></a>");

        var css = item.css;
        if(css!=null && css!=""){
            a.append("<i aria-hidden='true' class='fa " + css +"'></i>");
        }
        a.append("<cite>"+item.name+"</cite>");
        var li = $("<li></li>");
        li.append(a);
        m_menu.append(li);

        //多级菜单
        m_setChild(li, item.child)

    });
}

function m_setChild(parentElement, child){
    if(child != null && child.length > 0){
        var ul = $("<ul class='dl-submenu'></ul>");
        $.each(child, function(j,item2){


            var a = $("<a href='"+item2.href+"'></a>");

            var css = item2.css;
            if(css!=null && css!=""){
                a.append("<i aria-hidden='true' class='fa " + css +"'></i>");
            }
            a.append("<cite>"+item2.name+"</cite>");
            var li = $("<li></li>");
            li.append(a);
            m_setChild(li, item2.child);// 递归
            ul.append(li);
        });
        parentElement.append(ul);
    }
}