const LOGIN_PAGE = document.getAnimations('login-page');

window.onload = function() {
    document.getElementById('login').addEventListener('click', login)    ;
}

function login() {

    console.log('executing toDashboard()');

    let un = document.getElementById('login-username').value;
    let pw = document.getElementById('login-password').value;

    console.log(un + " " + pw);

    let credentials = {
        username: un,
        password: pw
    }

    let credentialsJSON = JSON.stringify(credentials);

    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'login');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(credentialsJSON);

    xhr.onreadystatechange = function() {

        console.log(xhr.readyState);
         
        if (xhr.readyState == 4 && xhr.status == 204) {
            console.log('login successful!');
            toDashboard();
        } else if (xhr.readyState == 4 && xhr.status == 400) {
            console.log('login-unsucsseful...');
        }
    }
}

function toDashboard() {

    console.log('in toDashboard()');

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'dash');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 204) {
            console.log('Login Successful');
        }
    }
}