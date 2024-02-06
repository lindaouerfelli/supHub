package com.linda.suphub.services;

import java.util.List;

public interface AbstractService <T> {

    Integer save(T dto); // enreg et la modification au mm temps pas besoin de creer une methode save et update

    List<T> findAll();

    T findById (Integer id);

    void delete (Integer id);


}
