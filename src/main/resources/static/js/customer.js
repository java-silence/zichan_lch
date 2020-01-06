function showCusSelect(id, type, all) {
	// console.log(id);
	var data = getCustomer(type);
	var select = $("#" + id);
	select.empty();

	if (all != undefined && all) {
		select.append("<option value=''>请选择</option>");
	}

	$.each(data, function(k, v) {
		select.append("<option value ='" + k + "'>" + v + "</option>");
	});

	return data;
}

function getCustomer(type) {
	var v = sessionStorage[type];
	// if (v == null || v == "") {
		$.ajax({
			type : 'get',
			url : '/customers/listall',
			async : false,
			success : function(data) {
				v = {};
				$.each(data, function(i, d) {
                    v[d.id] = d.cname;
				});

				sessionStorage[type] = JSON.stringify(v);
			}
		});
	// }

	return JSON.parse(sessionStorage[type]);
}
