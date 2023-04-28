import {getCookie} from "./check";

export function signIn(email, password) {
    const data = {
        url: `http://localhost:8080/auth/signin`,
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic "+ btoa(email + ":" + password),
        }
    }
    return fetch(data.url, {
        mode: data.mode,
        method: data.method,
        headers: data.headers}
    );
}

export function signUp(username, email, password) {
    const data = {
        url: `http://localhost:8080/auth/signup`,
        method: 'POST',
        body: JSON.stringify({
            email: email,
            name: username,
            password: password
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            // 'Authorization': "Basic "+ btoa(email + ":" + password)
        }
    }
    return fetch(data.url, {
        method: data.method,
        body: data.body,
        headers: data.headers
    })
}

export function getUser() {
    const data = {
        url: 'http://localhost:8080/auth/signin',
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}

export function getTodo() {
    const data = {
        url: `http://localhost:8080/api/todo`,
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value.data)
}

export function addTodo() {
    const data = {
        url: `http://localhost:8080/api/todo`,
        method: 'POST',
        body: JSON.stringify({
            title: 'New Task'
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        body: data.body,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}

export function delTodo(getId){
    const data = {
        url: `http://localhost:8080/api/todo/${getId}`,
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}

export function upTodo(getId, title){
    const data = {
        url: `http://localhost:8080/api/todo/${getId}`,
        method: 'PUT',
        body: JSON.stringify({
            title: title
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        body: data.body,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}

export function getTodoList(getId){
    const data = {
        url: `http://localhost:8080/api/todolist/${getId}`,
        method: 'GET',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value.data)
}

export function addTodoList(task, id) {
    const data = {
        url: `http://localhost:8080/api/todolist`,
        method: 'POST',
        body: JSON.stringify({
            task: task,
            todoId: id,
            isComplete: false
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        body: data.body,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}

export function upTodoList(task, todoId, listId, status){
    const data = {
        url: `http://localhost:8080/api/todolist/${listId}`,
        method: 'PUT',
        body: JSON.stringify({
            task: task,
            todoId: todoId,
            isComplete: status
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        body: data.body,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}

export function delTodoList(getId){
    const data = {
        url: `http://localhost:8080/api/todolist/${getId}`,
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'Authorization': "Basic " + getCookie('token')
        }
    }
    return fetch(data.url,{
        method: data.method,
        headers: data.headers
    }).then(values => values.json())
        .then(value => value)
}