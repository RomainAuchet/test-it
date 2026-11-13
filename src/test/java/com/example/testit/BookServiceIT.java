package com.example.testit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;
import com.example.testit.service.BookService;

@SpringBootTest 
public class BookServiceIT {
     @Autowired
    BookService bookService;
    
    @Autowired
    BookRepository bookRepository;

    @Autowired 
    MemberRepository memberRepository;
    
    
    @Test
    public void testEmpruntEtRendu() {

        //given
        Member member = new Member("Bob");
        Book book = new Book("Le livre","l'auteur");
        memberRepository.save(member);
        bookRepository.save(book);

        //when
        bookService.borrowBook(book.getId(), member.getId());
        bookService.returnBook(book.getId(), member.getId());
        //then
        Assertions.assertThat(book.getStatus()).isEqualTo(Status.RENDU);
        
    }
}
