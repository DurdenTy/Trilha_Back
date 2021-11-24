package Testes;

import com.desafio10.Financys.Application.Controllers.CategoryController;
import com.desafio10.Financys.Domain.Entities.Category;
import com.desafio10.Financys.Domain.Services.CategoryService;
import com.desafio10.Financys.Domain.ValueObject.Types;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(categoryController)
                .build();
    }



    @Test
    public void testandoMetodoRead() throws Exception {

        List<Category> lista = Arrays.asList(
                new Category(1L, "Entrada1", "ItemDeTeste", Types.Conta),
                new Category(2L, "Entrada2", "ItemDeTeste", Types.Salario)
        );
        Mockito.when(categoryService.read()).thenReturn(lista);
        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(categoryService, times(1)).read();
    }

    @Test
    public void testandoMetodoReadById() throws Exception {

        Category category = new Category(1L, "Entrada1", "ItemDeTeste", Types.Dispesas);
        Mockito.when(categoryService.findById(1L)).thenReturn(category);
        mockMvc.perform(get("/categorias/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(categoryService, times(1)).findById(1L);
        verifyNoMoreInteractions(categoryService);

    }

    @Test
    public void testandoMetodoCreate() throws Exception {

        Category category = new Category(1L, "Entrada1", "ItemDeTeste", Types.Dispesas);
        Mockito.when(categoryService.create(category)).thenReturn(category);
        mockMvc.perform(post("/categorias").contentType(MediaType.APPLICATION_JSON));
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void testandoMetodoUpdate() throws Exception {
        Category category = new Category(1L, "Entrada1", "ItemDeTeste", Types.Conta);
        Mockito.when(categoryService.update(category, 1L)).thenReturn(category);
        mockMvc.perform(put("/categorias/{id}", 1L).contentType(MediaType.APPLICATION_JSON));
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void testandoMetodoDelete() throws Exception {
        Category category = new Category(1L, "Entrada1", "ItemDeTeste", Types.Dispesas);
        doNothing().when(categoryService).delete(1L);

        mockMvc.perform(delete("/categorias/{id}", 1))
                .andExpect(status().isOk());
        verify(categoryService, times(1)).delete(category.getId());
        verifyNoMoreInteractions(categoryService);
    }

}
