/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.SimpleStatement;
import com.icraus.vpl.codegenerator.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shoka
 */
public class SimpleListStatement extends SimpleStatement{
    private List<Statement> childern;
    public SimpleListStatement(String statement){
        super(statement);
        childern = new ArrayList<>();
    }
    @Override
    public String toText() throws ErrorGenerateCodeException {
        String str = super.toText() + "\n";
        for(Statement s : childern){
            str += s.toText() + "\n";
        }
        return str.trim();
    }

    public List<Statement> getChildern() {
        return childern;
    }
    
}
