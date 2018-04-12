<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/js/jquery.js"></script>
    <title>添加cookie缓存</title>
</head>
<body>
<div>
    <div>
        <h1>商品1</h1>
        <strong>10.00</strong>
        <i>1</i>
        <span onclick="addItem(1,'商品1',10.00,1)">点点点点我</span>
    </div>
    <div>
        <h1>商品2</h1>
        <strong>20.00</strong>
        <i>2</i>
        <span onclick="addItem(2,'商品2',20.00,2)">点点点点我</span>
    </div>
    <div>
        <h1>商品3</h1>
        <strong>30.00</strong>
        <i>3</i>
        <span onclick="addItem(3,'商品3',30.00,3)">点点点点我</span>
    </div>
</div>

</body>
<script type="text/javascript">
    function addItem(id,name,price,num) {
        $.ajax({
            type:"POST",
            dataType:"text",
            url:"/item/addItem",
            data:{id:id,name:name,price:price,num:num},
            success:function (data) {
                if(data=="1"){
                    alert("添加成功");
                }else if(data=="2"){
                    alert("添加失败");
                }
            },
            error:function (data) {
                alert("请求失败");
            }
        });
    }
</script>
</html>