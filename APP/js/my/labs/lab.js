function initLabs() {
	$.ajax({
		type : 'get',
		url : '/labs/listall',
		async : false,
		success : function(data) {
			var r = $("#labs");

			for (var i = 0; i < data.length; i++) {
				var d = data[i];
				var id = d['id'];
				var labname = d['labname'];

				var t = "<label><input type='checkbox' value='" + id + "'>"
						+ labname + "</label> &nbsp&nbsp";

				r.append(t);
			}
		}
	});
}

function getCheckedLabIds() {
	var ids = [];
	$("#labs input[type='checkbox']").each(function() {
		if ($(this).prop("checked")) {
			ids.push($(this).val());
		}
	});

	return ids;
}

function initLabDatas(userId) {
	$.ajax({
		type : 'get',
		url : '/labs?userId=' + userId,
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