/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.telalogin;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.github.britooo.looca.api.group.servicos.ServicosGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

/**
 *
 * @author Aluno
 */
public class Relatorio {
    public static void main(String[] args) {
        Looca looca = new Looca();
        //infos do SO
        Sistema sistema = looca.getSistema();

        //infos da memória
        Memoria memoria = looca.getMemoria();

        //infos do processador
        Processador processador = looca.getProcessador();

        //temperatura do processador
        Temperatura temperatura = looca.getTemperatura();

        //
        DiscosGroup grupoDeDiscos = looca.getGrupoDeDiscos();

        //
        ServicosGroup grupoDeServicos = looca.getGrupoDeServicos();

        //infos de uso
        //ProcessosGroup grupoDeProcessos = looca.getGrupoDeProcessos();

    }
}
