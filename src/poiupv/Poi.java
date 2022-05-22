package poiupv;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.DatePicker;
import model.Answer;
import model.Navegacion;
import model.Problem;
import model.Session;
import model.User;

public class Poi {
    public static User userActual ;
    public static Problem seleccionat;
    public static Navegacion navegacio;
    public static List<Session> llistaSesions;
    public static int hits;
    public static int faults;
    public static Session sessio;
    public static LocalDateTime TimeInici ;
    public static int totalHits;
    public static int totalFaults;
    public static LocalDate dataUltima;
    

    public static void setUserActual(User p){
        userActual = p;
    }
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
     public static List<String> problemesText(List<Problem> res){
        int i = res.size();
        List<String> problemes = new ArrayList<>();
        for(int j = 0; j < i; j++){
            Problem answ = res.get(j);
            problemes.add(answ.getText());
        }
        return problemes;
    }
     public static List<String> respostesText(List<Answer> res){
        int i = res.size();
        List<String> respostes = new ArrayList<>();
        for(int j = 0; j < i; j++){
            Answer answ = res.get(j);
            respostes.add(answ.getText());
        }
        return respostes;
    }
    public static List<String> sesionsText(List<Session> res, DatePicker e){
        int i = res.size();
        List<String> sesions = new ArrayList<>();
        totalHits =0;
        totalFaults = 0;
        dataUltima = (res.get(i -1)).getLocalDate();
        for(int j = 0; j < i; j++){
            
            Session ses = res.get(j);
            if(ses.getLocalDate().getYear() >= e.getValue().getYear() &&  ses.getLocalDate().getDayOfYear() >= e.getValue().getDayOfYear()){
                totalHits += ses.getHits();
                totalFaults += ses.getFaults();
                int hit = ses.getHits();
                int fault = ses.getFaults();
                LocalDate data = ses.getLocalDate();
                String tot = "Data sessió: " + data + "\r Encerts: " + hit + "\r Fallades: " + fault;
                sesions.add(tot);
            }
        }
        
        return sesions;
    }
    
    
    
    
}

    

