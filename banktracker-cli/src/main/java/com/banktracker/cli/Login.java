
package com.banktracker.cli;

import java.io.IOException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

public class Login {
    Conexao con;
    JdbcTemplate template;

    public Login() {
        this.con = new Conexao();
        this.template = new JdbcTemplate(con.getBanco());
    }

    public Boolean autenticaLogin(String login, String senha){
        List<FuncionarioSuporte> loginusuario = template.query("SELECT * FROM FuncionarioSuporte WHERE email LIKE '" + login + "' AND senha LIKE '" + senha + "'", new BeanPropertyRowMapper(FuncionarioSuporte.class));
        try {
            // Coleta o valor da fk da agencia
            Integer idAgencia = loginusuario.get(0).getFk_numAgencia();
            // Inicializa a istancia da agencia, com o valor da fk_numAgencia do usuario
            Agencia agencia = Agencia.getInstance(idAgencia);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Não retornou usuário");
            Log log1 = new Log("Nenhum usuário encontrado", e.toString());
            log1.GerarLog();
        }
        
        //System.out.println(agencia.getIdAgencia());
        
        return loginusuario.isEmpty();
    }
}
