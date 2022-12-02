class CreditCard1:CreditCard() {
    private var bonusBalance:Double = 0.0
    private var bonusFromPay:Double = 1.0
    private var cashBack:Double = 5.0
    private var bonusFromTopUp:Double = 0.005
    fun bonusProgramInfo(){
        println("""
Бонусные баллы в размере $bonusFromPay% от покупок.
Потенциальный кэшбэк $cashBack% от покупок при условии трат больше 5 000 тыс.
Накопление в размере $bonusFromTopUp% от суммы пополнений""")
    }
    fun changeBonusProgram(bonusFromPay:Double,cashBack:Double,bonusFromTopUp:Double){
        this.bonusFromPay = bonusFromPay
        this.cashBack = cashBack
        this.bonusFromTopUp = bonusFromTopUp
    }

    override fun pay(value: Double): Boolean {
        if(super.pay(value)){
            bonusBalance += value*bonusFromPay/100
            balance+=(if (value>5000) 5 else 0)/100*value
            return true
        }
        return false
    }
    override fun topUpBalance(value: Double) {
        super.topUpBalance(value)
        bonusBalance+=value*bonusFromTopUp/100
    }
    override fun availableFundsInfo() {
        super.availableFundsInfo()
        println("доступные бонусы $bonusBalance")
    }
}