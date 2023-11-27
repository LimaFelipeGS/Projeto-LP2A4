var taskName = null;
var taskDescription = null;
var taskDateTime = null;
var taskList = null;
var listName = null;
let userData = {};

function confirmTask(taskName, taskDescription, taskDate, taskList) {
    taskName = document.getElementById("taskName").value;
    taskDescription = document.getElementById("taskDescription").value;
    taskDateTime = document.getElementById("taskDate").value;
    taskList = document.getElementById("taskList").value;
    const body = JSON.stringify({
        nome: taskName,
        descricao: taskDescription,
        data: taskDateTime,
        // horario: taskDateTime.value,
        listaTarefas: taskList,
        usuarios: [userData.id]
    })
    console.log(body)
    // Perform further actions, e.g., store data, update UI, etc.
    fetch("http://localhost:8080/tarefa",
        {
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            method: "POST",
            body
            //TODO: Definitivamente errado na parte de data e horario.
        })
        .then(function (res){
            console.log(res)
            getUsuario()
        })
        .catch(function (res){console.log(res)})
    // Close the modal
    closeModal('taskModal');
}

function confirmList(){
    listName = document.getElementById("listName").value;
    const body = JSON.stringify({ nome: listName, usuarios: [userData.id] });
    fetch("http://localhost:8080/listaTarefas",
    {
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        method: "POST",
        body
    })
    .then(function (res){
        console.log(res)
        // atualizar informações após o cadrastro
        getUsuario();
    })
    .catch(function (res){console.log(res)})
    // Close the modal
    closeModal('listModal');
}

async function getUsuario(){
    fetch(`http://localhost:8080/usuario/${sessionStorage.getItem("userId")}`,
        {
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            method: "GET",
        })
        .then(function (res) {
            // Verifica se o cadastro foi bem-sucedido
            if (res.ok) {
                // Converte a resposta JSON para um objeto JavaScript
                return res.json();
            } else {
                // Se o cadastro falhar, exibe uma mensagem de erro ou realiza outra ação
                console.error('Falha no cadastro');
                throw new Error('Falha no cadastro');
            }
        })
        .then(function (data) {
            // Verifica se o cadastro foi bem-sucedido
            console.log(data);
            userData = data;
            let userNameSpan = document.getElementById("userName");
            userNameSpan.innerHTML = userData.nome;
            buildListSelectOptions(userData.listaTarefa);
            buildTaskListDiv();
            buildListsDiv();
        })
        .catch(function (res){console.log(res)})

};

function buildListSelectOptions (lists){
    lists.forEach(list => {
        const nome = list.nome;
        const option = document.createElement("option");
        option.text = nome.toLowerCase().replace(/\s/g, "_");
        option.value = list.id
        option.text = nome; // Texto visível da opção
        taskList.add(option);
    });

    // Adicione um evento de escuta para obter o ID selecionado
    taskList.addEventListener("change", function () {
        const selectedId = taskList.value;
        const selectedText = lists[selectedId];
    });
}

function buildTaskListDiv() {
    const taskListDiv = document.getElementById("taskListDiv");
    taskListDiv.innerHTML = '';

    // Adicione cards dinamicamente a partir do array
    userData.tarefas.forEach(task => {
        const card = document.createElement("div");
        card.classList.add("task-card");

        const title = document.createElement("h3");
        title.textContent = task.nome;

        const description = document.createElement("p");
        description.textContent = task.descricao;

        card.appendChild(title);
        card.appendChild(description);
        taskListDiv.appendChild(card);
    });
}

function buildListsDiv() {
    const taskListsDiv = document.getElementById("listsDiv");
    taskListsDiv.innerHTML = '';

    // Adicione cards dinamicamente a partir do array
    userData.listaTarefa.forEach(list => {
        const card = document.createElement("div");
        card.classList.add("list-card");

        const title = document.createElement("h3");
        title.textContent = list.nome;

        card.appendChild(title);
        taskListsDiv.appendChild(card);
    });
}

// Função a ser executada quando a página é completamente carregada
function pageLoaded() {
    taskName = document.getElementById("taskName").value;
    taskDescription = document.getElementById("taskDescription").value;
    taskDateTime = document.getElementById("taskDate").value;
    taskList = document.getElementById("taskList");
    listName = document.getElementById("listName").value;
    getUsuario();
}
// Atribui a função ao evento window.onload
window.onload = pageLoaded;