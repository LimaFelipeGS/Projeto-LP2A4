    var taskName = document.getElementById("taskName").value;
    var taskDescription = document.getElementById("taskDescription").value;
    var taskDateTime = document.getElementById("taskDate").value;
    var taskList = document.getElementById("taskList").value;
    var listName = document.getElementById("listName").value;

    function confirmTask(taskName, taskDescription, taskDate, taskList) {
    // Perform further actions, e.g., store data, update UI, etc.
    fetch("http://localhost:8080/tarefa",
        {
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: taskName.value,
                descricao: taskDescription.value,
                data: taskDateTime.value,
                horario: taskDateTime.value,
                listaTarefa: taskList.value
            //TODO: Definitivamente errado na parte de data e horario.
        })
        .then(function (res){console.log(res)})
        .catch(function (res){console.log(res)})
    })
    // Close the modal
    closeModal('taskModal');

    function confirmList(listName){
        fetch("http://localhost:8080/listaTarefa",
        {
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: listName.value
            //TODO: Definitivamente errado na parte de data e horario.
        })
        .then(function (res){console.log(res)})
        .catch(function (res){console.log(res)})
    })
    // Close the modal
    closeModal('listModal');
    }

}