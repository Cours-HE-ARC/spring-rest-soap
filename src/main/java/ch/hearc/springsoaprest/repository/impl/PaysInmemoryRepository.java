package ch.hearc.springsoaprest.repository.impl;

import ch.hearc.springsoaprest.repository.PaysRepository;
import ch.hearc.springsoaprest.soap.Monnaie;
import ch.hearc.springsoaprest.soap.Pays;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PaysInmemoryRepository implements PaysRepository {

    private static final Map<String, Pays> paysData = new HashMap<>();


    @PostConstruct
    public void initData() {
        Pays espagne = new Pays();
        espagne.setIsoCode("ESP");
        espagne.setNom("Espagne");
        espagne.setCapitale("Madrid");
        espagne.setMonnaie(Monnaie.EUR);
        espagne.setPopulation(46704314);
        paysData.put(espagne.getIsoCode(), espagne);

        Pays pologne = new Pays();
        pologne.setIsoCode("POL");
        pologne.setNom("Pologne");
        pologne.setCapitale("Varsovie");
        pologne.setMonnaie(Monnaie.PLN);
        pologne.setPopulation(38186860);

        paysData.put(pologne.getIsoCode(), pologne);

        Pays uk = new Pays();
        uk.setIsoCode("GBR");
        uk.setNom("Royaume Uni");
        uk.setCapitale("Londres");
        uk.setMonnaie(Monnaie.GBP);
        uk.setPopulation(63705000);
        paysData.put(uk.getIsoCode(), uk);
    }

    @Override
    public Pays findPaysByIsoCode(String iso) {
        Assert.notNull(iso, "Le code iso du pays ne peut pas être nulle");
        return paysData.get(iso.toUpperCase());
    }

    @Override
    public List<Pays> findAllPays(){
        return paysData.values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(Pays pays) {
        paysData.put(pays.getIsoCode(),pays);
    }


}
