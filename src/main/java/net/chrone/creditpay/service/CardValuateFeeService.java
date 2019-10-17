package net.chrone.creditpay.service;

import net.chrone.creditpay.model.CardValuateFee;

public interface CardValuateFeeService {
    int getCreditCardValuateFee();

    int getCreditCardValuateOldFee();

    CardValuateFee selectcardValuateFeeByPrimaryKey(int i);

    int updateCardValuateFeeByPrimaryKey(CardValuateFee cardValuateFee1);
}
