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

function loadHome() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'home.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureHomeScreen();
        }
    }
}

function loadRegisterUser() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'register.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureRegisterScreen();
        }
    }
}

function loadUpdateUser() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'updateUser.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureUpdateScreen();
        }
    }
}

// ---------------- CONFIGURE SCREENS ---------------

function configureLoginScreen() {
    document.getElementById('login').addEventListener('click', login);
}

// 'god' dashboard for admin, manager, employee
function configureHomeScreen() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'home');
    xhr.send();

    xhr.onreadystatechange = function() {

        if (xhr.readyState == 4 && xhr.status == 200) {

            let role = xhr.responseText;

            // ADMIN 
            if (role == '1') {
                console.log('admin page');
                document.getElementById('toRegister').addEventListener('click', loadRegisterUser);
                document.getElementById('toUpdate').addEventListener('click', loadUpdateUser);
                document.getElementById('toManagerReimbursement').setAttribute('hidden', true);
                document.getElementById('toEmployeeReimbursement').setAttribute('hidden', true);
                document.getElementById('toNewReimbursement').setAttribute('hidden', true);
                document.getElementById('toUpdateReimbursement').setAttribute('hidden', true);



            // MANAGER
            } else if (role == '2') {
                console.log('manager page');
                //document.getElementById('toManagerReimbursement').addEventListener('click', loadManagerReimbursements);

            // EMPLOYEE
            } else if (role == '3') {
                console.log('employee page');
                //document.getElementById('toReimbursements').addEventListener('click', toReimbursements);
                //document.getElementById('toAddReimbursement').addEventListener('click', addReimbursement);


            }
        }
    }
}

//             ------- ADMIN --------
function configureRegisterScreen() {
    document.getElementById('register').addEventListener('click', register);

}

function configureUpdateScreen() {
    populateAdminTable();
    //document.getElementById('update').addEventListener('click', updateUser);

}

// --------------------- OPERATIONS -----------------------------

//             --------- LOGIN OPS -------------

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
            console.log('login successful!');
            loadHome();
            // let user = JSON.parse(xhr.response);
        } else if (xhr.readyState == 4 && xhr.status == 401) {
            console.log('login unsuccessful');

        }

    }
}

//             --------- ADMIN OPS -------------

function register() {

    let fn = document.getElementById('reg-fn').value;
    let ln = document.getElementById('reg-ln').value;
    let un = document.getElementById('reg-un').value;
    let pw = document.getElementById('reg-pw').value;
    let em = document.getElementById('reg-email').value;
    let rl = document.getElementById('reg-role').value;

    let creds = {
        firstName: fn,
        lastName: ln,
        username: un,
        password: pw,
        email: em,
        role: rl
    }

    let newUserJSON = JSON.stringify(creds);

    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'register');
    xhr.send(newUserJSON);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 201) {
            loadHome();
        } else if (xhr.readyState == 4) {
            console.log('something went wrong');
            loadRegisterUser();
        }
    }


}

function populateAdminTable() {

    console.log('in populateAdminTable()');

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'updateUser');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {

            let allUsers = JSON.parse(xhr.responseText);

            let table = document.getElementById('all-users');

            for (i = 0; i < allUsers.length; i++) {

                // creates a new row each iteration
                let newRow = document.createElement('tr');

                newRow.innerHTML = '<td>' + allUsers[i].id + '</td>' +
                                   '<td>' + allUsers[i].username + '</td>' +
                                   '<td>' + allUsers[i].password + '</td>' +
                                   '<td>' + allUsers[i].firstName + '</td>' +
                                   '<td>' + allUsers[i].lastName + '</td>' +
                                   '<td>' + allUsers[i].email + '</td>' +
                                   '<td>' + allUsers[i].role + '</td>';
                table.append(newRow);                
            }

            

        } else if (xhr.readyState == 4) {

            console.log('something went wrong');
        }
    }
}