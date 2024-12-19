package view.member;

import javax.swing.table.*;
import java.util.List;
import model.Member;

public class MemberTableModel extends AbstractTableModel {
    private String[] coloumnNames = {"Nama", "Jenis Member"};
    private List<Member> data;
    
    public MemberTableModel(List<Member> data)
    {
        this.data = data;
    }
    
    public int getColoumnCount()
    {
        return coloumnNames.length;
    }
    
    public int getRowCount()
    {
        return data.size();
    }
    
    public String getColoumnName(int col)
    {
        return coloumnNames[col];
    }
    
    public Object getValueAt(int row, int col)
    {
        Member rowItem = data.get(row);
        String value = "";
        switch(col)
        {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();
                break;
        }
        return value;
    }
    
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
    
    public void add(Member value)
    {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
