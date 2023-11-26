function confirmTask() {
    var taskName = document.getElementById("taskName").value;
    var taskDescription = document.getElementById("taskDescription").value;
    var taskList = document.getElementById("taskList").value;

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
                email: .value,
                senha: Isenha.value})
        })
        .then(function (res){console.log(res)})
        .catch(function (res){console.log(res)})
    
    // Close the modal
    closeModal('taskModal');
}