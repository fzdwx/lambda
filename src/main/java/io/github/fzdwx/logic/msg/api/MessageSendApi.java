package io.github.fzdwx.logic.msg.api;

import io.github.fzdwx.inf.common.web.core.Context;
import io.github.fzdwx.inf.common.web.model.Rest;
import io.github.fzdwx.logic.contants.ChatConst;
import io.github.fzdwx.logic.msg.api.model.SendChatMessageReq;
import io.github.fzdwx.logic.msg.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/5 18:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageSendApi {

    private final MessageService messageService;

    /**
     * 向所有人发送消息
     */
    @PostMapping("/sendAll")
    public Rest<Object> sendAll(@RequestBody final SendChatMessageReq sendChatMessageReq) {
        sendChatMessageReq.setFromId(Long.valueOf(Context.user().getId()));
        sendChatMessageReq.setToId(ChatConst.SessionType.ALL);
        sendChatMessageReq.setSessionType(ChatConst.SessionType.ALL);
        sendChatMessageReq.setMsgFrom(ChatConst.MsgFrom.USER);
        return Rest.of(() -> messageService.send(sendChatMessageReq));
    }

    /**
     * 向群发送消息
     */
    @PostMapping("/group/{groupId}")
    public void group(@RequestBody final SendChatMessageReq sendChatMessageReq, @PathVariable final Long groupId) {
        sendChatMessageReq.setFromId(Long.valueOf(Context.user().getId()));
        sendChatMessageReq.setToId(groupId);
        sendChatMessageReq.setSessionType(ChatConst.SessionType.ALL);
        sendChatMessageReq.setMsgFrom(ChatConst.MsgFrom.USER);
        // TODO: 2022/4/6
        // messageService.send(chatMessageVO);
    }
}