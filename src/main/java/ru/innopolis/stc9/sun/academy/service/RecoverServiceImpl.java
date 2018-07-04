package ru.innopolis.stc9.sun.academy.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
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
    public RecoverServiceImpl(@Qualifier("hashDAOHibernate") HashDAO hashDAO) {
        this.hashDAO = hashDAO;
    }

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;


    @Override
    public boolean sendMail(UserDTO userDTO,HashDTO hashDTO) {
      try {
          SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
          mailMessage.setText(String.format(mailMessage.getText(),hashDTO.getHash()));
          //mailSender.send(mailMessage);
          LOGGER.info("Mail sended");
          LOGGER.info(mailMessage.getText());
          return true;
      } catch (MailException mailException) {
        LOGGER.error( mailException.getMessage(),mailException);
        return false;
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

    @Override
    public HashDTO getHashByHash(String hash) {
        HashDTO hashDTO = HashMapper.toDto(hashDAO.getByHash(hash));
        if(hashDTO.getRecovered()==0){
            return hashDTO;
        } else
        {return null;}
    }


    @Override
    public boolean setPassword(UserDTO userDTO) {
        HashDTO hashDTO = new HashDTO();
        hashDTO.setUserid(userDTO.getId());
        hashDTO.setRecovered(1);
        updateHash(hashDTO);
        userDTO.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
        return  userService.updateUser(userDTO);
    }

    @Override
    public boolean updateHash(HashDTO hashDTO) {
        return hashDAO.update(HashMapper.toEntity(hashDTO));
    }

    @Override
    public UserDTO passRecovery(String hash) {
        UserDTO recoveredUser = null;
        HashDTO recoveredHash = getHashByHash(hash);
        if(recoveredHash!=null) recoveredUser =userService.getUserById(recoveredHash.getUserid());
        if(recoveredUser!=null) recoveredUser.setPassword("");
        return recoveredUser;
    }
}
