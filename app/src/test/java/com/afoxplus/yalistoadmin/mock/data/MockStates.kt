package com.afoxplus.yalistoadmin.mock.data

import com.afoxplus.yalistoadmin.domain.entity.StatesEntity

val listStatesEntity =
    arrayListOf(
        StatesEntity(
            id = "64a4d7b39ee9ab69359e2c3b",
            code = "DONE",
            name = "Finalizado"
        ),
        StatesEntity(
            id = "64a4d7b39ee9ab69359e2c3c",
            code = "REJECTED",
            name = "Rechazado"
        ),
        StatesEntity(
            id = "64a4d7b39ee9ab69359e2c38",
            code = "TODO",
            name = "Pendiente"
        ),
        StatesEntity(
            id = "64a4d7b39ee9ab69359e2c39",
            code = "PROGRESS",
            name = "Proceso"
        ),
        StatesEntity(
            id = "64a4d7b39ee9ab69359e2c3a",
            code = "DELIVERY",
            name = "En camino"
        )
    )
