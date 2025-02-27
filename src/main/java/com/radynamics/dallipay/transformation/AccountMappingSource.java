package com.radynamics.dallipay.transformation;

import com.radynamics.dallipay.cryptoledger.Wallet;
import com.radynamics.dallipay.db.AccountMapping;
import com.radynamics.dallipay.iso20022.Account;

public interface AccountMappingSource {
    Wallet getWalletOrNull(Account account, String partyId) throws AccountMappingSourceException;

    Account getAccountOrNull(Wallet wallet, String partyId) throws AccountMappingSourceException;

    void add(AccountMapping mapping) throws AccountMappingSourceException;

    void open() throws AccountMappingSourceException;

    void close() throws AccountMappingSourceException;
}
