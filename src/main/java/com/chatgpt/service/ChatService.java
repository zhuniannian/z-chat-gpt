package com.chatgpt.service;

import com.chatgpt.util.Chat;
import com.unfbx.chatgpt.entity.chat.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/chat")
public class ChatService {

    @Resource private Chat chat;

//    @Resource
//    RestTemplate restTemplate;

    @PostMapping
    public List<Message> getChat(@RequestBody String content) {
        return chat.send(content);
    }

//    public static ExecutorService executors = Executors.newFixedThreadPool(15);
//
//    @GetMapping(value = "/get")
//    public String get() {
//        List<String> strings = new ArrayList<>();
//        long cur = System.currentTimeMillis();
//        List<CompletableFuture<String>> futures = new ArrayList<>();
//        for (int i = 0; i < 700; i++) {
//            futures.add(CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://localhost:8889/chat2", String.class), executors));
//        }
//        //等待全部完成
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//        futures.forEach(stringCompletableFuture -> {
//            try {
//                strings.add(stringCompletableFuture.get(3, TimeUnit.SECONDS));
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        });
//        System.out.println("end-----"+ (System.currentTimeMillis() - cur) / 1000);
//        System.out.println(strings.size());
//        return strings.toString();
//    }
//
//
//    @GetMapping(value = "/get2")
//    public String get2() {
//        return "hhh";
//    }

}
