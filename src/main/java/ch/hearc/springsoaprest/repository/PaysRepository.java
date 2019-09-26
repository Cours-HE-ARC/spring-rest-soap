package ch.hearc.springsoaprest.repository;

import ch.hearc.springsoaprest.soap.Pays;

import java.util.List;

public interface PaysRepository {
    Pays findPaysByIsoCode(String nom);

    List<Pays> findAllPays();

    void save(Pays pays);
}
