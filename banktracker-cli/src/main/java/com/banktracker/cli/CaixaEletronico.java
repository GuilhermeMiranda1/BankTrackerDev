/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banktracker.cli;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Pedro
 */

public class CaixaEletronico {

    private String hostname;
    private String sistemaOperacional;
    private Long cpuCaixa;
    private String processador;
    private Long ram;
    private Long hd;
    private Boolean ativo;
    private Integer fk_numAgencia;
    private List <Historico> historicos = new ArrayList();

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public void setCpuCaixa(Long cpuCaixa) {
        this.cpuCaixa = cpuCaixa;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public void setRam(Long ram) {
        this.ram = ram;
    }

    public void setHd(Long hd) {
        this.hd = hd;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setFk_numAgencia(Integer fk_numAgencia) {
        this.fk_numAgencia = fk_numAgencia;
    }

    public String getHostname() {
        return hostname;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public Long getCpuCaixa() {
        return cpuCaixa;
    }

    public String getProcessador() {
        return processador;
    }

    public Long getRam() {
        return ram;
    }

    public Long getHd() {
        return hd;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getFk_numAgencia() {
        return fk_numAgencia;
    }

    public void capturarConfigMaquina() {
        try {
            
            Looca looca = new Looca();
            String hostname = InetAddress.getLocalHost().getHostName();
            String so = looca.getSistema().getSistemaOperacional();
            Integer arq = looca.getSistema().getArquitetura();
            String fabricante = looca.getSistema().getFabricante();
            so = String.format("%s %s %d bits", fabricante, so, arq);
            Long cpuFrequencia = looca.getProcessador().getFrequencia();
            String processador = looca.getProcessador().getNome();
            Long totalRam = looca.getMemoria().getTotal();
            Long totalHd = looca.getGrupoDeDiscos().getTamanhoTotal();
            Integer numAgencia = Agencia.getInstance(null).getIdAgencia();
            this.hostname = hostname;
            this.sistemaOperacional = so;
            this.cpuCaixa = cpuFrequencia;
            this.processador = processador;
            this.ram = totalRam;
            this.hd = totalHd;
            this.ativo = true;
            this.fk_numAgencia = numAgencia;
            System.out.println("Dados da máquina coletados com sucesso!");
            cadastrarCaixaEletronico();
        } catch (Exception e) {
            System.out.println("Erro ao capturar os dados da máquina!");
            System.out.println(e);
        }
    }

    public void capturarHistoricoMaquina() {
        try {
            Historico hist = new Historico();
            Looca looca = new Looca();
            String hostname = InetAddress.getLocalHost().getHostName();

            Double espacoLivre = 0.0;
            List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

            for (Volume volume : volumes) {
                espacoLivre += volume.getDisponivel();
            }

            hist.setUsoCpu(looca.getProcessador().getUso());
            hist.setUsoRam(looca.getMemoria().getEmUso());
            hist.setUsoDisco(looca.getGrupoDeDiscos().getTamanhoTotal() - espacoLivre);
            hist.setDiscoLivre(espacoLivre);
            hist.setFk_hostname(hostname);
            System.out.println("Dados de utilização coletados com sucesso!");
            historicos.add(hist);
            hist.salvarHistorico();
        } catch (Exception e) {
            System.out.println("Falha ao coletar dados de utilização!");
        }
    }

    public void cadastrarCaixaEletronico() {
        try {
            Conexao con = new Conexao();
            JdbcTemplate template = new JdbcTemplate(con.getBanco());
            String caixaEletronico = "INSERT INTO CaixaEletronico VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            template.update(caixaEletronico, hostname, sistemaOperacional, cpuCaixa, processador, ram, hd, null, null, ativo, fk_numAgencia);
            System.out.println("Cadastro de caixa realizado com sucesso!");
            SlackMensagens.enviarMensagem("Um caixa novo foi cadastrado! Caixa: " + hostname);
        } catch (Exception e) {
            String err = e.toString();

            if (err.contains("Violation of PRIMARY KEY constraint")) {
                System.out.println("Caixa já cadastrado!");
            } else {
                System.out.println(err);
            }
        }
    }
}
