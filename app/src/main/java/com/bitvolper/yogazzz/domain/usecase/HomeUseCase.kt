package com.bitvolper.yogazzz.domain.usecase

import com.bitvolper.yogazzz.data.repository.HomeRepository
import com.bitvolper.yogazzz.domain.model.AdjustYogaLevel
import com.bitvolper.yogazzz.domain.model.FlexibilityStrength
import com.bitvolper.yogazzz.domain.model.PopularYoga
import com.bitvolper.yogazzz.domain.model.PopularYogaWithFlexibility
import com.bitvolper.yogazzz.domain.model.StressRelief
import com.bitvolper.yogazzz.domain.model.YogaCategory
import com.bitvolper.yogazzz.domain.model.YogaCategoryWithRecommendation
import com.bitvolper.yogazzz.domain.model.YogaRecommendation
import com.bitvolper.yogazzz.utility.Resource
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

interface HomeUseCase {
    fun getYogaCategory(): Flow<Resource<YogaCategory>>

    fun getRecommendation(): Flow<Resource<YogaRecommendation>>

    fun getYogaCategoryWithRecommendation(): Flow<Resource<YogaCategoryWithRecommendation>>

    fun getPopularYoga(): Flow<Resource<PopularYoga>>

    fun getAdjustYogaLevel(): Flow<Resource<AdjustYogaLevel>>

    fun getFlexibilityStrength(): Flow<Resource<FlexibilityStrength>>

    fun getStressRelief(): Flow<Resource<StressRelief>>

    fun getPopularYogaWithFlexibility(): Flow<Resource<PopularYogaWithFlexibility>>
}

class GetHomeUseCaseInteractors(private val repository: HomeRepository): HomeUseCase {

    private companion object {
        const val TAG = "HomeUseCase"
    }
    
    override fun getYogaCategory(): Flow<Resource<YogaCategory>> {
        Timber.tag(TAG).d("Interactor called")
        return repository.getYogaCategory()
    }

    override fun getRecommendation(): Flow<Resource<YogaRecommendation>> {
        return repository.getYogaRecommendation()
    }

    override fun getYogaCategoryWithRecommendation(): Flow<Resource<YogaCategoryWithRecommendation>> {
        return repository.getYogaCategoryWithRecommendation()
    }

    override fun getPopularYoga(): Flow<Resource<PopularYoga>> {
        return repository.getPopularYoga()
    }

    override fun getAdjustYogaLevel(): Flow<Resource<AdjustYogaLevel>> {
        return repository.getYogaAdjustLevel()
    }

    override fun getFlexibilityStrength(): Flow<Resource<FlexibilityStrength>> {
        return repository.getFlexibilityStrength()
    }

    override fun getStressRelief(): Flow<Resource<StressRelief>> {
        return repository.getStressRelief()
    }

    override fun getPopularYogaWithFlexibility(): Flow<Resource<PopularYogaWithFlexibility>> {
        return repository.getPopularYogaWithFlexibility()
    }
}