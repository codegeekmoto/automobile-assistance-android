package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;
import com.automobile.assistance.data.remote.pojo.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class UserUseCase extends UseCaseWrapper {

    @Inject
    Repository repository;

    @Inject
    public UserUseCase() {
    }

    public Register register(User user) {
        Register register = new Register(user);
        threads.add(register);
        return register;
    }

    public Login login(User user) {
        Login login = new Login(user);
        threads.add(login);
        return login;
    }

    public UpdateEmail updateEmail(String email) {
        UpdateEmail updateEmail = new UpdateEmail(email);
        threads.add(updateEmail);
        return updateEmail;
    }

    public UpdatePhone updatePhone(String phone) {
        UpdatePhone updatePhone = new UpdatePhone(phone);
        threads.add(updatePhone);
        return updatePhone;
    }

    public ChangePassword changePassword(String password) {
        ChangePassword changePassword = new ChangePassword(password);
        threads.add(changePassword);
        return changePassword;
    }

    public User getUser() {
        return repository.user().getUser();
    }

    public void logout() {
        repository.user().logout();
    }

    public class ChangePassword extends Thread<Boolean> {

        private String password;

        public ChangePassword(String password) {
            this.password = password;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().changePassword(password);
        }
    }

    public class UpdatePhone extends Thread<Boolean> {

        private String phone;

        public UpdatePhone(String phone) {
            this.phone = phone;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().updatePhone(phone);
        }
    }

    public class UpdateEmail extends Thread<Boolean> {

        private String email;

        public UpdateEmail(String email) {
            this.email = email;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().updateEmail(email);
        }
    }

    public class Login extends Thread<Boolean> {

        private User user;

        public Login(User user) {
            this.user = user;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().login(user);
        }
    }

    public class Register extends Thread<Boolean> {

        private User user;

        public Register(User user) {
            this.user = user;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().register(user);
        }
    }
}
