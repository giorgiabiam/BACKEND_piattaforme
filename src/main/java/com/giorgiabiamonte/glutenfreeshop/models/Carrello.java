package com.giorgiabiamonte.glutenfreeshop.models;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Getter @Setter
@Component
@SessionScope
public class Carrello {
    private int nProdotti;
    private List<ProdottoAcquistato> listaProdotti; //deve essere concorrente

    //TODO
}
