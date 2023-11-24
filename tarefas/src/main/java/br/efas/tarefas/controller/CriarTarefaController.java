package br.efas.tarefas.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class CriarTarefaController  {
    @RequestMapping("/criarTarefa")
    public String tarefaPage(){
        return "criarTarefa";
    }


}
