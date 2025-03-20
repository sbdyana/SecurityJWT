package com.cos.securityjwt.service;

import com.cos.securityjwt.dto.JoinDTO;
import com.cos.securityjwt.entity.UserEntity;
import com.cos.securityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 스프링에게 관리되도록 선언
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
//    public JoinService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        // 이미 존재하는지 확인하기
        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            return; // 강제 종료
        }

        UserEntity data = new UserEntity();
        data.setUsername(username);
        data.setPassword(password);
        // data.setRole();
    }
}
