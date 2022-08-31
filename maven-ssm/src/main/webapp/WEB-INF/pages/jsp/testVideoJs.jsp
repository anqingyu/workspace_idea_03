<%--
  Created by IntelliJ IDEA.
  User: xiefu
  Date: 2019/7/23
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/videojs/css/video-js.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/videojs/js/video.min.js"></script>
</head>
<body>
    <video id="example_video_1" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="none" width="640" height="264"
           poster="http://video-js.zencoder.com/oceans-clip.png"
           data-setup="{}">
        <source src="http://192.168.25.129/group1/M00/00/00/wKgZgV0LmuqAKiC9AAvGhAkN5gk968.mp4" type='video/mp4' />
        <%--<source src="http://视频地址格式2.webm" type='video/webm' />--%>
        <%--<source src="http://视频地址格式3.ogv" type='video/ogg' />--%>
        <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
        <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
    </video>
</body>
</html>