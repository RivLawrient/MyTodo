import {addTodo, getTodo} from "./api";
import {useEffect, useState} from "react";

export function GetTodo(){
    const [todo, setTodo] = useState([])

    useEffect(() => {
        getTodo().then(value => setTodo(value));
    }, [])

    return todo.map((hasil, i) =>{
        return <li key={i}><img className='mytodo-select selected' /><input   defaultValue={hasil.title}/><img className="mytodo-trash"/></li>})
}
