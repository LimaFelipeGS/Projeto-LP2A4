
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
        .then(function (res) {
            // Verifica se o cadastro foi bem-sucedido
            if (res.ok) {
                // Converte a resposta JSON para um objeto JavaScript
                return res.text();
            } else {
                // Se o cadastro falhar, exibe uma mensagem de erro ou realiza outra ação
                console.error('Falha no cadastro');
                throw new Error('Falha no cadastro');
            }
        })
        .then(function (data) {
            // Verifica se o cadastro foi bem-sucedido
                
                const userIdMatch = data.match(/userId:(\d+)/);
                const userId = userIdMatch[1];
                // Armazena dados em cache na sessão
                sessionStorage.setItem('userId', userId);
                // Redireciona para a página de dashboard
                window.location.href = 'dashboard.html';
          
        })
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
    const Isenha = document.getElementById("registerPassword");

    formulario.addEventListener('submit', function (event){
        event.preventDefault();
        cadastrar(Inome, Iemail, Isenha);
        // limpar();
    });
}
// Atribui a função ao evento window.onload
window.onload = pageLoaded;