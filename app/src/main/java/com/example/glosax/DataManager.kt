package com.example.glosax

object DataManager {
    val glosaries = ArrayList<Glosary>()

    init {
        initializeData()
    }


    private fun initializeData() {
        var glosary = Glosary(1,"Mango", "200", "C1")
        glosaries.add(glosary)
        glosary = Glosary(2,"Tomato", "200", "C1")
        glosaries.add(glosary)
        glosary = Glosary(3,"Orange", "200", "C1")
        glosaries.add(glosary)
        glosary = Glosary(4,"Maize", "200", "C2")
        glosaries.add(glosary)
        glosary = Glosary(5,"Cassava", "200", "C2")
        glosaries.add(glosary)
        glosary = Glosary(6,"Potato", "200", "C2")
        glosaries.add(glosary)
    }
}