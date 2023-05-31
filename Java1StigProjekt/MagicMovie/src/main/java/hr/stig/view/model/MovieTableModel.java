/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.view.model;

import hr.stig.models.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author natio
 */
public class MovieTableModel extends AbstractTableModel{
    private static final String[] COLUMNS = {
        "id",
        "title",
        "description",
        "genre",
        "duration",
        "year",
        "poster",
        "movies"};
    private List<Movie> movies;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    
    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
       // return Movie.class.getDeclaredFields().length ;
        return COLUMNS.length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getDescription();
            case 3:
                return movies.get(rowIndex).getGenre();
            case 4:
                return movies.get(rowIndex).getDuration();
            case 5:
                return movies.get(rowIndex).getYear();
            case 6:
                return movies.get(rowIndex).getPoster();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
    
}
