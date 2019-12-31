function controlAll() {
    var ckBoxObj = document.getElementById("ctrlBox");
    var Boxes = document.getElementsByName("checkBoxName");
    for (var i = 0; i < Boxes.length; i++)
        Boxes[i].checked = ckBoxObj.checked;
    document.getElementById("SelectedButton").disabled = !ckBoxObj.checked;
}

function controlItem() {
    var count = 0;
    var ckBoxObj = document.getElementById("ctrlBox");
    var Boxes = document.getElementsByName("checkBoxName");
    for (var i = 0; i < Boxes.length; i++)
        if (Boxes[i].checked) count++;
    if (count == Boxes.length) ckBoxObj.checked = true;
    if (count != Boxes.length) ckBoxObj.checked = false;
    document.getElementById("SelectedButton").disabled = !count;
}

function operate(url) {
    var str = "";
    var Boxes = document.getElementsByName("checkBoxName");
    if (url.indexOf("deleteAll") != -1 || url.indexOf("DeleteAll") != -1) {
        for (var i = 0; i < Boxes.length; i++)
            if (Boxes[i].checked) str += (str == "") ? "'" + Boxes[i].value + "'" : ",'" + Boxes[i].value + "'";
    }
    else if (url.indexOf("setCJ") != -1) {
        for (var i = 0; i < Boxes.length; i++)
            if (Boxes[i].checked) str += (str == "") ? "'" + Boxes[i].value + "'" : ",'" + Boxes[i].value + "'";
    }
    else if (url.indexOf("doCancelCJ") != -1) {
        for (var i = 0; i < Boxes.length; i++)
            if (Boxes[i].checked) str += (str == "") ? "'" + Boxes[i].value + "'" : ",'" + Boxes[i].value + "'";
    }
    else {
        for (var i = 0; i < Boxes.length; i++)
            if (Boxes[i].checked) str += (str == "") ? Boxes[i].value : "," + Boxes[i].value;
    }

    if (str != null && str != "") {
        //	window.alert("操作成功");
        if (url.indexOf("blank") != -1) {
            window.open(url + "&ids=" + str);
        } else {
            window.location.href = url + "&ids=" + str;
        }
    } else {
        window.alert("没有选中的数据");
    }
}

function HasCheckd() {
    var str = "";
    var Boxes = document.getElementsByName("checkBoxName");
    for (var i = 0; i < Boxes.length; i++) {
        if (Boxes[i].checked) str += (str == "") ? Boxes[i].value : "," + Boxes[i].value;
    }
    if (str == null || str == "") {
        window.alert("没有选中的数据");
        SelectedButton.disabled = disabled;
        return false;
    }
    return true;
}

function uncheckAll() {
    var ckBoxObj = document.getElementById("ctrlBox");
    var Boxes = document.getElementsByName("checkBoxName");
    for (i = 0; i < Boxes.length; i++) {
        if (Boxes[i].checked == false) {
            ckBoxObj.checked = false;
        }
    }
}

function nextpage(url) {
    var str = url;
    var Boxes = document.getElementsByName("checkBoxName");
    if (url.indexOf("deleteAll") != -1) {
        for (var i = 0; i < Boxes.length; i++)
            if (Boxes[i].checked) str += (str.indexOf(",") >= 0) ? "'" + Boxes[i].value + "'" : ",'" + Boxes[i].value + "'";
    } else {
        for (var i = 0; i < Boxes.length; i++)
            if (Boxes[i].checked && str.indexOf(Boxes[i].value) < 0) str += (str == "") ? Boxes[i].value : "," + Boxes[i].value;
    }
    window.location.href = str;
}

function isDelete() {
    if (window.confirm("确定要删除吗？")) {
        return true;
    } else {
        return false;
    }
}