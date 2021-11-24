package Testes;


import com.desafio10.Financys.Application.Exceptions.NoContentException;
import com.desafio10.Financys.Application.Exceptions.ParametrosNulos;
import com.desafio10.Financys.Desafio10Application;
import com.desafio10.Financys.Domain.Entities.Entry;
import com.desafio10.Financys.Domain.Services.EntryService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Desafio10Application.class)
public class TrilhaBackTestes{

    @Autowired
    private EntryService entryService;

    @Test
    public void entryServiceFoiInstanciado(){
        assertNotNull(entryService);
    }

    @Test
    public void funcaoGetLancamentosDependentesExceptionTodosParametrosNulos(){
        ParametrosNulos throwns = assertThrows(ParametrosNulos.class, () ->
                entryService.getLancamentosDependentes(null, null, null));
    }

    @Test
    public void funcaoGetLancamentosDependentesExceptionNotContentFunciona(){


        Entry entry = new Entry();
        entry.setAmount("312,23");
        entry.setDate("31/12/2021");
        entry.setPaid(false);

        entryService.create(entry);

        NoContentException thrown = assertThrows(NoContentException.class, () ->
                entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

    }

    @Test
    public void funcaoGetLancamentosDependentesAchaouAlgumValor(){

        Entry entry = new Entry();
        entry.setAmount("33,23");
        entry.setDate("29/12/2021");
        entry.setPaid(true);

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesAchaouAlgumValorEMostrar(){

        Entry entry = new Entry();
        entry.setAmount("33,23");
        entry.setDate("29/12/2021");
        entry.setPaid(true);

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesDataNula(){

        Entry entry = new Entry();
        entry.setAmount("33,23");
        entry.setPaid(true);

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesMontanteNulo(){

        Entry entry = new Entry();
        entry.setDate("29/12/2021");
        entry.setAmount("33,23");
        entry.setPaid(true);

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesCondicaoPagoNulo(){

        Entry entry = new Entry();
        entry.setDate("29/12/2021");
        entry.setAmount("33,23");

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesCondicaoPagoNuloEDataNula(){

        Entry entry = new Entry();
        entry.setAmount("3");

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "3", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "3", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesCondicaoDataEMontanteNula(){

        Entry entry = new Entry();
        entry.setPaid(true);

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

    @Test
    public void funcaoGetLancamentosDependentesCondicaoPagoNuloEMontanteNulos(){

        Entry entry = new Entry();
        entry.setDate("29/12/2021");

        entryService.create(entry);

        assertNotNull(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));

        System.out.println(entryService.getLancamentosDependentes("29/12/2021", "33,23", true));
    }

}
