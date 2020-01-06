/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
//根据流程ID提取该流程所有步骤
function showStepSelect(id, type, all) {
    var data = getStep(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
    }
    $.each(data, function(stepid, stepname) {
        select.append("<option value ='" + stepid + "'>" + stepname + "</option>");
    });
    return data;
}

function getStep(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/flowsteps/liststeps?flowid=' + type,
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.stepid] = d.stepname;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}