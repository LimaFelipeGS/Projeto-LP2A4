// Seletor do formulário de cadastro
const formulario = document.querySelector(".form-container sign-up");

// Seletor dos campos do formulário
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");

// Função para cadastrar um novo usuário
function cadastrar(Inome, Iemail, Isenha) {
    console.log(Inome);

    // Envia uma requisição POST para o servidor
    fetch("http://localhost:8080/usuario", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        method: "POST",
        body: JSON.stringify({
            nome: Inome.value,
            email: Iemail.value,
            senha: Isenha.value
        })
    })
    .then(function (res) {console.log(res.text())})
    .catch(function (res) {console.log(res);});
};

// Função para limpar os campos do formulário
function limpar() {
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
}

// Função a ser executada quando a página é completamente carregada
function pageLoaded() {
    console.log("A página foi completamente carregada.");

    // Atribui os elementos do formulário
    const formulario = document.getElementById("cadastroForm");
    const Inome = document.getElementById("nome");
    console.log(Inome);
    const Iemail = document.getElementById("email");
    const Isenha = document.getElementById("senha");

    // Adiciona um ouvinte de eventos para o envio do formulário
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        cadastrar(Inome, Iemail, Isenha);
        // limpar();
    });
}

// Atribui a função ao evento window.onload
window.onload = pageLoaded;