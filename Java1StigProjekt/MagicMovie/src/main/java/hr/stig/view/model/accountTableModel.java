/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.view.model;

import hr.stig.models.Account;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author natio
 */
public class AccountTableModel extends AbstractTableModel {

    private static final String[] COLUMN = {"Id", "Username", "Password", "UserType"};
    private List<Account> accounts;

    public AccountTableModel(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return accounts.get(rowIndex).getIdAccount();

            case 1:
                return accounts.get(rowIndex).getUsername();

            case 2:
                return accounts.get(rowIndex).getPassword();

            case 3:
                return accounts.get(rowIndex).isAdministrator() ? "Administrator" : "Member";

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN[column];
    }

}
