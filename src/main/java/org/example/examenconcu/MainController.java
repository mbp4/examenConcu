package org.example.examenconcu;

import org.example.examenconcu.servicios.MaquinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private MaquinaServicio maquinaServicio;

    @GetMapping("/main")
    public String principal(){
        return "index";
    }

    @GetMapping("/maquina")
    public String maquina(){
        maquinaServicio.arrancarMaquina();
        return "maquina";
    }
}
