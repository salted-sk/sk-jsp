/**
 * 统一处理日志
 */
(function ($) {
    $.fn.logText = function (addForm) {
        var o = "(";
        var val = [];
        var len = addForm.find("[opername]").length;
        addForm.find("[opername]").each(function(i,d) {
            o += $(d).attr("opername");
            o += ":";
            var type = $(d)[0].type;
            if (type != null && type.indexOf("select") > -1) {
                o += $(d).find("option:selected").text();
            } else {
                o += d.value;
            }
            if (len != i+1) {
                o += ";";
            }
        })
        o += ")";
        return o;
    };
})(jQuery);

var oldLogText;

saveTypeLog = function (operName, addForm) {
    //添加日志
    var log = addForm.logText(addForm);
    var idExist = addForm.find("input[name='id']").val();
    var operType = 1;//日志类型 1增加 2修改
    if (idExist) {
        operName = "修改" + operName;
        operType = 2
        log = "修改前:" + oldLogText + "修改为:" + log;
    } else {
        operName = "新增" + operName;
    }
    $.request("../../admin/sso/operLog/save", "post", {operName:operName, operType: operType,operContent:log}, function (data) {
        if (data == '1') {
            layer.msg("保存成功", {time: 1000},function(){
                $('#addModal').modal('hide');
                init();
            });
        } else {
            layer.msg("系统异常", {time: 1000});
        }
    })
}

delTypeLog = function (operName) {
    operName = "删除" + operName;
    var operType = 3;
    oldLogText = "删除前:" + oldLogText;
    $.request("../../admin/sso/operLog/save", "post", {operName:operName, operType: operType, operContent:oldLogText}, function (data) {
        if (data == '1') {
            layer.msg("删除成功", {time: 1000},function(){
                init();
            });
        } else {
            layer.msg("系统异常", {time: 1000});
        }
    })
}





