package ua.artemenko.bankapp.dao;

import ua.artemenko.bankapp.model.Credit;

import java.util.List;

public interface CreditDao {

    int addCredit(Credit credit);

    Credit getCreditById(int id);

    List<Credit> getListCredit();

    void updateCredit(Credit credit);
}
