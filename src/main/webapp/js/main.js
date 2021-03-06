const APP_VIEW = document.getElementById('app-view');

window.onload = function() {
    loadLogin();
}

//---------------------- LOAD SCREENS ------------------------

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

//                   ------- ADMIN --------            
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

//                  ------- MANAGER --------
function loadManagerReimbursements() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'managerReimburse.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureManageReimbursements();
        }
    }
}



//                 ------- EMPLOYEE --------
function loadNewReimbursement() {

        let xhr = new XMLHttpRequest();
    
        xhr.open('GET', 'newReimburse.screen');
        xhr.send();
    
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                APP_VIEW.innerHTML = xhr.responseText;
                configureNewReimbursementScreen();
            }
        }
}

function loadAllReimbursements() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'allReimbursements.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureAllReimbursementsScreen();
        }
    }
}

function loadUpdateReimbursement() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'updateReimburse.screen');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            APP_VIEW.innerHTML = xhr.responseText;
            configureUpdateReimbursementScreen();
        }
    }
}

// -------------------- CONFIGURE SCREENS --------------------------------------------------

//                   ------- LOGIN -------- 
function configureLoginScreen() {
    document.getElementById('login').addEventListener('click', login);
}

//                    ------- HOME --------
function configureHomeScreen() {
    document.getElementById('logout').addEventListener('click', logout);

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
                document.getElementById('toNewReimbursement').setAttribute('hidden', true);
                document.getElementById('allReimbursements').setAttribute('hidden', true);
                document.getElementById('toUpdateReimbursement').setAttribute('hidden', true);



            // MANAGER
            } else if (role == '2') {
                console.log('manager page');
                document.getElementById('toRegister').setAttribute('hidden', true);
                document.getElementById('toUpdate').setAttribute('hidden', true);
                document.getElementById('toManagerReimbursement').addEventListener('click', loadManagerReimbursements);
                document.getElementById('toNewReimbursement').setAttribute('hidden', true);
                document.getElementById('allReimbursements').setAttribute('hidden', true);
                document.getElementById('toUpdateReimbursement').setAttribute('hidden', true);

            // EMPLOYEE
            } else if (role == '3') {
                console.log('employee page');
                document.getElementById('toRegister').setAttribute('hidden', true);
                document.getElementById('toUpdate').setAttribute('hidden', true);
                document.getElementById('toManagerReimbursement').setAttribute('hidden', true);
                document.getElementById('toNewReimbursement').addEventListener('click', loadNewReimbursement);
                document.getElementById('allReimbursements').addEventListener('click', loadAllReimbursements)
                document.getElementById('toUpdateReimbursement').addEventListener('click', loadUpdateReimbursement);

            }
        }
    }
}

//                   ------- ADMIN --------
function configureRegisterScreen() {
    document.getElementById('register').addEventListener('click', register);
    document.getElementById('back').addEventListener('click', backToDash);
}

function configureUpdateScreen() {
    populateAdminTable();
    document.getElementById('update').addEventListener('click', updateUser);
    document.getElementById('back').addEventListener('click', backToDash);
}

//                   ------- MANAGER --------
function configureManageReimbursements() {
    populateManagerTable();
    document.getElementById('AoD').addEventListener('click', approveOrDeny);
    document.getElementById('back').addEventListener('click', backToDash);    
}


//                   ------- EMPLOYEE --------
function configureNewReimbursementScreen() {
    document.getElementById('reimb-submit').addEventListener('click', submitReimbursement);
    document.getElementById('back').addEventListener('click', backToDash);
}

function configureAllReimbursementsScreen() {
    populateAllReimbursements();
    document.getElementById('back').addEventListener('click', backToDash);
}

function configureUpdateReimbursementScreen() {
    populateReimbursementTable();
    document.getElementById('updateReimburse').addEventListener('click', updateReimbursement);
    document.getElementById('back').addEventListener('click', backToDash);
}


// ------------------------ OPERATIONS -------------------------------------------------------

//                --------- LOGIN OPS -------------

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
            loadLogin
        }

    }
}

function logout() {

    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'login');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log('Logging Out');
            loadLogin();
        } else {
            console.log('User session still active');
        }
    }
    
}

function backToDash() {
    loadHome();
}

//                 --------- ADMIN OPS -------------

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
            
            $('#all-users').DataTable( {
                data: allUsers,
                columns: [
                    { data: 'id' },
                    { data: 'username' },
                    { data: 'password' },
                    { data: 'firstName' },
                    { data: 'lastName' },
                    { data: 'email' },
                    { data: 'role' },
                    { data: 'status'}
                ]
            });
        } else if (xhr.readyState == 4 && xhr.status == 503) {
            console.log('The server is having trouple connecting right now...');
        }
    }
}

function updateUser() {

    let id = document.getElementById('up-id').value;
    let fn = document.getElementById('up-fn').value;
    let ln = document.getElementById('up-ln').value;
    let un = document.getElementById('up-un').value;
    let pw = document.getElementById('up-pw').value;
    let em = document.getElementById('up-email').value;
    let rl = document.getElementById('up-role').value;
    let st = document.getElementById('up-status').value;

    let info = {
        id: id,
        firstName: fn,
        lastName: ln,
        username: un,
        password: pw,
        email: em,
        role: rl,
        status: st
    }

    infoJSON = JSON.stringify(info);

    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'updateUser');
    xhr.send(infoJSON);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 202) {
            console.log('User updated!');
            loadUpdateUser();
        } else if (xhr.readyState == 4 && xhr.status == 503) {
            console.log('User failed to update...');
        }
    }
}

//                 --------- MANAGER OPS -------------
function populateManagerTable() {

    let xhr = new XMLHttpRequest();

        xhr.open('GET', 'manager');
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send();

        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {

                let userReimbursements = JSON.parse(xhr.responseText);
                
                $('#manager-reimbursements').DataTable( {
                    data: userReimbursements,
                    columns: [
                        { data: 'id' },
                        { data: 'amount' },
                        { data: 'submitted' },
                        { data: 'description' },
                        { data: 'authorName' },
                        { data: 'typeId' }
                    ]
                } );
            } else if (xhr.readyState == 4 && xhr.status != 200) {
                console.log('something went wrong');
            }
        }
    
}

function approveOrDeny() {

    let id = document.getElementById('id').value;
    let stat = document.getElementById('answer').value;

    let info = {
        id: id,
        statusId: stat
    }

    let approveOrDenyJSON = JSON.stringify(info);
    //console.log(approveOrDenyJSON);
    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'manager');
    xhr.send(approveOrDenyJSON);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 202) {
            console.log('Reimbursement has been approved or denied');
            loadManagerReimbursements();
        } else if (xhr.readyState == 4 && xhr.status == 400) {
            console.log('Failed to approve or deny reimbursement');
        }
    }

}


//                --------- EMPLOYEE OPS -------------
function populateReimbursementTable() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'updateReimburse');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {

            let userReimbursements = JSON.parse(xhr.responseText);
            
            $('#all-reimbursements').DataTable( {
                data: userReimbursements,
                columns: [
                    { data: 'id' },
                    { data: 'amount' },
                    { data: 'submitted' },
                    { data: 'description' },
                    { data: 'typeId' },
                    { data: 'statusId'}
                ]
            } );
        } else if (xhr.readyState == 4 && xhr.status != 200) {
            console.log('something went wrong');
        }
    }
}

function populateAllReimbursements() {

    let xhr = new XMLHttpRequest();

    xhr.open('GET', 'allReimbursements');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {

            let userReimbursements = JSON.parse(xhr.responseText);
            
            $('#every-reimbursements').DataTable( {
                data: userReimbursements,
                columns: [
                    { data: 'id' },
                    { data: 'amount' },
                    { data: 'submitted' },
                    { data: 'resolved' },
                    { data: 'description' },
                    { data: 'resolverName' },
                    { data: 'typeId' },
                    { data: 'statusId'}
                ]
            } );
        } else if (xhr.readyState == 4 && xhr.status != 200) {
            console.log('something went wrong');
        }
    }
}

function submitReimbursement() {

    let amt = document.getElementById('reg-amt').value;
    let desc = document.getElementById('reg-desc').value;
    let type = document.getElementById('reg-type').value;

    let reimbursement = {
        amount: amt,
        description: desc,
        typeId: type
    }

    let newReimburseJSON = JSON.stringify(reimbursement);

    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'newReimburse');
    xhr.send(newReimburseJSON);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 201) {
            loadHome();
        } else if (xhr.readyState == 4) {
            console.log('something went wrong');
            loadNewReimbursement();
        }
    }

}

function updateReimbursement() {

    let id = document.getElementById('up-id').value;
    let amt = document.getElementById('up-amt').value;
    let desc = document.getElementById('up-desc').value;
    let type = document.getElementById('up-type').value;

    let info = {
        id: id,
        amount: amt,
        description: desc,
        typeId: type,
    }

    let updateReimburseJSON = JSON.stringify(info);

    let xhr = new XMLHttpRequest();

    xhr.open('POST', 'updateReimburse');
    xhr.send(updateReimburseJSON);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 202) {
            console.log('Reimbursement updated!');
            loadUpdateReimbursement();
        } else if (xhr.readyState == 4 && xhr.status == 400) {
            console.log('User failed to update...');
        }
    }
}
