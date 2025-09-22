package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class InicioView extends VerticalLayout {

    public InicioView() {
        setSpacing(false);

        Image img = new Image("images/imaportada.png", "portada");
        img.setWidth("300px");
        add(img);

        //mensaje portada de bienvenida en la aplicacion web

        H2 header = new H2("bienvenido al portal de notas");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("debes de ingresar para poder ver tus notas en el sistema🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        //login del usuario para poder ingresar a la aplicacion web 

        TextField username = new TextField("digite su nombre");     
        TextField password = new TextField("ingresa tu contraseña");
        add(username,password); 

        
        Button  button = new Button("ingresar");

        button.addClickListener(e -> {
            UI.getCurrent().navigate("CalculadoraNotas");

        }
        
        );
        add(button);   
        
        
        
    }

}
