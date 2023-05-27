package com.giorgiabiamonte.glutenfreeshop.config;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {
    @Autowired
    UtenteRepository ur;
    @Autowired
    ProdottoRepository pr;

    @Override
    public void run(String... args) throws Exception {
        initUtenti();
        initProdotti();
    }

    private void initUtenti() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Utente u=new Utente();
        u.setNome("giorgia");
        u.setCognome("biamonte");
        u.setPassword(passwordEncoder.encode("12345"));
        u.setUsername("giobiam");
        u.setIndirizzo("via aldo moro");
        u.setRuolo("USER");
        ur.save(u);

        Utente u1=new Utente();
        u1.setNome("adminglf");
        u1.setCognome("adminglf");
        u1.setPassword(passwordEncoder.encode("adminglf"));
        u1.setUsername("adminglf");
        u1.setIndirizzo("via del campo");
        u1.setRuolo("ADMIN");
        ur.save(u1);
    }

    private void initProdotti() {
        ProdottoInMagazzino p = ProdottoInMagazzino.builder().nome("Panini morbidi Doria").qta(75).prezzo(3.89)
                .descrizione("Panini morbidi senza glutine e senza lattosio - Doria")
                .img("https://localhost:8443/images/panini-doria.jpg").build();

        ProdottoInMagazzino p1 = ProdottoInMagazzino.builder().nome("Panbauletto Nutrifree").qta(200).prezzo(3.42)
                .descrizione("Pane bianco senza glutine e senza lattosio con pasta madre - Nutrifree")
                .img("https://localhost:8443/images/Nutrifreepanbauletto.jpg").build();


        ProdottoInMagazzino p2 = ProdottoInMagazzino.builder().nome("Pizza Mergherita").qta(100).prezzo(4.69)
                .descrizione("Pizza margehrita senza glutine e senza lattosio (prodotto surgelato) - Buitoni ")
                .img("https://localhost:8443/images/PIZZAMARGHERITABUITONI.jpg").build();

        ProdottoInMagazzino p3 = ProdottoInMagazzino.builder().nome("Farina di riso").qta(30).prezzo(1.54)
                .descrizione("Farina di riso naturalmente senza glutine - Pedon")
                .img("https://localhost:8443/images/rice-flour-pedon.jpg").build();

        ProdottoInMagazzino p4 = ProdottoInMagazzino.builder().nome("Golomix").qta(50).prezzo(3.68)
                .descrizione("Biscotti al cioccolato con cacao senza glutine- Piaceri Mediterranei")
                .img("https://localhost:8443/images/Golomix-biscotto.jpg").build();

        ProdottoInMagazzino p5 = ProdottoInMagazzino.builder().nome("Mezze penne rigate").qta(300).prezzo(2.99)
                .descrizione("Pasta senza glutine di riso e mais - Rummo")
                .img("https://localhost:8443/images/penne-rummo.jpg").build();

        ProdottoInMagazzino p6 = ProdottoInMagazzino.builder().nome("Fusilli").qta(300).prezzo(2.99)
                .descrizione("Pasta di farina di lenticchie rosse senza glutine - Felicia")
                .img("https://localhost:8443/images/fusilli-lenticchie.jpg").build();

        ProdottoInMagazzino p7 = ProdottoInMagazzino.builder().nome("Cornetti all'albicocca").qta(50).prezzo(5.16)
                .descrizione("Cornetti senza glutine morbidi e fragranti, ripieni di farcitura all’albicocca - Nutrifree")
                .img("https://localhost:8443/images/cornetti-alb.jpg").build();


        ProdottoInMagazzino p8 = ProdottoInMagazzino.builder().nome("Madeleine al limone").qta(50).prezzo(3.78)
                .descrizione("Madeleine al limone con farina di riso - Cereal")
                .img("https://localhost:8443/images/cereal-madeleine-limone.jpg").build();

        ProdottoInMagazzino p9 = ProdottoInMagazzino.builder().nome("Cornetti alla panna").qta(50).prezzo(4.99)
                .descrizione("Cornetto gelato alla panna - Algida")
                .img("https://localhost:8443/images/cornetti-panna.jpg").build();

        pr.save(p);
        pr.save(p1);
        pr.save(p2);
        pr.save(p3);
        pr.save(p4);
        pr.save(p5);
        pr.save(p6);
        pr.save(p7);
        pr.save(p8);
        pr.save(p9);
    }
}
