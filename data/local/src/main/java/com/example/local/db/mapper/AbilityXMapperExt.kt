package com.example.local.db.mapper

import com.example.local.db.entitiy.AbilityXEntityImpl
import com.example.pokedex.model.model.AbilityX

internal fun AbilityX.toAbilityXEntityImpl() = AbilityXEntityImpl(name = this.name, url = this.url)