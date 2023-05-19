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
        u.setConvenzionato(true);
        u.setPassword(passwordEncoder.encode("12345"));
        u.setUsername("giobiam");
        u.setIndirizzo("via aldo moro");
        u.setSaldo(90);

        ur.save(u);
    }

    private void initProdotti() {
        ProdottoInMagazzino p = ProdottoInMagazzino.builder().nome("Panini morbidi Doria").qta(75).prezzo(3.89)
                .descrizione("Panini morbidi senza glutine e senza lattosio - Doria")
                .img("https://www.farmaexpert.it/src/29wm.php?p=EX&cat=204282.jpeg").build();

        ProdottoInMagazzino p1 = ProdottoInMagazzino.builder().nome("Panbauletto Nutrifree").qta(200).prezzo(3.42)
                .descrizione("Pane bianco senza glutine e senza lattosio con pasta madre - Nutrifree")
                .img("https://cdn.shopify.com/s/files/1/0284/2450/3375/products/Nutrifreepanbauletto_1800x1800.png?v=1676458092").build();


        ProdottoInMagazzino p2 = ProdottoInMagazzino.builder().nome("Pizza Mergherita").qta(100).prezzo(4.69)
                .descrizione("Pizza margehrita senza glutine e senza lattosio (prodotto surgelato) - Buitoni ")
                .img("https://www.carrefour.it/on/demandware.static/-/Sites-carrefour-master-catalog-IT/default/dw65dbd002/large/SGPIZZAMARGHERITABUITONISG-7613287199089-1.png").build();

        ProdottoInMagazzino p3 = ProdottoInMagazzino.builder().nome("Farina di riso").qta(30).prezzo(1.54)
                .descrizione("Farina di riso naturalmente senza glutine - Pedon")
                .img("https://it.etalianfood.com/pub/media/catalog/product/cache/80d2192410dad911a8a678a81e478d2b/r/i/rice-flour-pedon.jpg").build();

        ProdottoInMagazzino p4 = ProdottoInMagazzino.builder().nome("Golomix").qta(50).prezzo(3.68)
                .descrizione("Biscotti al cioccolato con cacao senza glutine- Piaceri Mediterranei")
                .img("https://labottegadelceliacoshop.it/wp-content/uploads/2022/09/X-Golomix-biscotto-piaceri-mediterranei.png").build();

        ProdottoInMagazzino p5 = ProdottoInMagazzino.builder().nome("Mezze penne rigate").qta(300).prezzo(2.99)
                .descrizione("Pasta senza glutine di riso e mais - Rummo")
                .img("https://openfarma.it/image/cache/data/pharmadb/972462701-900x900.jpg").build();

        ProdottoInMagazzino p6 = ProdottoInMagazzino.builder().nome("Fusilli").qta(300).prezzo(2.99)
                .descrizione("Pasta di farina di lenticchie rosse senza glutine - Felicia")
                .img("https://d2f5fuie6vdmie.cloudfront.net/asset/imported/ita/2021/04/21/78ee7c367768f9d1cc6498cbc9f2acdc38454e98.jpeg").build();

        ProdottoInMagazzino p7 = ProdottoInMagazzino.builder().nome("Cornetti all'albicocca").qta(50).prezzo(5.16)
                .descrizione("Cornetti senza glutine morbidi e fragranti, ripieni di farcitura all’albicocca - Nutrifree")
                .img("https://openfarma.it/image/cache/catalog/dottssa%20ventrella/nutrif%20corn%20alb-900x900.jpg").build();


        ProdottoInMagazzino p8 = ProdottoInMagazzino.builder().nome("Madeleine al limone").qta(50).prezzo(3.78)
                .descrizione("Madeleine al limone con farina di riso - Cereal")
                .img("https://www.marconifarma.it/media/000/043/029/f/500x500-cereal-sg-madeleine-limone180g.png").build();

        ProdottoInMagazzino p9 = ProdottoInMagazzino.builder().nome("Cornetti alla panna").qta(50).prezzo(4.99)
                .descrizione("Cornetto gelato alla panna - Algida")
                .img("https://www.farmaciafarneti.it/images/detailed/61/975062288.jpg").build();

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
