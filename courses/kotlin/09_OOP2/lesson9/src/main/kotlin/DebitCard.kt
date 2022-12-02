open class DebitCard : BankCard() {
    /*override fun topUpBalance(value: Double) {
        balance += value
    }*/
    override fun topUpBalance(value: Double) {
        return super.topUpBalance(value)
    }

    override fun pay(value: Double): Boolean {
        return super.pay(value)
    }

    override fun balanceInfo() {
        super.balanceInfo()
    }

    override fun availableFundsInfo() {
        super.availableFundsInfo()
    }
}