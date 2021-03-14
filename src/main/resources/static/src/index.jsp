<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta content="no-cache" http-equiv="Cache-Control"> <!-- PROD: remove -->

    <meta charset="UTF-8" content="text/html; charset=UTF-8" http-equiv="CONTENT-TYPE"/>
    <link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c&display=swap" rel="stylesheet">
    <script defer rel="script" src="https://code.jquery.com/jquery-3.5.1.min.js" type="application/javascript"></script>
    <!-- PROD: may be slim ver. will be better -->
    <title>TextQuest</title>
</head>
<body>
<noscript style="color: black">Для работы сайта включите JavaScript</noscript>

<div id="root"></div>

</body>


<script id="initialScript">
    let btnHref = [(${btnHref})]
    let isAuthorised = [(${isAuthorised})]
    document.querySelector("noscript").remove()
    document.getElementById("initialScript").remove()
</script>

</html>