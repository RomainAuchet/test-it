package com.example.testit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;
import com.example.testit.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MailService mailService;
    @InjectMocks
    private BookService bookService;

    @Test
    void verifierLivreEmprunt() {
    
        //given 
        List<Book> ListeVide = new ArrayList();
        BookService bookService = new BookService(bookRepository, memberRepository, mailService);
        Book book = new Book("test","testeur");
        book.setStatus(Status.DISPONIBLE);
        Member member = new Member("john");
        Mockito.when(bookRepository.findByMemberAndStatus(1l, Status.RENDU)).thenReturn(ListeVide);
        Mockito.when(memberRepository.findById(1l)).thenReturn(Optional.of(member));
        Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        
        //when 
        bookService.borrowBook(1l, 1l);
        
        //then
        Assertions.assertThat(book.getStatus()).isEqualTo(Status.EMPRUNTE);
        Assertions.assertThat(book.getBorrower()).isEqualTo(member);
        Mockito.verify(bookRepository).findByMemberAndStatus(1l, Status.RENDU);
        
    }
    @Test
    void verifierLivreRetour() {
    
        //given 
        List<Book> ListeVide = new ArrayList();
        BookService bookService = new BookService(bookRepository, memberRepository, mailService);
        Book book = new Book("test","testeur");
        book.setStatus(Status.EMPRUNTE);
        Member member = new Member("john");
        Mockito.when(bookRepository.findByMemberAndStatus(1l, Status.RENDU)).thenReturn(ListeVide);
        Mockito.when(memberRepository.findById(1l)).thenReturn(Optional.of(member));
        Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        
        //when 
        bookService.borrowBook(1l, 1l);
        
        //then
        Assertions.assertThat(book.getStatus()).isEqualTo(Status.EMPRUNTE);
        Assertions.assertThat(book.getBorrower()).isEqualTo(member);
       // Mockito.verify()
       // Mockito.verify(virementRepository).save(Mockito.any());
       
    }


}
