package shop.foreveryone.week03_individualtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.foreveryone.week03_individualtask.domain.Message;
import shop.foreveryone.week03_individualtask.domain.MessageRepository;
import shop.foreveryone.week03_individualtask.domain.MessageRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public long update(Long id, MessageRequestDto requestDto) {
        Message message = messageRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("게시글을 찾을 수 없습니다.")
        );
        message.update(requestDto);
        return message.getId();
    }
}
