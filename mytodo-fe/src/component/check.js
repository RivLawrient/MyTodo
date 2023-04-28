import {getUser} from "./api";
export async function check() {


    // if (getCookie('token') !== null) {
    //     if (getCookie('token') !== '') {
    //         const user = await getUser(getCookie('token'))
    //             // .then(value => value.json())
    //             // .then(values => values)
    //
    //
    //
    //         if(user.data.authorizated){
    //             return true
    //         }else{
    //             return false
    //         }
    //     } else {
    //         // window.location.href = "login.html";
    //         return false
    //     }
    // } else {
    //     // window.location.href = "login.html";
    //     return false
    // }

    if (getCookie('token') !== null && getCookie('token') !== ''){
        const user = await getUser()

        if(user.data.authorizated){
            const akun = document.querySelector('.mytodo-account');
            akun.innerText = user.data.email;
            return true
        }else{
            window.location.href = '/login'
        }
    }else{
        window.location.href = '/login'
    }
}

export function getCookie(name) {
    var cookieArr = document.cookie.split(";");

    for (var i = 0; i < cookieArr.length; i++) {
        var cookiePair = cookieArr[i].split("=");

        if (name === cookiePair[0].trim()) {
            return decodeURIComponent(cookiePair[1]);
        }
    }

    return null;
}