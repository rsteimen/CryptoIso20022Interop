package com.radynamics.dallipay.iso20022.pain001;

import com.radynamics.dallipay.exchange.ExchangeRate;
import com.radynamics.dallipay.exchange.Money;
import com.radynamics.dallipay.iso20022.Address;
import com.radynamics.dallipay.iso20022.Payment;
import com.radynamics.dallipay.iso20022.creditorreference.ReferenceType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class Assertion {
    static void assertEquals(Address actual, Address expected) {
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getStreet(), actual.getStreet());
        Assert.assertEquals(expected.getZip(), actual.getZip());
        Assert.assertEquals(expected.getCity(), actual.getCity());
    }

    static void assertEquals(Payment t, String receiverAccount, String receiverWallet, double amount) {
        assertEquals(t, receiverAccount, receiverWallet, amount, null, null);
    }

    static void assertEquals(Payment t, String receiverAccount, String receiverWallet, double amount, ReferenceType type, String referenceUnformatted) {
        assertEquals(t, "sender_CH5481230000001998736", receiverAccount, receiverWallet, amount, type, referenceUnformatted);
    }

    static void assertEquals(Payment t, String senderWallet, String receiverAccount, String receiverWallet, double amount, ReferenceType type, String referenceUnformatted) {
        assertEqualsWallet(t, senderWallet, receiverWallet);
        Assert.assertEquals(amount, t.getAmountTransaction().getNumber().doubleValue(), 0);
        Assert.assertNotNull(t.getAmountTransaction().getCcy());
        Assert.assertEquals("TEST", t.getAmountTransaction().getCcy().getCode());
        assertNotNull(t.getReceiverAccount());
        Assert.assertEquals(receiverAccount, t.getReceiverAccount().getUnformatted());
        assertNull(t.getId());
        assertNull(t.getInvoiceId());
        assertNotNull(t.getMessages());
        Assert.assertEquals(0, t.getMessages().length);
        assertNotNull(t.getStructuredReferences());
        if (referenceUnformatted == null) {
            Assert.assertEquals(0, t.getStructuredReferences().length);
        } else {
            Assert.assertEquals(1, t.getStructuredReferences().length);
            Assert.assertEquals(type, t.getStructuredReferences()[0].getType());
            Assert.assertEquals(referenceUnformatted, t.getStructuredReferences()[0].getUnformatted());
        }
    }

    public static void assertEqualsWallet(Payment t, String sender, String receiver) {
        if (sender == null) {
            assertNull(t.getSenderWallet());
        } else {
            assertNotNull(t.getSenderWallet());
            Assert.assertEquals(sender, t.getSenderWallet().getPublicKey());
        }
        if (receiver == null) {
            assertNull(t.getReceiverWallet());
        } else {
            assertNotNull(t.getReceiverWallet());
            Assert.assertEquals(receiver, t.getReceiverWallet().getPublicKey());
        }
    }

    public static void assertEqualsAccount(Payment p, String sender, String receiver) {
        if (sender == null) {
            assertNull(p.getSenderAccount());
        } else {
            assertNotNull(p.getSenderAccount());
            Assert.assertEquals(sender, p.getSenderAccount().getUnformatted());
        }
        if (receiver == null) {
            assertNull(p.getReceiverAccount());
        } else {
            assertNotNull(p.getReceiverAccount());
            Assert.assertEquals(receiver, p.getReceiverAccount().getUnformatted());
        }
    }

    public static void assertAmtCcy(Payment transaction, Double amt, String ccy, Double amtLedgerUnit, String ledgerCcy) {
        Assert.assertEquals(amt, transaction.getAmount());
        Assert.assertEquals(ccy, transaction.getUserCcyCodeOrEmpty());
        Assert.assertEquals(amtLedgerUnit, transaction.getAmountTransaction().getNumber());
        Assert.assertNotNull(transaction.getAmountTransaction().getCcy());
        Assert.assertEquals(ledgerCcy, transaction.getAmountTransaction().getCcy().getCode());
    }

    public static void assertEquals(Money expected, Money actual) {
        if (expected == null) {
            Assertions.assertNull(actual);
            return;
        }

        Assertions.assertEquals(expected.getNumber(), actual.getNumber());
        Assertions.assertEquals(actual.getCcy(), actual.getCcy());
    }

    public static void assertEquals(Payment transaction, ExchangeRate expected) {
        var actual = transaction.getExchangeRate();
        if (expected == null) {
            Assert.assertNull(actual);
        } else {
            Assert.assertNotNull(actual);
            Assert.assertEquals(actual.getPair().getFirstCode(), expected.getPair().getFirstCode());
            Assert.assertEquals(actual.getPair().getSecondCode(), expected.getPair().getSecondCode());
            Assert.assertEquals(actual.getRate(), expected.getRate(), 0);
            Assert.assertEquals(actual.getPointInTime(), expected.getPointInTime());
        }
    }
}
