var taskName = document.getElementById("taskName").value;
var taskDescription = document.getElementById("taskDescription").value;
var taskDateTime = document.getElementById("taskDate").value;
var taskList = document.getElementById("taskList").value;
var listName = document.getElementById("listName").value;

function confirmTask(taskName, taskDescription, taskDate, taskList) {
    try {
        var selectedDate = new Date(taskDateTime);
        
        fetch("http://localhost:8080/tarefa", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: taskName.value,
                descricao: taskDescription.value,
                data: taskDateTime.value,
                listaTarefa: taskList.value
            })
        })
        .then(function (res) {
            //Checa se resposta esta ok
            if (!res.ok) {
                throw new Error("Erro ao confirmar a tarefa. Por favor, verifique os dados e tente novamente.");
            }
            return res.json();
        })
        .then(function (data) {
            //processa dados
            console.log(data);
        })
        .catch(function (error) {
            //erros de JSON ou data fetch
            console.error("Erro durante a confirmação da tarefa:", error.message);
        });

        //Fecha o Modal
        closeModal('taskModal');
    } catch (error) {
        //lida com outros erros.
        console.error("Erro durante a confirmação da tarefa:", error.message);
    }
}

function confirmList(listName) {
    try {
        fetch("http://localhost:8080/listaTarefa", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: listName.value
            })
        })
        .then(function (res) {
            //Checa se resposta esta ok
            if (!res.ok) {
                throw new Error("Erro ao confirmar a lista. Por favor, verifique os dados e tente novamente.");
            }
            return res.json();
        })
        .then(function (data) {
            //processa dados
            console.log(data);
        })
        .catch(function (error) {
            //erros de JSON ou data fetch
            console.error("Erro durante a confirmação da lista:", error.message);
        });

        //Fecha o Modal
        closeModal('listModal');
    } catch (error) {
        //lida com outros erros.
        console.error("Erro durante a confirmação da lista:", error.message);
    }
}