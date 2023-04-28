import "./myTodo.css"
import play from '../assets/play.svg'
import plus from '../assets/plus.svg'
import trash from '../assets/trash.svg'
import pause from '../assets/pause.svg'
import {check, getCookie} from "./check";
import {addTodo, addTodoList, delTodo, delTodoList, getTodo, getTodoList, getUser, upTodo, upTodoList} from "./api";
import {useEffect, useRef, useState} from "react";
import {GetTodo} from "./GetTodo";
import {GetTodoList} from "./GetTodoList";

function MyTodo() {
    // const inputElement = useRef()
    const [todo, setTodo] = useState([])
    const [todoChange, setTodoChange] = useState('')
    const [select, setSelect] = useState('')
    const [todoList, setTodoList] = useState([])
    const [todoListChange, setTodoListChange] = useState('')

    function logout() {
        document.cookie = 'token=;domain=localhost;path=/'
        window.location.reload()
    }

    // function LoadTodo() {
    //     return todo.map((hasil, i) => {
    //         if (hasil.id === select.id) {
    //             return <li key={i}>
    //                 <img onClick={() => SelectTodo(hasil)} className='mytodo-select selected' src={pause}/>
    //                 <input onBlur={(e) => EditTodo(hasil.id, e.target.value)} defaultValue={hasil.title}/>
    //                 <img onClick={(e) => DeleteTodo(hasil.id, e)} className="mytodo-trash" src={trash}/>
    //             </li>
    //         } else {
    //             return <li key={i}>
    //                 <img onClick={() => SelectTodo(hasil)} className='mytodo-select' src={pause}/>
    //                 <input onBlur={(e) => EditTodo(hasil.id, e.target.value)} defaultValue={hasil.title}/>
    //                 <img onClick={(e) => DeleteTodo(hasil.id, e)} className="mytodo-trash" src={trash}/>
    //             </li>
    //         }
    //
    //     })
    // }
    //
    // function NewTodo() {
    //     addTodo().then(value => setMsg(value.data.id + value.status))
    // }
    //
    // function EditTodo(id, title) {
    //     upTodo(id, title).then(value => setMsg(value + id + title))
    // }
    //
    // function DeleteTodo(id, e) {
    //
    //     if (select.id === id) {
    //         e.target.parentElement.style.display = 'none'
    //         delTodo(id).then(setSelect(''))
    //
    //     } else {
    //         e.target.parentElement.style.display = 'none'
    //         delTodo(id)
    //     }
    //
    // }
    //
    // function SelectTodo(data) {
    //     if (data.id === select.id) {
    //         setSelect('')
    //
    //
    //     } else {
    //         setSelect(data)
    //
    //     }
    //     setMsg(data.id)
    // }
    //
    // function LoadTodoList() {
    //     return todoList.map((hasil, i) => {
    //         if (!hasil.isComplete) {
    //             return <li key={i}>
    //                 <span onClick={(e) => DoneTodoList(e, hasil)}></span>
    //                 <input readOnly className='mytodo-list-input' defaultValue={hasil.task}/>
    //                 <img onClick={(e) => DeleteTodoList(hasil ,e,i)} className='mytodo-del' src={trash}/>
    //             </li>
    //         } else {
    //             return <li key={i}>
    //                 <span onClick={(e) => DoneTodoList(e, hasil)} className='select'></span>
    //                 <input readOnly className='mytodo-list-input input-selct' defaultValue={hasil.task}/>
    //                 <img onClick={(e) => DeleteTodoList(hasil, e,i)} className='mytodo-del' src={trash}/>
    //             </li>
    //         }
    //     })
    // }
    //
    // function NewTodoList(e) {
    //     if (e.key === 'Enter') {
    //         addTodoList(e.target.value, select.id).then(value => {
    //             setSelect(value.data.todo)
    //             e.target.value = ''
    //         })
    //     }
    //
    // }
    //
    // function DoneTodoList(e, data) {
    //     if (e.target.className === '') {
    //         e.target.classList.add('select');
    //         e.target.parentNode.childNodes[1].classList.add('input-selct')
    //         upTodoList(data.task, select.id, data.id, true)
    //     } else {
    //         e.target.classList.remove('select')
    //         e.target.parentNode.childNodes[1].classList.remove('input-selct')
    //         upTodoList(data.task, select.id, data.id, false)
    //     }
    // }
    //
    // function DeleteTodoList(data, e,i) {
    //     e.target.parentElement.style.display = 'none'
    //     e.target.parentElement.style.pointer = 'none'
    //     delTodoList(data.id)
    // }
    //
    // useEffect(() => {
    //     getTodo().then(value => setTodo(valued))
    // }, [msg])
    //
    // useEffect(() => {
    //     if (select.id !== undefined) {
    //         getTodoList(select.id).then(value => setTodoList(value))
    //     }else{
    //         setTodoList([])
    //     }
    // }, [select])
    function LoadTodo() {
        return todo.map((hasil, i) => {
            return <li key={i}>
                <img onClick={() => SelectTodo(hasil)}
                     className={hasil.id === select.id ? 'mytodo-select selected' : 'mytodo-select'} src={pause}/>
                <input onBlur={(e) => ReEditTodo(e)} onChange={(e) => EditTodo(hasil, e)} defaultValue={hasil.title}/>
                <img onClick={(e) => DeleteTodo(hasil.id, e)} className="mytodo-trash" src={trash}/>
            </li>
        })
    }

    function NewTodo() {
        addTodo().then(getTodo().then(value => setTodoChange(value.length)))
    }

    function EditTodo(data, e) {
        upTodo(data.id, e.target.value)

        // const hasil = await upTodo(data.id, e.target.value)
        // console.log(hasil)
    }

    function ReEditTodo(e) {
        setTodoChange(e.target.value)
    }

    function DeleteTodo(id, es) {
        // select.id === id && setSelect('')
        if (select.id === id) {
            delTodo(id).then(value => {
                setSelect('')
                setTodoListChange(value.length)
                getTodo().then(value => setTodoChange(value.length))
            })
        } else {
            delTodo(id).then(getTodo().then(value => setTodoChange(value.length)))
        }
    }

    function SelectTodo(data) {

        if (select.id === data.id) {

            getTodoList(data.id).then(value => {
                setSelect('')
                getTodo().then(value => setTodoChange(value.id))
                setTodoListChange(value)
            })
        } else {

            getTodoList(data.id).then(value => {
                setSelect(data)
                getTodo().then(value => setTodoChange(value.id))
                setTodoListChange(value)
            })
        }
    }

    function LoadTodoList() {
        return todoList.map((hasil, i) => {
            return <li key={i}>
                <span onClick={(e) => DoneTodoList(e, hasil)} className={!hasil.isComplete ? '' : 'select'}></span>
                <input readOnly className={!hasil.isComplete ? 'mytodo-list-input' : 'mytodo-list-input input-selct'}
                       defaultValue={hasil.task}/>
                <img onClick={(e) => DeleteTodoList(hasil, e, i)} className='mytodo-del' src={trash}/>
            </li>
        })
    }

    function NewTodoList(e) {
        if (e.key === 'Enter') {
            addTodoList(e.target.value, select.id).then(value => {
                getTodoList(select.id).then(value => setTodoListChange(value))
                e.target.value = ''
            })
        }
    }

    function DoneTodoList(e, data) {
        if (e.target.className === '') {
            // e.target.classList.add('select');
            // e.target.parentNode.childNodes[1].classList.add('input-selct')
            upTodoList(data.task, select.id, data.id, true).then(getTodoList(select.id).then(value => setTodoListChange(value)))
        } else {
            // e.target.classList.remove('select')
            // e.target.parentNode.childNodes[1].classList.remove('input-selct')
            upTodoList(data.task, select.id, data.id, false).then(getTodoList(select.id).then(value => setTodoListChange(value)))
        }
    }

    function DeleteTodoList(data, e, i) {
        delTodoList(data.id).then(getTodoList(select.id).then(value => setTodoListChange(value)))
    }

    useEffect(() => {
        getTodo().then(value => setTodo(value))
    }, [todoChange])

    useEffect(() => {
        if (select.id !== undefined) {
            getTodoList(select.id).then(value => setTodoList(value))
        } else {
            setTodoList([])
        }
    }, [todoListChange])

    useEffect(() => {
        check()
    }, [document.cookie])


    return (<div className='all'>
        <div className="mytodo-logout">
            <div onClick={logout} className="mytodo-btn-logout">LOG OUT</div>
            <div className="mytodo-account"></div>
        </div>

        <div className="mytodo-view">
            <h1 className="mytodo-judul">myToDo.</h1>

            <div className="mytodo-conti modee">
                <div className="mytodo-todo ">
                    <ul>
                        <LoadTodo/>
                    </ul>
                </div>

                <img onClick={NewTodo} src={plus} className="mytodo-add"/>
            </div>

            <div className="mytodo-container mode">

                <nav className="mytodo-navbar">
                    <img className="mytodo-play" src={play}/>
                    <h1>{select.title}</h1>
                </nav>

                <div className="mytodo-list">
                    <ul>
                        <LoadTodoList/>
                    </ul>
                </div>
            </div>


            <div className="mytodo-cont">

                <input onKeyDown={NewTodoList} type="text" className="mytodo-do" placeholder="Do Something..."/>
            </div>

        </div>
    </div>)
}

export default MyTodo