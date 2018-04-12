<!DOCTYPE html>
<html>
<head>
    <#assign path=springMacroRequestContext.getContextPath() />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<#--告诉浏览器响应屏幕宽度-->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${path}/css/bootstrap.css" type="text/css">
   <#-- <link rel="stylesheet" href="/css/bootstrap-theme.css" type="text/css">-->
    <link rel="stylesheet" href="${path}/css/dataTables.bootstrap.css" type="text/css">
    <link rel="stylesheet" href=" https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css" type="text/css">
    <script type="text/javascript" src="${path}/js/jquery.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${path}/js/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js"></script>



        <title>看看看看看用户</title>
</head>
<body>
    <input type="button" class="btn-primary" value="批量删除" onclick="deleteAll()"/>
    <input type="button" class="btn-primary" value="导出" onclick="exportOh()"/>
    <table  class="table table-striped table-bordered table-hover table-condensed" id="dutyListTable" style="text-align: center" align="center">
        <thead>
        <tr>
            <th style="text-align: center"><input type="checkbox" id="checkAll" onclick="checkAll()"></th>
            <th style="text-align: center">序号</th>
            <th style="text-align: center">姓名</th>
            <th style="text-align: center">密码</th>
            <th style="text-align: center">操作</th>
        </tr>
        </thead>

    </table>
</body>
<script>
    var i=0;
    $(function () {
        showDataTable();
    });

    function showDataTable() {

        $("#dutyListTable").dataTable({
          /*  dom: 'Bfrtip',
            "buttons": [
                {
                    'extend': 'excel',
                    'text': '导出',
                    'className':'ml-15 btn btn-success',//按钮的class名称
                    'exportOptions': {
                        'modifier': {
                            'page': 'current'
                        },
                         'columns':'1,2,3',//导出的列从0开始，默认全部*!/
                        'format': { //用于导出将使用的单元格格式化函数的容器对象 format有三个子标签，header，body和foot
                            body: function (data, row, column, node) { //body区域的function，可以操作需要导出excel的数据格式
                                if (column ==0) {
                                    i=i+1;
                                    return i;
                                }
                                else {
                                    return data;
                                }
                            }
                        }
                    }
                }
            ],*/

            "destroy" : true,       //销毁表格对象
            "aLengthMenu":[5,10],  //用户可自选每页展示数量 5条或10条
            "searching":false,//禁用搜索（搜索框）
            "lengthChange":true,
            "paging": true,//开启表格分页
            "bProcessing" : true,
            "bServerSide" : true,
            "bAutoWidth" : false,
//                  "sort" : "position",
            "deferRender":true,//延迟渲染
//                  "bStateSave" : false, //在第三页刷新页面，会自动到第一页
//         "retrieve" : true,       //类似单例模式，重复利用以存在对象。
            "iDisplayLength" : 5,
            "iDisplayStart" : 0,

            "select": {
                style:    'os',
                selector: 'td:first-child'
            },
            "ordering": false,//全局禁用排序
            "ajax": {  //ajax方式向后台发送请求
                "type": "post",
                "url": "/user/showUser",
                "dataSrc": "data",
                "data": function (d) {
                    var param = {};
                    param.draw = d.draw;
                    param.start = d.start;
                    param.length = d.length;
                    var formData = $("#queryForm").serializeArray();//把form里面的数据序列化成数组
                    formData.forEach(function (e) {
                        param[e.name] = e.value;
                    });
                    return param;//自定义需要传递的参数。
                },
            },
            "columns" : [//对接收到的json格式数据进行处理，data为json中对应的key
                {"data":null},
                {"data":null},
                {"data" : "name"} ,
                {"data" : "password"},
                {"data":null},
            ],
            //渲染
            "columnDefs": [
                {
                    "render": function ( data, type, row ) {
                        return "<input type='checkbox' name='mycheck'  value='" + data.id + "'/>";
                    },
                    "targets": 0 //指定渲染列：第一列(渲染第一列为单选框，data自动匹配为  {"data":"id"}中数据）
                },
                {
                    "render": function ( data, type, row ) {
                        return "<button type='button' class='btn-primary'  onclick='deleteA("+data.id+")'>删除</button>";
                    },
                    "targets": 4
                },
            ],
            "oLanguage" : { // 国际化配置
                "sProcessing" : "正在获取数据，请稍后...",
                "sLengthMenu" : "显示 _MENU_ 条",
                "sZeroRecords" : "没有找到数据",
                "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                "sInfoEmpty" : "记录数为0",
                "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
                "sInfoPostFix" : "",
                "sSearch" : "查询",
                "sUrl" : "",
                "oPaginate" : {
                    "sFirst" : "第一页",
                    "sPrevious" : "上一页",
                    "sNext" : "下一页",
                    "sLast" : "最后一页"
                }
            },
            "fnDrawCallback"    : function(){
                this.api().column(1).nodes().each(function(cell, i) {
                    cell.innerHTML =  i + 1;
                });
            },
        });
    }

    function deleteA(id) {
        $.ajax({
            type:"POST",
            dataType:"text",
            url:"/user/deleteUser",
            data:{userId:id},
            success:function (data) {
                if(data=="1"){
                    alert("修改成功");
                    showDataTable();
                }else if(data=="2"){
                    alert("修改失败");
                }
            },
            error:function (data) {
                alert("请求失败");
            }
        });
    }

    function deleteAll() {
        var a=new Array();
        if($("input[name='mycheck']:checked").length==0){
            alert("请选择删除项");
            return;
        }
        $.each($("input[name='mycheck']:checked"),function (index,value) {
            a.push($(value).val());
        });
        $.ajax({
            type:"POST",
            dataType:"text",
            url:"/user/deleteAll",
            data:{abc:a.toString()},
           /* 传递数组用 traditional:true,*/
            success:function (data) {
                if(data=="1"){
                    alert("修改成功");
                    showDataTable();
                }else if(data=="2"){
                    alert("修改失败");
                }
            },
            error:function (data) {
                alert("请求失败");
            }
        });
    }

    function checkAll() {
        if($("#checkAll").prop("checked")){
            $("input[name='mycheck']").prop("checked",true);
        }else {
            $("input[name='mycheck']").prop("checked",false);
        }
    }

    function exportOh() {
        window.location.href="/user/export";
    }
</script>
</html>