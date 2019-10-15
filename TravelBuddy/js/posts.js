postUserToken = localStorage.userToken;

const createPost = (event) => {
  event.preventDefault();
  const title = event.target.children[0].value;
  const description = event.target.children[1].value;
  console.log(event);
  fetch('http://localhost:8080/post', {
    method: 'POST',
    headers: {
      "Authorization": "Bearer " + postUserToken,
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      title: title,
      description: description
    })
  })
  .then(res => {
    return res.json();
  })
  .then(res => {
    console.log(res);
    const id = res.id;
    const userName = res.user.username;
    // Refactor addPostLocal to fill out post properly.
    addPostLocal(title, description, userName, id);
  })
  .catch((err) => {
    console.log(err);
  })
};

const listAllPosts = (func) => {
  fetch('http://localhost:8080/post/list', {
    method: 'GET',
    headers: {
      "Content-Type": "application/json"
    }
  })
  .then(res => {
    return res.json();
  })
  .then(res => {
    console.log(res, 'list all posts');
    if(func) func(res);
  })
  .catch((err) => {
    console.log(err);
  })
};

function postsIterator(arr, nodeFunc){
  arr.reverse().forEach(i => {
    nodeFunc(i.user.username, i.title, i.description, i.id);
  });
};

// This is run in order to post all local posts
// In order for this set-up to work, we need to have
// postAllPosts(res) taking in the response from getPostsByUser
function postAllPosts(arr){
  arr.forEach(i => {
    addPostLocal(i.title, i.description, i.user.username, i.id);
  });
};

function getPostsByUser(token, func){
  fetch('http://localhost:8080/user/post', {
    method: 'GET',
    headers: {
      "Authorization": "Bearer " + token,
      "Content-Type": "application/json"
    }
  })
  .then(res => {
    return res.json();
  })
  .then(res => {
    if(func) func(res);
    console.log(res);
    postAllPosts(res);
  })
  .catch(err => {
    console.log(err)
  })
};



// Need to have the post id within the html
function addPostLocal(title, description, username, id){
  document.querySelector('.postForm').style.display = "block";
  const referenceNode = document.querySelector('.container-post');
  const parentNode = document.querySelector('.containerLanding');
  const postTemp = document.querySelector('.post-temp');
  const newTemp = postTemp.cloneNode(true);
  newTemp.setAttribute('data-id', id);
  newTemp.querySelector('.titleMsg').innerText = title;
  newTemp.querySelector('.message').innerText = description;
  newTemp.querySelector('.messageUserName').innerText = `Posted by ${username}`;
  newTemp.style.display = 'block';
  referenceNode.parentNode.insertBefore(newTemp, referenceNode.nextSibling);
};


function deletePost(postId){
  fetch('http://localhost:8080/post/'+postId, {
    method: 'DELETE',
    headers: {
      "Authorization": "Bearer " + localStorage.loginToken,
      "Content-Type": "application/json"
    }
  })
  .then(response => response.json())
  .then(response => {
    console.log('uh', response);
  })
  .catch(error => {
    console.log(error);
  })
}


