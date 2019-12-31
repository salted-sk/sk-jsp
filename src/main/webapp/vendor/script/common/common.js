/**
 * 统一处理form表单数据
 *
 * @param isDate 默认不处理时间
 */
(function ($) {
    $.fn.serializeFormJSON = function (isDate) {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            var name = this.name;
            var value = this.value;
            if ((name.toLocaleLowerCase ().indexOf("time") >-1 || name.toLocaleLowerCase ().indexOf("date")>-1) && isDate ) {
                if (value != null && value != '' && value.trim() != '') {
                    value = new Date(value);
                } else {
                    value = new Date();
                }
            }
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(value || '');
            } else {
                o[this.name] = value || '';
            }
        });
        return o;
    };
})(jQuery);

/**
 * 统一对后台时间格式处理
 *
 * @param longDate
 * @param fmt
 * @returns {*}
 */
function dateFormatShow(longDate,fmt) {
    if(typeof(longDate) == undefined ||longDate==null ||longDate==""){
        return "";
    }
    return new Date(longDate).format(fmt);
}
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 统一处理ajax请求
 */
var pendingRequests = {};
//配置全局变量默认防止ajax重复提交
var isAbort = true;
jQuery.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    var key = options.url;
    if (!pendingRequests[key]) {
        pendingRequests[key] = jqXHR;
    } else {
        if (isAbort) {
            //jqXHR.abort(); //放弃后触发的提交
            pendingRequests[key].abort(); // 放弃先触发的提交
        }
    }
    var complete = options.complete;
    options.complete = function (jqXHR, textStatus) {
        pendingRequests[key] = null;
        if (jQuery.isFunction(complete)) {
            complete.apply(this, arguments);
        }
    }
    //全局的ajax访问，处理ajax清求时sesion超时
    $.ajaxSetup({
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        complete : function(XMLHttpRequest, textStatus) {
            var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
            if (sessionstatus == "timeout") {
                parent.location.reload();
            }
        }
    });//session失效后跳转
}); //防止ajax重复提交




