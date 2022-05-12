package poiupv;


import model.User;

public class Poi {
    public static User userActual ;

    public static void setUserActual(User p){
        userActual = p;
    }
//    public static User GetUserActual(User.nickName p){
//        return navegacio.getUser(p);
//    }
    public static String creadorStringURL(String res) {
        String URL = null;
        switch(res){
            case "Avatar predeterminado":
                URL = "/resources/avatars/default.png";
                break;
            case "Avatar 1":
                URL = "/resources/avatars/avatar1.png";
                break;
            case "Avatar 2":
                URL = "/resources/avatars/avatar2.png";
                break;
            case "Avatar 3":
                URL = "/resources/avatars/avatar3.png";
                break;
            case "Avatar 4":
                URL = "/resources/avatars/avatar4.png";
                break;
        }
        return URL;
    }
}

    

