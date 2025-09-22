package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
@PageTitle("Calculadora de Notas")
@Route("calculadora-notas")
@Menu(order = 1, icon = LineAwesomeIconUrl.FILE)

//copio y pego lo del inicio para que se visible en la pagina como una vista mas 
// hice unas clases dentro de una clase  y se le hixo la herencia 
public class CalculadoraNotas extends VerticalLayout {

    public CalculadoraNotas() {
        addClassName("calculadora-notas-view");

        H2 title = new H2("Calculadora de Notas");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        TextField nota1Field = new TextField("Nota 1");
        TextField nota2Field = new TextField("Nota 2");
        TextField nota3Field = new TextField("Nota 3");
        TextField nota4Field = new TextField("Nota 4");
        


        HorizontalLayout inputLayout = new HorizontalLayout(nota1Field, nota2Field, nota3Field, nota4Field);
        inputLayout.setSpacing(true);

        // se inicializan los botones y se crean los parrafos para asi mostrar el resultado
        
        Button calcularButton = new Button("Calcular Promedio");
        Paragraph resultadoParagraph = new Paragraph("Promedio: ");
        Paragraph estadoParagraph = new Paragraph("Estado: ");

        calcularButton.addClickListener(e -> {
            try {//el try inicializa el bloque de codigo o la logica de negocio es como un conmdicional
                double nota1 = Double.parseDouble(nota1Field.getValue());
                double nota2 = Double.parseDouble(nota2Field.getValue());
                double nota3 = Double.parseDouble(nota3Field.getValue());


                double promedio = calcularPromedio(nota1, nota2, nota3);
                String estado = determinarEstado(promedio);

                resultadoParagraph.setText("Promedio: " + String.format("%.2f", promedio));
                estadoParagraph.setText("Estado: " + estado);
            } catch (NumberFormatException ex) { // y el catch haqce excepciones  como condicionales
                resultadoParagraph.setText("Por favor, ingrese números válidos.");
                estadoParagraph.setText("");
            }
        });

        add(title, inputLayout, calcularButton, resultadoParagraph, estadoParagraph);
    }
    
    public double calcularPromedio(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 4;
    }

    public String determinarEstado(double promedio) { // aqui ya se lleva acabo la logica y hace una comoparacion para dar un mensaje
        if (promedio >= 3.0){
            return "Aprobado";
        } else {
            return "Reprobado";

        }
    }

    
   

}
