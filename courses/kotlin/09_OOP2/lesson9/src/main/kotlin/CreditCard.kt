open class CreditCard: BankCard() {
    val creditLimit:Double = 10000.0
    protected var creditBalance = creditLimit
    override fun topUpBalance(value: Double) {
        if (creditBalance<=creditLimit){
            if (value<=creditLimit-creditBalance) creditBalance+=value
            else{
                creditBalance=creditLimit
                balance+=value-(creditLimit-creditBalance)
            }
        }
        println("после пополнения $value")
        availableFundsInfo()
    }

    override fun pay(value: Double): Boolean {
        if (balance+creditBalance>=value){
            if (balance>= value) balance-=value
            else {
                balance = 0.0
                creditBalance -= value-balance
            }
            println("после оплаты $value")
            availableFundsInfo()
            return true
        }
        println("недостаточно средств")
        return false
    }


    override fun availableFundsInfo() {
        println("Кредитные средства: $creditBalance \n Собственные средства: $balance")
    }

}