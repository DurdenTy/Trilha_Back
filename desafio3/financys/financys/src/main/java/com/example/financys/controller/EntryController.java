package com.example.financys.controller;

import com.example.financys.category.Category;
import com.example.financys.entry.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class EntryController {

    List<Entry> lista = new ArrayList<Entry>();

    @PostMapping("/lancamentos")
    public int create(@RequestBody Entry entry){

        lista.add(entry);
        return lista.indexOf(entry);

    }

    @GetMapping("/lancamentos")
    public List<Entry> read(){

        sortDates(lista);

        return lista;
    }

    //Método para organizar o retorno da lista por datas, prefiri deixar separado em uma função, para deixar
    //método get mais limpo.
    public static void sortDates(List<Entry> list){

        //Existem duas formas de fazer essa organização, ambas funcionam, deixei as duas como exemplo.


        list.sort(Comparator.comparing(Entry::getDate));
        
        //list.sort((value, value2) -> value.getDate().compareTo(value2.getDate()));

    }

}
