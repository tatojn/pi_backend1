
package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
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
        img.setWidth("200px");
        add(img);

        // Mensaje portada
        H2 header = new H2("Bienvenido al portal de notas");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Debes de ingresar para poder ver tus notas en el sistema🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        getStyle().set("background-color", "#b1cddeff");
        getStyle().set("text-align", "center");

        // Campos de login
        TextField username = new TextField("Nombre:");
        PasswordField password = new PasswordField("Contraseña");
        add(username, password);

        // Mensaje de feedback
        Paragraph info = new Paragraph("");
        add(info);

        Button button = new Button("Ingresar");

        button.addClickListener(e -> {
            String user = username.getValue();
            String pass = password.getValue();

            if (user.equals("admin") && pass.equals("1234")) {
                info.setText("✅ Bienvenido administrador");
                UI.getCurrent().navigate("gestion-estudiantes");
            } else if (user.equals("estudiante") && pass.equals("0000")) {
                info.setText("✅ Bienvenido estudiante");
                UI.getCurrent().navigate("calculadora-notas");
            } else {
                info.setText("❌ Usuario o contraseña incorrectos");
            }
        });

        add(button);
    }
}

