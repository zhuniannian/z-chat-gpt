package com.chatgpt.util;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Instruction GPT模型
 */
@Service
public class Chat {

    private static final OpenAiClient openAiClient;

    static {
        System.out.println("初始化chatgpt信息-------------------------");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .proxy(proxy)//自定义代理
                .addInterceptor(httpLoggingInterceptor)//自定义日志输出
                .connectTimeout(30, TimeUnit.SECONDS)//自定义超时时间
                .writeTimeout(3, TimeUnit.MINUTES)//自定义超时时间
                .readTimeout(3, TimeUnit.MINUTES)//自定义超时时间
                .build();
        //构建客户端
       openAiClient = OpenAiClient.builder()
                .apiKey(Collections.singletonList("sk-"))
                .okHttpClient(okHttpClient)
                //自己做了代理就传代理地址，没有可不不传
//                .apiHost("https://自己代理的服务器地址/")
                .build();
    }


    public List<Message> send(String content) {
        //聊天模型：gpt-3.5
        List<Message> resp = new ArrayList<>();
        Message message = Message.builder().role(Message.Role.USER).content(content).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        chatCompletionResponse.getChoices().forEach(e -> {
            System.out.println(e.getMessage());
            resp.add(e.getMessage());
        });
        return resp;
    }


}
