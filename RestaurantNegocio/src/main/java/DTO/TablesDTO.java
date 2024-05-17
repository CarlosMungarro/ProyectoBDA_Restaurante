/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Carlo
 */
public class TablesDTO extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        
        // Verificar si el valor en la columna 6 no es nulo y si el tipo de dato es una cadena
        Object columnValue = table.getValueAt(row, 6);
        if (columnValue != null && columnValue instanceof String) {
            String status = (String) columnValue;
            switch (status) {
                case "PENDIENTE" -> {
                    setBackground(new Color(255, 51, 51));
                    setForeground(Color.white);
                }
                case "FINALIZADO" -> {
                    setBackground(new Color(0, 102, 102));
                    setForeground(Color.white);
                }
                default -> {
                    setBackground(Color.white);
                    setForeground(Color.black);
                }
            }
        } else {
            // Si el valor es nulo o no es una cadena, establecer el fondo y el texto predeterminados
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
    
}
