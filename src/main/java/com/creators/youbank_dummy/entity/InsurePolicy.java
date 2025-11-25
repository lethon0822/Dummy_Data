package com.creators.youbank_dummy.entity;

import com.creators.youbank_dummy.entity.common.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_INSURE_POLICY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurePolicy {

    @Id
    @Column(name = "POLICY_ID", length = 10, nullable = false)
    private String policyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private InsureProduct productId;   // TB_INSURE_PRODUCT 참조

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", nullable = false)
    private Customer customerId;       // TB_CUST_MST 참조

    @Column(name = "START_DATE", nullable = false)
    private java.sql.Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private java.sql.Date endDate;

    @Column(name = "MODIFY_DATE")
    private java.sql.Date modifyDate;

    @Column(name = "PREMIUM_AMOUNT", nullable = false)
    private Integer premiumAmount;

    @Column(name = "PAY_DATE", length = 10, nullable = false)
    private String payDate;

    @Column(name = "PAY_TYPE", nullable = false)
    private Integer payType;

    @Column(name = "STATUS", nullable = false)
    private Integer status;
}