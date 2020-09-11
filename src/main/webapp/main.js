const APP_VIEW = document.getAnimations('app-view');

window.onload = function() {
    loadLogin();
    document.getElementById('toDash').addEventListener('click', login);
}

//----------- LOAD SCREENS -------------

function loadLogin() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'login.view');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureLoginScreen();
        }
    }
}

// ----------- CONFIGURE SCREENS ----------

function configureLoginScreen() {
    document.getElementById('login-message').setAttribute('hidden', true);
    document.getElementById('login').addEventListener('click', login);
}

// ----------- EVENT LISTENER FUNCTIONS ------------

function login() {
    let un = document.getElementById('login-username').Value;
    let pw = document.getElementById('login-password').Value;

    let creds = {
        username: un,
        password: pw
    }

    let credsJSON = JSON.stringify(creds);

    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'login');
    xhr.send(credsJSON);

    if (xhr.readyState == 4 && xhr.status == 204) {
        console.log('Login Successful!');
    } else if (xhr.readyState == 4 && xhr.status == 401) {
        console.log('Error: Bad login credentials.')
    }

}
