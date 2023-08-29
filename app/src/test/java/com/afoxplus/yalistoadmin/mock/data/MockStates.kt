package com.afoxplus.yalistoadmin.mock.data

import com.afoxplus.yalistoadmin.domain.entities.States

val listStates =
    arrayListOf(
        States(
            id = "64a4d7b39ee9ab69359e2c3b",
            code = "DONE",
            name = "Finalizado"
        ),
        States(
            id = "64a4d7b39ee9ab69359e2c3c",
            code = "REJECTED",
            name = "Rechazado"
        ),
        States(
            id = "64a4d7b39ee9ab69359e2c38",
            code = "TODO",
            name = "Pendiente"
        ),
        States(
            id = "64a4d7b39ee9ab69359e2c39",
            code = "PROGRESS",
            name = "Proceso"
        ),
        States(
            id = "64a4d7b39ee9ab69359e2c3a",
            code = "DELIVERY",
            name = "En camino"
        )
    )
