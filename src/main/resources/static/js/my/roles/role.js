function initRoles() {
	$.ajax({
		type : 'get',
		url : '/roles/all',
		async : false,
		success : function(data) {
			var r = $("#roles");

			for (var i = 0; i < data.length; i++) {
				var d = data[i];
				var id = d['id'];
				var name = d['name'];

				var t = "<label><input type='checkbox' value='" + id + "'>"
						+ name + "</label> &nbsp&nbsp";

				r.append(t);
			}
		}
	});
}

function getCheckedRoleIds() {
	var ids = [];
	$("#roles input[type='checkbox']").each(function() {
		if ($(this).prop("checked")) {
			ids.push($(this).val());
		}
	});

	return ids;
}

function initRoleDatas(userId) {
	$.ajax({
		type : 'get',
		url : '/roles?userId=' + userId,
		success : function(data) {
			var length = data.length;
			for (var i = 0; i < length; i++) {
				$("input[type='checkbox']").each(function() {
					var v = $(this).val();
					if (v == data[i]['id']) {
						$(this).attr("checked", true);
					}
				});
			}
		}
	});
}

function showRoleSelect(id, type, all) {
    var data = getRole(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined && all) {
        select.append("<option value=''>请选择</option>");
    }

    $.each(data, function(id, name) {
        select.append("<option value ='" + id + "'>" + name + "</option>");
    });

    return data;
}

function getRole(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/roles/all',  //?type=' + type,
            async : false,
            success : function(data) {
                console.log(data);
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.name;
                });

                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}

function getRoleDatas(userid){
    $.ajax({
        type : 'get',
        url : '/roles?userId='+userid,
        async : false,
        success : function(data) {
			var roleids = [];
			for (var i in data){
				roleids.push(data[i].id)
			}
			formSelects.value("roles",roleids);
        }
    })
}