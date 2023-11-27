//código que modifica a pagina de login e cadastro

const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});
//Página escrita por Ana Paula

//script Eduardo
// const formularioLogin = document.querySelector(".form-container sign-in") ;

// const Lemail = document.querySelector(".loginEmail");
// const Lsenha = document.querySelector(".loginPassword");


// function login(){

//     fetch("http://localhost:8080/usuario/login",
//         {
//             headers:{
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json'
//             },
//             method: "POST",
//             body: JSON.stringify({
//                 email: Lemail.value,
//                 senha: Lsenha.value})
//         })
//         .then(function (res){console.log(res)})
//         .catch(function (res){console.log(res)})

// };

// function limpar(){
//     Lemail.value = "";
//     Lsenha.value = ""
// }

// formularioLogin.addEventListener('submit', function (loginEvent){
//     loginEvent.preventDefault();
//     login();
//     limpar();
// });