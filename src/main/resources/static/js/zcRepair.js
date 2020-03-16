$("#printBt").click(function(){
    if (!zcRepairId){
        layer.msg('请选择报修单');
        return
    }

    var html = "";
    $.ajax({
        type : 'get',
        url : '/zcRepairs/' + zcRepairId ,
        async : false,
        success : function(data) {
            formatData(data);
            html += "<div class='zctitle'>垣曲农村商业银行报修申请单</div>"+
                "<table class='print-zctable'><tr><td style='border:none;text-align: left' colspan='6'>报修单号："+data.code+"</td>" +
                "<td style='border:none;text-align: right' colspan='5'>"+getDate()+"</td></tr>"+
                "<tr><td style='border-left: 1px solid #000;border-top: 1px solid #000;'>序号</td><td style='border-top: 1px solid #000;'>资产名称</td>" +
                "<td style='border-top: 1px solid #000;'>资产追溯码</td><td style='border-top: 1px solid #000;'>资产编码</td>" +
                "<td style='border-top: 1px solid #000;'>入账时间</td><td style='border-top: 1px solid #000;'>到期时间</td>" +
                "<td style='border-top: 1px solid #000;'>报修期限</td><td style='border-top: 1px solid #000;'>原值（万元）</td>" +
                "<td style='border-top: 1px solid #000;'>净值（万元）</td><td style='border-top: 1px solid #000;'>报修原因</td>" +
                "<td style='border-top: 1px solid #000;'>维修结果</td></tr>";
            $.ajax({
                type : 'get',
                url : "/zcRepairItems/listByZcReId?zcReId="+zcRepairId,
                async : false,
                success : function(datas) {

                    debugger
                    var allOri = 0;
                    var allNet = 0;
                    // var allImoney = 0;
                    for (var i=0;i<datas.data.length;i++){
                        var d = datas.data[i];
                        formatData(d);
                        var startUseTime = d.startUseTime?getSpecifyDate(d.startUseTime):'';
                        var laveTime = d.laveTime?getSpecifyDate(d.laveTime):'';
                        var qrStatus = "";
                        if (d.qrStatus == null){
                            qrStatus = ""
                        } else if(d.qrStatus == "1"){
                            qrStatus = "合格"
                        } else if (d.qrStatus == "0") {
                            qrStatus = "不合格"
                        }
                        // if (d.status != 4){
                        //     qrStatus = ""
                        // } else if(d.status == 4){
                        //     qrStatus = "合格"
                        // }
                        html += "<tr><td style='border-left: 1px solid #000;'>"+(i+1)+"</td><td>"+d.zcName+"</td><td>"+d.epcid+"</td><td>"+d.zcCodenum+"</td>" +
                            "<td>"+startUseTime+"</td><td>"+laveTime+"</td><td>"+d.warrantyperiod+"</td><td>"+parseFloat((d.originalValue))+"</td><td>"+parseFloat((d.netvalue))+"</td>" +
                            "<td>"+d.repairDes+"</td><td>"+qrStatus+"</td></tr>";
                        allOri = parseFloat((Number(d.originalValue) + Number(allOri)))
                        allNet = parseFloat((Number(d.netvalue) + Number(allNet)))
                        // allImoney = parseFloat((Number(imoney) + Number(allImoney)).toFixed(2))
                    }
                    html += "<tr><td style='border-left: 1px solid #000;'>合计：</td><td></td><td></td><td></td><td></td>" +
                        "<td></td><td></td><td>"+allOri+"</td><td>"+allNet+"</td><td></td><td></td></tr></table>";
                    html += "<div class='zctitle' style='margin-top:10px'>维修服务供应商列表</div>"+
                        "<table class='print-zctable'>"+
                        "<tr><td style='border-left: 1px solid #000;border-top: 1px solid #000;'>序号</td><td style='border-top: 1px solid #000;'>资产名称</td>" +
                        "<td style='border-top: 1px solid #000;'>资产追溯码</td><td style='border-top: 1px solid #000;'>资产编码</td>" +
                        "<td style='border-top: 1px solid #000;'>服务商名称</td>" +
                        "<td style='border-top: 1px solid #000;'>地址</td><td style='border-top: 1px solid #000;'>联系人</td>" +
                        "<td style='border-top: 1px solid #000;'>电话</td><td style='border-top: 1px solid #000;'>保修方式</td>" +
                        "<td style='border-top: 1px solid #000;'>送修地址</td></tr>";
                    for (var i=0;i<datas.data.length;i++){
                        var d = datas.data[i];
                        formatData(d);
                        html += "<tr><td style='border-left: 1px solid #000;'>"+(i+1)+"</td><td>"+d.zcName+"</td><td>"+d.epcid+"</td><td>"+d.zcCodenum+"</td><td>"+d.outCompany+"</td>" +
                            "<td>"+d.outAddress+"</td><td>"+d.outUsername+"</td><td>"+d.outPhone+"</td><td>"+d.repairMode+"</td>" +
                            "<td>"+d.repairAddress+"</td></tr>";
                    }

                    var map = {},
                        dest = [];
                    for(var i = 0; i < datas.data.length; i++){
                        var ai = datas.data[i];
                        formatData(ai);
                        if(!map[ai.glDeptId+ai.auditBy]){
                            dest.push({
                                id: ai.glDeptId+ai.auditBy,
                                glDeptName: ai.glDeptName,
                                auditor: ai.auditor,
                                auditTime: ai.auditTime
                            });
                            map[ai.glDeptId+ai.auditBy] = ai;
                        }
                    }
                    var createTime = data.createTime?data.createTime:'';
                    var confirmTime = data.confirmTime?data.confirmTime:'';
                    html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >使用部门：</span>"+data.deptname+"</td><td class='qianzi' colspan='3'><span class='songti ' >申请人：</span>"+data.nickname+"</td><td class='qianzi' colspan='2'><span class='songti ' >申请时间：</span>"+createTime+"</td><td align='left' class='qianzi' colspan='3'><span class='dayin4'>申请</span></td></tr>";
                    for (var i=0;i<dest.length;i++){
                        var auditTime = dest[i].auditTime?dest[i].auditTime:'';
                        html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >审批部门"+(i+1)+"：</span>"+dest[i].glDeptName+"</td><td class='qianzi' colspan='3'><span class='songti ' >审批人"+(i+1)+"：</span>"+dest[i].auditor+"</td><td class='qianzi' colspan='2'><span class='songti ' >审批时间"+(i+1)+"：</span>"+auditTime+"</td><td align='left' class='qianzi' colspan='3'><span class='dayin4'>"+qrStatus+"</span></td></tr>";
                    }
                    html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >确认部门：</span>"+data.confirmDeptname+"</td><td class='qianzi' colspan='3'><span class='songti ' >确认人：</span>"+data.confirmNickname+"</td><td class='qianzi' colspan='2'><span class='songti ' >确认时间：</span>"+confirmTime+"</td><td align='left' class='qianzi' colspan='3'><span class='dayin4'>已维修</span></td></tr>";
                    html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >验收部门：</span>"+data.confirmDeptname+"</td><td class='qianzi' colspan='3'><span class='songti ' >验收人：</span>"+data.confirmNickname+"</td><td class='qianzi' colspan='2'><span class='songti ' >验收时间：</span>"+confirmTime+"</td><td align='left' class='qianzi' colspan='3'><span class='dayin4'>已确认</span></td></tr>";
                }
            });
            html += "</table>"
        }
    });

    $("#test").print({
        //Use Global styles
        globalStyles : false,
        //Add link with attrbute media=print
        mediaPrint : false,
        //Print in a hidden iframe
        iframe : false,
        //Don't print this
        noPrintSelector : ".avoid-this",
        //Add this at top
        prepend : html,
        //Add this on bottom
        append : "",
        //Log to console when printing is done via a deffered callback
        deferred: $.Deferred().done(function() {}),
        stylesheet:'../../css/fahuoprint.css'
    });
});

$("#GlprintBt").click(function(){
    if (!zcRepairId){
        layer.msg('请选择报修单');
        return
    }

    var html = "";
    $.ajax({
        type : 'get',
        url : '/zcRepairs/' + zcRepairId ,
        async : false,
        success : function(data) {
            formatData(data);
            html += "<div class='zctitle'>垣曲农村商业银行报修申请单</div>"+
                "<table class='print-zctable'><tr><td style='border:none;text-align: left' colspan='6'>报修单号："+data.code+"</td>" +
                "<td style='border:none;text-align: right' colspan='5'>"+getDate()+"</td></tr>"+
                "<tr><td style='border-left: 1px solid #000;border-top: 1px solid #000;'>序号</td><td style='border-top: 1px solid #000;'>资产名称</td>" +
                "<td style='border-top: 1px solid #000;'>资产追溯码</td><td style='border-top: 1px solid #000;'>资产编码</td>" +
                "<td style='border-top: 1px solid #000;'>入账时间</td><td style='border-top: 1px solid #000;'>到期时间</td>" +
                "<td style='border-top: 1px solid #000;'>报修期限</td><td style='border-top: 1px solid #000;'>原值(元)</td>" +
                "<td style='border-top: 1px solid #000;'>净值(元)</td><td style='border-top: 1px solid #000;'>报修原因</td>" +
                "<td style='border-top: 1px solid #000;'>维修结果</td></tr>";
            $.ajax({
                type : 'get',
                url : "/zcRepairItems/listByZcReIdAndGldept?zcReId="+zcRepairId,
                async : false,
                success : function(datas) {
                    var allOri = 0;
                    var allNet = 0;
                    // var allImoney = 0;
                    debugger
                    for (var i=0;i<datas.data.length;i++){
                        var d = datas.data[i];
                        formatData(d);
                        var startUseTime = d.startUseTime?getSpecifyDate(d.startUseTime):'';
                        var laveTime = d.laveTime?getSpecifyDate(d.laveTime):'';
                        var qrStatus = "";
                        if (d.qrStatus == null){
                            qrStatus = ""
                        } else if(d.qrStatus == "1"){
                            qrStatus = "合格"
                        } else if (d.qrStatus == "0") {
                            qrStatus = "不合格"
                        }
                        console.log(qrStatus)
                        html += "<tr><td style='border-left: 1px solid #000;'>"+(i+1)+"</td><td>"+d.zcName+"</td><td>"+d.epcid+"</td><td>"+d.zcCodenum+"</td>" +
                            "<td>"+startUseTime+"</td><td>"+laveTime+"</td><td>"+d.warrantyperiod+"</td><td>"+parseFloat((d.originalValue))+"</td><td>"+parseFloat((d.netvalue))+"</td>" +
                            "<td>"+d.repairDes+"</td><td>"+qrStatus+"</td></tr>";
                        allOri = parseFloat((Number(d.originalValue) + Number(allOri)))
                        allNet = parseFloat((Number(d.netvalue) + Number(allNet)))
                        // allImoney = parseFloat((Number(imoney) + Number(allImoney)).toFixed(2))
                    }
                    html += "<tr><td style='border-left: 1px solid #000;'>合计：</td><td></td><td></td><td></td><td></td>" +
                        "<td></td><td></td><td>"+allOri+"</td><td>"+allNet+"</td><td></td><td></td></tr></table>";
                    html += "<div class='zctitle' style='margin-top:10px'>维修服务供应商列表</div>"+
                        "<table class='print-zctable'>"+
                        "<tr><td style='border-left: 1px solid #000;border-top: 1px solid #000;'>序号</td><td style='border-top: 1px solid #000;'>资产名称</td>" +
                        "<td style='border-top: 1px solid #000;'>资产追溯码</td><td style='border-top: 1px solid #000;'>资产编码</td>" +
                        "<td style='border-top: 1px solid #000;'>服务商名称</td>" +
                        "<td style='border-top: 1px solid #000;'>地址</td><td style='border-top: 1px solid #000;'>联系人</td>" +
                        "<td style='border-top: 1px solid #000;'>电话</td><td style='border-top: 1px solid #000;'>保修方式</td>" +
                        "<td style='border-top: 1px solid #000;'>送修地址</td></tr>";
                    for (var i=0;i<datas.data.length;i++){
                        var d = datas.data[i];
                        formatData(d);
                        html += "<tr><td style='border-left: 1px solid #000;'>"+(i+1)+"</td><td>"+d.zcName+"</td><td>"+d.epcid+"</td><td>"+d.zcCodenum+"</td><td>"+d.outCompany+"</td>" +
                            "<td>"+d.outAddress+"</td><td>"+d.outUsername+"</td><td>"+d.outPhone+"</td><td>"+d.repairMode+"</td>" +
                            "<td>"+d.repairAddress+"</td></tr>";
                    }

                    var map = {},
                        dest = [];
                    for(var i = 0; i < datas.data.length; i++){
                        var ai = datas.data[i];
                        formatData(ai);
                        if(!map[ai.glDeptId+ai.auditBy]){
                            dest.push({
                                id: ai.glDeptId+ai.auditBy,
                                glDeptName: ai.glDeptName,
                                auditor: ai.auditor,
                                auditTime: ai.auditTime
                            });
                            map[ai.glDeptId+ai.auditBy] = ai;
                        }
                    }
                    var createTime = data.createTime?data.createTime:'';
                    var confirmTime = data.confirmTime?data.confirmTime:'';
                    html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >使用部门：</span>"+data.deptname+"</td><td class='qianzi' colspan='3'><span class='songti ' >申请人：</span>"+data.nickname+"</td><td class='qianzi' colspan='2'><span class='songti ' >申请时间：</span>"+createTime+"</td><td align='left' class='qianzi' colspan='3'><span class='dayin4'>申请</span></td></tr>";
                    for (var i=0;i<dest.length;i++){
                        var auditTime = dest[i].auditTime?dest[i].auditTime:'';
                        html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >审批部门"+(i+1)+"：</span>"+dest[i].glDeptName+"</td><td class='qianzi' colspan='3'><span class='songti ' >审批人"+(i+1)+"：</span>"+dest[i].auditor+"</td><td class='qianzi' colspan='2'><span class='songti ' >审批时间"+(i+1)+"：</span>"+auditTime+"</td><td align='left' class='qianzi' colspan='3'><span class='dayin4'>已确认</span></td></tr>";
                    }
                    // html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >确认部门：</span>"+data.confirmDeptname+"</td><td class='qianzi' colspan='4'><span class='songti ' >确认人：</span>"+data.confirmNickname+"</td><td class='qianzi' colspan='3'><span class='songti ' >确认时间：</span>"+confirmTime+"</td></tr>";
                    // html += "<tr><td class='qianzi' colspan='3'><span class='songti ' >验收部门：</span>"+data.confirmDeptname+"</td><td class='qianzi' colspan='4'><span class='songti ' >验收人：</span>"+data.confirmNickname+"</td><td class='qianzi' colspan='3'><span class='songti ' >验收时间：</span>"+confirmTime+"</td></tr>";
                }
            });
            html += "</table>"
        }
    });

    $("#test").print({
        //Use Global styles
        globalStyles : false,
        //Add link with attrbute media=print
        mediaPrint : false,
        //Print in a hidden iframe
        iframe : false,
        //Don't print this
        noPrintSelector : ".avoid-this",
        //Add this at top
        prepend : html,
        //Add this on bottom
        append : "",
        //Log to console when printing is done via a deffered callback
        deferred: $.Deferred().done(function() {}),
        stylesheet:'../../css/fahuoprint.css'
    });
});