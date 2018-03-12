function we_register_person(email, pwd, pwd_rpt){
    // return:
    // loginResult: 0 if OK, <0 for different error codes
    // returnComment: some comments for display
    // otherData: not defined yet
    var loginResult = -1;
    var returnComment = "";
    var otherData = "";
    if(typeof(Storage) !== "undefined") {
        if (pwd == pwd_rpt) {
            returnComment = "user " + email + " registered!"
            localStorage.email = email;
            localStorage.pwd = pwd;
        } else {
            returnCommentL = "passwords not match";
        }
    } else {
        returnComment = "Sorry, your browser does not support web storage.";
    }
    return [loginResult,returnComment,otherData]
}

function we_login_person(email, pwd){
    var loginResult = -1;
    var returnComment = "";
    var otherData = "";
    if(typeof(Storage) !== "undefined") {
        if (localStorage.email == email){
            returnComment = "found user name " + localStorage.email;
            if (localStorage.pwd == pwd) {
                returnComment += " login ok!";
            }
            else{
                returnComment += " password not match";
            }
        } else {
            returnComment = "not found user name " + email;
        }
    } else {
        returnComment = "Sorry, your browser does not support web storage.";
    }
    return [loginResult,returnComment,otherData];
}
