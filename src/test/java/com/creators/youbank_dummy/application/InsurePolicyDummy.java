package com.creators.youbank_dummy.application;

import com.creators.youbank_dummy.Dummy;
import com.creators.youbank_dummy.application.common.BankAccountRepository;
import com.creators.youbank_dummy.application.common.CustomerRepository;
import com.creators.youbank_dummy.entity.InsurePolicy;
import com.creators.youbank_dummy.entity.InsureProduct;
import com.creators.youbank_dummy.entity.common.BankAccount;
import com.creators.youbank_dummy.entity.common.Customer;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class InsurePolicyDummy extends Dummy {
    @Autowired
    private InsureProductRepository insureProductRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private InsurePolicyRepository insurePolicyRepository;
    List<InsureProduct> insureProductList;
    List<BankAccount> customerList;

    @PostConstruct
    public void init() {
        insureProductList = insureProductRepository.findAll();
        customerList = bankAccountRepository.findAll();
    }


    @Test
    @Rollback(false)
    void insertPolicy() {
        final int SIZE = 2500;

        for (int i = 0; i < SIZE; i++) {
            InsurePolicy ip = generatePolicy();
            insurePolicyRepository.save(ip);
        }
        insurePolicyRepository.flush();
    }

    private static int policyIdCounter = 1;
    private String generatePolicyId() {
        // "P" + 5자리 숫자 형식으로 반환
        String id = String.format("PO%05d", policyIdCounter);
        policyIdCounter++; // 다음 제품을 위해 증가
        return id;
    }

    private static int productId = 1;
    public InsurePolicy generatePolicy() {
        return InsurePolicy.builder()
                .policyId(generatePolicyId())
                .customerId(customerList.get(faker.random().nextInt(customerList.size())).getCustomer())
                .productId(insureProductList.get(faker.random().nextInt(insureProductList.size())))
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now()))
                .modifyDate(Date.valueOf(LocalDate.now()))
                .premiumAmount(faker.random().nextInt(10000, 150000))
                .payDate("24")
                .payType(faker.random().nextInt(1, 3))
                .status(faker.random().nextInt(1, 3))
                .build();
    }

}
