<%--
  Created by IntelliJ IDEA.
  User: J丶晨星
  Date: 2018/11/16
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码</title>
</head>
<body>
<p><img src="/captcha.png" onclick="changeImg(this)"></p>
<p><input type="text" id="captcha" onblur="checkCaptcha()"><span id="captchaMsg"></span></p>
<p id="loading" style="display:none;">正在加载，请稍后...</p>
<script>
    function changeImg(img) {
        img.src = "/captcha.png?t="+new Date().getTime();
    }
    function checkCaptcha() {
        var n = document.getElementById("captcha").value;
        var xhr = new XMLHttpRequest();
        xhr.onload = function () {
            document.getElementById("loading").style.display="none";
            var span = document.getElementById("captchaMsg");
            var obj = JSON.parse(xhr.responseText);
            if(obj.result){
                span.innerText="验证码正确";
            } else {
                span.innerText="验证码错误";
            }
        };
        xhr.open("get","/result?captcha="+n, true);
        xhr.send();
        document.getElementById("loading").style.display="block";
    }
</script>
</body>
</html>
