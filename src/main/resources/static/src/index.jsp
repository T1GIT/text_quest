<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8" content="text/html; charset=UTF-8" http-equiv="CONTENT-TYPE"/>
    <link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap" rel="stylesheet">
    <script defer rel="script" src="https://code.jquery.com/jquery-3.5.1.min.js" type="application/javascript"></script>
    <!-- PROD: may be slim ver. will be better -->
    <title>TextQuest</title>
</head>
<body>
<noscript style="color: black; font-size: 30px !important;">Для работы сайта включите JavaScript</noscript>

<div id="preloader"
     style="font-family: sans-serif !important; color: gray; text-align: center; font-size: 30px !important;"> Загрузка,
    пожалуйста, подождите
</div>
<div id="root"></div>

</body>


<script id="initialScript">
    let isAuthorised = [(${isAuthorised})]
    document.querySelector("noscript").remove()
    document.getElementById("initialScript").remove()
</script>

</html>