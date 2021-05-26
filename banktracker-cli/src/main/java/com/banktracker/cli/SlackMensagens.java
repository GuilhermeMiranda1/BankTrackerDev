/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banktracker.cli;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author Guilherme
 */
public class SlackMensagens {

    static void publishMessage(String id, String text) {
        // you can get this instance via ctx.client() in a Bolt app
        var client = Slack.getInstance().methods();
        var logger = LoggerFactory.getLogger("BotSlack");
        try {
            // Call the chat.postMessage method using the built-in WebClient
            var result = client.chatPostMessage(r -> r
                // The token you used to initialize your app
                .token("xoxb-2043718059234-2029003706583-q8N3enDjT1JQEOicr0fjvUKr")
                .channel(id)
                .text(text)
                // You could also use a blocks[] array to send richer content
            );
            // Print result, which includes information about the message (like TS)
            logger.info("result {}", result);
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
    }
    public static String identificarCanal() throws UnknownHostException{
        String idCanal;
        Conexao con = new Conexao();
        JdbcTemplate template = new JdbcTemplate(con.getBanco());


        Integer idAgencia = Agencia.getInstance(null).getIdAgencia();
        idCanal = template.queryForList("SELECT idCanal FROM SlackCanais where fk_agencia = " + idAgencia).toString();
        idCanal = idCanal.replace("[{idCanal=", "");
        idCanal = idCanal.replace("}]", "");
        return idCanal;
    }
    
    public static void enviarMensagem(String mensagem){
        try {
            publishMessage(identificarCanal(), mensagem);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
