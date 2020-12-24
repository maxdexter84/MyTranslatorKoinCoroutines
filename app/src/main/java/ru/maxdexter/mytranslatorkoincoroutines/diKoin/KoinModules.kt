package ru.maxdexter.mytranslatorkoincoroutines.diKoin

import androidx.room.Room
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maxdexter.repository.db.Database
import ru.maxdexter.repository.repository.Repository
import ru.maxdexter.mytranslatorkoincoroutines.ui.MainViewModel
import ru.maxdexter.mytranslatorkoincoroutines.ui.bookmarkfragment.BookmarkViewModel
import ru.maxdexter.mytranslatorkoincoroutines.ui.detailfragment.DetailViewModel
import ru.maxdexter.mytranslatorkoincoroutines.ui.historyfragment.HistoryViewModel
import ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment.SearchViewModel

val application = module {
    // Функция single сообщает Koin, что эта зависимость должна храниться
    // в виде синглтона (в Dagger есть похожая аннотация)
    // Аннотация named выполняет аналогичную Dagger функцию
    single { Room.databaseBuilder(get(),Database::class.java,"appDatabase").build() }
    single(named("REPO")){ Repository(get()) }
}

val mainScreen = module {
    // Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
   // factory { MainViewModel(get(named("REPO"))) }
    single { MainViewModel(get(named("REPO"))) }
    viewModel { SearchViewModel(get(named("REPO"))) }
    viewModel { HistoryViewModel(get(named("REPO"))) }
    viewModel { DetailViewModel(get(named("REPO"))) }
    viewModel { BookmarkViewModel(get(named("REPO"))) }
}