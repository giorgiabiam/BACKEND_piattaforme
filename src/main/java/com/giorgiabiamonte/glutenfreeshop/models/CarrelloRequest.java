package com.giorgiabiamonte.glutenfreeshop.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CarrelloRequest {
    private int codice_prodotto;
    private int qta;
}
