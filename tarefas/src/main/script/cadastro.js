const formulario = document.querySelector(".form-container sign-up") ;

const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");


function cadastrar(Inome, Iemail, Isenha){

    console.log(Inome)

    fetch("http://localhost:8080/usuario",
        {
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            method: "POST",
            body: JSON.stringify({
                nome: Inome.value,
                email: Iemail.value,
                senha: Isenha.value})
        })
        .then(function (res){console.log(res.text())})
        .catch(function (res){console.log(res)})

};

function limpar(){
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = ""
}



// Função a ser executada quando a página é completamente carregada
function pageLoaded() {
    console.log("A página foi completamente carregada.");

    const formulario = document.getElementById("cadastroForm") ;

    const Inome = document.getElementById("nome");
    console.log(Inome)
    const Iemail = document.getElementById("email");
    const Isenha = document.getElementById("senha");

    formulario.addEventListener('submit', function (event){
        event.preventDefault();
        cadastrar(Inome, Iemail, Isenha);
        // limpar();
    });
}
// Atribui a função ao evento window.onload
window.onload = pageLoaded;