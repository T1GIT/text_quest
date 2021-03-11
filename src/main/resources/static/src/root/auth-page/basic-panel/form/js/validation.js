function validateEmail(email) {
    let valid = false
    let hint = ""
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email.length > 320) {
        hint = "Не больше 320 символов"
    } else if (re.test(email.toLowerCase())) {
        valid = true
    } else {
        hint = "Некорректная почта"
    }
    return {valid: valid, hint: hint}
}


function validatePsw(psw) {
    let valid = false
    let hint = ""
    const char = /[a-zA-Zа-яА-Я]/.test(psw)
    const num = /[0-9]/.test(psw)
    const other = /\W/.test(psw)
    if (psw.length > 120) {
        hint = "Не больше 120 символов"
    } else if (psw.length < 8) {
        hint = "Слишком короткий пароль"
    } else if (!char) {
        hint = "Необходимы буквы"
    } else if (!num) {
        hint = "Необходимы цифры"
    } else {
        valid = true
        if (psw.length > 18 || (psw.length > 12 && other))
            hint = "Сильный пароль"
        else if (psw.length > 12 || other)
            hint = "Средний пароль"
        else
            hint = "Слабый пароль"
    }
    return {valid: valid, hint: hint}
}


export {validateEmail, validatePsw};