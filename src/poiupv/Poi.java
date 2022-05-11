package poiupv;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import model.User;
import static poiupv.controladors.IniciSesioController.navegacio;
import static poiupv.controladors.IniciSesioController.nickName;

public class Poi {
    public static User userActual ;

    public static void setUserActual(User p){
        userActual = p;
    }
//    public static User GetUserActual(User.nickName p){
//        return navegacio.getUser(p);
//    }
}

    

