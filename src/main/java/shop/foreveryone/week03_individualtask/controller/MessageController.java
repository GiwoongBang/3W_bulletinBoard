package shop.foreveryone.week03_individualtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import shop.foreveryone.week03_individualtask.domain.Message;
import shop.foreveryone.week03_individualtask.domain.MessageRepository;
import shop.foreveryone.week03_individualtask.domain.MessageRequestDto;
import shop.foreveryone.week03_individualtask.service.MessageService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;

    @PostMapping("/api/messages")
    public Message CreateMessage(@RequestBody MessageRequestDto requestDto) {
        Message message = new Message(requestDto);
        return messageRepository.save(message);
    }

    @GetMapping("/api/messages")
    public List<Message> getMessages() {
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/api/messages/{id}")
    public Message detailMessage(@PathVariable long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @PutMapping("/api/messages/{id}")
    public void updateMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            if(message.get().getPassword().equals(requestDto.getPassword())) {
                messageService.update(id, requestDto);
                System.out.println("수정이 완료되었습니다.");
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        }
    }

    @DeleteMapping("api/messages/{id}")
    public void deleteMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()) {
            if(message.get().getPassword().equals(requestDto.getPassword())) {
                messageRepository.deleteById(id);
                System.out.println("삭제가 완료되었습니다.");
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        }
    }

}