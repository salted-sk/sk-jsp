<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title> </title>
    <!-- 引入公共js+css -->
    <jsp:include page="/WEB-INF/common/include/public.jsp"></jsp:include>

</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row">
        <div class="col-sm-12">

            <div class="ibox">
                <div class="ibox-title">
                    <h5>问卷方案列表</h5>
                    <div class="ibox-tools">
                        <a onclick="add();" class="btn btn-primary btn-xs" permission="/admin/exam/saveAndUpdate">新建问卷方案</a>

                    </div>
                </div>
                <div class="ibox-content">
                    <form method="get" class="form-horizontal" name="store-list-form" id="examForm"
                          action="${ctx}/admin/exam/list">
                        <div class="form-group">
                            <div class="col-md-8">
                                <input type="text" class="form-control" placeholder="请输入名称、编号" name="name"
                                       value="${exam.name}"/>
                            </div>
                            <span>
                                <button data-toggle="" class="btn btn-primary" style="margin-left: 15px;"
                                        type="button" onclick="init();">搜索</button>
                            </span>
                        </div>
                    </form>

                    <table id="examTables"
                           class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th style="width: 5%;">序号</th>
                            <th>编号</th>
                            <th>名称</th>
                            <th>问卷</th>
                            <th>评分标准</th>
                            <th>学年</th>
                            <th>学期</th>
                            <th>状态</th>
                            <th style="width: 15%;">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 添加问卷方案 -->
<div class="modal inmodal fade" id="addModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content col-md-11 modal-new-dialog">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"></span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="modal-title">添加问卷方案</h4>
            </div>
            <div class="ibox float-e-margins modal-body">
                <div class="ibox-content" style="margin-bottom:0;">
                    <form class="form-horizontal m-t" id="addForm" method="post">
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active modal-content-wrap">
                                <input type="hidden" name="id" value="">
                                <input type="hidden" name="purpose" value="">
                                <div class="form-group col-sm-12">
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;编号：</label>
                                    <div class="col-sm-4">
                                        <input name="code" maxlength="100" class="form-control" type="text" required="required"
                                               readonly
                                               placeholder="自动生成"
                                               aria-required="true"
                                               aria-invalid="false"
                                               onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-a-z-A-Z]+/,'');}).call(this)"
                                               onblur="this.v();">
                                    </div>
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;名称：</label>
                                    <div class="col-sm-4">
                                        <input name="name" maxlength="50" class="form-control" type="text" required="required">
                                    </div>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;问卷：</label>
                                    <div class="col-sm-4">
                                        <select name="paperId" class="form-control m-b">

                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;评分标准：</label>
                                    <div class="col-sm-4">
                                        <select name="standardId" class="form-control m-b">

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;学年：</label>
                                    <div class="col-sm-4">
                                        <select name="studyYearId" class="form-control m-b">

                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;学期：</label>
                                    <div class="col-sm-4">
                                        <select name="xq" class="form-control m-b">
                                            <option value="1">上学期</option>
                                            <option value="2">下学期</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label class="col-sm-2 control-label">描述：</label>
                                    <div class="col-sm-10">
                                        <textarea maxlength="2000" class="form-control message-input" name="desp"></textarea>
                                    </div>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label class="col-sm-2 control-label"><span class="required" style="color: #ff0000">*</span>&nbsp;状态：</label>
                                    <div class="col-sm-4">
                                        <select name="status" class="form-control m-b">
                                            <option value="1">启用</option>
                                            <option value="2">禁用</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="modal-footer modal-footer-bar">
                <button id="entityBtnAdd" class="bk-margin-5 btn btn-info">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    $(function () {
        //保存
        $("#entityBtnAdd").click(function () {
            var form = $("#addForm");
            var name = form.find("input[name='name']").val();
            var paperId = form.find("select[name='paperId']").val();
            var standardId = form.find("select[name='standardId']").val();
            var studyYearId = form.find("select[name='studyYearId']").val();
            form.find("input[name='purpose']").val("1");
            if (name == null || name == '' || name.trim() == '') {
                layer.msg("名称不能为空", {time: 1000});
                return;
            }
            if (paperId == null || paperId == '' || paperId.trim() == '') {
                layer.msg("请选择问卷", {time: 1000});
                return;
            }
            if (standardId == null || standardId == '' || standardId.trim() == '') {
                layer.msg("请选择评分标准", {time: 1000});
                return;
            }if (studyYearId == null || studyYearId == '' || studyYearId.trim() == '') {
                layer.msg("学年不能为空", {time: 1000});
                return;
            }
            else {
                $("#entityBtnAdd").attr("disabled", true);
            }

            var data = $("#addForm").serializeFormJSON();
            $.request("${ctx}/admin/exam/saveAndUpdate", "post", data, function (data) {
                if (data == '1') {
                    layer.msg("保存成功", {time: 1000},function(){
                        $('#addModal').modal('hide');
                        init();
                    });
                } else {
                    layer.msg("保存失败", {time: 1000});
                }

            });
        })

        init();

    });

    init = function () {
        tables();
    }

    //查询列表
    tables = function () {
        $("#examTables").dataTable().fnDestroy();
        var form = $("#examForm");
        var name = form.find("input[name='name']").val();
        return $("#examTables").dataTable({
            processing: true,
            serverSide: true,
            lengthChange: true,//是否允许用户改变表格每页显示的记录数
            ordering: false,//是否允许用户排序
            paging: true,//是否分页
            pagingType: "full_numbers",//除首页、上一页、下一页、末页四个按钮还有页数按钮
            searching: false,//是否开始本地搜索
            stateSave: false,//刷新时是否保存状态
            autoWidth: false,//自动计算宽度
            "ajax": {
                "url": "${ctx}/admin/exam/getList",
                "type": "POST",
                data: function (exam) {
                    exam.name = name;
                    exam.purpose = "1";
                }
            },
            //列表表头字段
            columns: [
                {"data": null, "targets": 0},
                {"data": "code"},
                {"data": "name"},
                {"data": "paperName"},
                {"data": "standardName"},
                {"data": "xnName"},
                {"name": "status", render: function (data, type, row) {
                        if (row.xq == 1) {
                            return '上学期';
                        } else {
                            return '下学期';
                        }
                    }
                },
                {"name": "status", render: function (data, type, row) {
                        if (row.status == 1) {
                            return '启用';
                        } else {
                            return '禁用';
                        }
                    }
                },
                {
                    "name": "opt", render: function (data, type, row) {
                        var btn = '<a title="修改" permission="/admin/exam/saveAndUpdate" onclick="edit(\''+row.id+'\');" class="btn btn-warning btn-xs" style="margin-left:5px;">修改</a> ';
                        btn += '<a title="删除" permission="/admin/exam/delById" onclick="del(\''+row.id+'\');" class="btn btn-danger btn-xs" style="margin-left:5px;">删除</a> ';
                        return btn;
                    }
                }
            ],
            fnDrawCallback: function () {
                // 权限处理
                $.permission();
                var api = this.api();
                var startIndex = api.context[0]._iDisplayStart;//获取到本页开始的条数
                api.column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = startIndex + i + 1;
                });
            }
        });
    }

    //添加弹出
    add = function () {
        qingkong();
        //问卷
        formSelect("paperId", "paper/getSelectList", {status:1,purpose:1})
        //学年
        formSelect("studyYearId", "bms/studyYear/getList", {})
        //评分标准
        formSelect("standardId", "standard/getSelectList", {status:1,purpose:1})
        $("#modal-title").html("新建问卷方案");
        $('#addModal').modal('show');
        $("#entityBtnAdd").attr("disabled", false);
    }

    //修改弹出
    edit = function (id) {
        qingkong();
        $(".btn-xs").attr("disabled", true);
        getById(id ,function () {
            $("#modal-title").html("修改问卷方案");
            $('#addModal').modal('show');
            $("#entityBtnAdd").attr("disabled", false);
            $(".btn-xs").attr("disabled", false);
        })
    }

    getById = function (id,successFun) {
        $.request("${ctx}/admin/exam/getById", "post", {id: id}, function (data) {
            var addForm = $('#addModal').find('#addForm');
            addForm.find("input[name='id']").val(id);
            //赋值
            addForm.find("input[name='name']").val(data.name);
            addForm.find("input[name='code']").val(data.code);
            //问卷
            formSelect("paperId", "paper/getSelectList", {status:1},data.paperId)
            //学年
            formSelect("studyYearId", "bms/studyYear/getList", {},data.studyYearId )
            //评分标准
            formSelect("standardId", "standard/getSelectList", {status:1},data.standardId)
            addForm.find("select[name='status']").val(data.status);
            addForm.find("textarea[name='desp']").val(data.desp);
            addForm.find("select[name='xq']").val(data.xq);

            oldLogText = $("#addForm").logText(addForm);
            successFun();
        })
    }

    //删除
    del = function (id) {
        layer.confirm('确定删除？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.request("${ctx}/admin/exam/delById", "post", {id: id}, function (data) {
                if (data == '1') {
                    layer.msg("删除成功", {time: 1000},function(){
                        $('#addModal').modal('hide');
                        init();
                    });
                } else {
                    layer.msg("删除失败", {time: 1000});
                }
            });
        });
    }

    //下拉
    formSelect = function (selectName, urlName, data, selectedVal) {
        var form = $('#addModal').find('#addForm');
        var option = form.find("select[name='"+selectName+"']");
        option.html("");
        option.append("<option value=''>请选择</option>");
        $.request("${ctx}/admin/"+urlName, "post", data, function (data) {
            option.html("");
            option.append("<option value=''>请选择</option>");
            $.each(data, function (index, item) {
                if (selectedVal == item.id) {
                    option.append("<option value='" + item.id + "' selected=selected>" + item.name + "</option>");
                } else {
                    option.append("<option value='" + item.id + "'>" + item.name + "</option>");
                }
            })
        });
    }

    //清除form表单
    qingkong = function () {
        $("#addForm input,textarea").each(function(){
            $(this).val('');
        });
        $("#addForm select").each(function(){
            $(this).find("option:selected").attr("selected", false);
            $(this).find("option").first().attr("selected", true);
        });
    }
</script>
</html>