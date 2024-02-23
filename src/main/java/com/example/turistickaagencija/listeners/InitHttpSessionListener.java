package com.example.turistickaagencija.listeners;

import com.example.turistickaagencija.controller.KorisnikController;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class InitHttpSessionListener implements HttpSessionListener {

    /** kod koji se izvrsava po kreiranju sesije */
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("Inicijalizacija sesisje HttpSessionListener...");
//
//		//pri kreiranju sesije inicijalizujemo je ili radimo neke dodatne aktivnosti
//        List<Knjiga> zaIznajmljivanje = new ArrayList<Knjiga>();
//        String registarskiBrojCK = "";
        String korsnici  = "";
        HttpSession session  = arg0.getSession();
//        System.out.println("session id korisnika je "+session.getId());
//        session.setAttribute(KnjigeController.KNJIGE_ZA_IZNAJMLJIVANJE, zaIznajmljivanje);
//
//        session.setAttribute(ClanskeKarteController.CLANSKA_KARTA, registarskiBrojCK);

        session.setAttribute(KorisnikController.KORISNIK_KEY, korsnici);
//
        System.out.println("Uspeh HttpSessionListener!");
    }

    /** kod koji se izvrsava po brisanju sesije */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("Brisanje sesisje HttpSessionListener...");

        System.out.println("Uspeh HttpSessionListener!");
    }

}