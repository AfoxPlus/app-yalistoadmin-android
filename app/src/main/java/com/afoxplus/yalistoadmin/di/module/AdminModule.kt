package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.network.api.RetrofitGenerator
import com.afoxplus.network.global.AppProperties
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.api.AdminApiProductNetwork
import com.afoxplus.yalistoadmin.data.api.AdminApiRestaurantNetwork
import com.afoxplus.yalistoadmin.data.repository.global.GlobalRepository
import com.afoxplus.yalistoadmin.data.repository.global.GlobalRepositorySource
import com.afoxplus.yalistoadmin.ui.main.AppPropertiesMain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object AdminModule {

    @Provides
    fun providesAdminApiRestaurantNetwork(retrofitGenerator: RetrofitGenerator): AdminApiRestaurantNetwork {
        return retrofitGenerator.createRetrofit(AdminApiRestaurantNetwork::class.java)
    }

    @Provides
    fun providesAdminApiOrdersNetwork(retrofitGenerator: RetrofitGenerator): AdminApiOrdersNetwork {
        return retrofitGenerator.createRetrofit(AdminApiOrdersNetwork::class.java)
    }

    @Provides
    fun provideAdminApiProductNetwork(retrofitGenerator: RetrofitGenerator): AdminApiProductNetwork {
        return retrofitGenerator.createRetrofit(AdminApiProductNetwork::class.java)
    }

    @Provides
    fun providesAppProperties(properties: AppPropertiesMain): AppProperties {
        return properties
    }

    @Provides
    fun providesGlobalRepository(globalRepository: GlobalRepositorySource): GlobalRepository {
        return globalRepository
    }
}
