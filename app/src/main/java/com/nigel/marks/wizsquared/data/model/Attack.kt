package com.nigel.marks.wizsquared.data.model

data class Attack(
    var name: String,
    var mod: String, //Ability Mod used by the Attack (Ability Mod or Spell Mod)
    var otherBonus: Int, //Additional Bonus added to the Attack
    var isProficient: Boolean,
    var range: Int,
    var critRange: Int = 20,
    var damageDice1: Pair<Int,Int>, //First D Second, so 1d6 would be (1,6)
    var damageMod1: String, //See var mod
    var damageBonus1: Int, //Any extra damage
    var damageType1: String,
    var dammageCrit1: Pair<Int,Int> = Pair(0,0), //In case of extra crit calc (0,0 = standard calc)
    var damageDice2: Pair<Int,Int>, //First D Second, so 1d6 would be (1,6)
    var damageMod2: String, //See var mod
    var damageBonus2: Int, //Any extra damage
    var damageType2: String,
    var dammageCrit2: Pair<Int,Int> = Pair(0,0), //In case of extra crit calc (0,0 = standard calc)
    var isSavThrow: Boolean,
    var savEffect: String? = null,
)
