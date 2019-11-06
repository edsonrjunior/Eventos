package br.com.fiap.g1.eventos.relacionamento.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
  
  public class SlackUtils {
      private static String slackWebhookUrl = "${slack.webhook}";
  
      public static void sendMessage(SlackMessage message) {
          CloseableHttpClient client = HttpClients.createDefault();
          HttpPost httpPost = new HttpPost(slackWebhookUrl);
        
          System.out.println("Slack webhook utilizado: " + slackWebhookUrl);
  
          try {
              ObjectMapper objectMapper = new ObjectMapper();
              String json = objectMapper.writeValueAsString(message);
  
              StringEntity entity = new StringEntity(json);
              httpPost.setEntity(entity);
              httpPost.setHeader("Accept", "application/json");
              httpPost.setHeader("Content-type", "application/json");
  
              client.execute(httpPost);
              client.close();
          } catch (IOException e) {
             e.printStackTrace();
          }
      }
  }
