const settings = document.querySelector('.settings-icon');
const dropDownMenu = document.querySelector('.create-profile');
const homeBtn = document.querySelector('.logo-wrap a');
const settingsIcon = document.querySelector('.settings-icon');
const userHeader = document.querySelector('.userHeader');

const userToken2 = localStorage.userToken;

const logOut = (event) => {
  event.preventDefault();
  switchPagesUser();
  localStorage.removeItem('userName');
  localStorage.removeItem('loginToken');
};

// function showLiveComment;

const switchPagesUser = () => {
  const urlArry = window.location.href.split('/');
  const newUrl = urlArry.slice(0,urlArry.length-1)
  .join('/');
  if(window.location.href.includes('index.html')){
    location.replace(newUrl + '/userProfile.html');
  } else {
    location.replace(newUrl + '/index.html');
  }
};


const createProfile = (event) => {
  fetch('http://localhost:8080/username', {
    method: 'POST',
    headers: {
      "Authorization": "Bearer " + userToken2,
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      mobile: mobile,
      address: address
    })
  })
  .then(response => response.json())
  .then(response => {
  // This needs to be fixed up or deleted
    console.log(response, 'CREATING PROFILE!');
    dropDownMenu.classList.remove('create-profile-slide');
    userHeader.style.display = 'flex';

  })
  .catch(error => {
    console.log(error);
  })
};

function getProfile(func){
  fetch('http://localhost:8080/username', {
    method: 'GET',
    headers: {
      "Authorization": "Bearer " + userToken2,
      "Content-Type": "application/json"
    },
  })
  .then(res => {
    return res.json();
  })
  .then(res => {
    //////////////////////////////////////////////////////////
    console.log(res);
    if(func) func(res);
  })
  .catch(err => {
    console.log(err);
  })
};

// checkLogin();
// This adds userProfile from masterObj
// addUserProfile();

settings.addEventListener('click', function(e){
  if(dropDownMenu.classList.contains('create-profile-slide')){
    dropDownMenu.classList.remove('create-profile-slide');
  } else {
    dropDownMenu.classList.add('create-profile-slide');
  }
});

