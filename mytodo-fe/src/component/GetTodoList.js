import {useEffect, useState} from "react";
import {getTodoList} from "./api";

export function GetTodoList(id) {
    const [todoList, setTodoList] = useState([]);

    useEffect(() => {
        getTodoList(id).then(data => {
            setTodoList(data)})
    },[])
    return todoList
}