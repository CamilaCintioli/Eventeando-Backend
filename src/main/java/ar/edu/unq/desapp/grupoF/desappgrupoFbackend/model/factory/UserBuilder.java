package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.factory;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;

public class UserBuilder {

        private User anyUser;


        public UserBuilder anyUser() {
            User anUser = new User();
            anUser.setName("Pepita");
            anUser.setLastName("Obj");
            anUser.setEmail("pepita@gmail.com");
            anUser.setPassword("root");
            anyUser= anUser;
            return this;
        }

        public UserBuilder withName(String name){
            anyUser.setName("Pepita");
            return this;
        }

        public UserBuilder withLastName(String lastName){
            anyUser.setLastName(lastName);
            return this;
        }
        public UserBuilder withEmail(String email){
            anyUser.setEmail(email);
            return this;
        }

        public UserBuilder withPassword(String password){
            anyUser.setPassword(password);
            return this;
        }

        public User get(){
            return anyUser;
        }
    }
