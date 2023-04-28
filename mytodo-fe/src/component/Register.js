import './register.css'
import {signUp} from "./api";
import {useState} from "react";
// import {useHref} from "react-router-dom";


function Register() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confPassword, setConfPassword] = useState('');

    // let username = '';
    // let email = '';
    // let password = '';
    // let confPassword = '';

    function check(){
        let cek = username === '' ? 'username required' : true && email === '' ? 'email required' : true && password === '' ? 'password required' : true && confPassword === '' ? "confirm password required" : true
        return cek
    }
    async function click(){
        const eror = document.querySelector('.register-container h2')
        const emails = document.querySelector('.register-email')

        if(check() === true){
                if (password === confPassword){
                    console.log('lesgo')



                    await signUp(username,email,password)
                        .then(value => value.json())
                        .then(values => {
                            if (values.status === 500){
                                eror.innerHTML = values.message;
                                emails.value = ''
                            }else {
                                eror.innerHTML = `User: ${email} created`
                                window.location.href = '/login'

                                document.querySelector('.login-email').value = email;
                            }

                        })

                }else {
                    eror.innerHTML = 'password must be same'
                }
        }else {
                eror.innerHTML = check();
                // console.log(check())
        }
    }

    return (
        <div className="register-view">
            <h1 className="register-judul">myToDo.</h1>


            <div className="register-container">
                <h1>Register</h1>
                <input onChange={(e) => {
                    setUsername(e.target.value)}}
                       type="text" className="register-username" placeholder="Username"/>

                <input onChange={(e) => {
                    setEmail(e.target.value)}}
                       type="text" className="register-email" placeholder="Email"/>

                <input onChange={(e) => {
                    setPassword(e.target.value)}}
                       type="password" className="register-password" placeholder="Password"/>

                <input onChange={(e) => {
                    setConfPassword(e.target.value)}}
                       type="password" className="register-confpassword" placeholder="Confirm Password"/>

                <button onClick={click} type="submit">Sign in</button>
                <h2></h2>
            </div>

            <div className='back' onClick={() => window.location.href = '/login'}>
                <h1>Login Here</h1>
            </div>
        </div>
    )
}

export default Register