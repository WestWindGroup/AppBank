package ua.artemenko.bankapp.service;

import ua.artemenko.bankapp.model.Credit;

import java.util.List;

public interface CreditService {

    int addCredit(Credit credit);

    Credit getCreditById(int id);

    List<Credit> getListCredit();

    void updateCredit(Credit credit);
}
