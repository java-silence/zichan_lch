function showVenSelect(id, type, all) {
	var data = getVendor(type);
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

function getVendor(type) {
	var v = sessionStorage[type];
	console.log('v' + v);
	// if (v == null || v == "") {
		$.ajax({
			type : 'get',
			url : '/vendors/listctype',
			async : false,
			success : function(data) {
				v = {};
				$.each(data, function(i, d) {
                    v[d.id] = d.cname;
				});
				sessionStorage[type] = JSON.stringify(v);
			}
		});
	return JSON.parse(sessionStorage[type]);
}
