<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta content="no-cache" http-equiv="Cache-Control"> <!-- PROD: remove -->

    <meta charset="UTF-8" content="text/html; charset=UTF-8" http-equiv="CONTENT-TYPE"/>
    <script defer rel="script" src="https://code.jquery.com/jquery-3.5.1.min.js" type="application/javascript"></script>
    <!-- PROD: may be slim ver. will be better -->
    <title>TextQuest</title>
</head>
<body>
<noscript>Для работы сайта включите JavaScript</noscript>
<script id="initialScript">

    const btnHref = [(${btnHref})]
    const isAuthorised = [(${isAuthorised})]

    scriptNode = document.getElementById("initialScript")
    scriptNode.previousElementSibling.remove()
    scriptNode.remove()
</script>
<div id="root"></div>
</body>
</html>