const APP_VIEW = document.getElementById('app-view');

window.onload = function() {
    loadLogin();
}

//----------- LOAD SCREENS -------------

function loadLogin() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'login.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureLoginScreen();
        }
    }
}

function loadAdmin() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'admin.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureAdminScreen
        }
    }
}

// ---------------- CONFIGURE SCREENS ---------------

function configureLoginScreen() {
    document.getElementById('login').addEventListener('click', login);
}

function configureAdminScreen() {
    
}

// -------------- EVENT LISTENER FUNCTIONS ------------------

// -------------------- LOGIN PAGE --------------------------

function login() {
    let un = document.getElementById('login-username').value;
    let pw = document.getElementById('login-password').value;

    let creds = {
        username: un,
        password: pw
    }

    let credsJSON = JSON.stringify(creds);
    console.log(credsJSON);
    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'login');
    xhr.send(credsJSON);

    xhr.onreadystatechange = function () {

        console.log(xhr.readyState);

        if (xhr.readyState == 4 && xhr.status == 204) {

            // let user = JSON.parse(xhr.response);

            console.log('login successful!');
            

        } else if (xhr.readyState == 4 && xhr.status == 401) {
            console.log('login unsuccessful');

        }

    }
}