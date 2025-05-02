package Service;

import Model.Message;

import DAO.AccountDAO;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
        MessageDAO messageDAO;
        AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public Message addMessage(Message message) {
        if( (message.getMessage_text().isEmpty()
            || message.getMessage_text().length() > 255)
            || !accountDAO.checkAccountExists(message.getPosted_by())) {
            return null;    
        }

        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        Message message = messageDAO.getMessageById(message_id);
        if(message == null) {
            return null;
        }
        messageDAO.deleteMessageById(message_id);
        return message;
    }

    public Message updateMessageById(int messageId, String messageText) {
        if( (messageText.isEmpty()
            || messageText.length() > 255)
            || !messageDAO.checkMessageExists(messageId)){
                return null;
        }

        return messageDAO.updateMessageById(messageId, messageText);
    }

    public List<Message> getAllMessagesFromUser(int accountId) {
        return messageDAO.getAllMessagesFromUser(accountId);
    }
}
