// código que modifica a página de login e cadastro

const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

// Página escrita por Ana Paula

// script Eduardo
const formularioLogin = document.querySelector(".form-container sign-in");

const Lemail = document.querySelector(".loginEmail");
const Lsenha = document.querySelector(".loginPassword");

function login(){
    try{
        fetch("http://localhost:8080/usuario/login", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                email: Lemail.value,
                senha: Lsenha.value
            })
        })
        .then(function (res) {
            //Checa se resposta esta ok
            if (!res.ok) {
                throw new Error("Falha no Login. Cheque suas credenciais.");
            }
            return res.json();
        }) //processa os dados
        .then(function (data) {console.log(data);
            //Lida com erros de fetch ou parsing the Json
        }).catch(function (error){console.error("Erro durante login:", error.message)});  
    } 
    catch(error){
        //Lida com erros sincronos
        console.error("Erro durante login:", error.message);
    }
}

function limpar(){
    Lemail.value = "";
    Lsenha.value = "";
}

formularioLogin.addEventListener('submit', function (loginEvent) {
    loginEvent.preventDefault();
    login();
    limpar();
});