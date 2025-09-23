package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
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

@PageTitle("Entrega de Actividades")
@Route("entrega-actividades")
@Menu(order = 3, icon = LineAwesomeIconUrl.CHECK_SQUARE_SOLID)
public class EntregaActividadesView extends VerticalLayout {

    private List<ActividadEntrega> entregas = new ArrayList<>();
    private Grid<ActividadEntrega> grid = new Grid<>(ActividadEntrega.class, false);

    public EntregaActividadesView() {
        addClassName("entrega-actividades-view");

        // Título
        H2 title = new H2("Gestión de Entrega de Actividades");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        // Campos de entrada
        TextField estudianteField = new TextField("Nombre del estudiante");
        TextField actividadField = new TextField("Nombre de la actividad");
        Checkbox entregadoCheckbox = new Checkbox("¿Entregada?");

        // Botón para registrar la entrega
        Button registrarButton = new Button("Registrar Entrega");

        Paragraph info = new Paragraph("");

        registrarButton.addClickListener(e -> {
            String estudiante = estudianteField.getValue();
            String actividad = actividadField.getValue();
            boolean entregado = entregadoCheckbox.getValue();

            if (estudiante.isEmpty() || actividad.isEmpty()) {
                info.setText("⚠️ Por favor complete todos los campos.");
                return;
            }

            // Crear un registro y agregarlo a la lista
            ActividadEntrega nuevaEntrega = new ActividadEntrega(estudiante, actividad, entregado);
            entregas.add(nuevaEntrega);
            grid.getDataProvider().refreshAll();

            info.setText("✅ Registro guardado para " + estudiante);

            // limpiar campos
            estudianteField.clear();
            actividadField.clear();
            entregadoCheckbox.clear();
        });

        // Configuración del grid (tabla)
        grid.addColumn(ActividadEntrega::getEstudiante).setHeader("Estudiante");
        grid.addColumn(ActividadEntrega::getActividad).setHeader("Actividad");
        grid.addColumn(entrega -> entrega.isEntregado() ? "Sí" : "No").setHeader("Entregado");
        grid.setItems(entregas);

        // Layout de inputs
        HorizontalLayout formLayout = new HorizontalLayout(estudianteField, actividadField, entregadoCheckbox, registrarButton);
        formLayout.setSpacing(true);

        // Agregar todo al layout principal
        add(title, formLayout, info, grid);
    }

    // Clase interna para representar un registro de entrega
    public static class ActividadEntrega {
        private String estudiante;
        private String actividad;
        private boolean entregado;

        public ActividadEntrega(String estudiante, String actividad, boolean entregado) {
            this.estudiante = estudiante;
            this.actividad = actividad;
            this.entregado = entregado;
        }

        public String getEstudiante() {
            return estudiante;
        }

        public String getActividad() {
            return actividad;
        }

        public boolean isEntregado() {
            return entregado;
        }
    }
}
