package com.board.pr.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * JpaRepository를 상속받으면 기본적인 CRUD 메서드(save, find, delete 등)가 자동 생성된다.
 * <Board, Long>은 <엔티티 클래스, PK 타입> 을 의미한다.
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
    
}
