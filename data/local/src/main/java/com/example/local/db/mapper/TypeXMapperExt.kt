package com.example.local.db.mapper

import com.example.local.db.entitiy.TypeXEntityImpl
import com.example.pokedex.model.model.TypeX

internal fun TypeX.toTypeXEntityImpl() = TypeXEntityImpl(name = this.name, url = this.url)