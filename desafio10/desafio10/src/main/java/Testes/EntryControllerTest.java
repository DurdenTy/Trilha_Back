package Testes;

import com.desafio10.Financys.Application.Controllers.EntryController;
import com.desafio10.Financys.Domain.Entities.Category;
import com.desafio10.Financys.Domain.Entities.Entry;
import com.desafio10.Financys.Domain.Services.EntryService;
import com.desafio10.Financys.Domain.ValueObject.Types;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EntryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EntryService entryService;

    @InjectMocks
    private EntryController entryController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entryController)
                .build();
    }

    @Test
    public void testandoMetodoRead() throws Exception {
        List<Entry> lista = Arrays.asList(
                new Entry(1L, "Entrada1", "ObjetoDeTeste", "23/09/2021",
                        "232", Types.Conta, true,  1L),
                new Entry(2L, "Entrada2", "ObjetoDeTeste", "13/03/2021",
                        "438", Types.Salario, true,  2L)
        );

        Mockito.when(entryService.read()).thenReturn((Stream<Entry>)lista);
        mockMvc.perform(get("/lancamentos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(entryService, times(1)).read();
    }

    @Test
    public void testandoMetodoReadById() throws Exception {

                Entry entry = new Entry(1L, "Entrada1", "ObjetoDeTeste", "23/09/2021",
                        "232", Types.Conta, true,  1L);
        Mockito.when(entryService.findById(1L)).thenReturn(entry);
        mockMvc.perform(get("/lancamentos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(entryService, times(1)).findById(1L);
        verifyNoMoreInteractions(entryService);
    }

    @Test
    public void testandoMetodoCreat() throws Exception {
        Entry entry = new Entry(1L, "Entrada1", "ObjetoDeTeste", "23/09/2021",
                "232", Types.Conta, true,  1L);
        Mockito.when(entryService.create(entry)).thenReturn(entry);
        mockMvc.perform(post("/lancamentos"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(entryService, times(1)).create(entry);
        verifyNoMoreInteractions(entryService);
    }

    @Test
    public void testandoMetodoUpdate() throws Exception {

        Entry entry = new Entry(1L, "Entrada1", "ObjetoDeTeste", "23/09/2021",
                "232", Types.Conta, true,  1L);
        Mockito.when(entryService.update(entry, entry.getId())).thenReturn(entry);
        mockMvc.perform(put("/categorias", entry.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(entryService, times(1)).update(entry, 1L);
        verifyNoMoreInteractions(entryService);
    }

    @Test
    public void testandoMetodoDelete() throws Exception {
        Entry entry = new Entry(1L, "Entrada1", "ObjetoDeTeste", "23/09/2021",
                "232", Types.Conta, true,  1L);
        doNothing().when(entryService).delete(1L);

        mockMvc.perform(delete("/lancamentos/{id}", 1))
                .andExpect(status().isOk());
        verify(entryService, times(1)).delete(entry.getId());
        verifyNoMoreInteractions(entryService);
    }

    @Test
    public void TestandoGetLancamentosDependentes() throws Exception {
        List<Entry> lista = Arrays.asList(
                new Entry(1L, "Entrada1", "ObjetoDeTeste", "23/09/2021",
                        "232", Types.Conta, true,  1L),
                new Entry(2L, "Entrada2", "ObjetoDeTeste", "13/03/2021",
                        "438", Types.Salario, true,  2L)
        );
        Mockito.when(entryService.getLancamentosDependentes("23/09/2021", "438", true
                )).thenReturn(lista);
        mockMvc.perform(get("/lancamentos/filter"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        
    }


}
