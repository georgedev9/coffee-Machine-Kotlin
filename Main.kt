class CoffeeMachine{

    private var balance = 550
    private var waterAmount = 400
    private var milkAmount = 540
    private var coffeeBeansAmount = 120
    private var cupsAmount = 9

    fun start(){

        var running = true

        while (running) {

            println("\nWrite action (buy, fill, take, remaining, exit): ")

            when (readln()) {
                "buy" -> buy()
                "fill" -> fill()
                "take" -> take()
                "remaining" -> showState()
                "exit" -> running = false
                else -> println("Invalid input.")
            }
        }
    }

    private fun buy(){

        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")

        val number = readln()

        when (number) {
            "1" -> {

                if (resources(water = CoffeeType.EXPRESSO.water, coffeeBeans = CoffeeType.EXPRESSO.coffeeBeans)){

                    waterAmount -= CoffeeType.EXPRESSO.water
                    coffeeBeansAmount -= CoffeeType.EXPRESSO.coffeeBeans
                    cupsAmount -= 1
                    balance += CoffeeType.EXPRESSO.price

                }


            }
            "2" -> {

                if (resources(CoffeeType.LATTE.water,CoffeeType.LATTE.milk,CoffeeType.LATTE.coffeeBeans)) {

                    waterAmount -= CoffeeType.LATTE.water
                    milkAmount -= CoffeeType.LATTE.milk
                    coffeeBeansAmount -= CoffeeType.LATTE.coffeeBeans
                    cupsAmount -= 1
                    balance += CoffeeType.LATTE.price

                }

            }
            "3" -> {

                if (resources(CoffeeType.CAPPUCCINO.water,CoffeeType.CAPPUCCINO.milk,CoffeeType.CAPPUCCINO.coffeeBeans)) {

                    waterAmount -= CoffeeType.CAPPUCCINO.water
                    milkAmount -= CoffeeType.CAPPUCCINO.milk
                    coffeeBeansAmount -= CoffeeType.CAPPUCCINO.coffeeBeans
                    cupsAmount -= 1
                    balance += CoffeeType.CAPPUCCINO.price

                }

            }
            "back" -> {

            }
            else -> println("Invalid input!")
        }
    }


    private fun fill(){

        println("Write how many ml of water you want to add:")
        waterAmount += readln().toInt()
        println("Write how many ml of milk you want to add:")
        milkAmount += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        coffeeBeansAmount += readln().toInt()
        println("Write how many disposable cups you want to add:")
        cupsAmount += readln().toInt()

    }

    private fun take(){

        println("I gave you $$balance")
        balance = 0

    }

    private fun showState(){

        println("""
        
        The coffee machine has:
        $waterAmount ml of water
        $milkAmount ml of milk
        $coffeeBeansAmount g of coffee beans
        $cupsAmount disposable cups
        $$balance of money
        
    """.trimIndent())
    }

    private enum class CoffeeType(
        val water: Int,
        val milk: Int,
        val coffeeBeans: Int,
        val price: Int
    ){
        EXPRESSO(250,0,16,4),
        LATTE(350,75,20,7),
        CAPPUCCINO(200,100,12,6)
    }

    private fun resources(water:Int, milk:Int = 0, coffeeBeans:Int): Boolean {

        val waterQuantity: Int
        var milkQuantity = 1
        val coffeeBeansQuantity: Int
        var isThereResources = false


        waterQuantity = waterAmount / water
        if (milk > 0) milkQuantity = milkAmount / milk
        coffeeBeansQuantity = coffeeBeansAmount / coffeeBeans


        when {
            waterQuantity >= 1 && milkQuantity >= 1 && coffeeBeansQuantity >= 1 && cupsAmount >= 1
            -> {
                println("I have enough resources, making you a coffee!")
                isThereResources = true
            }

            else ->  println("Sorry, not enough resources!")
        }

        return isThereResources

    }

}

fun main() {

    CoffeeMachine().start()

}
