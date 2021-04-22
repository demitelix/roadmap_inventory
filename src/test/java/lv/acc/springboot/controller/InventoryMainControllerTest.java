package lv.acc.springboot.controller;

import lv.acc.springboot.service.BookManagmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(InventoryMainController.class)
class InventoryMainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookManagmentService service;

    @Test
    void index() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andReturn();
    }

    @Test
    void allBooks() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/allBooks")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("viewbooks"))
                .andReturn();
    }

    @Test
    void returnBook() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/1/returnBook")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/allBooks"))
                .andReturn();
    }

    @Test
    void reserveBook() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/1/reserveBook")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/allBooks"))
                .andReturn();
    }

    @Test
    void newBookForm() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/newbookform")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("newbookform"))
                .andReturn();
    }

    @Test
    void addNewBook() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/addnewbook")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("addnewbookresult"))
                .andReturn();
    }

    @Test
    void findByIdForm() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/findbyidform")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("findbyidform"))
                .andReturn();

    }

    @Test
    void findByTitleForm() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/findbytitleform")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("findbytitleform"))
                .andReturn();

    }

    @Test
    void findById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/findbyid")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("viewbooks"))
                .andReturn();

    }

    @Test
    void findByTitle() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/findbytitle")
                .accept(MediaType.ALL);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("viewbooks"))
                .andReturn();

    }
}