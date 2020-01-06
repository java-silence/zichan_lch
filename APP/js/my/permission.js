function checkPermission() {
	
	var pers = [];
	$.ajax({
		type : 'get',
		url : server_url+'/permissions/owns',
		contentType : "application/json; charset=utf-8",
		headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
		async : false,
		success : function(data) {
			
			pers = data;
			$("[permission]").each(function() {
				var per = $(this).attr("permission");
				if ($.inArray(per, data) < 0) {
					$(this).hide();
				}
			});
		}
	});
	
	return pers;
}