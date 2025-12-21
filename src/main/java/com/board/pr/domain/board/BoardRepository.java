package com.board.pr.domain.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * JpaRepository를 상속받으면 기본적인 CRUD 메서드(save, find, delete 등)가 자동 생성된다.
 * <Board, Long>은 <엔티티 클래스, PK 타입> 을 의미한다.
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
    /**
     * Spring Data JPA에서 제공하지 않는 복잡한 쿼리는 @Query를 사용하여 직접 수정할 수 있다.
     * 여기서는 최신순 정렬을 위해 'order by id desc'를 추가
     */
    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    List<Board> findAllDesc();
}
