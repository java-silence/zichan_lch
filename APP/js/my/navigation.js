/**
 * Created by Hezhilin on 2018/3/25 0025.
 */

function initMenu(id,pid){
    $.ajax({
        url:"/cms/cmsmenu?id=" + id,
        type:"get",
        async:false,
        success:function(data){
            // if(!$.isArray(data)){
            //     location.href='/login.html';
            //     return;
            // }
            //console.log(data);
            var menu = $("#navigation");
            $.each(data, function(i,item){
                var li ;
                if(item.id==pid) {
                    li = $("<li class='current-menu-item'></li>");
                }else{
                    li = $("<li></li>");
                }
                if(item.name=="新闻中心"){
                    li.append("<span style='color:red'>" + item.name + "</span>");
                }else{
                    li.append("<span>" + item.name + "</span>");
                }

                //二级菜单
                var child2 = item.child;
                if(child2 != null && child2.length > 0){
                    var dl = $("<ul></ul>");
                    $.each(child2, function(j,item2){
                        var ca = $("<a href='" + item2.href + "'>"+ item2.name +"</a>");
                        var dd = $("<li></li>");
                        dd.append(ca);
                        dl.append(dd);
                    });
                    li.append(dl);
                }
                menu.append(li);
            });
        }
    });
}

function initChildMenu(id,mid){
    $.ajax({
        url:"/cms/childmenu?id=" + id,
        type:"get",
        async:false,
        success:function(data){
            // if(!$.isArray(data)){
            //     location.href='/login.html';
            //     return;
            // }
            //console.log(data);
            var menu = $("#childmenu");
            $.each(data, function(i,item){
                var div = $("<div class='banner-wrapper'></div>");
                var ca ;
                if (item.id==mid){
                    ca = $("<a class='banner animate-onscroll' style='background:#63b2f5;' href='"+ item.href +"'></a>");
                    ca.append("<i class='icons icon-check'></i>")
                    ca.append("<h4 style='color:#fff'>" + item.name + "</h4><p></p>")
                }else{
                    ca = $("<a class='banner animate-onscroll' href='"+ item.href +"'></a>");
                    ca.append("<i class='icons icon-check'></i>")
                    ca.append("<h4>" + item.name + "</h4><p></p>")
                }

                div.append(ca);

                menu.append(div);
            });
        }
    });
}