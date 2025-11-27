package com.creators.youbank_dummy.application;

import com.creators.youbank_dummy.Dummy;
import com.creators.youbank_dummy.application.common.BankAccountRepository;
import com.creators.youbank_dummy.entity.InsurePolicy;
import com.creators.youbank_dummy.entity.InsureTerm;
import com.creators.youbank_dummy.entity.InsureTermYN;
import com.creators.youbank_dummy.entity.common.BankAccount;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

public class InsureTermYNDummy extends Dummy {

    @Autowired
    private InsurePolicyRepository insurePolicyRepository;
    @Autowired
    private InsureTermRepository insureTermRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    private List<InsurePolicy> insurePolicyList;
    private List<InsureTerm> insureTermList;
    private List<BankAccount> bankAccountList;
    @Autowired
    private InsureTermYNRepository insureTermYNRepository;

    @PostConstruct
    void BeforeAll() {
        insurePolicyList = insurePolicyRepository.findAll();
        insureTermList = insureTermRepository.findAll();
        bankAccountList = bankAccountRepository.findAll();
    }

    @Test
    @Rollback(false)
    void insertProducts() {
        final int SIZE = 100;

        for (int i = 0; i < SIZE; i++) {
            InsureTermYN p = generateTermYN();
            insureTermYNRepository.save(p);
            System.out.println(p);
        }
        insureTermYNRepository.flush();
    }

    private InsureTermYN generateTermYN() {

        int index = faker.random().nextInt(insureTermList.size());
        return InsureTermYN.builder()
                .term(insureTermList.get(index))
                .policy(insurePolicyList.get(faker.random().nextInt(insurePolicyList.size())))
                .termYn(insureTermList.get(index).getTermType() == 0 ? "Y" : "N")
                .build();
    }

}
