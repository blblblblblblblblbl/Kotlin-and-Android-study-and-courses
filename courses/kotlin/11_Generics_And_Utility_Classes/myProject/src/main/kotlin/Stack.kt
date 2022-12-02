class Stack<T> {
    var stack= mutableListOf<T>()
    fun push(item:T){
        stack.add(item)
    }
    fun pop():T{
        val item = stack.last()
        stack.removeAt(stack.lastIndex)
        return item
    }
    fun isEmpty():Boolean{
        return stack.isEmpty()
    }
}
