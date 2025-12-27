package com.board.pr.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.pr.domain.board.Board;
import com.board.pr.domain.board.BoardRepository;
import com.board.pr.domain.user.User;
import com.board.pr.domain.user.UserRepository;
import com.board.pr.web.dto.BoardListResponseDto;
import com.board.pr.web.dto.BoardResponseDto;
import com.board.pr.web.dto.BoardSaveRequestDto;
import com.board.pr.web.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 선언된 필드(Repository)에 대해 생성자를 자동 생성하여 의존성 주입
@Service
public class BoardService {
    
    // @RequiredArgsConstructor 때문에 @Autowired 안해도 됨
    private final BoardRepository boardRepository;
    private final UserRepository userRepository; //사용자 정보를 찾기 위해 주입

    @Transactional
    public Long save(BoardSaveRequestDto requestDto, String email){
        // 리포지토리를 통해 엔티티를 DB에 저장하고, 생성된 ID 반환
        //1. 현재 로그인한 유저의 정보를 DB에서 가져온다.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email=" + email));

        // DTO를 엔티티로 변환할 때 조회한 User 객체를 전달하여 연관관계를 맺습니다.
        //2. DTO에 조회한 유저 정보를 실어서 엔티티로 변환 후 저장
        return boardRepository.save(requestDto.toEntity(user)).getId();
    }

    /**
     * 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도를 개선.
     * 등록, 수정, 삭제 기능이 전혀 없는 메서드에서 사용하면 성능상 이점이 있습니다.
     */
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new) //결과로 넘어온 Board 엔티티들을 DTO로 변환
                .collect(Collectors.toList()); //변환된 DTO들을 리스트로 묶어서 반환
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id = " + id));
        return new BoardResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto, String currentEmail){
        Board board = boardRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        if (!board.getUser().getEmail().equals(currentEmail)){
            throw new IllegalArgumentException("본인의 글만 수정할 수 있습니다.");
        }
        
        board.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    // @Transactional
    // public Long save(BoardSaveRequestDto requestDto, String email){
    //     //1. 세션에서 넘어온 이메일을 이용해 DB에서 사용자 엔티티를 찾는다.
    //     User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email = " + email));
        
    //     //2. DTO를 엔티티로 변환할 때 찾은 사용자(User) 정보를 함께 전달합니다.
    //     // (Board 엔티티의 빌더에 user가 추가되어 있어야 합니다.)
    //     return boardRepository.save(requestDto.toEntity(user)).getId();
    // }

    @Transactional
    public void delete(Long id, String email) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    
        /**
         * [강력한 권한 검증]
         * 본인이 작성한 글이거나, 현재 사용자의 권한이 ADMIN인 경우에만 삭제를 허용합니다.
         */
        boolean isAuthor = board.getUser().getEmail().equals(email);
        boolean isAdmin = currentUser.getRole().name().equals("ADMIN");
    
        if (!isAuthor && !isAdmin) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
    
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> search(String keyword){
        return boardRepository.findByTitleContainingOrContentContainingOrderByIdDesc(keyword, keyword).stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }
}
