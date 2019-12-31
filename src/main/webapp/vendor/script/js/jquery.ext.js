/**
 * Created by Administrator on 2016/1/18.
 */
/*扩展jquery 请求*/
var responseHandler = function (data, successFun) {
    if (data) {
        with (data) {
            switch (responseStatus) {
                case 'success':
                    if (successFun) {
                        successFun(responseBody)
                    }
                    break;
                case 'failure':
                    if (responseBody && responseBody === 'system.error.login.undefined') {
                        window.top.location.href = ctx;
                    } else {
                        throw new Error(responseMessage);
                    }
                    break;
                default :
                    throw new Error(responseMessage);
                    break;
            }
        }
    } else {
        throw new Error('未知错误.');
    }
};
jQuery.extend({
    //ajax
    request: function (url, type, param, successFun, errorFun) {
        type = (type || "post").toLowerCase();
        var REQUEST_TYPES = ['get', 'post', 'delete', 'put'];
        for (var i = 0; i < REQUEST_TYPES.length; i++) {
            if (type == REQUEST_TYPES[i]) {
                if (i > 1) {
                    param._method = REQUEST_TYPES[i];
                    type = REQUEST_TYPES[1];
                }
            }
        }
        $.ajax({
            type: type,
            url: url,
            data: param,
            dataType: "JSON",
            cache: false,
            success: function (data) {
                try {
                    responseHandler(data, successFun);
                } catch (e) {
                    alert("错误信息!" + e.message);
                    if (errorFun) {
                        errorFun(e.message);
                    }
                }
            },
            error: function (err) {
                //$.messager.loading.hide();
                alert("错误信息!" + err.responseText || err.message);
            }
        });
    },
//form submit
    submit: function (formId, url, successFun) {
        //
        var _form = $(formId);
        var pk = _form[0].id.value;
        if (pk) {
            //截取URL后的post param,添加_method
            var param = null;
            var split = url.split('?');
            url = split[0];
            if (split.length > 1) {
                param = split[1];
            }
            url += '/' + pk + '?_method=put' + (param ? ('&' + param) : '');
        }
        _form.form('submit', {
            url: url,
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;
            },
            success: function (data) {
                data = eval('(' + data + ')');
                try {
                    responseHandler(data, successFun);
                } catch (e) {
                    alert("错误信息!" + err.message);
                }
            },
            error: function (err) {
                alert("错误信息!" + err.responseText || err.message);
            }
        });
    }
});