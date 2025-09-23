package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Gestión de Estudiantes")
@Route("gestion-estudiantes")
@Menu(order = 4, icon = LineAwesomeIconUrl.USER)
public class GestionEstudiantesView extends VerticalLayout {

    private List<Estudiante> estudiantes = new ArrayList<>();
    private Grid<Estudiante> grid = new Grid<>(Estudiante.class, false);

    public GestionEstudiantesView() {
        addClassName("gestion-estudiantes-view");

        // Título
        H2 title = new H2("Gestión de Estudiantes");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        // Campos de entrada
        TextField nombreField = new TextField("Nombre del estudiante");
        TextField codigoField = new TextField("Código del estudiante");
        TextField cursoField = new TextField("Curso");

        // Botón de registro
        Button registrarButton = new Button("Registrar Estudiante");
        Paragraph info = new Paragraph("");

        registrarButton.addClickListener(e -> {
            String nombre = nombreField.getValue();
            String codigo = codigoField.getValue();
            String curso = cursoField.getValue();

            if (nombre.isEmpty() || codigo.isEmpty() || curso.isEmpty()) {
                info.setText("⚠️ Por favor complete todos los campos.");
                return;
            }

            // Crear un estudiante y agregarlo a la lista
            Estudiante nuevo = new Estudiante(nombre, codigo, curso);
            estudiantes.add(nuevo);
            grid.getDataProvider().refreshAll();

            info.setText("✅ Estudiante registrado: " + nombre);

            // Limpiar campos
            nombreField.clear();
            codigoField.clear();
            cursoField.clear();
        });

        // Configuración de la tabla (Grid)
        grid.addColumn(Estudiante::getNombre).setHeader("Nombre");
        grid.addColumn(Estudiante::getCodigo).setHeader("Código");
        grid.addColumn(Estudiante::getCurso).setHeader("Curso");
        grid.setItems(estudiantes);

        // Layout de formulario
        HorizontalLayout formLayout = new HorizontalLayout(nombreField, codigoField, cursoField, registrarButton);
        formLayout.setSpacing(true);

        // Agregar componentes
        add(title, formLayout, info, grid);
    }

    // Clase interna para representar un estudiante
    public static class Estudiante {
        private String nombre;
        private String codigo;
        private String curso;

        public Estudiante(String nombre, String codigo, String curso) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.curso = curso;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getCurso() {
            return curso;
        }
    }
}
