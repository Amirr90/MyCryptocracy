package com.e.cryptocracy.component;


import com.e.cryptocracy.module.ActivityBuilderModule;
import com.e.cryptocracy.module.AppModule;
import com.e.cryptocracy.utility.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ActivityBuilderModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,}
)

public interface AppComponent extends AndroidInjector<App> {

   // void inject(SplashScreen screen);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(DaggerApplication screen);
        AppComponent build();
    }

}
