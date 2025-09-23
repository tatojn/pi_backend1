package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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

@PageTitle("Gestión de Actividades")
@Route("gestion-actividades")
@Menu(order = 3, icon = LineAwesomeIconUrl.FILE)
public class GestionActividadesView extends VerticalLayout {

    // Lista temporal de actividades
    private List<Actividad> actividades = new ArrayList<>();
    private Grid<Actividad> grid = new Grid<>(Actividad.class, false);

    public GestionActividadesView() {
        addClassName("gestion-actividades-view");

        // Título
        H2 title = new H2("Gestión de Actividades para Estudiantes");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        // Campos de formulario
        TextField estudianteField = new TextField("Nombre del Estudiante");
        TextField actividadField = new TextField("Nombre de la Actividad");

        ComboBox<String> tipoCombo = new ComboBox<>("Tipo de Actividad");
        tipoCombo.setItems("Tarea", "Examen", "Taller", "Proyecto");

        Button registrarButton = new Button("Registrar Actividad");

        HorizontalLayout inputLayout = new HorizontalLayout(estudianteField, actividadField, tipoCombo, registrarButton);
        inputLayout.setSpacing(true);

        // Configuración de tabla (grid)
        grid.addColumn(Actividad::getEstudiante).setHeader("Estudiante");
        grid.addColumn(Actividad::getNombreActividad).setHeader("Actividad");
        grid.addColumn(Actividad::getTipo).setHeader("Tipo");

        Paragraph mensaje = new Paragraph();

        // Acción al registrar
        registrarButton.addClickListener(e -> {
            String estudiante = estudianteField.getValue();
            String actividad = actividadField.getValue();
            String tipo = tipoCombo.getValue();

            if (estudiante.isEmpty() || actividad.isEmpty() || tipo == null) {
                mensaje.setText("⚠️ Todos los campos son obligatorios.");
                return;
            }

            Actividad nuevaActividad = new Actividad(estudiante, actividad, tipo);
            actividades.add(nuevaActividad);

            grid.setItems(actividades);

            mensaje.setText("✅ Actividad registrada correctamente.");

            // Limpiar campos
            estudianteField.clear();
            actividadField.clear();
            tipoCombo.clear();
        });

        add(title, inputLayout, mensaje, grid);
    }

    // Clase interna para representar una Actividad
    public static class Actividad {
        private String estudiante;
        private String nombreActividad;
        private String tipo;

        public Actividad(String estudiante, String nombreActividad, String tipo) {
            this.estudiante = estudiante;
            this.nombreActividad = nombreActividad;
            this.tipo = tipo;
        }

        public String getEstudiante() {
            return estudiante;
        }

        public String getNombreActividad() {
            return nombreActividad;
        }

        public String getTipo() {
            return tipo;
        }
    }
}
