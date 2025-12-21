package com.board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.pr.domain.board.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
    
    @Autowired
    BoardRepository boardRepository;

    @AfterEach //테스트가 하나 끝날 때마다 실행되는 메서드
    public void cleanup(){
        /**
         * 테스트 데이터가 DB에 남아있으면 다음 테스트에 영향을 줄 수 있다.
         * 각 테스트가 끝나면 DB를 깨끗하게 비워주는 역할
         */
        boardRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 본문입니다.";

        //빌더 패턴을 사용하여 테스트용 게시글 엔터티를 생성하고 저장합니다.
        
    }
}
