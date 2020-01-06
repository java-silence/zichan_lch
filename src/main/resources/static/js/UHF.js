var ajaxtimeout = 10000;
var apiServerUrl="http://localhost:8085";

function openCom() {
    var url = apiServerUrl + "/api/openCom";
    var resultData= QingQiu(url,{linkconfig: "RS232",rs232com: "COM9",rs232BaudRate: 115200})
    if (resultData.code=="501"){
        layer.alert("资产关联器连接失败，请检查资产关联器是否连接!");
        return false;
    }
}

function GetEpcID() {
    var url = apiServerUrl + "/api/readTag";

   var resultData= QingQiu(url,{
        cotWordAdd: 02,
            comWordCnt: 1,
            comAryPassWord: "00 00 00 00",
            linkconfig: "RS232",
            comMemBank: 1,
            ipMemBank: 0,
            memBank: 1,
            wordAdd: 02,
            wordCnt: 1,
            aryPassWord: "00 00 00 00"
    });
   if (resultData!=null && resultData.code=="16"){
    return  resultData.operateTagBuffer.lsTagList[0].strEPC.replace(/\s*/g, "");
   }else{
       if (resultData.code="501"){
           layer.alert(resultData.message);
           return false;
       }
       if (resultData.code="205") {
           layer.alert(resultData.strLogger + ",请重新挪动标签位置重新操作一次");
           return false;
       }
   }
}

function closeCom() {
    var url = apiServerUrl + "/api/closeCom";
    var data= {linkconfig: "RS232",rs232BaudRate: 115200};
    $.ajax({
        async: false,
        url: url,
        data: data,
        type: "POST",
        timeout: ajaxtimeout,
        dataType: "JSON",
    });
}
function QingQiu(url,data) {
    var result;

    $.ajax({
        async: false,
        url: url,
        data: data,
        type: "POST",
        timeout: ajaxtimeout,
        dataType: "JSON",
        success: function (data) {
             result = data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(XMLHttpRequest.status==0){
                layer.confirm('未检测到入库关联器程序,请检查关联器程序是否已经打开', {
                    btn: ['重试','关闭'] //按钮
                }, function(){
                    openCom();
                }, function(){
                    layer.closeAll();
                  // closeCom();
                });
            }
        }
    });
    return result;
}
