import'./login.css'
import {addTodo, signIn} from "./api";
import {useState} from "react";
import {wait} from "@testing-library/user-event/dist/utils";
function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    async function click() {
        const hasil = await signIn(username, password)
            .then(value => value.json()
                .then(values => {
                    if (values.code === 200){
                        document.cookie = "token=" +btoa(username+":"+password)+";domain=localhost;path=/";
                        // setTimeout(window.location.href = "/", 10000)
                        window.location.href = "/"
                    }else {
                        document.querySelector('.login-password').value = '';
                        const pesan = document.querySelector('.login-container h2');
                        pesan.innerHTML = values.status;
                    }
                })
            )
        return hasil;
    }

    return(
        <div className='login-view'>
            <h1 className="login-judul">myToDo.</h1>

            <div className="login-container">
                <h1>Login</h1>
                <input onBlur={e => setUsername(e.target.value)} type="email" className="login-email" placeholder="Email"/>
                <input onBlur={e => setPassword(e.target.value)} type="password" className="login-password" placeholder="Password"/>
                <div>Don't have account create <a href='/register'>here</a></div>
                <button onClick={click} type="submit">Log In</button>
                <h2></h2>
            </div>
        </div>
    )
}

export default Login;