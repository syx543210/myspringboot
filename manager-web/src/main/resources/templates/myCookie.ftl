<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/js/jquery.js"></script>
    <title>cookie缓存</title>
</head>
<body>
<div id="itemList">

</div>

</body>
<script type="text/javascript">
    $(function () {
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"/item/showItem",
            success:function (data) {
               $("#itemList").html("");
               $.each(data,function (index,value) {
                   var $div=$("<div><h1>"+value.name+"</h1><strong>"+value.price+"</strong><i>"+value.num+"</i></div>");
                   $("#itemList").append($div);
               });
            },
            error:function (data) {
                alert("请求失败");
            }
        });
    });
</script>
</html>