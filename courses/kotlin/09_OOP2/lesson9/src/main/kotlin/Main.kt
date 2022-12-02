fun main(args: Array<String>) {
    val debitCard = DebitCard1()
    val creditCard = CreditCard1()
    debitCard.bonusProgramInfo()
    debitCard.topUpBalance(10000.0)
    debitCard.balanceInfo()
    debitCard.pay(10000.0)
    debitCard.pay(10000.0)
    debitCard.availableFundsInfo()

    creditCard.bonusProgramInfo()
    creditCard.topUpBalance(10000.0)
    creditCard.balanceInfo()
    creditCard.pay(10000.0)
    creditCard.pay(10000.0)
    creditCard.pay(10000.0)
    creditCard.topUpBalance(5000.0)
    creditCard.availableFundsInfo()
}