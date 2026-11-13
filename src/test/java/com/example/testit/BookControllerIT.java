package com.example.testit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testit.service.BookService;

@SpringBootTest
public class BookControllerIT {
     @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookService bookService;
    @Test
    void itShouldCreateBookandborrowit()
            throws Exception {
        //mockMvc.perform(MockMvcRequestBuilders.post("/borrow").content("""
       //         {

       //         }
      //          """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
       // Mockito.verify();
    }
}
