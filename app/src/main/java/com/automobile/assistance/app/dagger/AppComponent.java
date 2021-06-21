package com.automobile.assistance.app.dagger;

import com.automobile.assistance.ui.SplashActivity;
import com.automobile.assistance.ui.auth.login.LoginFragment;
import com.automobile.assistance.ui.auth.register.RegisterFragment;
import com.automobile.assistance.ui.client.ClientActivity;
import com.automobile.assistance.ui.client.getassistance.FuelFragment;
import com.automobile.assistance.ui.client.getassistance.LockoutFragment;
import com.automobile.assistance.ui.client.getassistance.TireFragment;
import com.automobile.assistance.ui.client.getassistance.TowFragment;
import com.automobile.assistance.ui.client.mycar.AddCarFragment;
import com.automobile.assistance.ui.client.mymechanic.MyMechanicsFragment;
import com.automobile.assistance.ui.client.profile.ProfileFragment;
import com.automobile.assistance.ui.mechanic.MechanicActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ContextModule.class,
        LocalDataModule.class,
        RemoteDataModule.class
})
public interface AppComponent {

    void inject(SplashActivity splashActivity);
    void inject(ClientActivity clientActivity);
    void inject(MechanicActivity mechanicActivity);

    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
    void inject(ProfileFragment profileFragment);
    void inject(LockoutFragment lockoutFragment);
    void inject(AddCarFragment addCarFragment);
    void inject(TowFragment towFragment);
    void inject(TireFragment tireFragment);
    void inject(FuelFragment fuelFragment);
    void inject(MyMechanicsFragment myMechanicsFragment);
}
