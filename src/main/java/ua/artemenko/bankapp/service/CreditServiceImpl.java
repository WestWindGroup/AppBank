package ua.artemenko.bankapp.service;

import ua.artemenko.bankapp.dao.CreditDao;
import ua.artemenko.bankapp.model.Credit;

import java.util.List;


public class CreditServiceImpl implements CreditService {
    private CreditDao creditDao;

    public void setCreditDao(CreditDao creditDao) {
        this.creditDao = creditDao;
    }

    @Override
    public int addCredit(Credit credit) {
        return this.creditDao.addCredit(credit);
    }

    @Override
    public Credit getCreditById(int id) {
        return this.creditDao.getCreditById(id);
    }

    @Override
    public List<Credit> getListCredit() {

        return this.creditDao.getListCredit();
    }

    @Override
    public void updateCredit(Credit credit) {
        this.creditDao.updateCredit(credit);
    }
}
