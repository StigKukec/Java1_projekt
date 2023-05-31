/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.view.model;

import hr.stig.models.Director;
import hr.stig.models.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author natio
 */
public class DirectorTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {"Id", "First_name", "Last_name"};
    private List<Director> directors;

    public DirectorTableModel(List<Director> directors) {
        this.directors = directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return directors.size();
    }

    @Override
    public int getColumnCount() {
        return Director.class.getDeclaredFields().length + Person.class.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return directors.get(rowIndex).getIdDirector();
            case 1:
                return directors.get(rowIndex).getFirstName();
            case 2:
                return directors.get(rowIndex).getLastName();

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

}
