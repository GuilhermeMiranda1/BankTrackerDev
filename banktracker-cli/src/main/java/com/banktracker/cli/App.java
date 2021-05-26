/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banktracker.cli;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.processos.Processo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class App {

    public static void main(String[] args) {
        Looca looca = new Looca();
        Timer timer = new Timer();
        System.out.println("Bem vindo a versão CLI do Banktracker!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu login");
        String loginUsuario = scanner.nextLine();
        System.out.println("Digite sua senha");
        String senhaUsuario = scanner.nextLine();
        Login login = new Login();

        if (!login.autenticaLogin(loginUsuario, senhaUsuario)) {
            System.out.println("Usuário autenticado com sucesso!");
            System.out.println("Coletando os dados da máquina...");
            CaixaEletronico cx = new CaixaEletronico();
            cx.capturarConfigMaquina();

            final long SEGUNDOS = (1000 * 6);

            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        cx.capturarHistoricoMaquina();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, 0, SEGUNDOS);
        } else {
            System.out.println("Login ou senha incorretos");
        }
    }
}
