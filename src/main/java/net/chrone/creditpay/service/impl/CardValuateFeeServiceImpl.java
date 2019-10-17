package net.chrone.creditpay.service.impl;

import net.chrone.creditpay.mapper.CardValuateFeeMapper;
import net.chrone.creditpay.model.CardValuateFee;
import net.chrone.creditpay.service.CardValuateFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardValuateFeeServiceImpl implements CardValuateFeeService {

    @Autowired
    private CardValuateFeeMapper cardValuateFeeMapper;

    @Override
    public int getCreditCardValuateFee() {
        return cardValuateFeeMapper.selectFeeByPrimaryKey(1);
    }

    @Override
    public int getCreditCardValuateOldFee() {
        return cardValuateFeeMapper.selectOldFeeByPrimaryKey(1);
    }

    @Override
    public CardValuateFee selectcardValuateFeeByPrimaryKey(int i) {
        return cardValuateFeeMapper.selectByPrimaryKey(1);
    }



    @Override
    public int updateCardValuateFeeByPrimaryKey(CardValuateFee cardValuateFee1) {
        return cardValuateFeeMapper.updateByPrimaryKeySelective(cardValuateFee1);
    }
}
