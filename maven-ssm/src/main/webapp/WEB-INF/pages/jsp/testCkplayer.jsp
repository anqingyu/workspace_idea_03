<%--
  Created by IntelliJ IDEA.
  User: xiefu
  Date: 2019/7/5
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src=" ${pageContext.request.contextPath}/ckplayer/ckplayer.js"></script>
<div class="video" style="width: 1000px;height: 600px;"></div>
<script type="text/javascript">
    var videoObject = {
        container: '.video',//“#”代表容器的ID，“.”或“”代表容器的class
        variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
        poster:'http://192.168.25.129/group1/M00/00/00/wKgZgV0SHQWAFHQtAALLbiENMkw717.jpg',//封面图片
        video:'http://192.168.25.129/group1/M00/00/00/wKgZgV0LmuqAKiC9AAvGhAkN5gk968.mp4'//视频地址
        <%--video:'${pageContext.request.contextPath}/vedio/08aab014c4f427a749ed77a5965b590a.mp4'//视频地址--%>
        <%--video:'${pageContext.request.contextPath}/ckplayer/vedioDownload.do'//视频地址--%>
    };
    var player=new ckplayer(videoObject);
</script>
</body>
</html>
