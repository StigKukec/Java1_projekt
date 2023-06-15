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
public class AllmoviesTableModel extends AbstractTableModel {

    private static final String[] COLUMN = {"Title", "Genre", "Duration", "Movie description", "Release year", "Actors", "Directors", "Posters",};

    private List<Movie> allMovies;

    public void setAllmovies(List<Movie> allmovies) {
        this.allMovies = allmovies;
        fireTableDataChanged();
    }

    public AllmoviesTableModel(List<Movie> allmovies) {
        this.allMovies = allmovies;
    }

    @Override
    public int getRowCount() {
        return allMovies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return allMovies.get(rowIndex).getTitle();

            case 1:
                return allMovies.get(rowIndex).getGenres();
                //return allMovies.get(rowIndex).getGenre();

            case 2:
                return allMovies.get(rowIndex).getDuration();

            case 3:
                return allMovies.get(rowIndex).getDescription();

            case 4:
                return allMovies.get(rowIndex).getYear();

            case 5:
                return allMovies.get(rowIndex).getActors();

            case 6:
                return allMovies.get(rowIndex).getDirectors();

            case 7:
                return allMovies.get(rowIndex).getPoster();

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN[column];
    }
}
