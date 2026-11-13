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

@SpringBootTest
public class BookRepositoryIT {
    @Autowired
    BookRepository bookRepository;

    @Autowired 
    MemberRepository memberRepository;
    @Test
    public void testFindByMemberAndStatus() {
        var member1 = new Member();
        var member2 = new Member();
        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);

        var book1 = new Book("le book","l'auteur");
        book1.setBorrower(member2);
        book1.setStatus(Status.EMPRUNTE);
        var book2 = new Book("le book 2","l'auteur");
        book2.setStatus(Status.EMPRUNTE);
        book2.setBorrower(member1);
        var book3 = new Book("le book 3","l'auteur");
        book3.setStatus(Status.EMPRUNTE);
        book3.setBorrower(member2);
        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        book3 = bookRepository.save(book3);
        

        
        var savedBooks = bookRepository.findByMemberAndStatus(member1.getId(), Status.EMPRUNTE);
        var savedBooks2 = bookRepository.findByMemberAndStatus(member2.getId(), Status.EMPRUNTE);

        Assertions.assertThat(savedBooks.size()).isEqualTo(1);
        Assertions.assertThat(savedBooks2.size()).isEqualTo(2);

    }
}
