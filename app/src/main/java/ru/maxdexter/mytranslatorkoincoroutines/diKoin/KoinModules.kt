package ru.maxdexter.mytranslatorkoincoroutines.diKoin

import androidx.room.Room
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maxdexter.mytranslatorkoincoroutines.db.Database
import ru.maxdexter.mytranslatorkoincoroutines.repository.Repository
import ru.maxdexter.mytranslatorkoincoroutines.ui.MainViewModel
import ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment.SearchViewModel

val application = module {
    // Функция single сообщает Koin, что эта зависимость должна храниться
    // в виде синглтона (в Dagger есть похожая аннотация)
    // Аннотация named выполняет аналогичную Dagger функцию
    single { Room.databaseBuilder(get(),Database::class.java,"appDatabase").build() }
    single(named("REPO")){ Repository(get(),get()) }
}

val mainScreen = module {
    // Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
   // factory { MainViewModel(get(named("REPO"))) }
    single { MainViewModel(get(named("REPO"))) }
    viewModel { SearchViewModel(get(named("REPO"))) }
}