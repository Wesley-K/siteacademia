package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Seguranca.class)
public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void telaUsu() {
        render();
    }
    
   // public static void telaLogin() {
   // render();
   // }

}