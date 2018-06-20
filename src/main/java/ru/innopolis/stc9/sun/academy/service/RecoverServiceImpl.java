package ru.innopolis.stc9.sun.academy.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.controllers.IndexController;
import ru.innopolis.stc9.sun.academy.dao.HashDAO;
import ru.innopolis.stc9.sun.academy.dto.HashDTO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.dto.mapper.HashMapper;
import ru.innopolis.stc9.sun.academy.entity.Hash;

import java.util.UUID;

@Service
public class RecoverServiceImpl implements RecoverService {
    private final HashDAO hashDAO;
    private static final Logger LOGGER = Logger.getLogger(RecoverServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    public RecoverServiceImpl(HashDAO hashDAO) {
        this.hashDAO = hashDAO;
    }

    @Override
    public boolean sendMail(UserDTO userDTO,HashDTO hashDTO) {
        try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {
            context.load("classpath:applicationContext.xml");
            context.refresh();
            JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
            SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);

            SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
            mailMessage.setTo(userDTO.getEmail());
            mailMessage.setText("Добрый день. Ссылка на восстановление - /recover?hash=" + hashDTO.getHash());
            try {
                //mailSender.send(mailMessage);
                LOGGER.info("Mail sended");
                LOGGER.info(mailMessage.getText());
                return true;
            } catch (MailException mailException) {
                LOGGER.error( mailException.getMessage(),mailException);
                return false;
            }
        }
    }

    @Override
    public boolean makeHash(UserDTO userDTO) {
        UserDTO recoveredUser =userService.getUserByEmail(userDTO.getEmail());
        if(recoveredUser != null)
        {
            Hash hash = new Hash(UUID.randomUUID().toString(),recoveredUser.getId(),0);
            HashDTO hashDTO = HashMapper.toDto(hash);
            LOGGER.info("Making hash for " + recoveredUser.getEmail() );
            if(hashDAO.add(hash))
            {
                return sendMail(recoveredUser,hashDTO);
            }
            else
            {
                return false;
            }
        } else {
            return false;
        }
    }
}
