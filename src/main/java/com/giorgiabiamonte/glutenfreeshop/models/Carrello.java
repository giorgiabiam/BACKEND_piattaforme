package com.giorgiabiamonte.glutenfreeshop.models;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Component
@SessionScope   //TODO
public class Carrello {
    private int nProdotti;
    private List<ProdottoAcquistato> listaProdotti; //deve essere concorrente?
}
