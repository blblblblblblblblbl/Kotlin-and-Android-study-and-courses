abstract class BankCard {
    protected var balance:Double = 0.0
    open fun topUpBalance(value:Double){
        balance+=value
        println("После пополнения $value")
        balanceInfo()
    }
    open fun pay(value: Double):Boolean{
        if (balance>=value) {
            balance-=value
            println("После оплаты $value")
            balanceInfo()
            return true
        }
        println("недостаточно средств")
        return false
    }
    open fun balanceInfo(){
        println("Собственные средства:$balance")
    }
    open fun availableFundsInfo(){
        println("Собственные средства:$balance")
    }
}