const setUserForm = document.querySelector('.signUpLogIn');
const logInBtn = document.querySelector('.logInBtn');
const signUpBtn = document.querySelector('.signUpBtn');
const signUpForm = document.querySelector('.signUpForm');
const logInForm = document.querySelector('.logInForm');

let userToken = localStorage.userToken;
let posts = [];


const switchPages = () => {
  const urlArry = window.location.href.split('/');
  const newUrl = urlArry.slice(0,urlArry.length-1).join('/');
  console.log(newUrl, "NEW URL")
  if(window.location.href.includes('index.html')){
    location.replace(newUrl + '/userProfile.html');
  } else {
    location.replace(newUrl + '/index.html');
  }
};

const saveToken = (token) => {
  localStorage.userToken = token;
  userToken = token;
};



//When you click "sign up" on index.html, the form collects info via the event using this function (below)
const newUser = (event) => {
  event.preventDefault();
  const emailIn = event.target[0].value;
  const passIn = event.target[1].value;
  const userIn = event.target[2].value;
  //If the email doesn't include a @ symbol, alert user, otherwise use user info with signUp function
  emailIn.includes('@') ? signUp(emailIn, passIn, userIn) :
      alert("You need to enter a valid email address");
};


// Sends user info via newUser (Above) in a call to sign up endpoint. The response is a token.
const signUp = (email, pass, user) => {
  fetch('http://localhost:8080/signup', {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: email,
      password: pass,
      username: user
    })
  })
  .then(response => response.json())
  .then(response => {

    // Here you are adding the token received via the sign up endpoint
    saveToken(response.token);

    switchPages();

    // logIn(email, pass);
  })
  .catch(error => {
    console.log(error);
  })
};


/* When you use the login form in the index.html - this captureLogin function captures the input data
 Sends the data to the logIn function */
const captureLogin = (event) => {
  event.preventDefault();
  const email = event.target[0].value;
  const pass = event.target[1].value;
  if(!email || !pass){
    alert('Email or password not recognized');
    return
  }
  logIn(email, pass);
};

const logIn = (email, pass) => {
  fetch('http://localhost:8080/login', {
    method: 'POST',
    headers: {
      // "Authorization": "Bearer " + userToken,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: email,
      password: pass
    })
  })
  .then(response => response.json())
  .then(response => {

    // Saving login token
    userToken = response.token
    localStorage.userToken = userToken;

    switchPages();
  })
  .catch(error => {
    console.log(error);
  })
};


setUserForm.addEventListener('mouseover', function(e){
    if(e.target === signUpBtn){
      for(let i = 0; i < 3; i++){
        signUpForm.children[i].classList.add('showInputs');
        signUpForm.children[i].classList.remove('collapseInputs');
      }
      for(let i = 0; i < 2; i++){
        logInForm.children[i].classList.remove('showInputs');
        logInForm.children[i].classList.add('collapseInputs');
      }
    }
    if(e.target === logInBtn){
      for(let i = 0; i < 3; i++){
        signUpForm.children[i].classList.remove('showInputs');
        signUpForm.children[i].classList.add('collapseInputs');
      }
      for(let i = 0; i < 2; i++){
        logInForm.children[i].classList.add('showInputs');
        logInForm.children[i].classList.remove('collapseInputs');
      }
    }
});
