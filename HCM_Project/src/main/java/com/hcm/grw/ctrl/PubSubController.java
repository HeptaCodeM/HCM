package com.hcm.grw.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.RoomMessage;
import com.hcm.grw.model.service.RedisPublisher;
import com.hcm.grw.model.service.RedisSubscriber;



@Controller
@RequestMapping("/doc/")
public class PubSubController {
	
	@Autowired
	@Qualifier("listenerContainer")
    // topic에 메시지 발행을 기다리는 Listener
    private RedisMessageListenerContainer redisMessageListener;
	// 발행자
	@Autowired
	private RedisPublisher redisPublisher;	
	// 구독자
	@Autowired
	private RedisSubscriber redisSubscriber;
	// topic 이름으로 topic정보를 가져와 메시지를 발송할 수 있도록 Map에 저장
	@Autowired
	private Map<String, ChannelTopic> channels;
	
	@PostConstruct
	public void init() {
		channels = new HashMap<>();
	}

	// 유효한 Topic 리스트 반환
    @GetMapping("room.do")
    public String findAllRoom(Model model) {
        System.out.println("================"+channels.keySet()); 
        System.out.println("================"+channels); 
        model.addAttribute("rooms", channels.keySet());
        return "pubsubMesage";
    }

    // 신규 Topic을 생성하고 Listener등록 및 Topic Map에 저장
    @PutMapping("room.do")
    @ResponseBody
    public ResponseEntity<String> createRoom(@RequestBody Map<String, String> requestBody) {
    	
    	System.out.println(requestBody);
    	String roomId = requestBody.get("roomId");
        ChannelTopic channel = new ChannelTopic(roomId);
        redisMessageListener.addMessageListener(redisSubscriber, channel);
        channels.put(roomId, channel);
        return ResponseEntity.ok().body("roomId");
    }

    // 특정 Topic에 메시지 발행
    @PostMapping("room.do")
    public String pushMessage(@RequestParam String roomId, @RequestParam String name, @RequestParam String message) {
        ChannelTopic channel = channels.get(roomId);
        redisPublisher.publish(channel, RoomMessage.builder().name(name).roomId(roomId).message(message).build());
        return "redirect:room.do";
    }

    // Topic 삭제 후 Listener 해제, Topic Map에서 삭제
    @DeleteMapping("room.do")
    public ResponseEntity<String> deleteRoom(@RequestBody Map<String, String> requestBody) {
    	String roomId = requestBody.get("roomId");
        ChannelTopic channel = channels.get(requestBody.get("roomId"));
        redisMessageListener.removeMessageListener(redisSubscriber, channel);
        channels.remove(roomId);
        return ResponseEntity.ok().body("roomId");
    }
    
    
    
    @PostMapping("joinroom.do")
    public void joinRoom(@RequestParam String userId) {
        // room01 채널 생성 또는 가져오기
        ChannelTopic room01Channel = new ChannelTopic("room01");
        // Redis Pub/Sub에서 해당 채널을 구독하도록 설정
        redisMessageListener.addMessageListener(new RedisSubscriber(), room01Channel);
    }

}
