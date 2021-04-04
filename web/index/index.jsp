<!DOCTYPE html>
<html lang="ru" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8" content="text/html; charset=UTF-8" http-equiv="CONTENT-TYPE"/>
    <script defer rel="script" src="https://code.jquery.com/jquery-3.5.1.min.js" type="application/javascript"></script>
    <!-- PROD: may be slim ver. will be better -->
    <title>TextQuest</title>
</head>
<body>
<noscript style="color: black; font-size: 30px !important;">Для работы сайта включите JavaScript</noscript>

<div id="initialPreloader"
     style="position: absolute; top: 0; left: 0; width: 100%; height: min-content; padding-top: 30px; background: linear-gradient(180deg, rgb(101,191,156) 0%, rgba(162,237,255,0) 100%); display: flex; flex-direction: column; align-items: center">
    <style>#preloader {
        --background: linear-gradient(135deg, #135058, #F1F2B5);
        --shadow: rgba(39, 94, 254, 0.28);
        --page: rgba(255, 255, 255, 0.36);
        --page-fold: rgba(255, 255, 255, 0.52);
        --duration: 3s;
        width: 200px;
        height: 140px;
        position: relative
    }

    #preloader:before, #preloader:after {
        --r: -6deg;
        content: "";
        position: absolute;
        bottom: 8px;
        width: 120px;
        top: 80%;
        box-shadow: 0 16px 12px var(--shadow);
        transform: rotate(var(--r))
    }

    #preloader:before {
        left: 4px
    }

    #preloader:after {
        --r: 6deg;
        right: 4px
    }

    #preloader div {
        width: 100%;
        height: 100%;
        border-radius: 13px;
        position: relative;
        z-index: 1;
        perspective: 600px;
        box-shadow: 0 4px 6px var(--shadow);
        background-image: var(--background)
    }

    #preloader div ul {
        margin: 0;
        padding: 0;
        list-style: none;
        position: relative
    }

    #preloader div ul li {
        --r: 180deg;
        --o: 0;
        --c: var(--page);
        position: absolute;
        top: 10px;
        left: 10px;
        transform-origin: 100% 50%;
        color: var(--c);
        opacity: var(--o);
        transform: rotateY(var(--r));
        -webkit-animation: var(--duration) ease infinite;
        animation: var(--duration) ease infinite
    }

    #preloader div ul li:nth-child(2) {
        --c: var(--page-fold);
        -webkit-animation-name: page-2;
        animation-name: page-2
    }

    #preloader div ul li:nth-child(3) {
        --c: var(--page-fold);
        -webkit-animation-name: page-3;
        animation-name: page-3
    }

    #preloader div ul li:nth-child(4) {
        --c: var(--page-fold);
        -webkit-animation-name: page-4;
        animation-name: page-4
    }

    #preloader div ul li:nth-child(5) {
        --c: var(--page-fold);
        -webkit-animation-name: page-5;
        animation-name: page-5
    }

    #preloader div ul li svg {
        width: 90px;
        height: 120px;
        display: block
    }

    #preloader div ul li:first-child {
        --r: 0deg;
        --o: 1
    }

    #preloader div ul li:last-child {
        --o: 1
    }

    #preloader span {
        display: block;
        left: 0;
        right: 0;
        top: 100%;
        margin-top: 20px;
        text-align: center;
        color: var(--text)
    }

    @-webkit-keyframes page-2 {
        0% {
            transform: rotateY(180deg);
            opacity: 0
        }
        20% {
            opacity: 1
        }
        35%, 100% {
            opacity: 0
        }
        50%, 100% {
            transform: rotateY(0deg)
        }
    }

    @keyframes page-2 {
        0% {
            transform: rotateY(180deg);
            opacity: 0
        }
        20% {
            opacity: 1
        }
        35%, 100% {
            opacity: 0
        }
        50%, 100% {
            transform: rotateY(0deg)
        }
    }

    @-webkit-keyframes page-3 {
        15% {
            transform: rotateY(180deg);
            opacity: 0
        }
        35% {
            opacity: 1
        }
        50%, 100% {
            opacity: 0
        }
        65%, 100% {
            transform: rotateY(0deg)
        }
    }

    @keyframes page-3 {
        15% {
            transform: rotateY(180deg);
            opacity: 0
        }
        35% {
            opacity: 1
        }
        50%, 100% {
            opacity: 0
        }
        65%, 100% {
            transform: rotateY(0deg)
        }
    }

    @-webkit-keyframes page-4 {
        30% {
            transform: rotateY(180deg);
            opacity: 0
        }
        50% {
            opacity: 1
        }
        65%, 100% {
            opacity: 0
        }
        80%, 100% {
            transform: rotateY(0deg)
        }
    }

    @keyframes page-4 {
        30% {
            transform: rotateY(180deg);
            opacity: 0
        }
        50% {
            opacity: 1
        }
        65%, 100% {
            opacity: 0
        }
        80%, 100% {
            transform: rotateY(0deg)
        }
    }

    @-webkit-keyframes page-5 {
        45% {
            transform: rotateY(180deg);
            opacity: 0
        }
        65% {
            opacity: 1
        }
        80%, 100% {
            opacity: 0
        }
        95%, 100% {
            transform: rotateY(0deg)
        }
    }

    @keyframes page-5 {
        45% {
            transform: rotateY(180deg);
            opacity: 0
        }
        65% {
            opacity: 1
        }
        80%, 100% {
            opacity: 0
        }
        95%, 100% {
            transform: rotateY(0deg)
        }
    }</style>
    <div id="preloader">
        <div>
            <ul>
                <li>
                    <svg viewBox="0 0 90 120" fill="currentColor">
                        <path d="M90,0 L90,120 L11,120 C4.92486775,120 0,115.075132 0,109 L0,11 C0,4.92486775 4.92486775,0 11,0 L90,0 Z M71.5,81 L18.5,81 C17.1192881,81 16,82.1192881 16,83.5 C16,84.8254834 17.0315359,85.9100387 18.3356243,85.9946823 L18.5,86 L71.5,86 C72.8807119,86 74,84.8807119 74,83.5 C74,82.1745166 72.9684641,81.0899613 71.6643757,81.0053177 L71.5,81 Z M71.5,57 L18.5,57 C17.1192881,57 16,58.1192881 16,59.5 C16,60.8254834 17.0315359,61.9100387 18.3356243,61.9946823 L18.5,62 L71.5,62 C72.8807119,62 74,60.8807119 74,59.5 C74,58.1192881 72.8807119,57 71.5,57 Z M71.5,33 L18.5,33 C17.1192881,33 16,34.1192881 16,35.5 C16,36.8254834 17.0315359,37.9100387 18.3356243,37.9946823 L18.5,38 L71.5,38 C72.8807119,38 74,36.8807119 74,35.5 C74,34.1192881 72.8807119,33 71.5,33 Z"></path>
                    </svg>
                </li>
                <li>
                    <svg viewBox="0 0 90 120" fill="currentColor">
                        <path d="M90,0 L90,120 L11,120 C4.92486775,120 0,115.075132 0,109 L0,11 C0,4.92486775 4.92486775,0 11,0 L90,0 Z M71.5,81 L18.5,81 C17.1192881,81 16,82.1192881 16,83.5 C16,84.8254834 17.0315359,85.9100387 18.3356243,85.9946823 L18.5,86 L71.5,86 C72.8807119,86 74,84.8807119 74,83.5 C74,82.1745166 72.9684641,81.0899613 71.6643757,81.0053177 L71.5,81 Z M71.5,57 L18.5,57 C17.1192881,57 16,58.1192881 16,59.5 C16,60.8254834 17.0315359,61.9100387 18.3356243,61.9946823 L18.5,62 L71.5,62 C72.8807119,62 74,60.8807119 74,59.5 C74,58.1192881 72.8807119,57 71.5,57 Z M71.5,33 L18.5,33 C17.1192881,33 16,34.1192881 16,35.5 C16,36.8254834 17.0315359,37.9100387 18.3356243,37.9946823 L18.5,38 L71.5,38 C72.8807119,38 74,36.8807119 74,35.5 C74,34.1192881 72.8807119,33 71.5,33 Z"></path>
                    </svg>
                </li>
                <li>
                    <svg viewBox="0 0 90 120" fill="currentColor">
                        <path d="M90,0 L90,120 L11,120 C4.92486775,120 0,115.075132 0,109 L0,11 C0,4.92486775 4.92486775,0 11,0 L90,0 Z M71.5,81 L18.5,81 C17.1192881,81 16,82.1192881 16,83.5 C16,84.8254834 17.0315359,85.9100387 18.3356243,85.9946823 L18.5,86 L71.5,86 C72.8807119,86 74,84.8807119 74,83.5 C74,82.1745166 72.9684641,81.0899613 71.6643757,81.0053177 L71.5,81 Z M71.5,57 L18.5,57 C17.1192881,57 16,58.1192881 16,59.5 C16,60.8254834 17.0315359,61.9100387 18.3356243,61.9946823 L18.5,62 L71.5,62 C72.8807119,62 74,60.8807119 74,59.5 C74,58.1192881 72.8807119,57 71.5,57 Z M71.5,33 L18.5,33 C17.1192881,33 16,34.1192881 16,35.5 C16,36.8254834 17.0315359,37.9100387 18.3356243,37.9946823 L18.5,38 L71.5,38 C72.8807119,38 74,36.8807119 74,35.5 C74,34.1192881 72.8807119,33 71.5,33 Z"></path>
                    </svg>
                </li>
                <li>
                    <svg viewBox="0 0 90 120" fill="currentColor">
                        <path d="M90,0 L90,120 L11,120 C4.92486775,120 0,115.075132 0,109 L0,11 C0,4.92486775 4.92486775,0 11,0 L90,0 Z M71.5,81 L18.5,81 C17.1192881,81 16,82.1192881 16,83.5 C16,84.8254834 17.0315359,85.9100387 18.3356243,85.9946823 L18.5,86 L71.5,86 C72.8807119,86 74,84.8807119 74,83.5 C74,82.1745166 72.9684641,81.0899613 71.6643757,81.0053177 L71.5,81 Z M71.5,57 L18.5,57 C17.1192881,57 16,58.1192881 16,59.5 C16,60.8254834 17.0315359,61.9100387 18.3356243,61.9946823 L18.5,62 L71.5,62 C72.8807119,62 74,60.8807119 74,59.5 C74,58.1192881 72.8807119,57 71.5,57 Z M71.5,33 L18.5,33 C17.1192881,33 16,34.1192881 16,35.5 C16,36.8254834 17.0315359,37.9100387 18.3356243,37.9946823 L18.5,38 L71.5,38 C72.8807119,38 74,36.8807119 74,35.5 C74,34.1192881 72.8807119,33 71.5,33 Z"></path>
                    </svg>
                </li>
                <li>
                    <svg viewBox="0 0 90 120" fill="currentColor">
                        <path d="M90,0 L90,120 L11,120 C4.92486775,120 0,115.075132 0,109 L0,11 C0,4.92486775 4.92486775,0 11,0 L90,0 Z M71.5,81 L18.5,81 C17.1192881,81 16,82.1192881 16,83.5 C16,84.8254834 17.0315359,85.9100387 18.3356243,85.9946823 L18.5,86 L71.5,86 C72.8807119,86 74,84.8807119 74,83.5 C74,82.1745166 72.9684641,81.0899613 71.6643757,81.0053177 L71.5,81 Z M71.5,57 L18.5,57 C17.1192881,57 16,58.1192881 16,59.5 C16,60.8254834 17.0315359,61.9100387 18.3356243,61.9946823 L18.5,62 L71.5,62 C72.8807119,62 74,60.8807119 74,59.5 C74,58.1192881 72.8807119,57 71.5,57 Z M71.5,33 L18.5,33 C17.1192881,33 16,34.1192881 16,35.5 C16,36.8254834 17.0315359,37.9100387 18.3356243,37.9946823 L18.5,38 L71.5,38 C72.8807119,38 74,36.8807119 74,35.5 C74,34.1192881 72.8807119,33 71.5,33 Z"></path>
                    </svg>
                </li>
                <li>
                    <svg viewBox="0 0 90 120" fill="currentColor">
                        <path d="M90,0 L90,120 L11,120 C4.92486775,120 0,115.075132 0,109 L0,11 C0,4.92486775 4.92486775,0 11,0 L90,0 Z M71.5,81 L18.5,81 C17.1192881,81 16,82.1192881 16,83.5 C16,84.8254834 17.0315359,85.9100387 18.3356243,85.9946823 L18.5,86 L71.5,86 C72.8807119,86 74,84.8807119 74,83.5 C74,82.1745166 72.9684641,81.0899613 71.6643757,81.0053177 L71.5,81 Z M71.5,57 L18.5,57 C17.1192881,57 16,58.1192881 16,59.5 C16,60.8254834 17.0315359,61.9100387 18.3356243,61.9946823 L18.5,62 L71.5,62 C72.8807119,62 74,60.8807119 74,59.5 C74,58.1192881 72.8807119,57 71.5,57 Z M71.5,33 L18.5,33 C17.1192881,33 16,34.1192881 16,35.5 C16,36.8254834 17.0315359,37.9100387 18.3356243,37.9946823 L18.5,38 L71.5,38 C72.8807119,38 74,36.8807119 74,35.5 C74,34.1192881 72.8807119,33 71.5,33 Z"></path>
                    </svg>
                </li>
            </ul>
        </div>
    </div>
</div>

<div id="root"></div>

</body>

<script id="initialScript">
    let isAuthorised = [(${isAuthorised})]
    let error = [(${error})]
    document.querySelector("noscript").remove()
    document.getElementById("initialScript").remove()
</script>

</html>