package com.cos.securityjwt.service;

import com.cos.securityjwt.dto.JoinDTO;
import com.cos.securityjwt.entity.UserEntity;
import com.cos.securityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 스프링에게 관리되도록 선언
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
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
        data.setPassword(bCryptPasswordEncoder.encode(password)); // 무조건 암호화 진행해서 넣어야 함 > BCryptPasswordEncoder 빈으로 등록해놨기 때문에 주입받아서 쓸 것임
        data.setRole("ROLE_ADMIN"); // "ROLE_ 은 고정이고 그 뒤에 원하는 권한
        // 회원 정보 저장

        userRepository.save(data);
    }
}
