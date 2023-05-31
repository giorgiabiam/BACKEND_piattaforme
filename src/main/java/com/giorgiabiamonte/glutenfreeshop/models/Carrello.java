package com.giorgiabiamonte.glutenfreeshop.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Getter @Setter @ToString
@Component
public class Carrello {
    private double totale;
    private List<CarrelloItem> lista;

    public Carrello(){
        totale = 0d;
        lista = new LinkedList<>();
    }
}
