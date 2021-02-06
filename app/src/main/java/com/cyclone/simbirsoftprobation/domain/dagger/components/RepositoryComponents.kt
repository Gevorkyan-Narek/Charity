package com.cyclone.simbirsoftprobation.domain.dagger.components

import com.cyclone.simbirsoftprobation.domain.dagger.modules.DAOModule
import com.cyclone.simbirsoftprobation.domain.dagger.modules.RepositoryModule
import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.presentation.presenter.CategoryOfHelpPresenter
import com.cyclone.simbirsoftprobation.presentation.presenter.NewsPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.history.HistoryFragment
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.SplashActivity
import com.cyclone.simbirsoftprobation.presentation.ui.news.DetailActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class, DAOModule::class])
@Singleton
interface RepositoryComponents {

    fun inject(newsPresenter: NewsPresenter)
    fun inject(categoryOfHelpPresenter: CategoryOfHelpPresenter)
    fun inject(splashActivity: SplashActivity)
    fun inject(detailActivity: DetailActivity)
    fun inject(eventsDataRepository: EventsDataRepository)
    fun inject(categoriesDataRepository: CategoriesDataRepository)
    fun inject(history: HistoryFragment)
}